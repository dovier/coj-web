package cu.uci.coj.dao;

import cu.uci.coj.model.Entry;

public interface EntryDAO extends BaseDAO {
	public void addEntry(Entry entry, boolean isAdmin, String username);

	public void updateEntry(Entry entry);

	public void enableEntry(int id, String username);

	public void disableEntry(int id);

	public void deleteEntry(int id);

	public void forward(int id, boolean isAdmin, String username);
}
