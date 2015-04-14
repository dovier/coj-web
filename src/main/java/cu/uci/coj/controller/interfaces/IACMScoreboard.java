package cu.uci.coj.controller.interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.RowMapper;

import cu.uci.coj.controller.BaseController;
import cu.uci.coj.model.Contest;
import cu.uci.coj.model.Group;
import cu.uci.coj.model.Problem;
import cu.uci.coj.model.User;

public class IACMScoreboard extends BaseController implements RowMapper<Object> {

	List<Group> groups;
	List<Problem> problems;
	Map<String, Integer> groupsRecords;
	Map<Integer, Integer> uidPos;
	List<User> users;
	boolean groupby;
	int min, toffset;
	Integer[] minimums;
	// para las comparaciones de sameRank()
	int[][] orderedProblems;
	boolean solved = false;
	private String guestGroup = null;

	public List<User> assignMedals(Contest contest) {
		int i = 0;
		List<User> medals = new ArrayList<>();
		while (i < users.size() && users.get(i).getAcc() != 0 && users.get(i).getRank() <= contest.getGold()) {
			users.get(i).setMedal(User.GOLD);
			medals.add(users.get(i++));
		}
		while (i < users.size() && users.get(i).getAcc() != 0 && users.get(i).getRank() <= (contest.getGold() + contest.getSilver())) {
			users.get(i).setMedal(User.SILVER);
			medals.add(users.get(i++));
		}
		while (i < users.size() && users.get(i).getAcc() != 0 && users.get(i).getRank() <= (contest.getGold() + contest.getSilver() + contest.getBronze())) {
			users.get(i).setMedal(User.BRONZE);
			medals.add(users.get(i++));
		}
		return medals;
	}

	public void groupSort() {
		if (groups != null) {
			if (!StringUtils.isEmpty(guestGroup)) {
				Group group = null;
				int i = 0;
				while (i < groups.size() && !groups.get(i).getName().equals(guestGroup))
					i++;
				if (i < groups.size()) {
					group = groups.get(i);
					groups.remove(i);
				}
				Collections.sort(groups);
				if (group != null)
					groups.add(group);
			} else
				Collections.sort(groups);
		}
	}

	public void init(boolean groupby, String guestGroup, List<Problem> prbls, Integer[] a) {
		groups = new LinkedList<Group>();
		groupsRecords = new HashMap<String, Integer>();
		users = new LinkedList<User>();
		this.guestGroup = guestGroup;
		this.groupby = groupby;

		orderedProblems = new int[2][a.length];
		problems = prbls;
		minimums = a;
		min = minimums[0];
		for (int ii = 1; ii < minimums.length; ii++) {
			if (minimums[ii] < min) {
				min = minimums[ii];
			}
		}
	}

	public void init2(boolean groupby, List<Problem> prbls, Integer[] a) {
		groups = new LinkedList<Group>();
		groupsRecords = new HashMap<String, Integer>();
		users = new LinkedList<User>();

		this.groupby = groupby;

		orderedProblems = new int[2][a.length];
		problems = prbls;
		minimums = a;
		min = minimums[0];
		for (int ii = 1; ii < minimums.length; ii++) {
			if (minimums[ii] < min) {
				min = minimums[ii];
			}
		}
	}

	public List<Group> getGroups() {
		return groups;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	private boolean sameRank(User user1, User user2) {
		// nunca debe suceder
		if (user1 == null && user2 == null)
			return true;
		// si alguno de los dos es null pero el otro no, obviamente no hay el
		// mismo rango. Este es el caso siempre con el primer user de cada grupo
		if (user1 == null || user2 == null)
			return false;

		if (user1.getAcc() == 0 && user2.getAcc() == 0)
			return true;

		// al menos un aceptado, misma cantidad de acs y de tiempo de penalidad,
		// comparando con los ultimos aceptados de cada problema

		String la1 = user1.getLastacc();
		if (la1 == null)
			la1 = "";
		String la2 = user2.getLastacc();
		if (la2 == null)
			la2 = "";
		if ((user1.getAcc() == user2.getAcc() && user1.getPenalty() == user2.getPenalty()) && la1.equals(la2))
			return true;

		return false;
	}

	public Object mapRow(ResultSet result, int ii) throws SQLException {
		User user = new User(result.getString(2), result.getString(3), result.getInt(4), result.getInt(5), (result.getInt("lastacc") / 60000 + ""), result.getString(6), result.getString(7),
				result.getString(8), result.getString(9), problems);
		user.setGroup(result.getString("groupd"));
		user.setOnline(result.getBoolean("ONLINE"));
		user.setCoach(result.getString("COACH"));
		user.setUser_1(result.getString("USER_1"));
		user.setUser_2(result.getString("USER_2"));
		user.setUser_3(result.getString("USER_3"));
		user.buildToolTip();
		char c = 'a';
		for (int i = 0; i < problems.size(); i++) {
			int time = result.getInt(c + "_time"), bac = result.getInt(c + "_beforeac"), aac = result.getInt(c + "_afterac"), pen = result.getInt(c + "_pending");
			user.getProblems().get(i).setBeforeac(bac);
			user.getProblems().get(i).setAfterac(aac);
			user.getProblems().get(i).setPending(pen);
			if (bac > 0) {
				user.getProblems().get(i).setScoreClass("WA");
			}
			if (time != 100000000) {
				user.getProblems().get(i).setAccepted(true);
				user.getProblems().get(i).setScoreClass("ACC");
				user.getProblems().get(i).setAc_time(time + "");
				user.getProblems().get(i).setAcmin((time / 60000) + "");
				if (time == min) {
					user.getProblems().get(i).setScoreClass("FPS");
				} else if (time == minimums[i]) {
					user.getProblems().get(i).setScoreClass("FS");
				}
			}
			if (pen > 0 && !user.getProblems().get(i).isAccepted()) {
				user.getProblems().get(i).setScoreClass("PEN");
			}
			c++;
		}
		if (groupby) {
			if (groupsRecords.containsKey(user.getGroup())) {
				int pos = groupsRecords.get(user.getGroup());
				User lastUser = null;
				if (!groups.get(pos).getUsers().isEmpty())
					lastUser = groups.get(pos).getUsers().get(groups.get(pos).getUsers().size() - 1);

				user.setRank(sameRank(lastUser, user) ? lastUser.getRank() : groups.get(pos).getUsers().size() + 1);
				groups.get(pos).addUser(user);

			} else {
				Group gr = new Group(user.getGroup());
				user.setRank(1);
				gr.addUser(user);
				gr.setId(groups.size());
				groups.add(gr);
				groupsRecords.put(user.getGroup(), groups.size() - 1);
			}
		} else {

			if (!users.isEmpty()) {
				user.setRank(sameRank(users.get(users.size() - 1), user) ? users.get(users.size() - 1).getRank() : users.size() + 1);
			} else
				user.setRank(1);
			users.add(user);
		}
		return null;
	}
}
