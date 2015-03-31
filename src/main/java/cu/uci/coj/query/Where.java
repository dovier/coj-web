package cu.uci.coj.query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

public class Where {
	private String name;
	private Object value;
	private List<Where> params;
	private int op = NOOP;

	public final static int NOOP = 0;
	public final static int EQ = 1;
	public final static int AND = 2;
	public final static int TRUE = 3;
	public final static int FALSE = 4;
	public final static int LT = 5;
	public final static int GT = 6;
	public final static int LE = 7;
	public final static int GE = 8;
	public final static int OR = 9;
	public final static int IN = 10;
	public final static int ILIKE = 11;
	public final static int NE = 12;
	public final static int BETWEEN = 13;

	private Where(int op, String name, Object value) {
		this.op = op;
		this.name = name;
		this.value = value;
	}

	private Where(int op, Where... params) {
		this(op,Arrays.asList(params));
	}

	private Where(int op, List<Where> params) {
		this.op = op;
		this.name = "";
		this.value = null;
		this.params = new ArrayList<>();
		this.params.addAll(params);
		this.params = filterNoop(this.params);
		if (this.params.size() == 0) this.op=NOOP;
	}

	public List<Object> getValues() {
		List<Object> values = new ArrayList<>();
		if (this.isNoop())
			return values;
		if (value instanceof Query)
			values.addAll(((Query) value).getArguments());
		else if (value instanceof List)
			values.addAll((List)value);
		else if (value != null)
			values.add(value);
		if (params != null)
			for (Where param : params)
				values.addAll(param.getValues());
		return values;
	}

	public List<Where> filterNoop(List<Where> params) {
		Iterator<Where> it = params.iterator();
		while (it.hasNext()) {
			if (it.next().isNoop())
				it.remove();
		}
		return params;
	}

	public String toString() {
		String str = "";
		String val = " ? ";
		if (value instanceof Query) {
			val = " (" + ((Query) value).select() + ") ";
		}

		if (name != null) {
			switch (op) {
			case BETWEEN:
				return name + " between ? and ?";
			case EQ:
				return name + "=" + val;
			case NE:
				return name + "!=" + val;
			case TRUE:
				return name + " = true ";
			case FALSE:
				return name + " = false ";
			case LT:
				return name + " <" + val;
			case GT:
				return name + " >" + val;
			case LE:
				return name + " <=" + val;
			case GE:
				return name + " >=" + val;
			case ILIKE:
				return name + " ilike ?";
			case IN:
				if (!(value instanceof Query)) {
					val = name + " in (";
					for (Object obj : ((Object[]) value))
						val += "?,";
					val = val.substring(0, val.length() - 1) + ") ";
				}
			case AND:
				str = " (1=1";
				for (Where param : params)
					if (!param.isNoop())
						str += " and " + param.toString();
				str += ") ";
				return str;
			case OR:
				str = " (1!=1";
				for (Where param : params)
					if (!param.isNoop())
						str += " or " + param.toString();
				str += ") ";
				return str;
			}
		}
		return str;
	}
	
	public boolean isNoop() {
		return op == NOOP || ( (op == AND || op == OR) && CollectionUtils.isEmpty(this.params));
	}

	public static Where noop() {
		return new Where(NOOP, null, null);
	}

	public static Where and(Where... names) {
		if (names == null || names.length == 0)
			return noop();
		return new Where(AND, names);
	}

	public static Where and(List<Where> names) {
		if (CollectionUtils.isEmpty(names))
			return noop();
		return new Where(AND, names);
	}

	public static Where or(Where... names) {
		if (names == null || names.length == 0)
			return noop();
		return new Where(OR, names);
	}

	public static Where or(List<Where> names) {
		if (CollectionUtils.isEmpty(names))
			return noop();
		return new Where(OR, names.toArray(new Where[0]));
	}

	// ----------------------------

	public static Where ilike(String name, String value) {
		if (StringUtils.isEmpty(value) || StringUtils.isEmpty(name))
			return noop();
		return new Where(ILIKE, name, "%" + value + "%");
	}

	public static Where in(String name, Object... values) {
		if (values == null || values.length == 0)
			return noop();
		return new Where(IN, name, values);
	}

	public static Where in(String name, List<Object> values) {
		if (CollectionUtils.isEmpty(values))
			return noop();
		return new Where(IN, name, values.toArray());
	}

