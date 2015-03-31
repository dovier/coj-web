package cu.uci.coj.dao.impl;

import java.util.Date;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cu.uci.coj.dao.EntryDAO;
import cu.uci.coj.model.Entry;

@Repository("EntryDAO")
@Transactional
public class EntryDAOImpl extends BaseDAOImpl implements EntryDAO {

	public void deleteEntry(int id) {
		dml("delete.entry.rate", id);
		dml("delete.entries", id);
	}

	public void addEntry(Entry entry, boolean isAdmin, String username) {
		if (isAdmin)
			dml("add.auth.entry", entry.getText(), idByUsername(username), new Date());
		else
			dml("add.entry", entry.getText(), idByUsername(username), new Date());
	}
	
	public void updateEntry(Entry entry) {
		dml("update.entries", entry.getText(),entry.getId());
	}

	@Override
	public void enableEntry(int id, String username) {
		dml("admin.enable.entry", id);
		log("authorizing entry " + id, username);
	}

	@Override
	public void forward(int id, boolean isAdmin,String username) {
		Entry entry = object("entry.by.id", Entry.class, id);
		entry.setForward(true);
		addEntry(entry, isAdmin,username);
	}

	@Override
	public void disableEntry(int id) {
		dml("admin.disable.entry", id);
	}

}
