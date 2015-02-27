package cu.uci.coj.query;

import org.apache.commons.lang.StringUtils;

public class DmlPart {

	private String name;
	private Object value;
	private DmlPart(String name, Object value) {
		this.name = name;
		this.value = value;
	}
	
	public static DmlPart check(String name,Object value) {
		if (StringUtils.isEmpty(name) || value == null )
			return null;
		return new DmlPart(name,value);
	}
	
	public String getName() {
		return name;
	}
	
	public Object getValue() {
		return value;
	}
	
	public String toString() {
		return name + "= ?";
	}
}
