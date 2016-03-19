package cu.uci.coj.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;

public interface BaseDAO {

	public void log(String message,String user);
	public Integer idByUsername(String username);

	public Double real(String sqlKey, Object... args);

	public Double real(Double defaultValue, String sqlKey, Object... args);

	public Map<String, Object> map(String sqlKey, Object... args);

	public List<Map<String, Object>> maps(String sqlKey, Object... args);

	public Boolean bool(String sql, Object... args);

	public Integer integer(String sql, Object... args);

	public Integer integer(Integer defaultValue, String sql, Object... args);

	public String string(String sql, Object... args);

	public String defaultedString(String defaultValue, String sqlKey, Object... args);

	public int dml(String sql, Object... args);

	public List<String> strings(String sql, Object... args);

	public List<Integer> integers(String sql, Object... args);

	public <T> T object(String sqlKey, Class<T> clazz, Object... args);

	public <T> List<T> objects(String sqlKey, Class<T> clazz, Object... args);

	public <T> IPaginatedList<T> paginated(String sqlKey, Class<T> clazz, int pageSize, PagingOptions options, Object... args);

	public String parseDate(Date date);
	String getClassificationNameById(Integer idClassification);
}
