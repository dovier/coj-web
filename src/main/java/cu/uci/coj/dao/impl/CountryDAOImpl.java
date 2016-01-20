package cu.uci.coj.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cu.uci.coj.dao.CountryDAO;
import cu.uci.coj.model.Country;
import cu.uci.coj.query.Order;
import cu.uci.coj.query.Query;
import cu.uci.coj.query.Where;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;

@Repository("countryDAO")
@Transactional
public class CountryDAOImpl extends BaseDAOImpl implements CountryDAO {

	@Transactional(readOnly = true)
	public int countEnabledCountries(String pattern) {
		if (pattern != null) {
			return integer("count.enabled.countries.pattern", "%" + pattern + "%", "%" + pattern + "%");
		}
		return integer("count.enabled.countries");
	}

	@Transactional(readOnly=true)
    public IPaginatedList<Country> getEnabledCountries(String pattern, int found,PagingOptions options) {

        Query query = new Query("users join country on country.country_id = users.country_id join user_profile on users.uid = user_profile.uid join user_stats on user_profile.uid = user_stats.uid");
        query.where(Where.yes("country.enabled"),Where.yes("users.enabled"),Where.or(Where.ilike("country.name", pattern),Where.ilike("country.zip", pattern)));
        query.group("country.zip","country.name","users.country_id");
        query.paginate(options, 30);
        
        if (options.getSort() != null) {
        	String orderAttrib = "";
            if (options.getSort().equals("acc")) {
            	orderAttrib = "acc";
            } else if (options.getSort().equals("usr")) {
            	orderAttrib = "users";
            } else if (options.getSort().equals("inst")) {
                orderAttrib = "institutions";
            } else if (options.getSort().equals("points")) {
                orderAttrib = "points";
            }
            query.order("asc".equals(options.getDirection())?Order.asc(orderAttrib):Order.desc(orderAttrib));
        } else {
        	query.order(Order.desc("points"),Order.desc("acc"));
        }
        
        final int number = options.getOffset(30);
        List<Country> countries = objects(query.select("country.zip","country.name","count(*) as users","sum(points) as points","sum(ac) as acc","users.country_id as id","(select count(*) from institution where country_id = users.country_id and inst_id in (select inst_id from users join user_profile using (uid) where enabled = true)) as institutions"), Country.class,query.arguments());
       for(int i = 0;i < countries.size();i++) {
    	   countries.get(i).setRank(i + 1 + number);
       }
        return getPaginatedList(options, countries, 30, found);
    }

	@Transactional(readOnly = true)
	public IPaginatedList<Country> getCountry(String pattern, PagingOptions options) {
		if (pattern != null) {
			return paginated("countries.pattern", Country.class, 30, options, "%" + pattern + "%", "%" + pattern + "%");
		}
		return paginated("countries", Country.class, 30, options);
	}
}
