package com.scau.dao.service;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scau.dao.ICommentDao;
import com.scau.dao.imp.CommentDaoImpl;
import com.scau.entity.Comment;
@Service
public class CommentService {
	@Autowired
	private ICommentDao dao ;
	
	public Comment addComment(Comment comment) {
		comment.setComment_time(new Date());
		return dao.addComment(comment);
	}
	
	public int deleteComment(long serial_number){
		return dao.deleteComment(serial_number);
	}
	
	public Comment findCommentBySerial(long serial_number){
		return dao.findCommentBySerial(serial_number);
	}
	
	public int countCommentByUserId(long user_id){
		return dao.countCommentByUserId(user_id);
	}
	
	public int countCommentByShareId(long share_id){
		return dao.countCommentByShareId(share_id);
	}
	
	public List<Comment> findCommentByShareId(long share_id){
		return dao.findCommentByShareId(share_id);
	}
	public List<Comment> findCommentByUserId(long user_id) {
		return dao.findCommentByUserId(user_id);
	}
	
}
