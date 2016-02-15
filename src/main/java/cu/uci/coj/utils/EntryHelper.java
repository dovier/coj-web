package cu.uci.coj.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Component;

import cu.uci.coj.dao.EntryDAO;
import cu.uci.coj.model.Entry;

@Component("entryHelper")
public class EntryHelper {
	
	@Resource
	private EntryDAO entryDAO;
	
	private Map<String, String> emoties = new HashMap<String, String>();

	@PostConstruct
	public void init() {
		emoties.put(":(", "<i class=\"fa fa-frown-o\"></i>");
		emoties.put(":|", "<i class=\"fa fa-meh-o\"></i>");
		emoties.put(":)", "<i class=\"fa fa-smile-o\"></i>");
		/*
		 * Esto fue codigo necesario para cuando adicionamos los emoticones,
		 * reconvertir todas las entries anteriores de manera adecuada. Cuando
		 * se compruebe que los emoticones funcionan, hay que eliminarlo
		 * List<Entry> entries =
		 * entryDAO.objects("select e.eid as id,e.* from entries e",
		 * Entry.class); for (Entry e : entries) { preProcessEntry(e);
		 * entryDAO.updateEntry(e); }
		 */
	}
	
	public Map<String, String> getEmoties() {
		return emoties;
	}

	public void preProcessEntry(Entry entry) {
		// adicionar html para vinculos a paginas y a usuarios.
		String text = entry.getText();
		String[] tokens = text.split(" ");
		Set<String> tokenSet = new HashSet<String>(Arrays.asList(tokens));
		for (String token : tokenSet) {
			token = token.trim();
			if (token.startsWith("p#")) {
				if (NumberUtils.isNumber(token.substring(2))) {
					entryDAO.bool("select exists(select pid from problem where pid=?)", Integer.valueOf(token.substring(2)));
					text = text.replace(token, "<a target=\"new\" href=\"24h/problem.xhtml?pid=" + token.substring(2) + "\" >" + token.substring(2) + "</a>");
				}
			} else if (token.startsWith("c#")) {
				if (NumberUtils.isNumber(token.substring(2))) {
					entryDAO.bool("select exists(select pid from contest where pid=?)", Integer.valueOf(token.substring(2)));
					text = text.replace(token, "<a target=\"new\" href=\"contest/contestview.xhtml?cid=" + token.substring(1) + "\" >" + token.substring(1) + "</a>");
				}
			} else if (token.startsWith("@")) {
				boolean reply = entryDAO.bool("select exists (select uid from users where username=?)", token.substring(1));
				entry.setReply(true);
				entry.setHasUsers(true);
				if (reply)
					text = text.replace(token, "<a target=\"new\" href=\"user/useraccount.xhtml?username=" + token.substring(1) + "\" >" + token + "</a>");
			} else if (token.startsWith("http://")) {
				text = text.replace(token, "<a target=\"new\" href=\"" + token + "\">" + token + "</a>");
				entry.setHasLinks(true);
			} else if (emoties.keySet().contains(token)) {
				text = text.replace(token, emoties.get(token));
			}
		}
		entry.setText(text);
	}
	
}
