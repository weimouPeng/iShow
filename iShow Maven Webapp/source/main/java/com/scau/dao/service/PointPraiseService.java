package com.scau.dao.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scau.dao.IPointPraiseDao;


@Service
public class PointPraiseService {
	@Autowired
	private IPointPraiseDao dao;
	
	public int addPointPraise(long share_id, long user_id){
		//if(dao.checkPointPraise(share_id, user_id)) return 0;
		Date date = new Date();
		return dao.addPointPraise(share_id, user_id, date);
	}
	
	public int deletePointPraise(long share_id, long user_id){
		return dao.deletePointPraise(share_id, user_id);
	}
	
	public int countSharePointByUserId(long user_id){
		return dao.countSharePointByUserId(user_id);
	}
	
	public List<Long> getPointPraiseUserIds(long share_id){
		return dao.getPointPraiseUserIds(share_id);
	}
	public List<Long> getPointPraiseByUserId(long user_id){
		
		return dao.getPointPraiseByUserId(user_id);
		
	}
	
	public boolean checkPointPraise(long share_id,long user_id){
		return dao.checkPointPraise(share_id, user_id);
	} 
	
	

}
