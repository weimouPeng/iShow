package com.scau.dao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.scau.dao.ICommentDao;
import com.scau.entity.Administrator;
import com.scau.entity.Comment;

public class CommentDaoImpl implements ICommentDao{
	private JdbcTemplate jdbcTemplate;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Comment addComment(Comment comment) {
		// TODO Auto-generated method stub
		//Timestamp CommentTime = new Timestamp(comment.getComment_time().getTime());
		try{
		jdbcTemplate.update("insert into comment(share_id,user_id,comment_time,be_commented_id,comment_content) values(?,?,?,?,?)", 
				new Object[]{comment.getShare_id(),comment.getUser_id(),comment.getComment_time(),comment.getBe_commented_id(),comment.getComment_content()},
		        new int[]{java.sql.Types.INTEGER,java.sql.Types.INTEGER,java.sql.Types.TIMESTAMP,java.sql.Types.INTEGER,java.sql.Types.VARCHAR});
		String sql = "select * from comment where share_id = "+comment.getShare_id()+ " and user_id = "+comment.getUser_id()+" and comment_time = "+comment.getComment_time();
		HashMap<String,Object> map = (HashMap<String, Object>) jdbcTemplate.queryForMap(sql);
		Long id =(Long) map.get("serial_number");
		comment.setSerial_number(id);
		return comment;
		}catch(Exception e){
			System.out.println("There are some error in addComment");
			e.printStackTrace();
		}
		return comment;
	}

	@Override
	public int deleteComment(long serial_number) {
		int count = jdbcTemplate.update("delete from comment where serial_number = ? ",new Object[]{serial_number},
		        new int[]{java.sql.Types.INTEGER});
		return count;
	}

	@Override
	public Comment findCommentBySerial(long serial_number) {
		return (Comment) jdbcTemplate.queryForObject(  
                "select * from comment where serial_number = ?",   
                new Object[]{serial_number},   
                new RowMapper<Object>(){  
                    @Override  
                    public Object mapRow(ResultSet rs,int rowNum)throws SQLException {
                    	Comment comment  = new Comment();  
                    	comment.setSerial_number(rs.getInt("serial_number"));  
                    	comment.setShare_id(rs.getInt("share_id"));
                    	comment.setUser_id(rs.getInt("user_id"));
						comment.setComment_time(rs.getTimestamp("Comment_time"));
                    	comment.setBe_commented_id(rs.getInt("be_commented_id"));
                    	comment.setComment_content(rs.getString("Comment_content"));
                        return comment;  
                    }  
              
        });
	}

	@Override
	public int countCommentByUserId(long user_id) {
		int count=jdbcTemplate.queryForObject("SELECT COUNT(distinct share_id) FROM comment where user_id = ?",new Object[] {user_id},Integer.class); 
		return count;
	}

	@Override
	public int countCommentByShareId(long share_id) {
		int count=jdbcTemplate.queryForObject("SELECT COUNT(*) FROM comment where share_id = ?",new Object[] {share_id},Integer.class); 
		return count;
	}

	@Override
	public List<Comment> findCommentByShareId(long share_id) {
		// TODO Auto-generated method stub
		List<Comment> comments=new ArrayList<Comment>();
		List rows = jdbcTemplate.queryForList("SELECT * FROM comment where share_id = ? limit 0,10000", new Object[] {share_id},new int[]{java.sql.Types.INTEGER});  
		//((Comment)rows.get(0)).toString();
		Iterator it = rows.iterator();  
		while(it.hasNext()) {
			HashMap<String, Object> CommentMap = (HashMap<String, Object>) it.next();  
			Comment comment  = new Comment();  
        	comment.setSerial_number((long) CommentMap.get("serial_number"));  
        	comment.setShare_id((long)CommentMap.get("share_id"));
        	comment.setUser_id((long)CommentMap.get("user_id"));
			comment.setComment_time((Timestamp) CommentMap.get("Comment_time"));
			if(CommentMap.get("be_commented_id")==null){
				comment.setBe_commented_id(0);
			}else{
				comment.setBe_commented_id((long) CommentMap.get("be_commented_id"));
			}
        	comment.setComment_content((String) CommentMap.get("Comment_content"));
        	comments.add(comment);
        	System.out.print(comment.toString());
		} 
		return comments;
	}

	@Override
	public List<Comment> findCommentByUserId(long user_id) {
		// TODO Auto-generated method stub
		String sql = "select * from comment where user_id = "+user_id;
		ArrayList<Comment> ret = new ArrayList<Comment>();
		List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);
		for(Map<String,Object> CommentMap:list){
			Comment comment = new Comment();
			comment.setSerial_number((long) CommentMap.get("serial_number"));  
        	comment.setShare_id((long)CommentMap.get("share_id"));
        	comment.setUser_id((long)CommentMap.get("user_id"));
			comment.setComment_time((Timestamp) CommentMap.get("Comment_time"));
        	comment.setBe_commented_id((long)CommentMap.get("be_commented_id"));
        	comment.setComment_content((String) CommentMap.get("Comment_content"));
        	ret.add(comment);
		}
		return ret;
	}

}
