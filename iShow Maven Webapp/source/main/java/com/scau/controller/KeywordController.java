package com.scau.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.scau.dao.service.KeywordService;
import com.scau.entity.Keyword;

@Controller
@RequestMapping("/keyword")
public class KeywordController {
	
	@Autowired
	private KeywordService keyWordService;
	
	@RequestMapping(value="getAllKeyWord.do")
	@ResponseBody
	public Map<String,Object> getAllKeyWord(){
		List<Keyword> kds= keyWordService.findKeyword();
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("keywords", kds);
		return map;
		
	}
	@RequestMapping(value="addKeyWord.do")
	@ResponseBody
	public Map<String,Object> addKeyWord(@RequestParam(value = "wd")String keyword){
		HashMap<String,Object> map = new HashMap<String, Object>();
//		boolean isExisted = keyWordService.checkKeywordInString(keyword);
		
		Keyword key = keyWordService.addKeyword(keyword);
		
//		if(isExisted){
		if(key==null){
			map.put("flag", "fail");
			return map;
		}
//		keyWordService.addKeyword(keyword);
		map.put("flag", "true");
		return map;
		
	}
	
	@RequestMapping(value="deleteKeyWord.do")
	@ResponseBody
	public Map<String,Object> deleteKeyWord(@RequestParam(value = "wd")String keyword){
		HashMap<String,Object> map = new HashMap<String, Object>();
//		boolean isExisted = keyWordService.checkKeywordInString(keyword);
		
		int key = keyWordService.deleteKeyword(keyword);
		if(key==0){
			map.put("flag", "fail");
			return map;
		}
//		keyWordService.addKeyword(keyword);
		map.put("flag", "true");
		return map;
	}
	

}
