package cu.uci.coj.query;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import cu.uci.coj.utils.paging.PagingOptions;

public class Query {

	private String table;
	private Where where;
	private Order order;
	private List<Object> arguments;
	private PagingOptions options;
	private String[] attributes;
	private int pageSize;
	private String[] group;

	public Query(String table) {
		this(table, "*");
	}

	public Query(String table, String... attributes) {
		this.table = table;
		where = null;
		this.attributes = attributes;
		order = null;
		arguments = new LinkedList<>();
		pageSize = 0;
		options = null;
		group = null;
	}

	public Object[] arguments() {
		return arguments.toArray();
	}

	public List<Object> getArguments() {
		return arguments;
	}

	public String count() {
		return select("count(*)");
	}

	public String exists(String... attributes) {
		return "select exists(" + select(attributes) + ")";
	}

	public void group(String... group) {
		this.group = group;
	}

	public String select(String... attributes) {
		StringBuffer sql = new StringBuffer();
		sql.append("select ");
		String[] attrs = (attributes == null || attributes.length == 0) ? this.attributes : attributes;
		for (String attr : attrs) {
			if (!StringUtils.isEmpty(attr))
				sql.append(attr).append(",");
		}
		sql = sql.delete(sql.length() - 1, sql.length());
		sql.append(" from ");
		sql.append(table);

		if (where != null && !where.isNoop()) {
			sql.append(" where ");
			sql.append(where.toString());

			arguments = where.getValues();
		}

		if (group != null && group.length != 0) {
			sql.append(" group by ");
			for (String g : group)
				sql.append(g).append(",");
			sql = sql.delete(sql.length() - 1, sql.length());
		}

		if (order != null) {
			sql.append(" order by ");
			sql.append(order.toString()).append(",");
			sql = sql.delete(sql.length() - 1, sql.length());
		}

		if (options != null) {
			if (options.getPage() != null) {
				arguments.add(pageSize);
				arguments.add(options.getOffset(pageSize));
			}
			sql.append(" limit ? offset ? ");
		}

		return sql.toString();
	}

	public Query where(Where wheres) {
		this.where = wheres;
		return this;
	}

	public Query where(Where... wheres) {
		List<Where> whereList = new ArrayList<>();
		for (Where where : wheres)
				whereList.add(where);
			this.where = Where.and(whereList);
		return this;
	}

	public Query where(List<Where> wheres) {
		this.where = Where.and(wheres);
		return this;
	}

	public Query order(List<Order> order) {
		this.order = Order.list(order);
		return this;
	}

	public Query order(Order... order) {
		this.order = Order.list(order);
		return this;
	}

	public Query paginate(PagingOptions options, int pageSize) {
		this.options = options;
		this.pageSize = pageSize;
		return this;
	}
}
