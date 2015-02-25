package cu.uci.coj.query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

public class Order {
	private String name;
	private List<Order> params;
	private int op;

	public final static int NOOP = 0;
	public final static int ASC = 1;
	public final static int DESC = 2;

	public List<Order> filterNoop(List<Order> params) {
		Iterator<Order> it = params.iterator();
		while (it.hasNext()) {
			if (it.next().isNoop())
				it.remove();
		}
		return params;
	}
	
	private Order(int op, String name) {
		this.op = op;
		this.name = name;
	}

	private Order(Order... params) {
		this.name = "";
		this.params = new ArrayList<>();
		this.params.addAll(Arrays.asList(params));
		this.params = filterNoop(this.params);
	}
	
	private Order(List<Order> params) {
		this.name = "";
		this.params = new ArrayList<>();
		this.params.addAll(params);
		this.params = filterNoop(this.params);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isNoop() {
		return op == NOOP;
	}

	public String toString() {
		if (name != null) {
			switch (op) {
			case ASC:
				return name + " asc ";
			case DESC:
				return name + " desc ";
			}
		}
		String res = "";
		if (!CollectionUtils.isEmpty(params)) {
			for (Order order : params)
					res += order.toString() + ",";
			res = res.substring(0, res.length()-1);
		}
		return res;
	}

	public static Order noop() {
		return new Order(NOOP, "");
	}

	// ----------------------------
	public static Order list(Order... names) {
		if (names == null || names.length == 0)
			return noop();
		return new Order(Arrays.asList(names));
	}

	public static Order list(List<Order> names) {
		if (CollectionUtils.isEmpty(names))
			return noop();
		return new Order(names);
	}

	public static Order asc(String name) {
		if (StringUtils.isEmpty(name))
			return noop();
		return new Order(ASC, name);
	}

	public static Order desc(String name) {
		if (StringUtils.isEmpty(name))
			return noop();
		return new Order(DESC, name);
	}
}
