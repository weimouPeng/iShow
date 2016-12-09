package com.scau.dao.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scau.dao.IShareDao;
import com.scau.entity.Share;
import com.scau.entity.User;



@Service
public class ShareService {
	@Autowired
	private IShareDao dao;
	
	public Share addShare(long user_id,String text,int state){
		if(text==null) text="";
		Date date = new Date();
		Share share = new Share();
		share.setPublisher_id(user_id);
		share.setRelease_time(date);
		share.setDescribe(text);
		share.setPoint_of_praise(0);
		share.setComment_number(0);
		share.setState(state);
		return dao.addShare(share);
	}
	
	public int deleteShare(long share_id){
		Share share = new Share();
		share.setShare_id(share_id);
		/*
		 * 
		 */
		return dao.deleteShare(share);
	}
	
	public int updateShare(long share_id,int state){
		Share share = new Share();
		share.setShare_id(share_id);
		share.setState(state);
		
		return dao.updateShare(share);
	}
	
	public Share findShareById(long share_id){
		return dao.findShareById(share_id);
	}
	
	public List<Share> findShare(){
		int state = 3;
		int begin = 0;
		int size = 1000;
		return dao.findShare(state, begin, size);
	}
	
	
	public List<Share> findShareByUserId(long user_id){
		int begin = 0;
		int size = 1000;
		int oneself = 0;
		return dao.findShareByUserId(user_id, begin, size,oneself);
	}
	
	
	public List<Share> findShareByKey(String key){
		int state = 3;
		int begin = 0;
		int size = 1000;
		return dao.findShareByKey(state, key, begin, size);
	}
	
	public List<Share> findShareByPointToday(){
		Date date = new Date();
		int days = 0;
		int state = 3;
		int begin = 0;
		int size = 1000;
		return dao.findShareByPointAndDays(date, days, state, begin, size);
	}
	
	public List<Share> findShareByPointWeek(){
		Date date = new Date();
		int days = 7;
		int state = 3;
		int begin = 0;
		int size = 1000;
		return dao.findShareByPointAndDays(date, days, state, begin, size);
	}
	
	public List<Share> findShareByPointAll(){
		Date date = new Date();
		int days = -1;
		int state = 3;
		int begin = 0;
		int size = 1000;
		return dao.findShareByPointAndDays(date, days, state, begin, size);
	}
	
	public List<Share> findShareByUserIdAndPoint(long user_id){
		int begin = 0;
		int size = 1000;
		return dao.findShareByUserIdAndPoint(user_id, begin, size);
	}
	
	public List<Share> findShareByUserIdAndCollection(long user_id){
		int begin = 0;
		int size = 1000;
		return dao.findShareByUserIdAndCollection(user_id, begin, size);
	}
	
	public List<Share> findShareByUserIdAndComment(long user_id){
		int begin = 0;
		int size = 1000;
		return dao.findShareByUserIdAndComment(user_id, begin, size);
	}
	
	public List<User> findUserByShareIdOfPoint(long share_id){
		return dao.findUserByShareIdOfPoint(share_id);
	}
	
	public List<Share> findShareByOtherUserId(long user_id){
		int begin = 0;
		int size = 1000;
		int oneself = 1;
		return dao.findShareByUserId(user_id, begin, size,oneself);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
