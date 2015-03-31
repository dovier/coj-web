package cu.uci.coj.dao.impl;

import org.springframework.stereotype.Repository;

import cu.uci.coj.dao.AccountActivationDAO;

@Repository("accountActivationDAO")
public class AccountActivationDAOImpl extends BaseDAOImpl implements AccountActivationDAO{
	
	public void purgeOldActivations(){
		dml("delete.old.activations");
		log("Purging old activations", "No user");
	}
	
}
