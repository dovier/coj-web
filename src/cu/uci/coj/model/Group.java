package cu.uci.coj.model;

import java.util.LinkedList;

import cu.uci.coj.config.Config;

public class Group implements Comparable<Object>{

    private String name;
    private LinkedList<User> users;
    private int acs;
    private int time;
    private int id;
    private boolean unique;

    public Group() {
    }

    public Group(String name) {
        this.name = name;
        this.users =  new LinkedList<User>();
        this.acs = 0;
        this.time = 0;
    }

    
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAcs() {
        return acs;
    }

    public void setAcs(int acs) {
        this.acs = acs;
    }
    
    public void addAcs(int acs) {
        this.acs += acs;
    }

    public int getTime() {
        return time;
    }

    public void addTime(int time) {
        this.time += time;
    }
    
    public void setTime(int time) {
        this.time = time;
    }

    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedList<User> getUsers() {
        return users;
    }

    public void setUsers(LinkedList<User> users) {
        this.users = users;
    }

    public boolean isUnique() {
        return unique;
    }

    public void setUnique(boolean unique) {
        this.unique = unique;
    }

	@Override
	public int compareTo(Object o) {
		Group g = ((Group)o);
		
		
		if (acs == g.acs){
			if (g.users.size() == users.size())
				return time - g.time;
			return users.size() - g.users.size();
		}
		return g.acs-acs;
	}

	
	public void addUser(User user){
		getUsers().add(user);
		addAcs(user.getAcc());
		addTime(user.getPenalty());
		
	}
}
