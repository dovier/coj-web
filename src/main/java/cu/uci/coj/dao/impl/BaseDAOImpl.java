package cu.uci.coj.dao.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.ArrayUtils;
import org.displaytag.properties.SortOrderEnum;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cu.uci.coj.config.Config;
import cu.uci.coj.dao.BaseDAO;
import cu.uci.coj.query.PostgresDml;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PaginatedListImpl;
import cu.uci.coj.utils.paging.PagingOptions;

@Repository("baseDAO")
@Transactional
public class BaseDAOImpl implements BaseDAO {

	@Resource
	protected JdbcTemplate jdbcTemplate;
	@Resource
	protected Config config;

	@Resource
	protected PostgresDml dml;

	public void log(String message, String user) {
		dml("insert.log", message, user);
	}

	private <T> IPaginatedList<T> getPaginatedList(String direction, String sort, Integer page) {

		IPaginatedList<T> paginatedList = new PaginatedListImpl<T>();
		paginatedList.setSortDirection(IPaginatedList.IRequestParameters.DESC.equals(direction) ? SortOrderEnum.DESCENDING : SortOrderEnum.ASCENDING);

		paginatedList.setSortCriterion(sort);
		int pageSize = 25; // Rows per page
		paginatedList.setPageSize(pageSize);
		if (page != null) {
			int index = paginatedList == null ? 0 : page - 1;
			paginatedList.setIndex(index);
		} else {
			paginatedList.setIndex(0);
		}

		return paginatedList;
	}

	protected <T> IPaginatedList<T> getPaginatedList(PagingOptions options, List<T> list, int pageSize, int totalNumberOfRows) {
		IPaginatedList<T> paginatedList = getPaginatedList(options.getDirection(), options.getSort(), options.getPage());
		paginatedList.setList(list);
		paginatedList.setPageSize(pageSize);
		paginatedList.setFullListSize(totalNumberOfRows);
		return paginatedList;
	}

	protected String getSql(String sqlKey) {
		String sql = Config.getProperty(sqlKey);
		return sql == null ? sqlKey : sql;
	}

	protected String replaceSql(String sqlKey, Map<String, String> keys) {
		for (Map.Entry<String, String> key : keys.entrySet()) {
			sqlKey = replaceSql(sqlKey, key.getKey(), key.getValue());
		}
		return sqlKey;
	}

	protected String replaceSql(String sqlKey, String key, String value) {
		sqlKey = getSql(sqlKey).replaceAll(key, value);
		return sqlKey;
	}

	public Integer idByUsername(String username) {
		return integer("select.uid.by.username", username);
	}

	@Transactional(readOnly = true)
	public Map<String, Object> map(String sqlKey, Object... args) {
		return jdbcTemplate.queryForMap(getSql(sqlKey), args);
	}

	@Transactional(readOnly = true)
	public Date date(String sqlKey, Object... args) {
		return jdbcTemplate.queryForObject(getSql(sqlKey), Date.class, args);
	}

	@Transactional(readOnly = true)
	public Boolean bool(String sqlKey, Object... args) {
		return jdbcTemplate.queryForObject(getSql(sqlKey), Boolean.class, args);
	}

	@Transactional(readOnly = true)
	public Integer integer(String sqlKey, Object... args) {
		try {
			return jdbcTemplate.queryForInt(getSql(sqlKey), args);
		} catch (Exception e) {
			return null;
		}
	}

	@Transactional(readOnly = true)
	public Integer integer(Integer defaultValue, String sqlKey, Object... args) {
		try {
			Integer res = jdbcTemplate.queryForInt(getSql(sqlKey), args);

			return res == null ? defaultValue : res;
		} catch (Exception e) {
			return defaultValue;
		}
	}

	@Transactional(readOnly = true)
	public Double real(String sqlKey, Object... args) {
		try {
			return (Double) jdbcTemplate.queryForObject(getSql(sqlKey), args, Double.class);
		} catch (Exception e) {
			return null;
		}
	}

	@Transactional(readOnly = true)
	public Double real(Double defaultValue, String sqlKey, Object... args) {
		try {
			Double res = (Double) jdbcTemplate.queryForObject(getSql(sqlKey), args, Double.class);
			return (res == null) ? defaultValue : res;
		} catch (Exception e) {
			return defaultValue;
		}
	}

	@Transactional(readOnly = true)
	public String string(String sqlKey, Object... args) {
		try {
			return jdbcTemplate.queryForObject(getSql(sqlKey), String.class, args);
		} catch (Exception e) {
			return null;
		}
	}

	@Transactional(readOnly = true)
	public String defaultedString(String defaultValue, String sqlKey, Object... args) {
		try {
			String res = jdbcTemplate.queryForObject(getSql(sqlKey), String.class, args);
			return res == null ? defaultValue : res;
		} catch (Exception e) {
			return defaultValue;
		}
	}

	@Override
	public int dml(String sqlKey, Object... args) {
		try {
			return jdbcTemplate.update(getSql(sqlKey), args);
		} catch (Exception e) {
			return 0;
		}
	}


	@Transactional(readOnly = true)
	public List<String> strings(String sqlKey, Object... args) {
		return jdbcTemplate.queryForList(getSql(sqlKey), String.class, args);
	}

	@Transactional(readOnly = true)
	public List<Integer> integers(String sqlKey, Object... args) {
		return jdbcTemplate.queryForList(getSql(sqlKey), Integer.class, args);
	}

	@Transactional(readOnly = true)
	public <T> T object(String sqlKey, Class<T> clazz, Object... args) {
		try {
			T obj = jdbcTemplate.queryForObject(getSql(sqlKey), new BeanPropertyRowMapper<T>(clazz), args);
			return obj;
		} catch (Exception e) {
			return null;
		}
	}

	@Transactional(readOnly = true)
	public <T> List<T> objects(String sqlKey, Class<T> clazz, Object... args) {
		List<T> obj = jdbcTemplate.query(getSql(sqlKey), new BeanPropertyRowMapper<T>(clazz), args);
		return obj;
	}

	public <T> IPaginatedList<T> paginated(String sqlKey, Class<T> clazz, int pageSize, PagingOptions options, Object... args) {
		Object[] argsOffset = ArrayUtils.add(args, options.getOffset(pageSize));
		Integer found = integer(0, "count." + sqlKey, args);

		List<T> objs = Collections.emptyList();
		if (found != 0) {
			objs = jdbcTemplate.query(getSql(sqlKey), new BeanPropertyRowMapper<T>(clazz), argsOffset);
		}
		return getPaginatedList(options, objs, pageSize, found == null ? 0 : found);
	}

	@Transactional(readOnly = true)
	public List<Map<String, Object>> maps(String sqlKey, Object... args) {
		try {
			List<Map<String, Object>> obj = jdbcTemplate.queryForList(getSql(sqlKey), args);
			return obj;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public String parseDate(Date date) {
		return (date.getYear() + 1900) + "-" + (date.getMonth() + 1) + "-" + date.getDate() + " " + date.getHours() + ":" + date.getMinutes();
	}

}
