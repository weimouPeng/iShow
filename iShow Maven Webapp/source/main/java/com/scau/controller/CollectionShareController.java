package com.scau.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.scau.dao.service.CollectionShareService;
import com.scau.dao.service.ShareService;
import com.scau.entity.Share;

@Controller
@RequestMapping("/CollectionShare")
public class CollectionShareController {
	@Autowired
	private CollectionShareService collectionShareService;
	@Autowired
	private ShareService shareService;

	@RequestMapping(value = "getCollectionShare.do")
	@ResponseBody
	public Map<String, Object> getShareList(
			@RequestParam(value = "id") int user_id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<Long> shareIds = collectionShareService
				.getCollectShareIds(user_id);
		ArrayList<Share> list = new ArrayList<Share>();
		for (long shareId : shareIds) {
			Share share = shareService.findShareById(shareId);
			list.add(share);
		}
		map.put("shares", list);
		return map;

	}

	@RequestMapping(value = "collectShare.do")
	@ResponseBody
	public Map<String, Object> collectShare(@RequestParam(value="user_id")int user_id,
			@RequestParam(value = "share_id")int share_id){
		HashMap<String, Object> map = new HashMap<String, Object>();
		boolean hasCollected = collectionShareService.checkCollectionShare(
				share_id, user_id);
		if (hasCollected) {
			map.put("flag", "fail");
			return map;
		}
		collectionShareService.addCollectionShare(share_id, user_id);
		map.put("flag", "true");
		return map;

	}

	@RequestMapping(value = "deleteCollectionShare.do")
	@ResponseBody
	public Map<String, Object> deleteCollectionShare(int share_id, int user_id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			collectionShareService.deleteCollectionShare(share_id, user_id);
			map.put("flag", "true");
			return map;
		} catch (Exception e) {
			map.put("flag", "fail");
			return map;
		}

	}
}
