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
import com.scau.dao.service.ShareService;
import com.scau.entity.Comment;
import com.scau.entity.Share;

@Controller
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	private CommentService commentService;
	@Autowired
	private ShareService shareService;

	@RequestMapping(value = "getAllCommentsByUserId.do")
	@ResponseBody
	public Map<String, Object> getAllCommentsByUserId(
			@RequestParam(value = "id") int user_id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<Comment> comments = commentService.findCommentByUserId(user_id);
		map.put("comments", comments);
		ArrayList<Share> shares = new ArrayList<Share>();
		for (Comment comment : comments) {
			long share_id = comment.getShare_id();
			Share share = shareService.findShareById(share_id);
			shares.add(share);
		}
		map.put("shares", shares);
		return map;

	}
	@RequestMapping(value = "addComment.do")
	@ResponseBody
	public Map<String,Object> addComment(Comment comment){
		HashMap<String,Object> map = new HashMap<String, Object>();
		if(comment==null){
			map.put("flag", "flase");
			return map;
		}
		commentService.addComment(comment);
		map.put("flag", "true");
		return map;
		
	}

}
