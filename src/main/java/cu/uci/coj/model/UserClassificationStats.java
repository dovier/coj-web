package cu.uci.coj.model;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class UserClassificationStats {

	private String labels;
	private String top;
	private String classifications;
	private int count;
	
	private String percentTop;
	private String percentTotal;
	
	private String probTop;
	
	private String dates;
	private String acStatus;
	private String errorStatus;
	private int dateCount;

	public UserClassificationStats(List<Map<String, Object>> probs) {
		initProbs(probs);
	}

	public UserClassificationStats(List<Map<String, Object>> raw, List<Map<String, Object>> probs, List<Map<String, Object>> timeline) {


		String[] labels = new String[raw.size()];
		int[][] classifications = new int[raw.size()][5];
		int[] top = new int[raw.size()];
		for (int i = 0; i < raw.size(); i++) {
			Map<String, Object> row = raw.get(i);
			labels[i] = "'" + String.valueOf(row.get("name")) + "'";
			classifications[i][0] = Integer.valueOf(String.valueOf(row.get("1")));
			classifications[i][1] = Integer.valueOf(String.valueOf(row.get("2")));
			classifications[i][2] = Integer.valueOf(String.valueOf(row.get("3")));
			classifications[i][3] = Integer.valueOf(String.valueOf(row.get("4")));
			classifications[i][4] = Integer.valueOf(String.valueOf(row.get("5")));
			top[i] = Integer.valueOf(String.valueOf(row.get("top")));
		}

		String[] array = new String[classifications.length];
		for (int i = 0; i < classifications.length; i++) {
			array[i] = Arrays.toString(classifications[i]);
		}
		this.classifications = Arrays.toString(array);
		this.top = Arrays.toString(top);
		this.labels = Arrays.toString(labels);
		this.count = labels.length;


		int[] probTop = initProbs(probs);

		initPercent(top, probTop);

		//0 es AC, 1 es error
		String[][] status = new String[2][timeline.size()];

		String[] dates = new String[timeline.size()];
		dateCount = timeline.size();
		for (int i = 0; i < timeline.size(); i++) {
			Map<String, Object> row = timeline.get(i);
			dates[i] = "'" + String.valueOf(row.get("label")) + "'";
			status[0][i] = String.valueOf(row.get("ac"));
			status[1][i] = String.valueOf(row.get("error"));
		}
		this.dates = Arrays.toString(dates);
		this.acStatus = Arrays.toString(status[0]);
		this.errorStatus = Arrays.toString(status[1]);
	}
	
	private int[] initProbs(List<Map<String,Object>> probs) {
		String[] labels = new String[probs.size()];
		int[] probTop = new int[probs.size()];
		for(int i = 0;i < probs.size();i++) {
			Map<String,Object> row = probs.get(i);
			labels[i] = "'" + String.valueOf(row.get("name"))+"'";
			probTop[i] = Integer.valueOf(String.valueOf(row.get("top")));

		}
		this.labels = Arrays.toString(labels);
		this.probTop = Arrays.toString(probTop);
		return probTop;
	}
	
	private void initPercent(int[] top,int[] probTop) {
		int[] percent = new int[top.length];
		int[] percentTotal = new int[top.length];
		for(int i =0 ;i < top.length;i++) {
			percent[i] = top[i] * 100 / Math.max(probTop[i], 1);
			percentTotal[i] = 100;
		}
		this.percentTop = Arrays.toString(percent);
		this.percentTotal = Arrays.toString(percentTotal);
	}

	public String getPercentTotal() {
		return percentTotal;
	}

	public String getPercentTop() {
		return percentTop;
	}

	public int getDateCount() {
		return dateCount;
	}

	public String getLabels() {
		return labels;
	}

	public int getCount() {
		return count;
	}
	
	public String getTop() {
		return top;
	}

	public String getClassifications() {
		return classifications;
	}

	public String getDates() {
		return dates;
	}

	public String getAcStatus() {
		return acStatus;
	}

	public String getErrorStatus() {
		return errorStatus;
	}

	public String getProbTop() {
		return probTop;
	}

}
