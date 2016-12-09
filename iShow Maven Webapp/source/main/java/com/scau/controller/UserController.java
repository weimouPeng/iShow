package com.scau.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.scau.dao.service.*;
import com.scau.entity.User;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private ShareService shareService;
	@Autowired
	private CollectionShareService collectionShareService;
	@Autowired
	private CollectionUserService collectionUserService;
	
	
	@RequestMapping(value = "addUser.do")
	@ResponseBody
	public Map<String, Object> addUser(User newUser) {
		User user = new User();
		user = userService.addUser(newUser);
		Map<String, Object> map = new HashMap<String, Object>();
		if(user==null){
			map.put("flag", "failed");
			return map;
		}
		
		map.put("flag", "success");
		map.put("id", user.getUser_id());
		map.put("nickname", user.getNickname());
		map.put("mailbox", user.getMailbox());
		map.put("head_portrait", user.getHead_portrait());
		map.put("jurisdiction", user.getJurisdiction());
		return map;

	}
	@RequestMapping(value = " getUserById.do")
	@ResponseBody
	public Map<String,Object> getUserById(@RequestParam(value="id")int id){
		HashMap<String,Object> map = new HashMap<String,Object>();
		User user = userService.findUserById(id);
		if(user==null){
			map.put("flag", "fail");
			return map;
		}
		map.put("flag", "true");
		map.put("user", user);
		return map;
		
	}
	
	@RequestMapping(value = "findUserById.do")
	@ResponseBody
	public Map<String, Object> findUserById() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		User user = userService.findUserById(7);
		map.put("id", user.getUser_id());
		map.put("nickname", user.getNickname());
		map.put("mailbox", user.getMailbox());
		System.out.println("---------------->" + userService.countUser());
		return map;
	}

	@RequestMapping(value = "login.do")
	@ResponseBody
	public Map<String, Object> login(
			@RequestParam(value = "mailbox") String mailbox,
			@RequestParam(value = "password") String password) {
		HashMap<String,Object> map = new HashMap<String, Object>();
		User user = userService.login(mailbox, password);
		if(user==null){
			map.put("flag", "failed");
			return map;
		}
		map.put("flag", "success");
		map.put("id", user.getUser_id());
		map.put("nickname", user.getNickname());
		map.put("mailbox", user.getMailbox());
		map.put("head_portrait", user.getHead_portrait());
		map.put("jurisdiction", user.getJurisdiction());
		int numsOfCollectionShare = collectionShareService.countCollectionShare(user.getUser_id());
		map.put("numsOfCollectionShare", numsOfCollectionShare);
		int numsOfCollectionUser = collectionUserService.countCollectionUser(user.getUser_id());
		map.put("numsOfCollectionUser", numsOfCollectionUser);
		if(shareService.findShareByUserId(user.getUser_id())==null){
			map.put("numsOfUserShare",0 );
		}else{
		int numsOfUserShare = shareService.findShareByUserId(user.getUser_id()).size();
		map.put("numsOfUserShare",numsOfUserShare );
		}
		return map;

	}
	
	@RequestMapping(value = "recommend.do")
	@ResponseBody
	public Map<String, Object> recommendUsers(@RequestParam(value="id")int id){
		List<User> list = userService.recommendUsers(id);
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("list", list);
		return map;
		
	}
	
	@RequestMapping(value = "findMailBox.do")
	@ResponseBody
	public Map<String, Object> isExistedMailbox(@RequestParam(value="mailbox")String mailbox){
		HashMap<String,Object> map  = new HashMap<String, Object>();
		boolean flag = userService.isExistedMailbox(mailbox);
		map.put("flag", flag);
		return map;
	}
	@RequestMapping(value = "updatePhoto.do")
	@ResponseBody
	public Map<String,Object> updatePhoto(@RequestParam(value = "id")int user_id,
			@RequestParam(value = "path")String path){
		HashMap<String,Object> map  = new HashMap<String, Object>();
		boolean flag = userService.setPhoto(user_id, path);
		map.put("flag", flag);
		return map;
		
	}
	@RequestMapping(value = "updateNickname.do")
	@ResponseBody
	public Map<String,Object> updateNickname(@RequestParam(value = "id")int user_id,
			@RequestParam(value = "nickname")String nickname){
		HashMap<String,Object> map  = new HashMap<String, Object>();
		boolean flag = userService.updateNickname(user_id, nickname);
		map.put("flag", flag);
		return map;
		
	}
	
}
