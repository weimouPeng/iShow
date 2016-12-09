package com.scau.dao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scau.dao.IAdministrationDao;
import com.scau.dao.imp.AdministrationDaoImpl;
import com.scau.entity.Administrator;
import com.scau.entity.User;
@Service
public class AdministrationService {
	@Autowired
	private IAdministrationDao dao ;
	
	public Administrator updataAdmin(Administrator admin){
		return dao.updataAdmin(admin);
	}
	
	public Administrator login(long admin_id,String password){
		Administrator admin = dao.findAdmin(admin_id);
		if (admin==null) 
			return null;
		if(admin.getPassword().equals(password))
			return admin;
		else return null;	
	}
	
	public Administrator login(Administrator admin){
		Administrator admin2 = dao.checkAdmin(admin);
		if (admin2==null) 
			return null;
		else return admin2;	
	}
	
	public boolean banUser(long user_id){
		return dao.banUser(user_id);
	}
	public boolean allowUser(long user_id){
		return dao.allowUser(user_id);
	}
}
