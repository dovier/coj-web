package cu.uci.coj.api.json.controller;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cu.uci.coj.config.Config;


@Controller
@RequestMapping(value = "/json/files")
@Scope("session")
public class JsonFiles {
	
	@PostConstruct
	public void init(){		
		ufile = new UploadedFile();
	}

	UploadedFile ufile;

	@RequestMapping(value = "/get.xhtml", method = RequestMethod.GET)
	public void get(HttpServletResponse response, @RequestParam("value") String value) {
		try {
			response.setContentType(ufile.type);
			response.setContentLength(ufile.length);
			FileCopyUtils.copy(ufile.bytes, response.getOutputStream());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/upload.xhtml", method = RequestMethod.POST)
	public @ResponseBody
	String upload(MultipartHttpServletRequest request, HttpServletResponse response) {

		// 0. notice, we have used MultipartHttpServletRequest

		// 1. get the files from the request object
		
		java.util.Iterator<String> itr = request.getFileNames();

		MultipartFile mpf = request.getFile(itr.next());
		File f = new File(Config.getProperty("base.upload.dir.images")+mpf.getOriginalFilename());
		try {
			mpf.transferTo(f);
		} catch (IllegalStateException | IOException e1) {
			System.out.println(e1.getMessage());
		}		

//		try {
//			// just temporary save file info into ufile
//			ufile.length = mpf.getBytes().length;
//			ufile.bytes = mpf.getBytes();
//			ufile.type = mpf.getContentType();
//			ufile.name = mpf.getOriginalFilename();
//
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//return "<img src='/json/files/get.xhtml?value=" + f.getAbsolutePath() + "'/>";
		return "<img src='/downloads/images/" + mpf.getOriginalFilename() + "'/>";
	}

}

class UploadedFile {

	public int length;
	public byte[] bytes;
	public String name;
	public String type;
}
