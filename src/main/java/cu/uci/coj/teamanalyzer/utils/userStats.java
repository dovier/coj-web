/*
* userStats.java
*
* v1.0
*
* 14/05/2016
*/

package cu.uci.coj.teamanalyzer.utils;

/**
 * Clase que almacena las estadísticas del desempeño del concursante
 */
public class userStats {

    /** Almacena el id del concursante */
    private int id;
    /** Matriz que almacena el los desempeños de del concursante y sus valores */
    private int[][] stats;

    public userStats(int id, int[][] stats) {
        this.id = id;
        this.stats = stats;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int[][] getStats() {
        return stats;
    }

    public void setStats(int[][] stats) {
        this.stats = stats;
    }
}
