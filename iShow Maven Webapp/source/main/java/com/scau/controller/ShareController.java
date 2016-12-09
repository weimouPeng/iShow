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

import com.scau.dao.service.CommentService;
import com.scau.dao.service.PictureService;
import com.scau.dao.service.PointPraiseService;
import com.scau.dao.service.ShareService;
import com.scau.dao.service.UserService;
import com.scau.entity.Comment;
import com.scau.entity.Picture;
import com.scau.entity.Share;
import com.scau.entity.User;

@Controller
@RequestMapping("/share")
public class ShareController {
	@Autowired
	private ShareService shareService;
	@Autowired
	private UserService userService;
	@Autowired
	private PictureService pictureService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private PointPraiseService pointPraiseService;

	@RequestMapping(value = "getTodayShareList.do")
	@ResponseBody
	public Map<String, Object> getTodayShareList() {
		List<Share> list = shareService.findShareByPointToday();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);

		return map;

	}

	@RequestMapping(value = "getWeekShareList.do")
	@ResponseBody
	public Map<String, Object> getWeekShareList() {
		List<Share> list = shareService.findShareByPointWeek();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);

		return map;

	}

	@RequestMapping(value = "getAllShareList.do")
	@ResponseBody
	public Map<String, Object> getAllShareList() {
		List<Share> list = shareService.findShareByPointAll();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);

		return map;

	}

	@RequestMapping(value = "findShareByKey.do")
	@ResponseBody
	public Map<String, Object> findShareByKey(
			@RequestParam(value = "key") String key) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<Share> list = shareService.findShareByKey(key);
		map.put("list", list);
		return map;

	}

	@RequestMapping(value = "findShareByTime.do")
	@ResponseBody
	public Map<String, Object> findShareByTime() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<Share> shares = shareService.findShare();
		ArrayList<User> users = new ArrayList<User>();
		ArrayList<List<Picture>> pictures = new ArrayList<List<Picture>>();
		for (Share share : shares) {
			long user_id = share.getPublisher_id();
			User user = userService.findUserById(user_id);
			users.add(user);
			long share_id = share.getShare_id();
			List<Picture> picture = pictureService
					.findPictureByShareId(share_id);
			pictures.add(picture);
		}
		map.put("shares", shares);
		map.put("users", users);
		map.put("pictures", pictures);
		return map;

	}

	@RequestMapping(value = "findShareById.do")
	@ResponseBody
	public Map<String, Object> findShareById(
			@RequestParam(value = "id") int share_id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Share share = shareService.findShareById(share_id);
		if (share == null) {
			map.put("flag", "true");
			return map;
		}
		map.put("flag", "true");
		long user_id = share.getPublisher_id();
		User user = userService.findUserById(user_id);
		map.put("share", share);
		map.put("user", user);
		List<Picture> pictures = pictureService.findPictureByShareId(share_id);
		map.put("pictures", pictures);
		List<Comment> comments = commentService.findCommentByShareId(share_id);
		map.put("comments", comments);
		List<Long> pointPraiseUserIds = pointPraiseService
				.getPointPraiseUserIds(share_id);
		List<User> pointPraiseUsers = new ArrayList<User>();
		for (long pointUserId : pointPraiseUserIds) {
			User pointUser = userService.findUserById(pointUserId);
			pointPraiseUsers.add(pointUser);
		}
		map.put("pointPraiseUsers", pointPraiseUsers);
		return map;

	}

	@RequestMapping(value = "findOtherUserSharesById.do")
	@ResponseBody
	public Map<String, Object> findOtherUserSharesById(
			@RequestParam(value = "id") int user_id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<Share> shares = shareService.findShareByOtherUserId(user_id);
		map.put("shares", shares);
		return map;

	}

	@RequestMapping(value = "findUserSharesById.do")
	@ResponseBody
	public Map<String, Object> findUserSharesById(
			@RequestParam(value = "id") int user_id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<Share> shares = shareService.findShareByUserId(user_id);
		map.put("shares", shares);
		return map;

	}

	@RequestMapping(value = "addShare.do")
	@ResponseBody
	public Map<String, Object> addShare(Share share) {
		if (share == null)
			return null;
		long user_id = share.getPublisher_id();
		String text = share.getDescribe();
		int state = share.getState();
		Share temp = shareService.addShare(user_id, text, state);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("share", temp);
		return map;

	}
	@RequestMapping(value = "chageShareState.do")
	@ResponseBody
	public Map<String, Object> chageShareState(
			@RequestParam(value = "shareId") int share_id,
			@RequestParam(value = "state") int state) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {

			shareService.updateShare(share_id, state);
			map.put("flag", "true");
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			map.put("flag", "false");
			return map;
		}
	}
}
