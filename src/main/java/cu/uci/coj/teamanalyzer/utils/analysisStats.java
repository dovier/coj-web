package cu.uci.coj.teamanalyzer.utils;

import cu.uci.coj.teamanalyzer.dao.analysisDAO;
import cu.uci.coj.teamanalyzer.models.analysis;

import java.util.List;

/**
 * Created by ricardo on 2/06/16.
 */
public class analysisStats {
    private List<userStats> usersStats;
    private int[] idsClassifications;
    private analysisDAO analysisDAO;
    private analysis analysis;

    public analysisStats(List<userStats> usersStats, int[] idsClassifications, analysis analysis, analysisDAO analysisDAO) {
        this.usersStats = usersStats;
        this.idsClassifications = idsClassifications;
        this.analysis = analysis;
        this.analysisDAO = analysisDAO;
    }

    public static int repeter(int[] max, int prim, int ult) {
        int i, frec, maxfrec, moda;
        prim++;
        if (prim == ult) {
            if (max[prim] == -1)
                return 0;
            return max[prim];
        }
        moda = max[prim];
        maxfrec = frecuency(max, max[prim], prim, ult);
        for (i = prim + 1; i <= ult; i++) {
            if (max[i] != -1) {
                frec = frecuency(max, max[i], i, ult);
                if (frec > maxfrec) {
                    maxfrec = frec;
                    moda = max[i];
                }
            }
        }
        if (moda == -1)
            return 0;
        return moda;
    }

    public static int frecuency(int a[], int p, int prim, int ult) {
        int i, suma;
        if (prim > ult) return 0;
        suma = 0;
        for (i = prim; i <= ult; i++)
            if (a[i] == p)
                suma++;
        return suma;

    }

    public List<userStats> getUsersStats() {
        return usersStats;
    }

    public void setUsersStats(List<userStats> usersStats) {
        this.usersStats = usersStats;
    }

    public int[] getIdsClassifications() {
        return idsClassifications;
    }

    public void setIdsClassifications(int[] idsClassifications) {
        this.idsClassifications = idsClassifications;
    }

    public void makeTeams() {
        if (usersStats.size() > 3) {
            int[] team = new int[3];
            team[0] = getMember();
            team[1] = getMember();
            team[2] = getMember();
            saveTeam(team);
            makeTeams();
        } else if (usersStats.size() == 3) {
            int[] team = new int[3];
            team[0] = usersStats.get(0).getId();
            team[1] = usersStats.get(1).getId();
            team[2] = usersStats.get(2).getId();
            usersStats.clear();
            saveTeam(team);
        }
    }

    private void saveTeam(int[] team) {
        analysisDAO.saveTeam(team[0], team[1], team[2], analysis.getId());
    }

    private int getMember() {
        int[] max = new int[idsClassifications.length];
        for (int i = 0; i < max.length; i++) {
            max[i] = getMax(i);
        }
        int pos = repeter(max, 0, max.length - 1);
        int userId = usersStats.get(pos).getId();
        usersStats.remove(pos);
        return userId;
    }

    private int getMax(int i) {
        int mayor;
        int pos = -1;
        mayor = usersStats.get(0).getStats()[i][1];
        if (usersStats.get(0).getStats()[i][1] != 0) {
            pos = 0;
        }
        for (int f = 0; f < usersStats.size(); f++) {
            if (usersStats.get(f).getStats()[i][1] > mayor) {
                mayor = usersStats.get(f).getStats()[i][1];
                pos = f;
            }
        }
        return pos;
    }

}
