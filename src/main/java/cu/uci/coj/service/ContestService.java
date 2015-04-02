package cu.uci.coj.service;

import java.util.List;

public interface ContestService {

	public List<String> importICPCUsers(String prefix, String[] person, String[] school, String[] site, String[] team, String[] teamPerson, Integer cid, Integer warmupCid);
}
