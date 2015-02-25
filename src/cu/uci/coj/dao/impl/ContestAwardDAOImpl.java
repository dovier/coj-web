package cu.uci.coj.dao.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import cu.uci.coj.dao.ContestAwardDAO;
import cu.uci.coj.model.ContestAwards;
import cu.uci.coj.model.ContestAwardsFlags;

@Repository("contestAwardDAO")
@Transactional
public class ContestAwardDAOImpl extends BaseDAOImpl implements ContestAwardDAO {

	private String issueAward(String sql, Integer cid, Integer aid) {
		String username = string(sql, cid);
		if (!StringUtils.isEmpty(username)) {
			Integer uid = integer("select.uid.by.username", username);
			dml("set.contest.award", uid, cid, aid);
			return username;
		} else
			return "None";
	}

	private String issueFastAward(String sql, Integer cid, Integer aid) {
		String username = string(sql, cid, cid, cid, cid, cid, cid, cid, cid, cid, cid, cid, cid);
		if (!StringUtils.isEmpty(username)) {
			Integer uid = integer("select.uid.by.username", username);
			dml("set.contest.award", uid, cid, aid);
			return username;
		} else
			return "None";
	}

	private String issueExclusiveAward(String username, Integer cid) {
		if (!StringUtils.isEmpty(username)) {
			Integer uid = integer("select.uid.by.username", username);
			dml("set.contest.award", uid, cid, ContestAwardsFlags.EXCLUSIVE_AWARD);
			return username;
		} else
			return "None";
	}

	public void insertContestAwardsFlags(ContestAwardsFlags contestAwardsFlags) {

		int cid = contestAwardsFlags.getCid();
		dml("delete.contest.awards", cid);
		if (contestAwardsFlags.isAccurate())
			dml("insert.contest.award", ContestAwardsFlags.ACCURATE_AWARD, cid);
		if (contestAwardsFlags.isFast())
			dml("insert.contest.award", ContestAwardsFlags.FAST_AWARD, cid);
		if (contestAwardsFlags.isExclusive())
			dml("insert.contest.award", ContestAwardsFlags.EXCLUSIVE_AWARD, cid);

		if (bool("is.past.contest", cid))
			issueAwards(cid);
	}

	public ContestAwardsFlags loadContestAwardsFlags(Integer cid) {
		List<Integer> aids = integers("load.contest.awards.id", cid);
		ContestAwardsFlags flags = new ContestAwardsFlags(cid, aids);
		return flags;
	}

	public List<ContestAwards> loadAwards(Integer cid) {
		return objects("load.issued.contest.awards", ContestAwards.class, cid);
	}

	@Override
	public void issueAwards(Integer cid) {
		if (bool("contest.awards.not.issued", cid)) {
			ContestAwardsFlags flags = loadContestAwardsFlags(cid);
			if (flags.isFast())
				issueFastAward("fast.award", cid, ContestAwardsFlags.FAST_AWARD);
			if (flags.isAccurate())
				issueAward("accurate.award", cid, ContestAwardsFlags.ACCURATE_AWARD);
			if (flags.isExclusive()) {
				// la matriz de competidores x problemas
				List<Map<String, Object>> mapas = maps("exclusive.award", cid);
				// si hay competidores y hay problemas
				if (!CollectionUtils.isEmpty(mapas) && mapas.get(0).size() > 0) {
					int[] problems = new int[mapas.get(0).size()];
					// primer usuario que resuelve el problema. si es exclusivo,
					// entonces es el unico usuario.
					int[] users = new int[mapas.get(0).size()];
					// el tiempo que demoro cada usuario en resolver el problema
					// exclusivo. Es importante para los desempates
					int[] penalty = new int[mapas.get(0).size()];
					Arrays.fill(penalty, Integer.MAX_VALUE);

					for (int j = 0; j < mapas.get(0).size() - 1; j++) {
						for (int k = 0; k < mapas.size(); k++) {
							int time = ((Integer) mapas.get(k).get(String.valueOf((char) (j + 97))));
							if (time != 0) {
								problems[j]++;
								// me voy quedando con el usuario que resolvio
								// el problema j en el menor tiempo.
								if (penalty[j] > time) {
									users[j] = k;
									penalty[j] = time;
								}
							}
						}
					}

					// contamos la cantidad de problemas resueltos por un solo
					// equipo, y los agrupamos en buckets por el nombre del
					// equipo que los resolvio.
					// De esta forma sabemos cuantos equipos resolvieron uno o
					// mas problemas de forma exclusiva, y cuales son esos
					// equipos.
					int[] usersCount = new int[mapas.size()];
					int[] usersPenalty = new int[mapas.size()];
					boolean desertedFlag = true;
					for (int k = 0; k < problems.length; k++) {
						if (problems[k] == 1) {
							desertedFlag = false;
							usersCount[users[k]]++;
							usersPenalty[users[k]] = penalty[k];
						}
					}

					//desertedFlag es falsa si alguien resolvio un problema que nadie mas resolvio. Si esta en true, nadie merece este premio (no issue)
					if (!desertedFlag) {
						int maxCount = 0;
						for (int i = 0; i < usersCount.length; i++) {
							if (usersCount[maxCount] < usersCount[i])
								maxCount = i;
							else if (usersCount[maxCount] == usersCount[i] && usersPenalty[maxCount] > usersPenalty[i])
								maxCount = i;
						}
						issueExclusiveAward(mapas.get(maxCount).get("username").toString(), cid);
					}

				}
			}
		}
	}
}
