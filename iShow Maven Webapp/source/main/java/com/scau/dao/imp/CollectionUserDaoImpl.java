package com.scau.dao.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.scau.dao.ICollectionUserDao;

public class CollectionUserDaoImpl implements ICollectionUserDao{
	private JdbcTemplate jdbcTemplate;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public int addCollectionUser(long user_id, long be_collectioned_id) {
		int count = jdbcTemplate.update("insert into collectionuser(user_id,be_collection_user_id) values(?,?)", new Object[]{user_id,be_collectioned_id},
		        new int[]{java.sql.Types.INTEGER,java.sql.Types.INTEGER});
		return count;
	}
	
	@Override
	public int deleteCollectionUser(long user_id, long be_collectioned_id) {
		// TODO Auto-generated method stub
		int count = jdbcTemplate.update("delete from collectionuser where user_id = ? and be_collection_user_id = ?",new Object[]{user_id,be_collectioned_id},
		        new int[]{java.sql.Types.INTEGER,java.sql.Types.INTEGER});
		return count;
	}
	
	@Override
	public int countCollectionUser(long user_id) {
		// TODO Auto-generated method stub
		int count=jdbcTemplate.queryForObject("SELECT COUNT(*) FROM collectionuser where user_id = ?",new Object[] {user_id},Integer.class); 
		return count;
	}
	
	@Override
	public List<Long> UserCollectionOtherUserID(long user_id) {
		// TODO Auto-generated method stub
		List<Long> Userid = new  ArrayList<>();
		List rows = jdbcTemplate.queryForList("SELECT * FROM collectionuser where user_id = ?", new Object[] {user_id},new int[]{java.sql.Types.INTEGER}); 
		Iterator it = rows.iterator();  
		while(it.hasNext()) {
			HashMap<String, Object> CollectionMap = (HashMap<String, Object>) it.next();
			Userid.add((long)CollectionMap.get("be_collection_user_id"));
			System.out.print((long)CollectionMap.get("be_collection_user_id"));
		}
		return Userid;
	}
	
	@Override
	public boolean checkCollectionUser(long user_id,long other_id){
//		SELECT `user_id`,`be_collection_user_id`
//		FROM `collectionuser` 
//		WHERE `user_id`='1500013' AND `be_collection_user_id`='1500015'
		String sql = "SELECT `user_id`,`be_collection_user_id` "
				+ "FROM `collectionuser`  WHERE `user_id`='"+user_id
				+"' AND `be_collection_user_id`='"+other_id+"'";
		try {
			List<Map<String, Object>> list =jdbcTemplate.queryForList(sql);
			if(list.size()!=0) return true;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	
	
}
