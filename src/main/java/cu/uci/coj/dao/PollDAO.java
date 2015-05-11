package cu.uci.coj.dao;

import java.util.List;

import cu.uci.coj.model.Poll;

public interface PollDAO extends BaseDAO{

	public void add(Poll poll);
	public void update(Poll poll);
	public void delete(int pid);
	public void toggleEnable(int pid);
	public void vote(int pid, int uid, int option);
	public Poll get(int pid);
	public List<Poll> list();
	public List<Poll> listEnabled();
	public boolean voted(Integer uid,Integer pid);
}
