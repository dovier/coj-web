package cu.uci.coj.teamanalyzer.models;


import cu.uci.coj.model.Contest;
import cu.uci.coj.model.User;

import java.util.Date;
import java.util.List;

public class analysis {

    private int id;
    private String name;
    private Date date;
    private int coach;

    private int[] usersids;
    private int[] contestsids;

    private List<User> users;
    private List<Contest> contest;

    public List<Contest> getContest() {
        return contest;
    }

    public void setContest(List<Contest> contest) {
        this.contest = contest;
    }

    public analysis(String name, Date date, int coach, int[] usersids, int[] contestsids) {
        this.name = name;
        this.date = date;
        this.coach = coach;
        this.usersids = usersids;
        this.contestsids = contestsids;
    }

    public analysis(String name, Date date, int coach) {
        this.name = name;
        this.date = date;
        this.coach = coach;
        this.usersids = null;
        this.contestsids = null;
    }

    public analysis() {
    }

    public int[] getUsersids() {
        return usersids;
    }

    public void setUsersids(int[] usersids) {
        this.usersids = usersids;
    }

    public int[] getContestsids() {
        return contestsids;
    }

    public void setContestsids(int[] contestsids) {
        this.contestsids = contestsids;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCoach() {
        return coach;
    }

    public void setCoach(int coach) {
        this.coach = coach;
    }
}
