package com.scau.dao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scau.dao.ICollectionShareDao;
import com.scau.dao.imp.CollectionShareDaoImpl;

@Service
public class CollectionShareService {
	@Autowired
	private ICollectionShareDao dao ;
	
	public int addCollectionShare(long share_id, long user_id){
		//if(dao.checkCollectShare(share_id, user_id)) return 0;
		return dao.addCollectionShare(share_id, user_id);
	}
	 
	public int deleteCollectionShare(long share_id, long user_id) {
		return dao.deleteCollectionShare(share_id, user_id);
	}
	
	public int countCollectionShare(long user_id) {
		return dao.countCollectionShare(user_id);
	}
	public List<Long> getCollectShareIds(long user_id){
		return dao.getCollectShareIdsByUserId(user_id);
	}
	
	public boolean checkCollectionShare(long share_id,long user_id){
		return dao.checkCollectShare(share_id, user_id);
	}
	
	
}
