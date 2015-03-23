package cu.uci.coj.utils;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import cu.uci.coj.model.SubmissionJudge;

@Component(value="testSubmit")
public class TestSubmitContainer {
	private ConcurrentHashMap<Integer, SubmissionJudge> map;
	
	public TestSubmitContainer() {
		this.map = new ConcurrentHashMap<>();
	}

	public ConcurrentHashMap<Integer, SubmissionJudge> getMap() {
		return map;
	}

	public void setMap(ConcurrentHashMap<Integer, SubmissionJudge> map) {
		this.map = map;
	}

	public void add(SubmissionJudge submission) {
		map.put(submission.getUid(), submission);
	}
	
	public SubmissionJudge current(int uid) {
		return map.get(uid);
	}
	
}
