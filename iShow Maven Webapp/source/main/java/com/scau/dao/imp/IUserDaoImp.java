package com.scau.dao.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;







import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.mysql.jdbc.log.Log;
import com.scau.dao.IUserDao;
import com.scau.entity.User;

public class IUserDaoImp implements IUserDao{


	private JdbcTemplate jdbcTemplate;
    private static Logger log = LogManager.getLogger();
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public User addUser(User user) {
		// TODO Auto-generated method stub
		
		StringBuilder sb = new StringBuilder();
		sb.append("insert into user(nickname,password,mailbox,jurisdiction,head_portrait) values (");
		sb.append("\""+user.getNickname()+"\",\""+user.getPassword()+"\",\""+user.getMailbox()+"\"");
		sb.append(","+user.getJurisdiction()+",\""+user.getHead_portrait()+"\" )");
		String sql = sb.toString();
		System.out.println(sql);
		try{
		jdbcTemplate.update(sql);
		StringBuilder buffer = new StringBuilder();
		buffer.append("select user_id from user where mailbox = \""+user.getMailbox()+"\"");
		System.out.println(buffer.toString());
		HashMap<String,Object> map = (HashMap<String, Object>) jdbcTemplate.queryForMap(buffer.toString());
		Long id =(Long) map.get("user_id");
		user.setUser_id(id);
		}catch(Exception e){
			log.error("add user error");
			return null;
		}
		return user;
	}

	@Override
	public int updateUser(User user) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		return 0;
	}

	@Override
	public User checkUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkMailbox(String mailbox) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User findUserById(long id) {
		// TODO Auto-generated method stub
		String sql = "select * from user where user_id = "+id;
		try{
			HashMap<String,Object> map = (HashMap<String, Object>) jdbcTemplate.queryForMap(sql);
			if(map==null||map.isEmpty()){
				return null;
			}
			User user = new User();
			user.setNickname((String)map.get("nickname"));
			//user.setPassword((String) map.get("password"));
			user.setUser_id((long) map.get("user_id"));
			user.setMailbox((String) map.get("mailbox"));
			user.setJurisdiction((int) map.get("jurisdiction"));
			user.setHead_portrait((String) map.get("head_portrait"));
			return user;
		}catch(Exception ex){
			log.debug("findUserById"+id+" error!!");
		}
		return null;
	}

	@Override
	public long countUser() {
		// TODO Auto-generated method stub
		String sql = "select COUNT(*) as num from user;";
		HashMap<String,Object> map = (HashMap<String, Object>) jdbcTemplate.queryForMap(sql);
		long num = (long) map.get("num");
		return num;
	}

	@Override
	public List<User> findUserByPage(int begin, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findUserCollectionUser(int id, int begin, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findUserByMail(String mailbox) {
		// TODO Auto-generated method stub
		String sql = "select * from user where mailbox = \""+mailbox+"\"";
		try{
			HashMap<String,Object> map = (HashMap<String, Object>) jdbcTemplate.queryForMap(sql);
			if(map==null||map.isEmpty()){
				return null;
			}
			User user = new User();
			user.setNickname((String)map.get("nickname"));
			user.setPassword((String) map.get("password"));
			user.setUser_id((long) map.get("user_id"));
			user.setMailbox((String) map.get("mailbox"));
			user.setJurisdiction((int) map.get("jurisdiction"));
			user.setHead_portrait((String) map.get("head_portrait"));
			return user;
		}catch(Exception ex){
			log.debug("findUserByMailxbox "+mailbox+" error!!");
		}
		return null;
	}

	@Override
	public long getNumsOfBannedUser() {
		// TODO Auto-generated method stub
		String sql = "select count(*) as nums from user where jurisdiction = 1";
		Map<String,Object> map = jdbcTemplate.queryForMap(sql);
		long nums = (long) map.get("nums");
		return nums;
	}

	@Override
	public List<User> recommendUsers(long id) {
		// TODO Auto-generated method stub
		String sql = "SELECT `user_id`,`nickname`,`password`,`mailbox`,"
				+ "`jurisdiction`,`head_portrait` FROM `user`  "
				+ "WHERE `user_id`!='"+id+"' AND `jurisdiction`='0' "
				+ "ORDER BY RAND() LIMIT 0,6";
		List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);
		ArrayList<User> res = new ArrayList<User>();
		for(Map<String,Object> m : list){
			User user = new User();
			user.setNickname((String)m.get("nickname"));
			//user.setPassword((String) m.get("password"));
			user.setUser_id((long) m.get("user_id"));
			user.setMailbox((String) m.get("mailbox"));
			user.setJurisdiction((int) m.get("jurisdiction"));
			user.setHead_portrait((String) m.get("head_portrait"));
			res.add(user);
		}
		return res;
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		String sql="select * from user";
		List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);
		ArrayList<User> res = new ArrayList<User>();
		for(Map<String,Object> m : list){
			User user = new User();
			user.setNickname((String)m.get("nickname"));
			user.setPassword((String) m.get("password"));
			user.setUser_id((long) m.get("user_id"));
			user.setMailbox((String) m.get("mailbox"));
			user.setJurisdiction((int) m.get("jurisdiction"));
			user.setHead_portrait((String) m.get("head_portrait"));
			res.add(user);
		}
		return res;
	}

	@Override
	public boolean setPhoto(long user_id, String path) {
		// TODO Auto-generated method stub
		try{
		String sql = "update user set head_portrait = '"+path+"' where user_id = "+user_id;
		jdbcTemplate.update(sql);
		return true;
		}catch(Exception e){
			return false;
		}
	}

	@Override
	public boolean updateNickname(long user_id, String nickname) {
		// TODO Auto-generated method stub
		try{
			String sql = "update user set nickname = '"+nickname+"' where user_id = "+user_id;
			jdbcTemplate.update(sql);
			return true;
			}catch(Exception e){
				return false;
			}
	}

}
