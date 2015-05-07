package cu.uci.coj.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cu.uci.coj.dao.PollDAO;
import cu.uci.coj.model.Poll;

@Repository("pollDAO")
@Transactional
public class PollDAOImpl extends BaseDAOImpl implements PollDAO {

	@Override
	public void add(Poll poll) {
		dml("insert.poll",poll.getQuestion(),poll.getAnswer1(),poll.getAnswer2(),poll.getAnswer3(),poll.getAnswer4(),poll.getAnswer5(),poll.isEnabled());
	}

	@Override
	public void update(Poll poll) {
		dml("update.poll",
				poll.getQuestion(),poll.getAnswer1(),poll.getAnswer2(),poll.getAnswer3(),poll.getAnswer4(),poll.getAnswer5(),
				poll.isEnabled(),poll.getPid());
	}

	@Override
	public void delete(int pid) {
		dml("delete.poll.votes",pid);
		dml("delete.poll",pid);
	}

	@Override
	public void toggleEnable(int pid) {
		dml("toggle.enabled.poll",pid);
	}

	@Override
	public void vote(int pid, int uid, int option) {
		dml("vote.poll",pid,uid,option);
	}

	@Override
	public Poll get(int pid) {
		return object("select.poll",Poll.class,pid);
	}

	@Override
	public List<Poll> list() {
		return objects("select.polls",Poll.class);
	}
	
	@Override
	public List<Poll> listEnabled() {
		return objects("select.polls.public",Poll.class);
	}

	@Override
	public boolean voted(Integer uid, Integer pid) {
		return bool("exist.vote.poll",uid,pid);
	}

}
