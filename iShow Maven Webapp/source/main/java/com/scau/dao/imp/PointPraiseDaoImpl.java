package com.scau.dao.imp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.scau.dao.IPointPraiseDao;

public class PointPraiseDaoImpl implements IPointPraiseDao {

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	
	/**
	 * 锟斤拷锟斤拷说锟斤拷锟斤拷锟斤拷锟斤拷一锟斤拷锟斤拷锟睫硷拷录
	 * @return 锟斤拷影锟斤拷锟斤拷锟斤拷锟�
	 * @author 
	 */
	@Override
	public int addPointPraise(long share_id, long user_id, Date date) {
//		INSERT INTO `ishow`.`pointpraise` (`share_id`, `user_id`, `praise_time`) VALUES ('8', '1500013', '2016-11-23 21:27:19')
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
		String datestr=df.format(date);
		String sql = "INSERT INTO `ishow`.`pointpraise` "
				+ "(`share_id`, `user_id`, `praise_time`) "
				+ "VALUES ('"+share_id+"', '"+user_id+"', '"+datestr+"')";
		try {
			int row = jdbcTemplate.update(sql);
			return row;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return 0;
	}

	

	/**
	 * 锟斤拷锟斤拷说锟斤拷锟斤拷删锟斤拷一锟斤拷锟斤拷锟睫硷拷录
	 * @return 锟斤拷影锟斤拷锟斤拷锟斤拷锟�
	 * @author 
	 */
	@Override
	public int deletePointPraise(long share_id, long user_id) {
//		DELETE FROM `ishow`.`pointpraise` WHERE `share_id` = '9' AND`user_id` = '1500017'
		String sql = "DELETE FROM `ishow`.`pointpraise` "
				+ "WHERE `share_id` = '"+share_id+"' AND`user_id` = '"+user_id+"'";
		try {
			int row = jdbcTemplate.update(sql);
			return row;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return 0;
	}

	
	/**
	 * 锟斤拷锟斤拷说锟斤拷锟斤拷统锟斤拷一锟斤拷锟矫伙拷锟斤拷锟睫的凤拷锟斤拷锟斤拷锟斤拷
	 * @return int
	 * @author 
	 */
	@Override
	public int countSharePointByUserId(long user_id) {
//		SELECT COUNT(1) FROM `pointpraise` WHERE `user_id`='1500013'
		String sql = "SELECT COUNT(1) FROM `pointpraise` WHERE `user_id`='"+user_id+"'";
		try {
			Object[] args = new Object[0];
			int sum= (int) jdbcTemplate.queryForObject(sql, args, Integer.class);
			return sum;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return 0;
	}


	@Override
	public List<Long> getPointPraiseUserIds(long share_id) {
		// TODO Auto-generated method stub
		String sql = "select user_id from pointpraise where share_id = "+share_id;
		try {
			List<Long> list = jdbcTemplate.queryForList(sql,Long.class);
			return list;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public List<Long> getPointPraiseByUserId(long user_id) {
		// TODO Auto-generated method stub
		String sql = "select share_id from pointpraise where user_id = "+user_id;
		List<Long> list;
		try {
			list = jdbcTemplate.queryForList(sql,Long.class);
			return list;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	@Override
	public boolean checkPointPraise(long share_id,long user_id){
//		SELECT `share_id`,`user_id` 
//		FROM `pointpraise` 
//		WHERE `share_id`='6' AND `user_id`='1500013'
		String sql = "SELECT `share_id`,`user_id` "
				+ "FROM `pointpraise` WHERE `share_id`='"+share_id
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
