package cu.uci.coj.dao.impl;

import cu.uci.coj.dao.PollDAO;
import cu.uci.coj.model.Poll;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cu.uci.coj.dao.PollDAO;
import cu.uci.coj.model.Poll;

@Repository("pollDAO")
@Transactional
public class PollDAOImpl
  extends BaseDAOImpl
  implements PollDAO
{
  public void add(Poll poll)
  {
    dml("insert.poll", new Object[] { poll.getQuestion(), poll.getAnswer1(), 
      poll.getAnswer2(), poll.getAnswer3(), poll.getAnswer4(), 
      poll.getAnswer5(), Boolean.valueOf(poll.isEnabled()) });
	}

  public void update(Poll poll)
  {
    dml("update.poll", new Object[] { poll.getQuestion(), poll.getAnswer1(), 
      poll.getAnswer2(), poll.getAnswer3(), poll.getAnswer4(), 
      poll.getAnswer5(), Boolean.valueOf(poll.isEnabled()), poll.getPid() });
	}

  public void delete(int pid)
  {
    dml("delete.poll.votes", new Object[] { Integer.valueOf(pid) });
    dml("delete.poll", new Object[] { Integer.valueOf(pid) });
	}

  public void toggleEnable(int pid)
  {
    dml("toggle.enabled.poll", new Object[] { Integer.valueOf(pid) });
	}

  public void vote(int pid, int uid, int option)
  {
    dml("vote.poll", new Object[] { Integer.valueOf(pid), Integer.valueOf(uid), Integer.valueOf(option) });
	}

  public Poll get(int pid)
  {
    return (Poll)object("select.poll", Poll.class, new Object[] { Integer.valueOf(pid) });
	}

  public List<Poll> list()
  {
    return objects("select.polls", Poll.class, new Object[0]);
	}
	
  public List<Poll> listEnabled(Integer uid)
  {
    if (uid == null) {
      return objects("select.polls.public", Poll.class, new Object[0]);
	}
    return objects("select.polls.public.voted", Poll.class, new Object[] { uid });
	}

  public boolean voted(Integer uid, Integer pid)
  {
    return bool("exist.vote.poll", new Object[] { uid, pid }).booleanValue();
}
}
