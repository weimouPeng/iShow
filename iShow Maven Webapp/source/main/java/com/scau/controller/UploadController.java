package com.scau.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/upload")
public class UploadController {
	
//	@RequestMapping(value = "/upload.do")
//	@ResponseBody
	public String upload(
			@RequestParam(value = "files", required = false) List<MultipartFile> files,
			HttpServletRequest request, ModelMap model) {

		if (files == null || files.size() <= 0)
			return "fail";
		System.out.println("开始");
		String path = request.getSession().getServletContext()
				.getRealPath("upload");

		for (MultipartFile file : files) {

			String fileName = file.getOriginalFilename();
			// String fileName = new Date().getTime()+".jpg";
			System.out.println("++++++++++++ "  + fileName);
			File targetFile = new File(path, fileName);
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}

			// 保存
			try {
				file.transferTo(targetFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
			 model.addAttribute("fileUrl",
			 request.getContextPath()+"/upload/"+fileName);
		}
		return "result";
	}
	

	@RequestMapping(value = "/toUploadPage.do")
	public String toUploadPage(ModelAndView model){
		return "upload";
	}

}
