package cu.uci.coj.controller.admin;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cu.uci.coj.utils.Notification;
import org.apache.commons.io.FilenameUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import cu.uci.coj.config.Config;
import cu.uci.coj.controller.BaseController;
import cu.uci.coj.utils.FileUtils;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Scope(value = "session")
@RequestMapping(value = "/admin/files")
public class FileSystemController extends BaseController {

	private File baseDir;
	private File publicDir;
	private File currentDir;
	private String problemDir;

	private Set<File> cutFiles;
	private Set<File> copiedFiles;

    private String STRING_PATTERN_CL = "[a-zA-Z0-9\\s]+";
    private Pattern pattern;
    private Matcher matcher;

	@PostConstruct
	public void init() {
		baseDir = new File(Config.getProperty("base.dir") + "/");
		publicDir = new File(Config.getProperty("base.public.dir"));
		currentDir = new File(Config.getProperty("base.dir"));
		problemDir = Config.getProperty("problems.directory");
		cutFiles = new HashSet<>();
		copiedFiles = new HashSet<>();
	}

	private File current(String dir) throws Exception {
		return new File(currentDir, new String(dir.getBytes(), "UTF-8"));
	}

	@RequestMapping(value = "/list.xhtml", method = RequestMethod.GET)
	public String download(
			Model model,
			@RequestParam(required = false, defaultValue = "", value = "f") String dir,
			@RequestParam(required = false, defaultValue = "false", value = "b") boolean back)
			throws Exception {

		File file = null;
		if (!(baseDir.equals(currentDir)) && back)
			file = currentDir = new File(currentDir.getAbsolutePath()
					.substring(
							0,
							FilenameUtils.indexOfLastSeparator(currentDir
									.getAbsolutePath())));
		else
			file = current(dir);

		model.addAttribute("downloadable",
				file.getAbsolutePath().startsWith(publicDir.getAbsolutePath()));
		// si es un directorio, se lista
		if (file.isDirectory()) {
			currentDir = file;
			File[] files = file.listFiles();
			boolean[] downloadables = new boolean[files.length];
			int idx = 0;
			for (File f : files)
				if (f.getAbsolutePath().startsWith(publicDir.getAbsolutePath())
						&& !f.getAbsolutePath().equals(
								publicDir.getAbsolutePath()))
					downloadables[idx++] = baseDAO.bool(
							"is.shared.file",
							f.getAbsolutePath().substring(
									publicDir.getAbsolutePath().length() + 1));

			model.addAttribute("downloadables", downloadables);
			model.addAttribute("files", files);
			model.addAttribute("copiedFiles", copiedFiles);
			model.addAttribute("cutFiles", cutFiles);
			model.addAttribute("back", !(baseDir.equals(currentDir)));
			model.addAttribute("currentDir", currentDir.getAbsolutePath()
					.substring(baseDir.getAbsolutePath().length()));
			return "/admin/files";
		}
		return null;
	}

