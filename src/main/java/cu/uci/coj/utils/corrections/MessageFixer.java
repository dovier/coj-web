package cu.uci.coj.utils.corrections;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

public class MessageFixer {

	// imprime una lista ordenada alfabeticamente y sin duplicados de las llaves
	// de los properties
	private static List<String> orderKeysSansDuplicates(String file, boolean print) throws Exception {
		Properties props = new Properties();
		props.load(new FileReader(file));

		Set<String> entries = new HashSet<>();

		List<String> keys = new ArrayList<>();
		for (Entry prop : props.entrySet()) {
			if (!entries.contains(prop)) {
				keys.add(prop.getKey().toString());
				entries.add(prop.getKey().toString());
			}
		}

		Collections.sort(keys);

		if (print)
			printProps(keys, props);

		return keys;
	}

	private static void printProps(List<String> keys, Properties props) {
		for (String key : keys) {
			System.out.println(key + "=" + props.getProperty(key));
		}

	}

	// llaves de las properties que no tienen uso
	private static void unusedKeys(String propFile, String... roots) throws Exception {

		Collection<File> files = new ArrayList<File>();
		for (String root : roots) {
			File file = new File(root);
			if (file.isDirectory()) {
				Collection<File> tempFiles = FileUtils.listFiles(file, TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
				files.addAll(tempFiles);
			} else
				files.add(file);
		}

		List<String> keys = orderKeysSansDuplicates(propFile, false);
		Set<String> keysFound = new HashSet<>();
		for (File file : files) {
			String fileContent = FileUtils.readFileToString(file);
			int i = 0;
			for (String key : keys) {
				if (fileContent.contains(key))
					keysFound.add(key);
			}
		}

		for (String key : keys)
			if (!keysFound.contains(key))
				System.out.println(key);
	}

	private static List<String> matchFmt(String content) {
		List<String> results = new ArrayList<>();
		Pattern pttn1 = Pattern.compile("(.*)<fmt:message key=\"(.*?)\".*/>.*");
		String[] ar = content.split("\n");

		for (String a : ar) {
			Matcher matcher1 = pttn1.matcher(a);
			matcher1.region(0, a.length());
			while (matcher1.matches()) {
				results.add(matcher1.group(2));
				matcher1.region(0, matcher1.end(1));
			}
		}

		Pattern pttn2 = Pattern.compile("(.*)<spring:message code=\"(.*?)\".*/>.*");
		ar = content.split("\n");

		for (String a : ar) {

			Matcher matcher2 = pttn2.matcher(a);
			matcher2.region(0, a.length());
			while (matcher2.matches()) {
				results.add(matcher2.group(2));
				matcher2.region(0, matcher2.end(1));
			}
		}

		return results;
	}

	// llaves que se usan pero no tienen valor en los properties
	private static void unvaluedKeys(String propFile, String... roots) throws Exception {
		Collection<File> files = new ArrayList<File>();
		for (String root : roots) {
			File file = new File(root);
			if (file.isDirectory()) {
				Collection<File> tempFiles = FileUtils.listFiles(file, TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
				files.addAll(tempFiles);
			} else
				files.add(file);
		}

		List<String> keys = orderKeysSansDuplicates(propFile, false);
		Set<String> keysFound = new HashSet<>();
		for (File file : files) {
			String fileContent = FileUtils.readFileToString(file);
			int i = 0;
			List<String> matchedKeys = matchFmt(fileContent);
			keysFound.addAll(matchedKeys);
		}

		for (String key : keysFound)
			if (!keys.contains(key))
				System.out.println(key);
	}

	public static void main(String[] args) throws Exception {

		// orderKeysSansDuplicates("D:/workspace/COJ/WebContent/WEB-INF/classes/messagess.properties",true);
		// orderKeysSansDuplicates("D:/workspace/COJ/WebContent/WEB-INF/classes/messages_es.properties",true);
		// orderKeysSansDuplicates("D:/workspace/COJ/WebContent/WEB-INF/classes/messages_pt.properties",true);
		 unvaluedKeys("/home/jasr/Documents/workspace-sts-3.6.1.RELEASE/CojMaven/coj/src/main/webapp/WEB-INF/classes/messages_en.properties",
				 "/home/jasr/Documents/workspace-sts-3.6.1.RELEASE/CojMaven/coj/src/main/webapp/WEB-INF/jsp",
				 "/home/jasr/Documents/workspace-sts-3.6.1.RELEASE/CojMaven/coj/src/main/webapp/WEB-INF/tiles");
	/*	unusedKeys("/home/jasr/Documents/workspace-sts-3.6.1.RELEASE/CojMaven/coj/src/main/webapp/WEB-INF/classes/messages_en.properties", 
				"/home/jasr/Documents/workspace-sts-3.6.1.RELEASE/CojMaven/coj/src/main/java/cu/uci/coj", "/home/jasr/Documents/workspace-sts-3.6.1.RELEASE/CojMaven/coj/src/main/webapp/WEB-INF/jsp",
				"/home/jasr/Documents/workspace-sts-3.6.1.RELEASE/CojMaven/coj/src/main/webapp/WEB-INF/tiles","/home/jasr/Documents/workspace-sts-3.6.1.RELEASE/CojMaven/coj/src/main/webapp/WEB-INF/tiles-defs.xml");
*/
	}
}
