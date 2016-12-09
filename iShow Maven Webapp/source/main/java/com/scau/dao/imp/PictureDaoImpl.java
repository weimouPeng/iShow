package com.scau.dao.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.scau.dao.IPictureDao;
import com.scau.entity.Picture;

public class PictureDaoImpl implements IPictureDao {

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	
	
	/**
	 * ����˵��������һ��ͼƬ
	 * ����ſ���¼�����SELECT LAST_INSERT_ID()�õ�
	 *   �趨share_id��image_path����Ҫ�󣬲���Ҫ���飩
	 * @return Picture
	 * @author 
	 */
	@Override
	public Picture addPicture(long share_id, String image_path) {
//		INSERT INTO `ishow`.`picture` (`share_id`, `image_path`) VALUES ('8', 'c:\\mm'); 
		String sql="INSERT INTO `ishow`.`picture` (`share_id`, `image_path`) "
				+ "VALUES ('"+share_id+"', '"+image_path+"'); ";
		try {
			jdbcTemplate.update(sql);
//		    SELECT MAX(serial_number) FROM `picture` WHERE `share_id`='8'
			String sql_1="SELECT MAX(serial_number) "
					+ "FROM `picture` WHERE `share_id`='"+share_id+"'";
			Object[] args = new Object[0];
			long num= (long) jdbcTemplate.queryForObject(sql_1, args, Long.class);
			Picture picture=new Picture();
			picture.setSerial_number(num);
			picture.setShare_id(share_id);
			picture.setImage_path(image_path);
			return picture;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	

	
	
	
	/**
	 * ����˵����ɾ��һ��ͼƬ
	 * ���趨ͼƬ·���µ�ͼƬ�Ѿ�ɾ����ֱ��ɾ�����ݿ��¼���ɣ�
	 * @return ��Ӱ�������
	 * @author 
	 */
	@Override
	public int deletePicture(long serial_number) {
//		DELETE FROM `ishow`.`picture` WHERE `serial_number` = '11';
		String sql="DELETE FROM `ishow`.`picture` WHERE `serial_number` = '"+serial_number+"';";
		try {
			int row=jdbcTemplate.update(sql);
			return row;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return 0;
	}

	
	
	
	/**
	 * ����˵��������ͼƬ��Ϣ
	 * @return Picture
	 * @author 
	 */
	@Override
	public Picture findPicture(long serial_number) {
//		SELECT `serial_number`,`share_id`,`image_path` FROM `picture` WHERE `serial_number`='10'
		String sql="SELECT `serial_number`,`share_id`,`image_path` "
				+ "FROM `picture` WHERE `serial_number`='"+serial_number+"'";
		try {
			List<Map<String, Object>> list =jdbcTemplate.queryForList(sql);
			if(list.size()==0) return null;
			HashMap<String, Object> map = (HashMap<String, Object>) list.get(0);
			Picture picture=new Picture();
			picture.setSerial_number((long) map.get("serial_number"));
			picture.setShare_id((long) map.get("share_id"));
			picture.setImage_path((String) map.get("image_path"));
			return picture;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	/**
	 * ����˵�������ݷ���id��ѯͼƬ
	 * @return ͼƬ�б�
	 * @author 
	 */
	public List<Picture> findPictureByShareId(long share_id) {
//		SELECT `serial_number`,`share_id`,`image_path` FROM `picture` WHERE `share_id`='8'
		String sql="SELECT `serial_number`,`share_id`,`image_path` "
				+ "FROM `picture` WHERE `share_id`='"+share_id+"'";
		try {
			List<Picture> picturelist = new ArrayList<Picture>();
			List<Map<String, Object>> list =jdbcTemplate.queryForList(sql);
			if(list.size()==0) return null;
			for(int i=0;i<list.size();i++){
				HashMap<String, Object> map = (HashMap<String, Object>) list.get(i);
				Picture picture = new Picture();
				picture.setSerial_number((long) map.get("serial_number"));
				picture.setShare_id((long) map.get("share_id"));
				picture.setImage_path((String) map.get("image_path"));
				picturelist.add(picture);
			}
			return picturelist;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

}
