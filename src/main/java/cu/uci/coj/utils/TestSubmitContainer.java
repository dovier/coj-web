package cu.uci.coj.utils;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import cu.uci.coj.model.SubmissionJudge;

@Component(value="testSubmit")
public class TestSubmitContainer {
	
	private AtomicInteger sidgen;
	
	private ConcurrentHashMap<Integer, SubmissionJudge> map;
	
	public TestSubmitContainer() {
		this.map = new ConcurrentHashMap<>();
		sidgen = new AtomicInteger(-1);
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

	public int getNextSid() {
		return sidgen.decrementAndGet();
	}
	
}