	@RequestMapping(value = "/share.xhtml", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void share(HttpServletResponse response,
			@RequestParam("file") String file) throws Exception {
		baseDAO.dml("insert.shared.file", file, current(file).getAbsolutePath()
				.substring(publicDir.getAbsolutePath().length() + 1));
	}

	@RequestMapping(value = "/unshare.xhtml", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void unshare(HttpServletResponse response,
			@RequestParam("file") String file) throws Exception {
		doUnshare(file);
	}

	private void doUnshare(String file) throws Exception {
		baseDAO.dml("delete.shared.file", current(file).getAbsolutePath()
				.substring(publicDir.getAbsolutePath().length() + 1));
	}

	@RequestMapping(value = "/download.xhtml", method = RequestMethod.GET)
	public void download(
			Model model,
			HttpServletResponse response,
			@RequestParam(required = false, defaultValue = "/", value = "f") String dir)
			throws Exception {
		File file = current(dir);
		doDownload(response, file,dir);
	}

	@RequestMapping(value = "/{problem}/{ext}/{id}.xhtml", method = RequestMethod.GET)
	public void dataset(Model model, HttpServletResponse response,
			@PathVariable("problem") String problem,
			@PathVariable("ext") String ext, @PathVariable("id") String id)
			throws Exception {
		String dir = problem + "/" + id + "." + ext;
		doDownload(response, new File(problemDir,dir),dir);
	}

	private void doDownload(HttpServletResponse response, File file,String dir)
			throws Exception {

		response.setHeader("Content-Disposition", "attachment; filename=\""
				+ dir);
		// si es un archivo, se descarga
		if (!file.isDirectory())
			FileUtils.redirectStreams(new FileInputStream(file),
					response.getOutputStream());
	}

    @RequestMapping(value = "/errorFileRedirectPage.xhtml", method = RequestMethod.GET)
    public String errorRedirectPage(HttpServletRequest request, Model model)
    {
        model.addAttribute("sizeexceeded", true);
        return "/admin/files";
    }

	@RequestMapping(value = "/list.xhtml", method = RequestMethod.POST)
	public String upload(Model model, MultipartFile file, String[] files,
                         String folder, RedirectAttributes redirectAttributes) throws Exception {

		int cont = 0;

        pattern = Pattern.compile(STRING_PATTERN_CL);
        matcher = pattern.matcher(folder);

        if (matcher.matches()) {
            File folderToSave = new File(currentDir, folder);
            folderToSave.mkdir();
            cont++;
        }

        /*if (StringUtils.hasText(folder)) {
			// no lleva codificacion UTF-8
			File folderToSave = new File(currentDir, folder);
			folderToSave.mkdir();
		}*/

		if (!file.isEmpty()) {
			File fileToSave = current(file.getOriginalFilename());
			file.transferTo(fileToSave);
            cont++;
		}

        if (cont == 0){
            /*se redirecciona y se envia un error de ambos campos estan vacios*/
            redirectAttributes.addFlashAttribute("message", Notification.getNotSuccesfull());
            redirectAttributes.addFlashAttribute("anerror", true);
            return "redirect:/admin/files/list.xhtml";
        }

        redirectAttributes.addFlashAttribute("message", Notification.getSuccesfullCreate());
		return "redirect:/admin/files/list.xhtml";
	}

	@RequestMapping(value = "/cut.xhtml", method = RequestMethod.GET)
	public String cut(Model model, @RequestParam(value = "f") String dir, RedirectAttributes redirectAttributes)
			throws Exception {

		File file = current(dir);

		copiedFiles.clear();
		cutFiles.add(file);
        redirectAttributes.addFlashAttribute("message", Notification.getSuccesfull());
		return "redirect:/admin/files/list.xhtml";
	}

	@RequestMapping(value = "/copy.xhtml", method = RequestMethod.GET)
	public String copy(Model model, @RequestParam(value = "f") String dir, RedirectAttributes redirectAttributes)
			throws Exception {

		File file = current(dir);

		cutFiles.clear();
		copiedFiles.add(file);
        redirectAttributes.addFlashAttribute("message", Notification.getSuccesfull());
		return "redirect:/admin/files/list.xhtml";
	}

	@RequestMapping(value = "/paste.xhtml", method = RequestMethod.GET)
	public String paste(
            Model model,
            @RequestParam(required = false, defaultValue = "/", value = "f") String dir, RedirectAttributes redirectAttributes)
			throws Exception {

		File file = current(dir);
		if (file.isDirectory()) {
			for (File cut : cutFiles) {
				if (cut.isDirectory()) {
					org.apache.commons.io.FileUtils.copyDirectory(cut, file);
					org.apache.commons.io.FileUtils.deleteDirectory(cut);
				} else {
					org.apache.commons.io.FileUtils.copyFileToDirectory(cut,
							file);
					org.apache.commons.io.FileUtils.deleteQuietly(cut);
				}
			}
			for (File copy : copiedFiles) {
				if (copy.isDirectory()) {
					org.apache.commons.io.FileUtils.copyDirectory(copy, file);
				} else {
					org.apache.commons.io.FileUtils.copyFileToDirectory(copy,
							file);
				}
			}
			cutFiles.clear();
			copiedFiles.clear();
		}
        redirectAttributes.addFlashAttribute("message", Notification.getSuccesfull());
		return "redirect:/admin/files/list.xhtml";
	}

	@RequestMapping(value = "/clear.xhtml", method = RequestMethod.GET)
	public String clearSelected(Model model,
                                @RequestParam(value = "f") String dir, RedirectAttributes redirectAttributes) throws Exception {

		File file = current(dir);
		Iterator<File> cIt = cutFiles.iterator();
		File cFile = null;
		while (cIt.hasNext() && file.equals(cFile = cIt.next()))
			;
		if (cIt.hasNext())
			cutFiles.remove(cFile);

		cIt = copiedFiles.iterator();
		cFile = null;
		while (cIt.hasNext() && file.equals(cFile = cIt.next()))
			;
		if (cIt.hasNext())
			copiedFiles.remove(cFile);

        redirectAttributes.addFlashAttribute("message", Notification.getSuccesfull());
		return "redirect:/admin/files/list.xhtml";
	}

	@RequestMapping(value = "/delete.xhtml", method = RequestMethod.GET)
	public String delete(Model model, @RequestParam(value = "f") String dir, RedirectAttributes redirectAttributes)
			throws Exception {
        try {
            File file = current(dir);
            org.apache.commons.io.FileUtils.deleteQuietly(file);
            doUnshare(dir);
        } catch (Exception e){
            redirectAttributes.addFlashAttribute("message", Notification.getNotSuccesfull());
            redirectAttributes.addFlashAttribute("anerror", true);
            return "redirect:/admin/files/list.xhtml";
        }

        redirectAttributes.addFlashAttribute("message", Notification.getSuccesfullDelete());
		return "redirect:/admin/files/list.xhtml";
	}

}
