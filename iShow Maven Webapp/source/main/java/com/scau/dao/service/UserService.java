package com.scau.dao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scau.dao.IUserDao;
import com.scau.entity.User;

@Service
public class UserService {
	@Autowired
	private IUserDao dao;
	
	public User addUser(User user){
		String mailbox = user.getMailbox();
		User temp = dao.findUserByMail(mailbox);
		if(temp!=null)
			return null;
		return dao.addUser(user);
		
	}
	
	public User findUserById(long id){
		return dao.findUserById(id);
	}
	
	public long countUser(){
		return dao.countUser();
	}
	
	public User login(String mailbox,String password){
		User user = dao.findUserByMail(mailbox);
		if(user==null)
			return null;
//		System.out.println("---"+password.toString());
//		System.out.println("---"+user.getPassword());
//		System.out.println(password.equals(user.getPassword()));
		if(password.equals(user.getPassword())){
			return user;
		}
		return null;
	}
	public long getNumsOfBannedUser(){
		return dao.getNumsOfBannedUser();
	}
	
	public List<User> recommendUsers(long id){
		return dao.recommendUsers(id);
		
	}
	
	public boolean isExistedMailbox(String mailbox){
		User temp = dao.findUserByMail(mailbox);
		if(temp==null)
			return false;
		return true;
		
	}
	
	public List<User> getAllUsers(){
		return dao.getAllUsers();
	}
	
	public boolean setPhoto(long user_id, String path){
		return dao.setPhoto(user_id, path);
	}
	public boolean updateNickname(long user_id,String nickname){
		return dao.updateNickname(user_id, nickname);
	}
}
