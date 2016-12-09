package com.scau.dao.imp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.scau.dao.IShareDao;
import com.scau.entity.Share;
import com.scau.entity.User;

public class ShareDaoImpl implements IShareDao {

	
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	
	
	@Override
	public Share addShare(Share share) {
		
//		INSERT INTO `ishow`.`share` (`publisher_id`, `release_time`, `describe`,`point_of_praise`,`comment_number`,`state`) 
//		VALUES ('1500016', '2016-11-17 13:05:37', 'jjj','0','0','0')
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
		String datestr=df.format(share.getRelease_time());
		String sql="INSERT INTO `ishow`.`share` (`publisher_id`, `release_time`, `describe`,`point_of_praise`,`comment_number`,`state`) "
				+"VALUES ('"+share.getPublisher_id()
				+"', '"+datestr
				+"', '"+share.getDescribe()
				+"','"+share.getPoint_of_praise()
				+"','"+share.getComment_number()
				+"','"+share.getState()
				+"')";
//		System.out.println(sql);
		try {
			jdbcTemplate.update(sql);
//		    SELECT MAX(share_id) FROM `ishow`.`share` WHERE `publisher_id` = '1500016'
			String sql_1="SELECT MAX(share_id) FROM `ishow`.`share` WHERE `publisher_id` = '"
			        +share.getPublisher_id()+"'";
			Object[] args = new Object[0];
			long share_id= (long) jdbcTemplate.queryForObject(sql_1, args, Long.class);
//		    System.out.println(share_id);
			share.setShare_id(share_id);
			return share;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int deleteShare(Share share) {
//		DELETE FROM `ishow`.`share` WHERE `share_id` = '27' 
		String sql="DELETE FROM `ishow`.`share` WHERE `share_id` = '"
				+share.getShare_id()+"' ";
//		System.out.println(sql);
		try {
			int row=jdbcTemplate.update(sql);
			return row;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return 0;
	}

	
	@Override
	public int updateShare(Share share) {
//		update `ishow`.`share` set `state`='1' where `share_id`='26'
		String sql="update `ishow`.`share` set `state`='"+
				share.getState()+"' where `share_id`='"+
				share.getShare_id()+"'";
//		System.out.println(sql);
		try {
			int row=jdbcTemplate.update(sql);
			return row;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public Share findShareById(long share_id) {
//		select `share_id`,`publisher_id`,`release_time`,`describe`,
//		`point_of_praise`,`comment_number`,`state` from `ishow`.`share` 
//		where `share_id`='30'
		String sql="select `share_id`,`publisher_id`,`release_time`,`describe`,"
				+ "`point_of_praise`,`comment_number`,`state` from `ishow`.`share` "
				+ "where `share_id`='"+share_id+"'";
//		System.out.println(sql);
		try {
			List<Map<String, Object>> list =jdbcTemplate.queryForList(sql);
			if(list.size()==0) return null;
			HashMap<String, Object> map = (HashMap<String, Object>) list.get(0);
			Share share=new Share();
			share.setShare_id((long)map.get("share_id"));
			share.setPublisher_id((long) map.get("publisher_id"));
			share.setRelease_time((Date) map.get("release_time"));
			share.setDescribe((String) map.get("describe"));
			share.setPoint_of_praise((int) map.get("point_of_praise"));
			share.setComment_number((int) map.get("comment_number"));
			share.setState((int) map.get("state"));
			return share;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int countShare(int state) {
//		SELECT COUNT(1) FROM `share` WHERE (state='0' OR state ='1') 
//		AND publisher_id  IN( SELECT user_id FROM `user` WHERE jurisdiction=0)
		String sql="";
		if(state==0){
			sql="SELECT COUNT(1) FROM `share` WHERE state='0' "
				+ "AND publisher_id  IN( SELECT user_id FROM `user` WHERE jurisdiction=0)";
		}else if(state==1){
			sql="SELECT COUNT(1) FROM `share` WHERE state ='1'"
				+ " AND publisher_id  IN( SELECT user_id FROM `user` WHERE jurisdiction=0)";
		}else if(state==2){
			sql="SELECT COUNT(1) FROM `share` WHERE state ='2'"
					+ " AND publisher_id  IN( SELECT user_id FROM `user` WHERE jurisdiction=0)";
		}else if(state==3){
			sql="SELECT COUNT(1) FROM `share` WHERE (state ='0' OR state='1')"
					+ " AND publisher_id  IN( SELECT user_id FROM `user` WHERE jurisdiction=0)";
		}else{
			sql="SELECT COUNT(1) FROM `share`";
		}
//		System.out.println(sql);
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
	public List<Share> findShare(int state, int begin, int size) {
//		SELECT `share_id`,`publisher_id`,`release_time`,`describe`,
//		`point_of_praise`,`comment_number`,`state` FROM `share` 
//		WHERE (`state`='0' OR `state` ='1') 
//		AND `publisher_id`  IN( SELECT `user_id` FROM `user` WHERE `jurisdiction`='0')
//		ORDER BY `release_time` DESC LIMIT 0,5
		String sql;
		if(state==0){
			sql="SELECT `share_id`,`publisher_id`,`release_time`,`describe`,"
				+ "`point_of_praise`,`comment_number`,`state` FROM `share` "
				+ "WHERE `state`='0' AND `publisher_id`  IN( "
				+ "SELECT `user_id` FROM `user` WHERE `jurisdiction`='0')"
				+ "ORDER BY `release_time` DESC "
				+ "LIMIT "+begin+","+size+"";
		}else if(state==1){
			sql="SELECT `share_id`,`publisher_id`,`release_time`,`describe`,"
					+ "`point_of_praise`,`comment_number`,`state` FROM `share` "
					+ "WHERE `state`='1' AND `publisher_id`  IN( "
					+ "SELECT `user_id` FROM `user` WHERE `jurisdiction`='0')"
					+ "ORDER BY `release_time` DESC "
					+ "LIMIT "+begin+","+size+"";
		}else if(state==2){
			sql="SELECT `share_id`,`publisher_id`,`release_time`,`describe`,"
					+ "`point_of_praise`,`comment_number`,`state` FROM `share` "
					+ "WHERE `state`='2' AND `publisher_id`  IN( "
					+ "SELECT `user_id` FROM `user` WHERE `jurisdiction`='0')"
					+ "ORDER BY `release_time` DESC "
					+ "LIMIT "+begin+","+size+"";
		}else if(state==3){
			sql="SELECT `share_id`,`publisher_id`,`release_time`,`describe`,"
					+ "`point_of_praise`,`comment_number`,`state` FROM `share` "
					+ "WHERE (`state`='0' OR `state`='1') AND `publisher_id`  IN( "
					+ "SELECT `user_id` FROM `user` WHERE `jurisdiction`='0')"
					+ "ORDER BY `release_time` DESC "
					+ "LIMIT "+begin+","+size+"";
		}else{
			sql="SELECT `share_id`,`publisher_id`,`release_time`,`describe`,"
					+ "`point_of_praise`,`comment_number`,`state` FROM `share` "
					+ "ORDER BY `release_time` DESC "
					+ "LIMIT "+begin+","+size+"";
		}
//		System.out.println(sql);
		try {
			List<Share> sharelist = new ArrayList<Share>();
			List<Map<String, Object>> list =jdbcTemplate.queryForList(sql);
			if(list.size()==0) return null;
			for(int i=0;i<list.size();i++){
				HashMap<String, Object> map = (HashMap<String, Object>) list.get(i);
				Share share=new Share();
				share.setShare_id((long)map.get("share_id"));
				share.setPublisher_id((long) map.get("publisher_id"));
				share.setRelease_time((Date) map.get("release_time"));
				share.setDescribe((String) map.get("describe"));
				share.setPoint_of_praise((int) map.get("point_of_praise"));
				share.setComment_number((int) map.get("comment_number"));
				share.setState((int) map.get("state"));
				sharelist.add(share);	
			}
			return sharelist;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	@Override
	public int countShareByUserId(long user_id,int oneself) {
//		SELECT COUNT(1) FROM `share` WHERE `publisher_id`='1500012'
//		SELECT COUNT(1) FROM `share` WHERE `publisher_id`='1500012' AND `state`!='2'
		String sql;
		if(oneself==0){
			sql = "SELECT COUNT(1) FROM `share` "
					+ "WHERE `publisher_id`='"+user_id+"'";
		}else{
			sql = "SELECT COUNT(1) FROM `share` "
					+ "WHERE `publisher_id`='"+user_id+"' AND `state`!='2'";
		}
//		System.out.println(sql);
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
	public List<Share> findShareByUserId(long user_id, int begin, int size,int oneself) {
//		SELECT `share_id`,`publisher_id`,`release_time`,`describe`,
//		`point_of_praise`,`comment_number`,`state` 
//		FROM `share` 
//		WHERE `publisher_id`='1500016'
//		ORDER BY `release_time` DESC LIMIT 0,20
		String sql;
		if(oneself==0){
			sql="SELECT `share_id`,`publisher_id`,`release_time`,`describe`,"
					+ "`point_of_praise`,`comment_number`,`state` FROM `share` "
					+ "WHERE `publisher_id`='"+user_id+"'"
					+ "ORDER BY `release_time` DESC "
					+ "LIMIT "+begin+","+size+"";
		}else{
			sql="SELECT `share_id`,`publisher_id`,`release_time`,`describe`,"
					+ "`point_of_praise`,`comment_number`,`state` FROM `share` "
					+ "WHERE `publisher_id`='"+user_id+"' AND `state`!='2'"
					+ "ORDER BY `release_time` DESC "
					+ "LIMIT "+begin+","+size+"";
		}
//		System.out.println(sql);
		try {
			List<Share> sharelist = new ArrayList<Share>();
			List<Map<String, Object>> list =jdbcTemplate.queryForList(sql);
			if(list.size()==0) return null;
			for(int i=0;i<list.size();i++){
				HashMap<String, Object> map = (HashMap<String, Object>) list.get(i);
				Share share=new Share();
				share.setShare_id((long)map.get("share_id"));
				share.setPublisher_id((long) map.get("publisher_id"));
				share.setRelease_time((Date) map.get("release_time"));
				share.setDescribe((String) map.get("describe"));
				share.setPoint_of_praise((int) map.get("point_of_praise"));
				share.setComment_number((int) map.get("comment_number"));
				share.setState((int) map.get("state"));
				sharelist.add(share);	
			}
			return sharelist;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int countShareByKey(int state, String key) {
//		SELECT COUNT(1) FROM `share`
//		WHERE `describe` LIKE '%h%' 
//		AND (`state`='0' OR `state`='1')
//		AND publisher_id IN 
//		(SELECT `user_id` FROM `user` WHERE `jurisdiction`='0')
		String sql;
		if(state==0){
			sql="SELECT COUNT(1) FROM `share` WHERE `describe` "
				+ "LIKE '%"+key+"%' AND `state`='0' "
				+ "AND `publisher_id` IN "
				+ "(SELECT `user_id` FROM `user` WHERE `jurisdiction`='0')";
		}else if(state==1){
			sql="SELECT COUNT(1) FROM `share` WHERE `describe` "
				+ "LIKE '%"+key+"%' AND `state`='1' "
				+ "AND `publisher_id` IN "
				+ "(SELECT `user_id` FROM `user` WHERE `jurisdiction`='0')";
		}else if(state==2){
			sql="SELECT COUNT(1) FROM `share` WHERE `describe` "
				+ "LIKE '%"+key+"%' AND `state`='2' "
				+ "AND `publisher_id` IN "
				+ "(SELECT `user_id` FROM `user` WHERE `jurisdiction`='0')";
		}else if(state==3){
			sql="SELECT COUNT(1) FROM `share` WHERE `describe` "
				+ "LIKE '%"+key+"%' AND (`state`='0' OR `state`='1') "
				+ "AND `publisher_id` IN "
				+ "(SELECT `user_id` FROM `user` WHERE `jurisdiction`='0')";
		}else{
			sql="SELECT COUNT(1) FROM `share` WHERE `describe` "
				+ "LIKE '%"+key+"%'";
		}
//		System.out.println(sql);
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
	public List<Share> findShareByKey(int state, String key, int begin, int size) {
//		SELECT `share_id`,`publisher_id`,`release_time`,`describe`,
//		`point_of_praise`,`comment_number`,`state` FROM `share`
//		WHERE `describe` LIKE '%j%' 
//		AND (`state`='0' OR `state`='1')
//		AND publisher_id IN 
//		(SELECT `user_id` FROM `user` WHERE `jurisdiction`='0')
//		ORDER BY `release_time` DESC LIMIT 0,20
		String sql;
		if(state==0){
			sql="SELECT `share_id`,`publisher_id`,`release_time`,`describe`,"
				+ "`point_of_praise`,`comment_number`,`state` FROM `share`"
				+ "WHERE `describe` LIKE '%"+key+"%' "
				+ "AND `state`='0' "
				+ "AND publisher_id IN (SELECT `user_id` FROM `user` "
				+ "WHERE `jurisdiction`='0') "
				+ "ORDER BY `release_time` "
				+ "DESC LIMIT "+begin+","+size+"";
		}else if(state==1){
			sql="SELECT `share_id`,`publisher_id`,`release_time`,`describe`,"
				+ "`point_of_praise`,`comment_number`,`state` FROM `share`"
				+ "WHERE `describe` LIKE '%"+key+"%' "
				+ "AND `state`='1' "
			    + "AND publisher_id IN (SELECT `user_id` FROM `user` "
				+ "WHERE `jurisdiction`='0') "
				+ "ORDER BY `release_time` "
				+ "DESC LIMIT "+begin+","+size+"";
		}else if(state==2){
			sql="SELECT `share_id`,`publisher_id`,`release_time`,`describe`,"
				+ "`point_of_praise`,`comment_number`,`state` FROM `share`"
				+ "WHERE `describe` LIKE '%"+key+"%' "
				+ "AND `state`='2' "
			    + "AND publisher_id IN (SELECT `user_id` FROM `user` "
				+ "WHERE `jurisdiction`='0') "
				+ "ORDER BY `release_time` "
				+ "DESC LIMIT "+begin+","+size+"";
		}else if(state==3){
			sql="SELECT `share_id`,`publisher_id`,`release_time`,`describe`,"
			    + "`point_of_praise`,`comment_number`,`state` FROM `share`"
			    + "WHERE `describe` LIKE '%"+key+"%' "
			    + "AND (`state`='0' OR `state`='1') "
			    + "AND publisher_id IN (SELECT `user_id` FROM `user` "
			    + "WHERE `jurisdiction`='0') "
			    + "ORDER BY `release_time` "
			    + "DESC LIMIT "+begin+","+size+"";
		}else{
			sql="SELECT `share_id`,`publisher_id`,`release_time`,`describe`,"
				+ "`point_of_praise`,`comment_number`,`state` FROM `share`"
				+ "WHERE `describe` LIKE '%"+key+"%' "
				+ "ORDER BY `release_time` "
				+ "DESC LIMIT "+begin+","+size+"";
		}
//		System.out.println(sql);
		try {
			List<Share> sharelist = new ArrayList<Share>();
			List<Map<String, Object>> list =jdbcTemplate.queryForList(sql);
			if(list.size()==0) return null;
			for(int i=0;i<list.size();i++){
				HashMap<String, Object> map = (HashMap<String, Object>) list.get(i);
				Share share=new Share();
				share.setShare_id((long)map.get("share_id"));
				share.setPublisher_id((long) map.get("publisher_id"));
				share.setRelease_time((Date) map.get("release_time"));
				share.setDescribe((String) map.get("describe"));
				share.setPoint_of_praise((int) map.get("point_of_praise"));
				share.setComment_number((int) map.get("comment_number"));
				share.setState((int) map.get("state"));
				sharelist.add(share);	
			}
			return sharelist;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	
	@Override
	public int countShareByPointAndDays(Date date, int days, int state) {
//		SELECT COUNT(1) FROM `share` 
//		LEFT JOIN ((SELECT share_id,COUNT(1) AS `num` FROM `pointpraise` 
//		WHERE praise_time >= DATE_FORMAT(DATE_SUB('2016-11-17 00:22:22',INTERVAL 0 DAY),'%Y-%m-%d') 
//		AND praise_time <= DATE_FORMAT(DATE_ADD('2016-11-17 00:22:22',INTERVAL 5 DAY),'%Y-%m-%d') 
//		GROUP BY share_id ) AS T )
//		ON `share`.`share_id`=T.`share_id`
//		WHERE (`state`='1' OR `state`='0')
//		AND `share`.`publisher_id` NOT IN ( SELECT user_id FROM `user` WHERE `jurisdiction`='1')
		String sql;
		if(days<0){
			days=20000;
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
		String datestr=df.format(date);
		if(state==0){
			sql="SELECT COUNT(1) FROM `share` LEFT JOIN "
				+ "((SELECT share_id,COUNT(1) AS `num` FROM `pointpraise` "
				+ "WHERE praise_time >= DATE_FORMAT(DATE_SUB('"+datestr
				+"',INTERVAL "+days+" DAY),'%Y-%m-%d') "
				+ "AND praise_time <= DATE_FORMAT(DATE_ADD('"+datestr
				+"',INTERVAL 1 DAY),'%Y-%m-%d') "
				+ "GROUP BY share_id ) AS T ) ON `share`.`share_id`=T.`share_id` "
				+ "WHERE `state`='0' AND `share`.`publisher_id` "
				+ "NOT IN ( SELECT user_id FROM `user` WHERE `jurisdiction`='1')";
			
		}else if(state==1){
			sql="SELECT COUNT(1) FROM `share` LEFT JOIN "
				+ "((SELECT share_id,COUNT(1) AS `num` FROM `pointpraise` "
				+ "WHERE praise_time >= DATE_FORMAT(DATE_SUB('"+datestr
				+"',INTERVAL "+days+" DAY),'%Y-%m-%d') "
				+ "AND praise_time <= DATE_FORMAT(DATE_ADD('"+datestr
				+"',INTERVAL 1 DAY),'%Y-%m-%d') "
				+ "GROUP BY share_id ) AS T ) ON `share`.`share_id`=T.`share_id` "
				+ "WHERE `state`='1' AND `share`.`publisher_id` "
				+ "NOT IN ( SELECT user_id FROM `user` WHERE `jurisdiction`='1')";
		}else if(state==2){
			sql="SELECT COUNT(1) FROM `share` LEFT JOIN "
				+ "((SELECT share_id,COUNT(1) AS `num` FROM `pointpraise` "
				+ "WHERE praise_time >= DATE_FORMAT(DATE_SUB('"+datestr
				+"',INTERVAL "+days+" DAY),'%Y-%m-%d') "
				+ "AND praise_time <= DATE_FORMAT(DATE_ADD('"+datestr
				+"',INTERVAL 1 DAY),'%Y-%m-%d') "
				+ "GROUP BY share_id ) AS T ) ON `share`.`share_id`=T.`share_id` "
				+ "WHERE `state`='2' AND `share`.`publisher_id` "
				+ "NOT IN ( SELECT user_id FROM `user` WHERE `jurisdiction`='1')";
		}else if(state==3){
			sql="SELECT COUNT(1) FROM `share` LEFT JOIN "
				+ "((SELECT share_id,COUNT(1) AS `num` FROM `pointpraise` "
				+ "WHERE praise_time >= DATE_FORMAT(DATE_SUB('"+datestr
				+"',INTERVAL "+days+" DAY),'%Y-%m-%d') "
				+ "AND praise_time <= DATE_FORMAT(DATE_ADD('"+datestr
				+"',INTERVAL 1 DAY),'%Y-%m-%d') "
				+ "GROUP BY share_id ) AS T ) ON `share`.`share_id`=T.`share_id` "
				+ "WHERE (`state`='0' OR `state`='1') AND `share`.`publisher_id` "
				+ "NOT IN ( SELECT user_id FROM `user` WHERE `jurisdiction`='1')";
		}else{
			sql="SELECT COUNT(1) FROM `share` LEFT JOIN "
				+ "((SELECT share_id,COUNT(1) AS `num` FROM `pointpraise` "
				+ "WHERE praise_time >= DATE_FORMAT(DATE_SUB('"+datestr
				+"',INTERVAL "+days+" DAY),'%Y-%m-%d') "
				+ "AND praise_time <= DATE_FORMAT(DATE_ADD('"+datestr
				+"',INTERVAL 1 DAY),'%Y-%m-%d') "
				+ "GROUP BY share_id ) AS T ) ON `share`.`share_id`=T.`share_id` ";
		}
//		System.out.println(sql);
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
	public List<Share> findShareByPointAndDays(Date date, int days, int state,
			int begin, int size) {
//		SELECT `share`.`share_id`,`publisher_id`,`release_time`,`describe`, 
//		`point_of_praise`,`comment_number`,`state`,IFNULL(`num`,0) AS `num` FROM `share` 
//		LEFT JOIN ((SELECT share_id, COUNT(1) AS `num` FROM `pointpraise` 
//		WHERE praise_time >= DATE_FORMAT(DATE_SUB('2016-11-17 00:22:22',INTERVAL 20 DAY),'%Y-%m-%d') 
//		AND praise_time <= DATE_FORMAT(DATE_ADD('2016-11-17 00:22:22',INTERVAL 5 DAY),'%Y-%m-%d') 
//		GROUP BY share_id ) AS T )
//		ON `share`.`share_id`=T.`share_id`
//		WHERE (`state`='1' OR `state`='0') 
//		AND `share`.`publisher_id` NOT IN ( SELECT user_id FROM `user` WHERE `jurisdiction`='1')
//		ORDER BY `num` DESC ,`release_time` DESC LIMIT 0,2
		String sql;
		if(days<0){
			days=20000;
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
		String datestr=df.format(date);
		if(state==0){
			sql="SELECT `share`.`share_id`,`publisher_id`,`release_time`,"
					+ "`describe`,  `point_of_praise`,`comment_number`,"
					+ "`state`,IFNULL(`num`,0) AS `num` FROM `share`"
					+ " LEFT JOIN ((SELECT share_id, COUNT(1) AS `num` "
					+ "FROM `pointpraise`  WHERE praise_time >= "
					+ "DATE_FORMAT(DATE_SUB('"+datestr
					+"',INTERVAL "+days+" DAY),'%Y-%m-%d')  "
					+ "AND praise_time <= DATE_FORMAT(DATE_ADD('"+datestr
					+"',INTERVAL 1 DAY),'%Y-%m-%d')  GROUP BY share_id ) AS T ) "
					+ "ON `share`.`share_id`=T.`share_id` "
					+ "WHERE `state`='0' "
					+ "AND `share`.`publisher_id` NOT IN "
					+ "( SELECT user_id FROM `user` WHERE `jurisdiction`='1') "
					+ "ORDER BY `num` DESC ,`release_time` DESC "
					+ "LIMIT "+begin+","+size+"";
		}else if(state==1){
			sql="SELECT `share`.`share_id`,`publisher_id`,`release_time`,"
					+ "`describe`,  `point_of_praise`,`comment_number`,"
					+ "`state`,IFNULL(`num`,0) AS `num` FROM `share`"
					+ " LEFT JOIN ((SELECT share_id, COUNT(1) AS `num` "
					+ "FROM `pointpraise`  WHERE praise_time >= "
					+ "DATE_FORMAT(DATE_SUB('"+datestr
					+"',INTERVAL "+days+" DAY),'%Y-%m-%d')  "
					+ "AND praise_time <= DATE_FORMAT(DATE_ADD('"+datestr
					+"',INTERVAL 1 DAY),'%Y-%m-%d')  GROUP BY share_id ) AS T ) "
					+ "ON `share`.`share_id`=T.`share_id` "
					+ "WHERE `state`='1'  "
					+ "AND `share`.`publisher_id` NOT IN "
					+ "( SELECT user_id FROM `user` WHERE `jurisdiction`='1') "
					+ "ORDER BY `num` DESC ,`release_time` DESC "
					+ "LIMIT "+begin+","+size+"";
		}else if(state==2){
			sql="SELECT `share`.`share_id`,`publisher_id`,`release_time`,"
					+ "`describe`,  `point_of_praise`,`comment_number`,"
					+ "`state`,IFNULL(`num`,0) AS `num` FROM `share`"
					+ " LEFT JOIN ((SELECT share_id, COUNT(1) AS `num` "
					+ "FROM `pointpraise`  WHERE praise_time >= "
					+ "DATE_FORMAT(DATE_SUB('"+datestr
					+"',INTERVAL "+days+" DAY),'%Y-%m-%d')  "
					+ "AND praise_time <= DATE_FORMAT(DATE_ADD('"+datestr
					+"',INTERVAL 1 DAY),'%Y-%m-%d')  GROUP BY share_id ) AS T ) "
					+ "ON `share`.`share_id`=T.`share_id` "
					+ "WHERE `state`='2'  "
					+ "AND `share`.`publisher_id` NOT IN "
					+ "( SELECT user_id FROM `user` WHERE `jurisdiction`='1') "
					+ "ORDER BY `num` DESC ,`release_time` DESC "
					+ "LIMIT "+begin+","+size+"";
		}else if(state==3){
			sql="SELECT `share`.`share_id`,`publisher_id`,`release_time`,"
					+ "`describe`,  `point_of_praise`,`comment_number`,"
					+ "`state`,IFNULL(`num`,0) AS `num` FROM `share`"
					+ " LEFT JOIN ((SELECT share_id, COUNT(1) AS `num` "
					+ "FROM `pointpraise`  WHERE praise_time >= "
					+ "DATE_FORMAT(DATE_SUB('"+datestr
					+"',INTERVAL "+days+" DAY),'%Y-%m-%d')  "
					+ "AND praise_time <= DATE_FORMAT(DATE_ADD('"+datestr
					+"',INTERVAL 1 DAY),'%Y-%m-%d')  GROUP BY share_id ) AS T ) "
					+ "ON `share`.`share_id`=T.`share_id` "
					+ "WHERE (`state`='1' OR `state`='0')  "
					+ "AND `share`.`publisher_id` NOT IN "
					+ "( SELECT user_id FROM `user` WHERE `jurisdiction`='1') "
					+ "ORDER BY `num` DESC ,`release_time` DESC "
					+ "LIMIT "+begin+","+size+"";
		}else{
			sql="SELECT `share`.`share_id`,`publisher_id`,`release_time`,"
					+ "`describe`,  `point_of_praise`,`comment_number`,"
					+ "`state`,IFNULL(`num`,0) AS `num` FROM `share`"
					+ " LEFT JOIN ((SELECT share_id, COUNT(1) AS `num` "
					+ "FROM `pointpraise`  WHERE praise_time >= "
					+ "DATE_FORMAT(DATE_SUB('"+datestr
					+"',INTERVAL "+days+" DAY),'%Y-%m-%d')  "
					+ "AND praise_time <= DATE_FORMAT(DATE_ADD('"+datestr
					+"',INTERVAL 1 DAY),'%Y-%m-%d')  GROUP BY share_id ) AS T ) "
					+ "ON `share`.`share_id`=T.`share_id` "
					+ "ORDER BY `num` DESC ,`release_time` DESC "
					+ "LIMIT "+begin+","+size+"";
		}
//		System.out.println(sql);
		try {
			List<Share> sharelist = new ArrayList<Share>();
			List<Map<String, Object>> list =jdbcTemplate.queryForList(sql);
			if(list.size()==0) return null;
			for(int i=0;i<list.size();i++){
				HashMap<String, Object> map = (HashMap<String, Object>) list.get(i);
				Share share=new Share();
				share.setShare_id((long)map.get("share_id"));
				share.setPublisher_id((long) map.get("publisher_id"));
				share.setRelease_time((Date) map.get("release_time"));
				share.setDescribe((String) map.get("describe"));
//			    share.setPoint_of_praise((int) map.get("point_of_praise"));
				long num=(long) map.get("num");
				share.setPoint_of_praise((int) num);
				share.setComment_number((int) map.get("comment_number"));
				share.setState((int) map.get("state"));
				sharelist.add(share);	
			}
			return sharelist;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Share> findShareByUserIdAndPoint(long user_id, int begin,
			int size) {
//		SELECT `share`.`share_id`,`publisher_id`,`release_time`,`describe`,  `point_of_praise`,`comment_number`,`state`
//		FROM `share` LEFT JOIN `pointpraise` ON `share`.`share_id`=`pointpraise`.`share_id` 
//		WHERE `pointpraise`.`user_id`='1500013' AND `share`.`state`!='2' AND `publisher_id` NOT IN 
//		(SELECT `user_id` FROM `user` WHERE `jurisdiction`='1')
//		ORDER BY `release_time` DESC LIMIT 0,20
		String sql="SELECT `share`.`share_id`,`publisher_id`,`release_time`,"
				+ "`describe`,  `point_of_praise`,`comment_number`,`state` "
				+ "FROM `share` LEFT JOIN `pointpraise` "
				+ "ON `share`.`share_id`=`pointpraise`.`share_id`  "
				+ "WHERE `pointpraise`.`user_id`='"+user_id
				+"' AND `share`.`state`!='2' AND `publisher_id` NOT IN "
				+ " (SELECT `user_id` FROM `user` WHERE `jurisdiction`='1') "
				+ "ORDER BY `release_time` DESC LIMIT "+begin+","+size+"";
//		System.out.println(sql);
		try {
			List<Share> sharelist = new ArrayList<Share>();
			List<Map<String, Object>> list =jdbcTemplate.queryForList(sql);
			if(list.size()==0) return null;
			for(int i=0;i<list.size();i++){
				HashMap<String, Object> map = (HashMap<String, Object>) list.get(i);
				Share share=new Share();
				share.setShare_id((long)map.get("share_id"));
				share.setPublisher_id((long) map.get("publisher_id"));
				share.setRelease_time((Date) map.get("release_time"));
				share.setDescribe((String) map.get("describe"));
				share.setPoint_of_praise((int) map.get("point_of_praise"));
				share.setComment_number((int) map.get("comment_number"));
				share.setState((int) map.get("state"));
				sharelist.add(share);	
			}
			return sharelist;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Share> findShareByUserIdAndCollection(long user_id, int begin,
			int size) {
//		SELECT `share`.`share_id`,`publisher_id`,`release_time`,`describe`,  `point_of_praise`,`comment_number`,`state` 
//		FROM `share` LEFT JOIN `collectionshare` ON `share`.`share_id`=`collectionshare`.`share_id` 
//		WHERE `collectionshare`.`user_id`='1500016' AND `share`.`state`!='2' AND `publisher_id` NOT IN 
//		(SELECT `user_id` FROM `user` WHERE `jurisdiction`='1')
//		ORDER BY `release_time` DESC LIMIT 0,20
		String sql="SELECT `share`.`share_id`,`publisher_id`,`release_time`,"
				+ "`describe`,  `point_of_praise`,`comment_number`,`state` "
				+ "FROM `share` LEFT JOIN `collectionshare` "
				+ "ON `share`.`share_id`=`collectionshare`.`share_id` "
				+ "WHERE `collectionshare`.`user_id`='"+user_id+"' AND `share`.`state`!='2' AND `publisher_id` NOT IN "
                + " (SELECT `user_id` FROM `user` WHERE `jurisdiction`='1')"
				+ "ORDER BY `release_time` DESC LIMIT "+begin+","+size;
//		System.out.println(sql);
		try {
			List<Share> sharelist = new ArrayList<Share>();
			List<Map<String, Object>> list =jdbcTemplate.queryForList(sql);
			if(list.size()==0) return null;
			for(int i=0;i<list.size();i++){
				HashMap<String, Object> map = (HashMap<String, Object>) list.get(i);
				Share share=new Share();
				share.setShare_id((long)map.get("share_id"));
				share.setPublisher_id((long) map.get("publisher_id"));
				share.setRelease_time((Date) map.get("release_time"));
				share.setDescribe((String) map.get("describe"));
				share.setPoint_of_praise((int) map.get("point_of_praise"));
				share.setComment_number((int) map.get("comment_number"));
				share.setState((int) map.get("state"));
				sharelist.add(share);	
			}
			return sharelist;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Share> findShareByUserIdAndComment(long user_id, int begin,
			int size) {
//		SELECT `share`.`share_id`,`publisher_id`,`release_time`,`describe`,  `point_of_praise`,`comment_number`,`state` 
//		FROM `share` RIGHT JOIN `comment` ON `share`.`share_id`=`comment`.`share_id` 
//		WHERE `comment`.`user_id`='1500016' AND `share`.`state`!='2' AND `publisher_id` NOT IN 
//		(SELECT `user_id` FROM `user` WHERE `jurisdiction`='1')
//		GROUP BY share_id
//		ORDER BY `comment_time` DESC,`release_time` DESC 
//		LIMIT 0,20
		String sql="SELECT `share`.`share_id`,`publisher_id`,`release_time`,"
				+ "`describe`,  `point_of_praise`,`comment_number`,`state`"
				+ " FROM `share` RIGHT JOIN `comment` ON "
				+ "`share`.`share_id`=`comment`.`share_id` "
				+ "WHERE `comment`.`user_id`='"+user_id
				+"' AND `share`.`state`!='2' AND `publisher_id` NOT IN "
                + "(SELECT `user_id` FROM `user` WHERE `jurisdiction`='1') "
				+ "GROUP BY share_id "
				+ "ORDER BY `comment_time` DESC,`release_time` DESC "
				+ "LIMIT "+begin+","+size+"";
//		System.out.println(sql);
		try {
			List<Share> sharelist = new ArrayList<Share>();
			List<Map<String, Object>> list =jdbcTemplate.queryForList(sql);
			if(list.size()==0) return null;
			for(int i=0;i<list.size();i++){
				HashMap<String, Object> map = (HashMap<String, Object>) list.get(i);
				Share share=new Share();
				share.setShare_id((long)map.get("share_id"));
				share.setPublisher_id((long) map.get("publisher_id"));
				share.setRelease_time((Date) map.get("release_time"));
				share.setDescribe((String) map.get("describe"));
				share.setPoint_of_praise((int) map.get("point_of_praise"));
				share.setComment_number((int) map.get("comment_number"));
				share.setState((int) map.get("state"));
				sharelist.add(share);	
			}
			return sharelist;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	@Override
	public List<User> findUserByShareIdOfPoint(long share_id){
//		SELECT `user`.`user_id`,`password`,`nickname`,
//		`mailbox`,`jurisdiction`,`head_portrait`
//		FROM `pointpraise` LEFT JOIN `user` 
//		ON `pointpraise`.`user_id`=`user`.`user_id`
//		WHERE `share_id`='8'
		String sql = "SELECT `user`.`user_id`,`password`,`nickname`,"
				+ "`mailbox`,`jurisdiction`,`head_portrait` "
				+ "FROM `pointpraise` LEFT JOIN `user`  "
				+ "ON `pointpraise`.`user_id`=`user`.`user_id` "
				+ "WHERE `share_id`='"+share_id+"'";
		try {
			List<User> userlist = new ArrayList<User>();
			List<Map<String, Object>> list =jdbcTemplate.queryForList(sql);
			if(list.size()==0) return null;
			for(int i=0;i<list.size();i++){
				HashMap<String, Object> map = (HashMap<String, Object>) list.get(i);
				User user = new User();
				user.setUser_id((long) map.get("user_id"));
				user.setPassword((String) map.get("password"));
				user.setNickname((String) map.get("nickname"));
				user.setMailbox((String) map.get("mailbox"));
				user.setJurisdiction((int) map.get("jurisdiction"));
				user.setHead_portrait((String) map.get("head_portrait"));
				userlist.add(user);
			}
			return userlist;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
