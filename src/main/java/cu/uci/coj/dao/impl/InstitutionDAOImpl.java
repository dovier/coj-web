/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cu.uci.coj.dao.InstitutionDAO;
import cu.uci.coj.model.Institution;
import cu.uci.coj.query.Order;
import cu.uci.coj.query.Query;
import cu.uci.coj.query.Where;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;

@Repository("InstitutionDAO")
@Transactional
public class InstitutionDAOImpl extends BaseDAOImpl implements InstitutionDAO {

	private int number;

	public void insertInstitution(Institution institution) {
		dml("insert.institution", institution.getName(), institution.getZip(), institution.getCountry_id(), institution.getWebsite());
	}

	@Transactional(readOnly = true)
	public int countInstitutionsByCountry(int country_id) {
		return integer("count.inst.country", country_id);
	}

	@Transactional(readOnly = true)
	public String getInstitutionName(int inst_id) {
		return string("get.inst.names", inst_id);
	}

	@Transactional(readOnly = true)
	public int countEnabledInstitutions(String pattern) {
		if (pattern != null)
			return integer("count.enabled.inst.pattern", "%" + pattern + "%", "%" + pattern + "%");
		return integer("count.enabled.inst");
	}

	@Transactional(readOnly = true)
	public int countEnabledInstitutionsByCountry(String pattern, int country_id) {
		if (pattern != null)
			return integer("count.enabled.inst.by.country.pattern", "%" + pattern + "%", "%" + pattern + "%", country_id);
		return integer("count.enabled.inst.by.country", country_id);
	}

	private void instOrder(Query query, String sort, boolean asc) {
		if (sort != null) {
			String ord = null;
			if (sort.equals("acc")) {
				ord = "acc";
			} else if (sort.equals("usr")) {
				ord = "users";
			} else if (sort.equals("points")) {
				ord = "points";
			}

			query.order(asc ? Order.asc(ord) : Order.desc(ord));
		} else {
			query.order(Order.desc("points"), Order.desc("acc"));
		}
	}

	@Transactional(readOnly = true)
	public IPaginatedList<Institution> getEnabledInstitutions(String pattern, int found, PagingOptions options) {
		Query query = new Query("users join institution USING(inst_id) join country on country.country_id = users.country_id join user_profile USING(uid) join user_stats USING(uid)");
		query.where(Where.yes("institution.enabled"), Where.or(Where.ilike("institution.name", pattern), Where.ilike("institution.zip", pattern)), Where.yes("users.enabled"),
				Where.ne("institution.inst_id", -1));
		instOrder(query, options.getSort(), "asc".equals(options.getDirection()));
		query.paginate(options, 30);
		query.group("institution.zip", "institution.name", "users.inst_id", "country.name", "country.zip");
		List<Institution> institutions = objects(
				query.select("institution.zip,institution.name,count(*) as users,sum(points) as points,sum(ac) as acc,users.inst_id as id,country.name as cname, country.zip as czip"),
				Institution.class, query.arguments());

		number = options.getOffset(30);
		for (int i = 0; i < institutions.size(); i++) {
			institutions.get(i).setRank(number + i + 1);
		}

		return getPaginatedList(options, institutions, 30, found);
	}

	@Transactional(readOnly = true)
	public IPaginatedList<Institution> getEnabledInstitutionsByCountry(String pattern, int found, PagingOptions options, int country_id) {
		Query query = new Query("users join institution USING(inst_id) join country on country.country_id = users.country_id join user_profile USING(uid) join user_stats " + "USING(uid)");
		query.where(Where.yes("institution.enabled"), Where.or(Where.ilike("institution.name", pattern), Where.ilike("institution.zip", pattern)), Where.yes("users.enabled"),
				Where.ne("institution.inst_id", -1), Where.eq("institution.country_id", country_id));
		instOrder(query, options.getSort(), "asc".equals(options.getDirection()));
		query.paginate(options, 30);
		query.group("institution.zip", "institution.name", "users.inst_id", "country.name", "country.zip");
		List<Institution> institutions = objects(query.select("institution.zip,institution.name,count(*) as users,sum(points) as points,sum(ac) as acc,users.inst_id as id,"
				+ "country.name as cname, country.zip as czip"), Institution.class, query.arguments());

		number = options.getOffset(30);
		for (int i = 0; i < institutions.size(); i++) {
			institutions.get(i).setRank(number + i + 1);
		}

		return getPaginatedList(options, institutions, 30, found);
	}

	@Transactional(readOnly = true)
	public List<Institution> getEnabledInstitutions() {

		return objects("get.enabled.inst", Institution.class);
	}

	@Transactional(readOnly = true)
	public List<Institution> getEnabledInstitutionsByCountry_id(int country_id) {

		return objects("get.enabled.inst.by.country", Institution.class, country_id);
	}

	@Transactional(readOnly = true)
	public IPaginatedList<Institution> getAllInstitutions(String pattern, PagingOptions options) {

		if (pattern != null)
			return paginated("get.all.inst.1", Institution.class, 30, options, "%" + pattern + "%", "%" + pattern + "%");
		return paginated("get.all.inst.2", Institution.class, 30, options);
	}

	public void updateInstitution(Institution institution) {
		this.dml("update.inst", institution.getName(), institution.getZip(), Boolean.valueOf(institution.isEnabled()), institution.getCountry_id(), institution.getWebsite(), institution.getId());
	}

	@Transactional(readOnly = true)
	public boolean existInstitutionNamebut(String name, int inst_id) {
		return bool("exist.inst.name.but", name, inst_id);
	}

	@Transactional(readOnly = true)
	public boolean existInstitutionName(String name) {
		return bool("exist.inst.name", name);
	}

	@Transactional(readOnly = true)
	public boolean existInstitutionNameByCountry(String name, int country_id) {
		if (country_id == 0) {
			return false;
		}
		return bool("exist.inst.name.by.country", name, country_id);
	}

	@Transactional(readOnly = true)
	public boolean existInstitutionZip(String zip) {
		return bool("exist.inst.zip", zip);
	}

	@Transactional(readOnly = true)
	public boolean existInstitutionZipBut(String zip, int inst_id) {
		return bool("exist.inst.zip.but", zip, inst_id);
	}

	@Transactional(readOnly = true)
	public double getScore(String zip) {
		return real("user.score.enabled.inst", Integer.valueOf(zip));
	}
}