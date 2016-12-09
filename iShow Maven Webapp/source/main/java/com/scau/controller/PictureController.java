package com.scau.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.scau.dao.service.PictureService;
import com.scau.dao.service.UserService;

@Controller
@RequestMapping("/picture")
public class PictureController {
	@Autowired
	private PictureService pictureService;
	@Autowired
	private UserService userService;
	@RequestMapping(value = "/upload.do")
	public String upload(
			@RequestParam(value = "files", required = false) List<MultipartFile> files,
			HttpServletRequest request, ModelMap model) {

		if (files == null || files.size() <= 0)
			return "result";
		System.out.println("开始");
		String path = request.getSession().getServletContext()
				.getRealPath("upload");

		for (MultipartFile file : files) {

			String fileName = file.getOriginalFilename();
			// String fileName = new Date().getTime()+".jpg";
			System.out.println("++++++++++++ upload\\"  + fileName);
			File targetFile = new File(path, fileName);
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}

			// 保存
			try {
				file.transferTo(targetFile);
				System.out.println("保存 upload\\"+fileName+" 成功" );
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("保存图片失败！！");
			}
//			 model.addAttribute("fileUrl",
//			 request.getContextPath()+"/upload/"+fileName);
		}
		return "result";
	}
	
	@RequestMapping(value = "/savePaths.do")
	@ResponseBody
	public Map<String,Object> savePath(@RequestParam(value="paths")List<String> paths,
			@RequestParam(value="shareId") int share_id
			){
		HashMap<String,Object> map = new HashMap<String, Object>();
		if(paths==null||paths.size()<=0){
			map.put("flag","false" );
			return null;
		}
		
		for(String path:paths){
			pictureService.addPicture(share_id, path);
		}
		map.put("flag", "true");
		return map;
		
	}
	
	@RequestMapping(value = "/uploadPhoto.do")
	public String uploadPhoto(
			@RequestParam(value = "files", required = false) List<MultipartFile> files,
			HttpServletRequest request, ModelMap model) {

		if (files == null || files.size() <= 0)
			return "register";
		System.out.println("开始");
		String path = request.getSession().getServletContext()
				.getRealPath("upload");

		for (MultipartFile file : files) {

			String fileName = file.getOriginalFilename();
			// String fileName = new Date().getTime()+".jpg";
			System.out.println("++++++++++++ upload\\"  + fileName);
			File targetFile = new File(path, fileName);
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}

			// 保存
			try {
				file.transferTo(targetFile);
				System.out.println("保存 upload\\"+fileName+" 成功" );
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("保存图片失败！！");
			}
//			 model.addAttribute("fileUrl",
//			 request.getContextPath()+"/upload/"+fileName);
		}
		return "register";
	}
	
	@RequestMapping(value = "/saveUserPhoto.do")
	@ResponseBody
	public Map<String,Object> saveUserPhoto(@RequestParam(value="paths")List<String> paths,
			@RequestParam(value="userid") int user_id
			){
		HashMap<String,Object> map = new HashMap<String, Object>();
		if(paths==null||paths.size()<=0){
			map.put("flag","false" );
			return null;
		}
		
		for(String path:paths){
			userService.setPhoto(user_id, path);
		}
		map.put("flag", "true");
		return map;
		
	}



}
