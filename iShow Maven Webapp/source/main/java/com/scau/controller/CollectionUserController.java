package com.scau.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.standard.MediaSize.Other;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.scau.dao.service.CollectionUserService;
import com.scau.dao.service.UserService;
import com.scau.entity.User;

@Controller
@RequestMapping("/CollectionUser")
public class CollectionUserController {

	@Autowired
	private CollectionUserService collectionUserService;
	@Autowired
	private UserService userService;

	@RequestMapping(value = "getCollectionUser.do")
	@ResponseBody
	public Map<String, Object> getCollectionUser(
			@RequestParam(value = "id") int user_id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<Long> userIds = collectionUserService
				.UserCollectionOtherUserID(user_id);
		ArrayList<User> users = new ArrayList<User>();
		for (long userId : userIds) {
			User user = userService.findUserById(userId);
			users.add(user);
		}
		map.put("users", users);
		return map;

	}

	@RequestMapping(value = "collectUser.do")
	@ResponseBody
	public Map<String, Object> collectUser(
					@RequestParam(value = "user_id")int user_id,
					@RequestParam(value = "collect_id")int collect_id){
		HashMap<String, Object> map = new HashMap<String, Object>();
		boolean hasCollected = collectionUserService.checkCollectionUser(
				user_id, collect_id);
		if (hasCollected) {
			map.put("flag", "false");
			return map;
		}
		collectionUserService.addCollectionUser(user_id, collect_id);
		map.put("flag", "true");
		return map;

	}

	@RequestMapping(value = "deleteCollectionUser.do")
	@ResponseBody
	public Map<String, Object> deleteCollectionUser(int user_id, int other_id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			collectionUserService.deleteCollectionUser(user_id, other_id);
			map.put("flag", "true");
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			map.put("flag", "fail");
			return map;
		}

	}

}
