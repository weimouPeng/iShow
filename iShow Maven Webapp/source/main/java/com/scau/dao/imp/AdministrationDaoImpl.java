package com.scau.dao.imp;

import java.sql.JDBCType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.scau.dao.IAdministrationDao;
import com.scau.entity.Administrator;

public class AdministrationDaoImpl implements IAdministrationDao{

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public Administrator checkAdmin(Administrator admin) {
		// TODO Auto-generated method stub
		return (Administrator) jdbcTemplate.queryForObject(  
                "select * from administrator where admin_id = ? and password = ?",   
                new Object[]{admin.getAdmin_id(),admin.getPassword()},   
                new RowMapper<Object>(){  
                    @Override  
                    public Object mapRow(ResultSet rs,int rowNum)throws SQLException {
                    	//System.out.print(rs!=null);
                    	Administrator admin  = new Administrator();  
                    	admin.setAdmin_id(rs.getInt("admin_id"));  
                    	admin.setNickname(rs.getString("nickname"));  
                    	admin.setPassword(rs.getString("password"));  
                        return admin;  
                    }  
              
        });
	}
	
	@Override
	public Administrator findAdmin(long id) {
		// TODO Auto-generated method stub
//		String s = String.valueOf(id);
//		Administrator admin = (Administrator) jdbcTemplate.queryForObject("select * from administrator where admin_id = "+id,
//			new RowMapper<Object>(){
//            @Override  
//            public Object mapRow(ResultSet rs,int rowNum)throws SQLException {  
//            	Administrator admin  = new Administrator();  
//            	admin.setAdmin_id(rs.getInt("admin_id"));  
//            	admin.setNickname(rs.getString("nickname"));  
//            	admin.setPassword(rs.getString("password"));  
//                return admin;  
//            }  
//		} );
		try{
		Administrator admin = new Administrator();
		String sql = "select * from administrator where admin_id = "+id;
		Map<String,Object> map = jdbcTemplate.queryForMap(sql);
		if(map==null||map.size()==0)
			return null;
	    admin.setAdmin_id((long) map.get("admin_id"));
	    admin.setNickname((String) map.get("nickname"));
	    admin.setPassword((String) map.get("password"));
		return admin;
		}catch(Exception ex){
			System.out.println("administrator not found!");
		}
		return null;
	}

	@Override
	public Administrator updataAdmin(Administrator admin) {
		jdbcTemplate.update("update administrator set nickname = ?,password = ? where admin_id =?", new Object[]{admin.getNickname(),admin.getPassword(),admin.getAdmin_id()},
		        new int[]{java.sql.Types.VARCHAR,java.sql.Types.VARCHAR, java.sql.Types.INTEGER});
		return findAdmin(admin.getAdmin_id());
	}

	@Override
	public boolean banUser(long user_id) {
		// TODO Auto-generated method stub
		try{
		String sql = "update user set jurisdiction=1 where user_id="+user_id;
		int flag = jdbcTemplate.update(sql);
		return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public boolean allowUser(long user_id) {
		// TODO Auto-generated method stub
		try{
		String sql = "update user set jurisdiction=0 where user_id="+user_id;
		int flag = jdbcTemplate.update(sql);
		return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

}
