package cu.uci.coj.query;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import cu.uci.coj.dao.BaseDAO;

@Component
public class PostgresDml{

	@Resource
	private BaseDAO baseDAO;
	
	public String delete(String table, Where... parameters) {

		if (StringUtils.isEmpty(table))
			return null;
		String dml = "delete from " + table + " where ";
		if (ArrayUtils.isEmpty(parameters))
			for (Where parameter : parameters)
				dml += parameter.toString();
		return dml;
	}

	public int insert(String table,DmlPart... attributes) {

		if (StringUtils.isEmpty(table) || ArrayUtils.isEmpty(attributes))
			return 0;
		List<Object> args = new ArrayList<>();
		String dml = "insert into " + table + " (";
		String values = ") values (";
		for (DmlPart attribute : attributes) {
			if (attribute != null) {
				dml += attribute.getName() + ",";
				values +="?,";
				args.add(attribute.getValue());
			}
		}
		dml = dml.substring(0, dml.length() - 1);
		values = values.substring(0, values.length() - 1);
		dml += values;
		dml += ")";
		return baseDAO.dml(dml,args);
	}

	public int update(String table, Where parameter, DmlPart... attributes) {
		if (StringUtils.isEmpty(table) || ArrayUtils.isEmpty(attributes))
			return 0;

		List<Object> args = new ArrayList<>();
		String dml = "update " + table + " set ";
		for (DmlPart attribute : attributes) {
			if (attribute != null) {
				dml += attribute.toString()  + ",";
				args.add(attribute.getValue());
			}
		}
		dml = dml.substring(0, dml.length() - 1);
		dml += " where ";
		dml += parameter.toString();
		args.addAll(parameter.getValues());
		return baseDAO.dml(dml, args);
	}
}
