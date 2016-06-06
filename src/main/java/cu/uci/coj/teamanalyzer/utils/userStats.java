package cu.uci.coj.teamanalyzer.utils;

public class userStats {
    private int id;
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
