package com.scau.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.scau.dao.service.AdministrationService;
import com.scau.dao.service.KeywordService;
import com.scau.dao.service.UserService;
import com.scau.entity.Administrator;
import com.scau.entity.User;

@Controller
@RequestMapping("/Admin")
public class AdmintratorController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private KeywordService keyWordService;
	@Autowired
	private AdministrationService  administrationService;
	
	@RequestMapping(value = "login.do")
	@ResponseBody
	public Map<String, Object> login(@RequestParam(value="id")long id,
									@RequestParam(value="pwd")String password){
		Administrator admin = administrationService.login(id, password);
		HashMap<String,Object> map = new HashMap<String, Object>();
		if(admin == null){
			map.put("flag", "failed");
			return map;
		}
		map.put("flag", "success");
		map.put("admin_id", admin.getAdmin_id());
		map.put("nickname", admin.getNickname());
		long numsOfBannedUser = userService.getNumsOfBannedUser();
		map.put("numsOfBannedUser", numsOfBannedUser);
		int numsOfBannedKeyWord = keyWordService.countKeyword();
		map.put("numsOfBannedKeyWord",numsOfBannedKeyWord );
		return map;
		
	}
	
	@RequestMapping(value = "getAllUsers.do")
	@ResponseBody
	public Map<String, Object> getAllUser(){
		HashMap<String,Object> map = new HashMap<String, Object>();
		List<User> users = userService.getAllUsers();
		map.put("users", users);
		return map;
		
	}

	@RequestMapping(value = "banUser.do")
	@ResponseBody
	public Map<String,Object> banUser(@RequestParam(value = "userId")int user_id){
		HashMap<String,Object> map = new HashMap<String, Object>();
		boolean res = administrationService.banUser(user_id);
		if(!res){
			map.put("flag", "false");
			return map;
		}
		map.put("flag", "true");
		return map;
		
		
	}
	
	@RequestMapping(value = "allowUser.do")
	@ResponseBody
	public Map<String,Object> allowUser(@RequestParam(value = "userId")int user_id){
		HashMap<String,Object> map = new HashMap<String, Object>();
		boolean res = administrationService.allowUser(user_id);
		if(!res){
			map.put("flag", "false");
			return map;
		}
		map.put("flag", "true");
		return map;
		
	}

}
