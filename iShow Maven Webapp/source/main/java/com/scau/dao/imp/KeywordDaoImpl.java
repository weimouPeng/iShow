package com.scau.dao.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.scau.dao.IKeywordDao;
import com.scau.entity.Keyword;

public class KeywordDaoImpl implements IKeywordDao {

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * 方法说明：增加一个关键字
	 * （设定不重复)
	 * @return Keyword
	 * @author 
	 */
	@Override
	public Keyword addKeyword(String key) {
//		INSERT INTO `ishow`.`keyword` (`key`) VALUES ('gg')
		String sql="INSERT INTO `ishow`.`keyword` (`key`) VALUES ('"+key+"')";
		try {
			jdbcTemplate.update(sql);
			Keyword keyword=new Keyword();
			keyword.setKey(key);
			return keyword;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	
	/**
	 * 方法说明：删除一个关键字
	 * @return 受影响的行数
	 * @author 
	 */
	@Override
	public int deleteKeyword(String key) {
//		DELETE FROM `ishow`.`keyword` WHERE `key` = 'gg'
		String sql="DELETE FROM `ishow`.`keyword` WHERE `key` = '"+key+"'";
		try {
			int row=jdbcTemplate.update(sql);
			return row;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return 0;
	}

	
	

	/**
	 * 方法说明：检验一个关键字是否存在
	 * @return boolean
	 * @author 
	 */
	@Override
	public boolean checkKeyword(String key) {
//		SELECT `keyword`.`key` FROM `keyword` WHERE `key`='d'
		String sql="SELECT `keyword`.`key` FROM `keyword` WHERE `key`='"+key+"'";
		try {
			List<Map<String, Object>> list =jdbcTemplate.queryForList(sql);
			if(list.size()!=0){
				return true;
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return false;
	}

	
	

	
	/**
	 * 方法说明：检验传入的字符串是否包含关键字
	 * @return boolean
	 * @author 
	 */
	@Override
	public boolean checkKeywordInString(String str) {
//		SELECT COUNT(1) FROM `keyword` 
//		WHERE 'dddddddddddddddfsgg' LIKE CONCAT("%",`key`,"%")
		String sql="SELECT COUNT(1) FROM `keyword` "
				+ "WHERE '"+str+"' LIKE CONCAT('%',`key`,'%')";
		try {
			Object[] args = new Object[0];
			int sum= (int) jdbcTemplate.queryForObject(sql, args, Integer.class);
			if(sum!=0){
				return true;
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return false;
	}

	
	

	
	
	/**
	 * 方法说明：全部关键字
	 * @return 关键字列表
	 * @author 
	 */
	@Override
	public List<Keyword> findKeyword() {
//		SELECT `key` FROM `keyword`
		String sql="SELECT `key` FROM `keyword`";
		try {
			List<Keyword> keylist = new ArrayList<Keyword>();
			List<Map<String, Object>> list =jdbcTemplate.queryForList(sql);
			if(list.size()==0) return null;
			for(int i=0;i<list.size();i++){
				HashMap<String, Object> map = (HashMap<String, Object>) list.get(i);
				Keyword keyword=new Keyword();
				keyword.setKey((String) map.get("key"));
				keylist.add(keyword);
			}
			return keylist;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	@Override
	public int countKeyword(){
//		SELECT COUNT(1) FROM `keyword`
		String sql = "SELECT COUNT(1) FROM `keyword`";
		try {
			Object[] args = new Object[0];
			int sum= (int) jdbcTemplate.queryForObject(sql, args, Integer.class);
			return sum;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	
	
	
}