	public static Where in(String name, Query values) {
		if (values != null)
			return noop();
		return new Where(IN, name, values);
	}

	public static Where bt(String name, Object fromValue, Object toValue) {
		if (fromValue == null && toValue == null)
			return Where.noop();
		if (fromValue == null && toValue != null)
			return Where.le(name, toValue);
		if (fromValue != null && toValue == null)
			return Where.ge(name, fromValue);
		return new Where(BETWEEN, name, Arrays.asList(fromValue, toValue));
	}

	public static Where eq(String name, String value) {
		if (StringUtils.isEmpty(value) || StringUtils.isEmpty(name))
			return noop();
		return new Where(EQ, name, value);
	}

	public static Where eq(String name, Object value) {
		if (value == null || StringUtils.isEmpty(name))
			return noop();
		return new Where(EQ, name, value);
	}

	public static Where eq(String name) {
		if (StringUtils.isEmpty(name))
			return noop();
		return new Where(EQ, name, null);
	}

	public static Where ne(String name, String value) {
		if (StringUtils.isEmpty(value) || StringUtils.isEmpty(name))
			return noop();
		return new Where(NE, name, value);
	}

	public static Where ne(String name, Object value) {
		if (value == null || StringUtils.isEmpty(name))
			return noop();
		return new Where(NE, name, value);
	}

	public static Where ne(String name) {
		if (StringUtils.isEmpty(name))
			return noop();
		return new Where(NE, name, null);
	}

	public static Where yes(String name, String value) {
		if (StringUtils.isEmpty(value) || StringUtils.isEmpty(name))
			return noop();
		return new Where(TRUE, name, null);
	}

	public static Where yes(String name, Object value) {
		if (value == null || StringUtils.isEmpty(name))
			return noop();
		return new Where(TRUE, name, null);
	}

	public static Where yes(String name) {
		if (StringUtils.isEmpty(name))
			return noop();
		return new Where(TRUE, name, null);
	}

	public static Where no(String name, String value) {
		if (StringUtils.isEmpty(value) || StringUtils.isEmpty(name))
			return noop();
		return new Where(FALSE, name, null);
	}

	public static Where no(String name, Object value) {
		if (value == null || StringUtils.isEmpty(name))
			return noop();
		return new Where(FALSE, name, null);
	}

	public static Where no(String name) {
		if (StringUtils.isEmpty(name))
			return noop();
		return new Where(FALSE, name, null);
	}

	public static Where lt(String name, String value) {
		if (StringUtils.isEmpty(value) || StringUtils.isEmpty(name))
			return noop();
		return new Where(LT, name, null);
	}

	public static Where lt(String name, Object value) {
		if (value == null || StringUtils.isEmpty(name))
			return noop();
		return new Where(LT, name, value);
	}

	public static Where lt(String name) {
		if (StringUtils.isEmpty(name))
			return noop();
		return new Where(LT, name, null);
	}

	public static Where gt(String name, String value) {
		if (StringUtils.isEmpty(value) || StringUtils.isEmpty(name))
			return noop();
		return new Where(GT, name, null);
	}

	public static Where gt(String name, Object value) {
		if (value == null || StringUtils.isEmpty(name))
			return noop();
		return new Where(GT, name, value);
	}

	public static Where gt(String name) {
		if (StringUtils.isEmpty(name))
			return noop();
		return new Where(GT, name, null);
	}

	public static Where le(String name, String value) {
		if (StringUtils.isEmpty(value) || StringUtils.isEmpty(name))
			return noop();
		return new Where(LE, name, null);
	}

	public static Where le(String name, Object value) {
		if (value == null || StringUtils.isEmpty(name))
			return noop();
		return new Where(LE, name, value);
	}

	public static Where le(String name) {
		if (StringUtils.isEmpty(name))
			return noop();
		return new Where(LE, name, null);
	}

	public static Where ge(String name, String value) {
		if (StringUtils.isEmpty(value) || StringUtils.isEmpty(name))
			return noop();
		return new Where(GE, name, null);
	}

	public static Where ge(String name, Object value) {
		if (value == null || StringUtils.isEmpty(name))
			return noop();
		return new Where(GE, name, value);
	}

	public static Where ge(String name) {
		if (StringUtils.isEmpty(name))
			return noop();
		return new Where(GE, name, null);
	}
}
