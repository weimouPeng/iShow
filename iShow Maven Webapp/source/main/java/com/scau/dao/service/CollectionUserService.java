package com.scau.dao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scau.dao.ICollectionUserDao;
import com.scau.dao.imp.CollectionShareDaoImpl;
import com.scau.dao.imp.CollectionUserDaoImpl;
@Service
public class CollectionUserService {
	@Autowired
	private ICollectionUserDao dao;
	
	public int addCollectionUser(long user_id, long be_collectioned_id) {
		//if(dao.checkCollectionUser(user_id, be_collectioned_id)) return 0;
		return dao.addCollectionUser(user_id, be_collectioned_id);
	}

	public int deleteCollectionUser(long user_id, long be_collectioned_id) {
		return dao.deleteCollectionUser(user_id, be_collectioned_id);
	}

	public int countCollectionUser(long user_id) {
		return dao.countCollectionUser(user_id);
	}
	
	public List<Long> UserCollectionOtherUserID(long user_id){
		return dao.UserCollectionOtherUserID(user_id);
	}
	
	public boolean checkCollectionUser(long user_id,long other_id){
		return dao.checkCollectionUser(user_id, other_id);
	}
	
}
