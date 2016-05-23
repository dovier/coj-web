package cu.uci.coj.model;

import java.util.ArrayList;
import java.util.List;

public class DataSetVerdictsJson {
	public DataSetVerdictsJson() {
		super();
		d = new ArrayList<DatasetVerdictJson>();
	}

	private List<DatasetVerdictJson> d;
	
	/**
	 * @return the d
	 */
	public List<DatasetVerdictJson> getD() {
		return d;
	}

	/**
	 * @param dvs the d to set
	 */
	public void setD(List<DatasetVerdictJson> dvs) {
		this.d = dvs;
	}

	public void addDataSetVerdict(DatasetVerdict dv) {
		DatasetVerdictJson dvjson = new DatasetVerdictJson();
		dvjson.setC(dv.getTestnum());
		dvjson.setT(dv.getUserTime());
		dvjson.setM(dv.getMemory());
		dvjson.setS(dv.getStatus());
		d.add(dvjson);
	}

}
