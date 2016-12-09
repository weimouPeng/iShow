package com.scau.dao.imp;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import com.scau.dao.ICollectionShareDao;

public class CollectionShareDaoImpl implements ICollectionShareDao{
	private JdbcTemplate jdbcTemplate;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	@Override
	public int addCollectionShare(long share_id, long user_id) {
		int count = jdbcTemplate.update("insert into collectionshare(share_id,user_id) values(?,?)", new Object[]{share_id,user_id},
		        new int[]{java.sql.Types.INTEGER,java.sql.Types.INTEGER});
		return count;
	}
	@Override
	public int deleteCollectionShare(long share_id, long user_id) {
		int count = jdbcTemplate.update("delete from collectionshare where share_id = ? and user_id = ?",new Object[]{share_id,user_id},
		        new int[]{java.sql.Types.INTEGER,java.sql.Types.INTEGER});
		return count;
	}
	@Override
	public int countCollectionShare(long user_id) {
		int count=jdbcTemplate.queryForObject("SELECT COUNT(*) FROM collectionshare where user_id = ?",new Object[] {user_id},Integer.class); 
		return count;
	}
	@Override
	public List<Long> getCollectShareIdsByUserId(long user_id) {
		// TODO Auto-generated method stub
		String sql = "select share_id from collectionshare where user_id = "+user_id;
		List<Long> shareIds = jdbcTemplate.queryForList(sql, Long.class);
		return shareIds;
	}
	
	@Override
	public boolean checkCollectShare(long share_id,long user_id){
//		SELECT `share_id`,`user_id` 
//		FROM `collectionshare` 
//		WHERE `share_id`='9' AND `user_id`='1500013'
		String sql = "SELECT `share_id`,`user_id` "
				+ "FROM `collectionshare` WHERE `share_id`='"+share_id
				+"' AND `user_id`='"+user_id+"'";
		try {
			List<Map<String, Object>> list =jdbcTemplate.queryForList(sql);
			if(list.size()!=0) return true;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	
	
}
