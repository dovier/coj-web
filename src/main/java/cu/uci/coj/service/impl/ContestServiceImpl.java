package cu.uci.coj.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import cu.uci.coj.dao.ContestDAO;
import cu.uci.coj.service.ContestService;
import cu.uci.coj.utils.Utils;

@Service("contestService")
public class ContestServiceImpl implements ContestService {

	@Resource
	private ContestDAO contestDAO;

	public List<String> importICPCUsers(String prefix, String[] person, String[] school, String[] site, String[] team, String[] teamPerson, Integer cid, Integer warmupCid) {
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		String groupd = "";
		String[][] persons = new String[person.length - 1][2];
		String[][] schools = new String[school.length - 1][2];
		String[][] sites = new String[site.length - 1][2];
		String[][] teams = new String[team.length - 1][4];
		String[][] teamPersons = new String[teamPerson.length - 1][3];
		String[][] teamCoaches = new String[teamPerson.length - 1][3];

		if (warmupCid != null)
			contestDAO.dml("delete from user_contest where cid=?", warmupCid);
		contestDAO.dml("delete from user_contest where cid=?", cid);

		// en el mapa es [personId, full name] en cada elemento de la lista
		for (int i = 1; i < person.length; i++) {
			String[] pArr = person[i].split("\t");
			persons[i - 1] = new String[] { pArr[0], pArr[3] + " " + pArr[4] };
		}

		// la llave es PersonId, el valor es un arreglo teamId en 0, y site Id
		// en pos 1
		int teamPersonsSize = 0,teamCoachesSize=0;
		for (int i = 1; i < teamPerson.length; i++) {
			String[] pArr = teamPerson[i].split("\t");
			if ("CONTESTANT".equals(pArr[3]))
				teamPersons[teamPersonsSize++] = new String[] { pArr[0], pArr[1], pArr[2] };
			if ("COACH".equals(pArr[3]))
				teamCoaches[teamCoachesSize++] = new String[] { pArr[0], pArr[1], pArr[2] };
		}

		// schools, llave es instId, valor es el nombre completo de la escuela
		for (int i = 1; i < school.length; i++) {
			String[] pArr = school[i].split("\t");
			schools[i - 1] = new String[] { pArr[0], pArr[2] };
		}

		// sites, llave es siteId, valor es el nombre
		for (int i = 1; i < site.length; i++) {
			String[] pArr = site[i].split("\t");
			sites[i - 1] = new String[] { pArr[0], pArr[1] };
		}

		// teams, llave es teamId, valor es un arreglo teamName, inst Id, siteId
		for (int i = 1; i < team.length; i++) {
			String[] pArr = team[i].split("\t");
			teams[i - 1] = new String[] { pArr[0], pArr[2], pArr[3], pArr[4] };
		}

		int digits = teams.length, digs = 0;
		while (digits > 0) {
			digits /= 10;
			digs++;
		}

		List<String> result = new ArrayList<String>();
		// ============================================================================
		for (int i = 0; i < teams.length; i++) {

			String[] user = new String[6];
			user[0] = prefix + String.format("%0" + digs + "d", i+1);
			String basePass = Utils.generateRandomPassword(8);
			user[1] = md5.encodePassword(basePass, "ABC123XYZ789");
			for (int m = 0; m < schools.length; m++)
				if (schools[m][0].equals(teams[i][2])) {
					user[2] = schools[m][1];
					user[3] = schools[m][1];
				}
			user[4] = teams[i][1];
			user[5] = teams[i][0];

			String[] teamUsers = new String[3];
			String teamCoach = new String();
			int k = 0;
			for (int j = 0; j < teamPersonsSize; j++) {

				if (teamPersons[j][1].equals(user[5])) {

					for (int l = 0; l < persons.length; l++){
						if (persons[l][0].equals(teamPersons[j][0]))
							teamUsers[k++] = persons[l][1];
					}
				}
			}
			for (int j = 0; j < teamCoachesSize; j++) {
				
				if (teamCoaches[j][1].equals(user[5])) {
					
					for (int l = 0; l < persons.length; l++){
						if (persons[l][0].equals(teamCoaches[j][0]))
							teamCoach = persons[l][1];
					}
				}
			}

			String cGroupd = "",groupTitle ="";
			for (int j = 0; j < sites.length; j++)
				if (sites[j][0].equals(teams[i][3])) {
					cGroupd = sites[j][0];
					groupTitle = sites[j][1];
				}

			contestDAO.insertData(groupTitle, teams[i], teamCoach,teamUsers, user, cid, warmupCid);

			if (!cGroupd.equals(groupd)) {
				result.add("\n========================");
				result.add("\nSite: " + groupTitle);
				groupd = cGroupd;
			}
			result.add("\nteam: " + user[4] + ", username: " + user[0] + ",password: " + basePass);
			result.add("\ncoach: " + teamCoach);
			result.add("\nmembers: " + teamUsers[0] + ", " + teamUsers[1] + ", " + teamUsers[2]);

		}

		return result;
	}
}








