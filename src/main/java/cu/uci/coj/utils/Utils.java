package cu.uci.coj.utils;

import cu.uci.coj.adapters.SubmissionJudgeToSubmissionDTOAdapter;
import cu.uci.coj.config.Config;
import cu.uci.coj.dao.ContestDAO;
import cu.uci.coj.dao.SubmissionDAO;
import cu.uci.coj.exceptions.PlagiCOJUnsupportedLanguageException;
import cu.uci.coj.model.DatagenDataset;
import cu.uci.coj.model.Filter;
import cu.uci.coj.model.Language;
import cu.uci.coj.model.Stats;
import cu.uci.coj.model.SubmissionJudge;
import cu.uci.coj.plagicoj.lexers.PlagiCOJCLexer;
import cu.uci.coj.plagicoj.lexers.PlagiCOJCSharpLexer;
import cu.uci.coj.plagicoj.lexers.PlagiCOJCppLexer;
import cu.uci.coj.plagicoj.lexers.PlagiCOJJavaLexer;
import cu.uci.coj.plagicoj.lexers.PlagiCOJPascalLexer;
import cu.uci.coj.plagicoj.lexers.PlagiCOJPythonLexer;
import cu.uci.coj.utils.paging.PagingOptions;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Semaphore;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.Lexer;
import org.antlr.runtime.Token;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class Utils {

	@Autowired
	private static RabbitTemplate submitTemplate;
	@Autowired
	private RabbitAdmin rabbitAdmin;
	@Resource
	private SubmissionDAO submissionDAO;
	@Resource
	private ContestDAO contestDAO;

	@Autowired
	void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
		submitTemplate = rabbitTemplate;
	}

	public boolean supportExtension(String filename) {
		String[] extensions = ".c,.cpp,.java,.py,.cs,.pl,.pas,.php,.rb,.txt,.js"
				.split(",");
		for (int i = 0; i < extensions.length; i++) {
			String string = extensions[i];
			if (filename.endsWith(string)) {
				return true;
			}
		}
		return false;
	}

	public Integer getQueuedSubmitsCount() {
		Properties props = rabbitAdmin.getQueueProperties("submit.queue");
		return Integer.parseInt(props.get("QUEUE_MESSAGE_COUNT").toString());
	}

	@Async
	public void rejudgeSubmits(Filter filter) {

		PagingOptions options = new PagingOptions();
		List<SubmissionJudge> submissions = submissionDAO.rejudgeSubmits(
				filter, options);
		while (!CollectionUtils.isEmpty(submissions)) {
			for (SubmissionJudge submission : submissions) {
				if (filter.getCid() != null && filter.getCid() != 0)
					rejudgeContestSubmit(submission.getSid());
				else
					rejudgeSubmit(submission.getSid());
			}
			options.setPage(options.getPage() + 1);
			submissions = submissionDAO.rejudgeSubmits(filter, options);
		}

	}

	public SubmissionJudge rejudgeContestSubmit(int sid) {
		SubmissionJudge submit = submissionDAO
				.object("select.contest.submit.for.removal",
						SubmissionJudge.class, sid);
		if (submit != null) {
                    int priority = 4;
                    startCalification(submit,priority);
		}
		return submit;
	}

	public SubmissionJudge rejudgeSubmit(int sid) {
		SubmissionJudge submit = submissionDAO.object(
				"select.submit.for.removal", SubmissionJudge.class, sid);
		if (submit != null) {
			submissionDAO.removeEffects(submit);
                        int priority = 2;
			startCalification(submit,priority);
		}
		return submit;
	}

	public static final String[] colorPerDictum = { "#00FF00", "#00FF55",
			"#00FFAA", "#00FFFF", "#0080FF", "#0000FF", "#8000FF", "#FF00FF",
			"#FF0080", "#FF0000" };
	private static HashMap<String, LinkedList<String>> charge;
	private String registerMSG = "Dear <user> ";
	public static HashMap<String, Float> statistics;
	public static HashMap<String, Float> cstatistics;
	public static HashMap<String, String> ip_url;
	public static HashMap<String, String> onlineusers;
	public static HashMap<String, String> sessionIdUsers;
	public static LinkedList<SubmissionJudge> submissions = new LinkedList<SubmissionJudge>();
	private static Semaphore sem = new Semaphore(1);
	public static Semaphore semJudge = new Semaphore(1);
	public static Semaphore semPool = new Semaphore(1);
	public static boolean is_judge_running = false;
	private static COJEvalImpl cojEvalImpl = new COJEvalImpl();

	public static String getLanguageByLid(List<Language> languages, String key) {
		for (Iterator<Language> it = languages.iterator(); it.hasNext();) {
			Language lang = it.next();
			if (lang.getKey().equals(key)) {
				return lang.getLanguage();
			}
		}
		return "C";
	}

	public void setCOJEvalImpl(COJEvalImpl cojEvalImpl) {
		this.cojEvalImpl = cojEvalImpl;
	}

	public static String generateOutput(DatagenDataset dataset) {
		return cojEvalImpl.generateOutput(dataset);
	}

	public static String generateInput(DatagenDataset dataset) {
		dataset.setInput(null);
		return cojEvalImpl.generateOutput(dataset);
	}

	/*
	 * public static String generateOutput(DatagenDataset dataset,
	 * SubmissionJudge submission) { String output = null; if
	 * (StringUtils.isEmpty(dataset.getCode())) { output =
	 * cojEvalImpl.generateOutput(submission); }
	 * 
	 * return output; }
	 */

	public static String generateRandomPassword(int length) {
		String password = "";
		for (int i = 0; i < length; i++) {
			Random r = new Random();
			password += (char) ('A' + r.nextInt(26));
		}
		password = password.toLowerCase();
		return password;
	}

	public static double formulaFreeContest(double ac, double points) {
		if (ac == 0.0) {
			return points;
		}
		return points / ac;
	}

	public double formulaProgressive(double points, int mxlevel, int prbmlevel) {
		return points / (double) (mxlevel - prbmlevel + 1);
	}

	
        public void startCalification(SubmissionJudge submit) {
            SubmissionJudgeToSubmissionDTOAdapter submission = new SubmissionJudgeToSubmissionDTOAdapter(submit);
            submitTemplate.convertAndSend(submission);
        }

         public void startCalification(SubmissionJudge submit,final int priority) {
            SubmissionJudgeToSubmissionDTOAdapter submission = new SubmissionJudgeToSubmissionDTOAdapter(submit);
            submitTemplate.convertAndSend(submission, new MessagePostProcessor() {
                    @Override
                    public Message postProcessMessage(Message message) throws AmqpException {
                        message.getMessageProperties().setPriority(priority);
                        return message;
                    }
                });
        }
        
        public void startCalification(List<SubmissionJudge> submits) {
            for (SubmissionJudge submit : submits) {
                startCalification(submit);
            }
        }
        
        public void startCalification(List<SubmissionJudge> submits, final int priority) {
            for (SubmissionJudge submit : submits) {
                startCalification(submit, priority);
            }
        }

        public void startAndWaitForCalification(SubmissionJudge submit) {
            SubmissionJudgeToSubmissionDTOAdapter submission = new SubmissionJudgeToSubmissionDTOAdapter(submit);
            submitTemplate.convertSendAndReceive(submission);
        }


	public static boolean isONline(String username) {
		if (onlineusers != null) {
			if (onlineusers.containsKey(username)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Reporta el nombre sin extension de los juegos de datos validos (o sea los
	 * que son pares de archivos .in/.out correspondientes)
	 * 
	 * @param pid
	 * @return una lista ordenada con los nombres de los datasets validos para
	 *         el problema
	 */
	public List<String> getDatasetsNames(int pid) {
		File problemFile = new File(Config.getProperty("problems.directory"),
				String.valueOf(pid));

		List<String> result = new ArrayList<>();
		File[] list = problemFile.listFiles(new FilenameFilter() {

			@Override
			public boolean accept(File arg0, String arg1) {
				return arg1.endsWith(".in");
			}
		});
		String name = null;
		for (int i = 0; i < list.length; i++) {
			File file = list[i];
			name = file.getName().split(".in$")[0];
			Path pathIn = problemFile.toPath().resolve(Paths.get(name + ".in"));
			Path pathOut = problemFile.toPath().resolve(
					Paths.get(name + ".out"));
			if (Files.exists(pathIn) && Files.exists(pathOut)) {
				result.add(name);
			}
		}
		Collections.sort(result);
		return result;
	}

	/**
	 * Reporta las fotos del contest (se asume extension png)
	 * 
	 * @param cid
	 * @return una lista ordenada de fotos del contest
	 */
	public List<String> getContestImages(int cid) {
		File contestFile = new File(Config.getProperty("base.contest.images"),
				String.valueOf(cid));

		List<String> result = new ArrayList<>();
		File[] list = contestFile.listFiles(new FilenameFilter() {

			@Override
			public boolean accept(File arg0, String arg1) {
				return arg1.toLowerCase().endsWith(".png")
						|| arg1.toLowerCase().endsWith(".jpg");
			}
		});

		if (!ArrayUtils.isEmpty(list))
			for (int i = 0; i < list.length; i++) {
				result.add(list[i].getName());
			}
		return result;
	}

	public Set<String> getDatasets(int pid) {
		File problemFile = new File(Config.getProperty("problems.directory"),
				String.valueOf(pid));

		Set<String> result = new HashSet<>();
		File[] list = problemFile.listFiles();
		for (int i = 0; i < list.length; i++) {
			File file = list[i];
			if (file.isFile()
					&& (file.getName().endsWith(".out") || file.getName()
							.endsWith(".in")))
				result.add(file.getName());
		}

		return result;
	}

	public void removeDatasets(int pid) {
		try {
			Set<String> datasets = getDatasets(pid);
			for (String dataset : datasets) {
				Files.deleteIfExists(Paths.get(
						Config.getProperty("problems.directory"),
						String.valueOf(pid)).resolve(dataset + ".in"));
				Files.deleteIfExists(Paths.get(
						Config.getProperty("problems.directory"),
						String.valueOf(pid)).resolve(dataset + ".out"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void removeDataset(int pid, String dataset) {
		try {
			Files.deleteIfExists(Paths.get(
					Config.getProperty("problems.directory"),
					String.valueOf(pid)).resolve(dataset + ".in"));
			Files.deleteIfExists(Paths.get(
					Config.getProperty("problems.directory"),
					String.valueOf(pid)).resolve(dataset + ".out"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void removeImage(int cid, String image) {
		try {
			Files.deleteIfExists(Paths.get(
					Config.getProperty("base.contest.images"),
					String.valueOf(cid)).resolve(image));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Para eliminar el caracter \r\n por \n a traves del dos2unix. Necesario ya
	 * que si el juego de datos es producido en una maquina de Windows, tiene un
	 * caracter extra que puede dar al traste con la entrada en algunos
	 * lenguajes o funciones. Tambien elimina los archivos in/out que no tengan
	 * pareja
	 * 
	 * @param problem
	 */
	public void normalizeProblem(int pid) {
		File problemFile = new File(Config.getProperty("problems.directory"),
				String.valueOf(pid));

		Set<String> datasets = getDatasets(pid);

		String[] exts = new String[] { ".in", ".out" };
		boolean inExt = true;
		boolean needReload = false;
		for (String fullname : datasets) {
			inExt = fullname.endsWith(".in");
			String name = fullname.split(exts[inExt ? 0 : 1] + "$")[0];
			if (!datasets.contains(name + exts[inExt ? 1 : 0])) {
				try {
					Files.delete(problemFile.toPath().resolve(fullname));
					needReload = true;
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else
				dos2Unix(problemFile.toPath().resolve(fullname)
						.toAbsolutePath());
		}

		if (needReload)
			datasets = getDatasets(pid);
		// para los datasets restantes, se le quitan los espacios en blanco y se
		// sustituyen por .
		for (String dataset : datasets) {
			new File(problemFile, dataset).renameTo(new File(problemFile,
					dataset.replace(" ", ".")));
		}
	}

	public static void dos2Unix(Path path) {
		try {
			ProcessBuilder process = new ProcessBuilder();
			process.command("dos2unix", path.toAbsolutePath().toString());
			process.start().waitFor();

		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
	}

	public static void putUser(String username, String sessionId) {
		if (onlineusers == null) {
			onlineusers = new HashMap<String, String>();
		}
		onlineusers.put(username, username);
		if (sessionIdUsers == null) {
			sessionIdUsers = new HashMap<String, String>();
		}
		sessionIdUsers.put(sessionId, username);
	}

	public static void removeUser(String sessionID) {
		if (sessionIdUsers != null && onlineusers != null) {
			if (sessionIdUsers.containsKey(sessionID)
					&& onlineusers.containsKey(sessionIdUsers.get(sessionID))) {
				onlineusers.remove(sessionIdUsers.get(sessionID));
				sessionIdUsers.remove(sessionID);
			}
		}
	}

	public static void addIpUrl(HttpServletRequest request) {
		try {
			if (request.getQueryString() == null) {
				// ip_url.put(request.getRequestedSessionId(),
				// request.getRequestURI());
				request.setAttribute("rurl", request.getRequestURI());
			} else {
				// ip_url.put(request.getRequestedSessionId(),
				// request.getRequestURI() + "?" + request.getQueryString());
				request.setAttribute("rurl", request.getRequestURI() + "?"
						+ request.getQueryString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String addParameter(String url, String parameter) {
		url = url.replace("?lang=en", "");
		url = url.replace("&lang=en", "");
		url = url.replace("?lang=es", "");
		url = url.replace("&lang=es", "");
		if (url.contains("?")) {
			url += "&" + parameter;
		} else {
			url += "?" + parameter;
		}
		return url;
	}

	public static String getUrl(String ip) {
		if (ip_url.containsKey(ip)) {
			String uri = ip_url.get(ip);
			uri = uri.replace("/chjudge", "");
			return uri;
		}
		return "/";
	}

	public static void loadStatistics(Stats stats) {
		statistics = new HashMap<String, Float>();
		statistics.put("ACC", stats.getAcc_percent());
		statistics.put("WA", stats.getWa_percent());
		statistics.put("PE", stats.getPe_percent());
		statistics.put("TLE", stats.getTle_percent());
		statistics.put("MLE", stats.getMle_percent());
		statistics.put("FLE", stats.getFle_percent());
		statistics.put("OLE", stats.getOle_percent());
		statistics.put("CE", stats.getCe_percent());
		statistics.put("RTE", stats.getRte_percent());
		statistics.put("UQ", stats.getUq_percent());
		statistics.put("IVF", stats.getIvf_percent());
	}

	public static void loadCStatistics(Stats stats) {
		cstatistics = new HashMap<String, Float>();
		cstatistics.put("ACC", stats.getAcc_percent());
		cstatistics.put("WA", stats.getWa_percent());
		cstatistics.put("PE", stats.getPe_percent());
		cstatistics.put("TLE", stats.getTle_percent());
		cstatistics.put("MLE", stats.getMle_percent());
		cstatistics.put("FLE", stats.getFle_percent());
		cstatistics.put("OLE", stats.getOle_percent());
		cstatistics.put("CE", stats.getCe_percent());
		cstatistics.put("RTE", stats.getRte_percent());
		cstatistics.put("UQ", stats.getUq_percent());
		cstatistics.put("IVF", stats.getIvf_percent());
	}

	public static void removeId(String id) {
		charge.remove(id);
	}

	public static void removeView(String id, String view) {
		LinkedList<String> list = charge.get(id);
		list.remove(view);
		charge.put(id, list);
	}

	public static void Mark(String id, String view) {
		if (charge == null) {
			charge = new HashMap<String, LinkedList<String>>();
		}
		if (charge.size() > 150) {
			charge.clear();
		}
		if (charge.containsKey(id)) {
			LinkedList<String> list = charge.get(id);
			list.add(view);
			charge.put(id, list);

		} else {
			LinkedList<String> list = new LinkedList<String>();
			list.add(view);
			charge.put(id, list);

		}

	}

	public static boolean checkFlush(String id, String view) {
		if (charge == null) {
			return false;
		}
		if (charge.containsKey(id)) {
			LinkedList<String> list = charge.get(id);
			if (list.contains(view)) {
				return true;
			}
			return false;
		} else {
			return false;
		}
	}

	public static LinkedList<String> getCommandList(String cmdline) {
		String[] array = cmdline.split(" ");
		LinkedList<String> list = new LinkedList<String>();
		for (int i = 0; i < array.length; i++) {
			String string = array[i];
			list.add(string);
		}
		return list;
	}

	public static String Cript(String pass) {
		int stop = 0;
		String cryptpass = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(pass.getBytes());
			byte[] convert = md.digest();

			for (int i = 0; i < convert.length; i++) {
				cryptpass += convert[i];

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println(cryptpass);
		return cryptpass;
	}

	public static double formula(int ac) {
		return (double) Integer.valueOf(Config.getProperty("formula.value"))
				/ (double) (40 + ac);
	}

	public static List<Token> getTokens(String sourceCode, String language)
			throws Exception {
		Lexer lexer = null;
		if (language.compareTo("C++") == 0) {
			lexer = new PlagiCOJCppLexer(new ANTLRStringStream(sourceCode));
		} else if (language.compareTo("Java") == 0) {
			lexer = new PlagiCOJJavaLexer(new ANTLRStringStream(sourceCode));
		} else if (language.compareTo("C#") == 0) {
			lexer = new PlagiCOJCSharpLexer(new ANTLRStringStream(sourceCode));
		} else if (language.compareTo("C") == 0) {
			lexer = new PlagiCOJCLexer(new ANTLRStringStream(sourceCode));
		} else if (language.compareTo("Python") == 0) {
			lexer = new PlagiCOJPythonLexer(new ANTLRStringStream(sourceCode));
		} else if (language.compareTo("Pascal") == 0) {
			lexer = new PlagiCOJPascalLexer(new ANTLRStringStream(sourceCode));
		} else {
			throw new PlagiCOJUnsupportedLanguageException();
		}
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		return tokens.getTokens();
	}

	public static Utils getInstance() {
		return UtilsHolder.INSTANCE;
	}

	private static class UtilsHolder {

		private static final Utils INSTANCE = new Utils();
	}
}
