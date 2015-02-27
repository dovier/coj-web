package cu.uci.coj.dao;

public interface AccountActivationDAO extends BaseDAO{
	public void purgeOldActivations();
}
