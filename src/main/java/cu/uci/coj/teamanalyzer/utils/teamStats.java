/*
* teamStats.java
*
* v1.0
*
* 14/05/2016
*/

package cu.uci.coj.teamanalyzer.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Clase que contiene los métodos necesarios para conformar los equipos
 */
public class teamStats {
    /** Para los nombres de las áreas de conocimientos */
    private String labels;
    /** Almacena el 100% tantas veces como áreas de conocimientos existan*/
    private String percentTotal;
    /** Almacena el porciento máximo alcanzado por integrante 1 del equipo*/
    private String percentTop1;
    /** Almacena el porciento máximo alcanzado por integrante 2 del equipo*/
    private String percentTop2;
    /** Almacena el porciento máximo alcanzado por integrante 3 del equipo*/
    private String percentTop3;
    /** Almacena el porciento máximo alcanzado en cada área de conocimiento por el equipo */
    private String percentTopMax;
    /** Almacena el valor máximo alcanzado por integrante 1 del equipo*/
    private String top1;
    /** Almacena el valor máximo alcanzado por integrante 2 del equipo*/
    private String top2;
    /** Almacena el valor máximo alcanzado por integrante 3 del equipo*/
    private String top3;
    /** Almacena el valor máximo alcanzado en cada área de conocimiento por el equipo */
    private String topMax;

    public teamStats(List<Map<String, Object>> maps1, List<Map<String, Object>> maps2, List<Map<String, Object>> maps3, List<Map<String, Object>> probs, List<Map<String, Object>> classifications) {

        String[] labels = new String[classifications.size()];
        int[] top1 = new int[classifications.size()];
        int[] top2 = new int[classifications.size()];
        int[] top3 = new int[classifications.size()];
        int[] topMax = new int[classifications.size()];

        int j = 0, k = 0, l = 0;
        // Se inicializan todos los datos estadísticos
        for (int i = 0; i < classifications.size(); i++) {
            Map<String, Object> row1 = maps1.get(j);
            Map<String, Object> row2 = maps2.get(k);
            Map<String, Object> row3 = maps3.get(l);
            Map<String, Object> row4 = classifications.get(i);
            labels[i] = "'" + String.valueOf(row4.get("name")) + "'";
            if (Integer.valueOf(String.valueOf(row4.get("id_classification"))) == Integer.valueOf(String.valueOf(row1.get("id_classification")))) {
                top1[i] = Integer.valueOf(String.valueOf(row1.get("top")));
                if (j < maps1.size() - 1) {
                    j++;
                }
            } else {
                top1[i] = 0;
            }
            if (Integer.valueOf(String.valueOf(row4.get("id_classification"))) == Integer.valueOf(String.valueOf(row2.get("id_classification")))) {
                top2[i] = Integer.valueOf(String.valueOf(row2.get("top")));
                if (k < maps2.size() - 1) {
                    k++;
                }
            } else {
                top2[i] = 0;
            }
            if (Integer.valueOf(String.valueOf(row4.get("id_classification"))) == Integer.valueOf(String.valueOf(row3.get("id_classification")))) {
                top3[i] = Integer.valueOf(String.valueOf(row3.get("top")));
                if (l < maps3.size() - 1) {
                    l++;
                }
            } else {
                top3[i] = 0;
            }
            topMax[i] = top1[i];
            if (topMax[i] < top2[i]) {
                topMax[i] = top2[i];
            }
            if (topMax[i] < top3[i]) {
                topMax[i] = top3[i];
            }

        }


        this.top1 = Arrays.toString(top1);
        this.top2 = Arrays.toString(top2);
        this.top3 = Arrays.toString(top3);
        this.topMax = Arrays.toString(topMax);
        this.labels = Arrays.toString(labels);

        int[] probTop = initProbs(probs, classifications);

        initPercent(top1, top2, top3, topMax, probTop);
    }

    private void initPercent(int[] top1, int[] top2, int[] top3, int[] topMax, int[] probTop) {
        int[] percent1 = new int[top1.length];
        int[] percent2 = new int[top2.length];
        int[] percent3 = new int[top3.length];
        int[] percentMax = new int[topMax.length];
        int[] percentTotal = new int[probTop.length];
        for (int i = 0; i < probTop.length; i++) {
            percent1[i] = top1[i] * 100 / Math.max(probTop[i], 1);
            percent2[i] = top2[i] * 100 / Math.max(probTop[i], 1);
            percent3[i] = top3[i] * 100 / Math.max(probTop[i], 1);
            percentMax[i] = topMax[i] * 100 / Math.max(probTop[i], 1);
            percentTotal[i] = 100;
        }
        this.percentTop1 = Arrays.toString(percent1);
        this.percentTop2 = Arrays.toString(percent2);
        this.percentTop3 = Arrays.toString(percent3);
        this.percentTopMax = Arrays.toString(percentMax);
        this.percentTotal = Arrays.toString(percentTotal);
    }

    private int[] initProbs(List<Map<String, Object>> probs, List<Map<String, Object>> classifications) {
        int[] probTop = new int[classifications.size()];
        int j = 0;

        for (int i = 0; i < classifications.size(); i++) {
            Map<String, Object> row = classifications.get(i);
            Map<String, Object> prob = probs.get(j);
            if (Integer.valueOf(String.valueOf(row.get("id_classification"))) == Integer.valueOf(String.valueOf(prob.get("id_classification")))) {
                probTop[i] = Integer.valueOf(String.valueOf(prob.get("top")));
                if (j < probs.size() - 1) {
                    j++;
                }
            } else {
                probTop[i] = 0;
            }
        }
        return probTop;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }

    public String getPercentTotal() {
        return percentTotal;
    }

    public void setPercentTotal(String percentTotal) {
        this.percentTotal = percentTotal;
    }

    public String getPercentTop1() {
        return percentTop1;
    }

    public void setPercentTop1(String percentTop1) {
        this.percentTop1 = percentTop1;
    }

    public String getPercentTop2() {
        return percentTop2;
    }

    public void setPercentTop2(String percentTop2) {
        this.percentTop2 = percentTop2;
    }

    public String getPercentTop3() {
        return percentTop3;
    }

    public void setPercentTop3(String percentTop3) {
        this.percentTop3 = percentTop3;
    }

    public String getPercentTopMax() {
        return percentTopMax;
    }

    public void setPercentTopMax(String percentTopMax) {
        this.percentTopMax = percentTopMax;
    }

    public String getTop1() {
        return top1;
    }

    public void setTop1(String top1) {
        this.top1 = top1;
    }

    public String getTop2() {
        return top2;
    }

    public void setTop2(String top2) {
        this.top2 = top2;
    }

    public String getTop3() {
        return top3;
    }

    public void setTop3(String top3) {
        this.top3 = top3;
    }

    public String getTopMax() {
        return topMax;
    }

    public void setTopMax(String topMax) {
        this.topMax = topMax;
    }
}
