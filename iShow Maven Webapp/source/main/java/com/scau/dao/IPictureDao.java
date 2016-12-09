package com.scau.dao;

import java.util.List;

import com.scau.entity.Picture;



public interface IPictureDao {
	/**
	 * 锟斤拷锟斤拷说锟斤拷锟斤拷锟斤拷锟斤拷一锟斤拷图片
	 * 锟斤拷锟斤拷趴锟斤拷锟铰硷拷锟斤拷锟斤拷SELECT LAST_INSERT_ID()锟矫碉拷
	 *   锟借定share_id锟斤拷image_path锟斤拷锟揭拷螅锟斤拷锟揭拷锟斤拷椋�
	 * @return Picture
	 * @author 
	 */
	public Picture addPicture(long share_id,String image_path);
	
	/**
	 * 锟斤拷锟斤拷说锟斤拷锟斤拷删锟斤拷一锟斤拷图片
	 * 锟斤拷锟借定图片路锟斤拷锟铰碉拷图片锟窖撅拷删锟斤拷直锟斤拷删锟斤拷锟斤拷菘锟斤拷录锟斤拷锟缴ｏ拷
	 * @return 锟斤拷影锟斤拷锟斤拷锟斤拷锟�
	 * @author 
	 */
	public int deletePicture(long serial_number);
	
	/**
	 * 锟斤拷锟斤拷说锟斤拷锟斤拷锟斤拷锟斤拷图片锟斤拷息
	 * @return Picture
	 * @author 
	 */
	public Picture findPicture(long serial_number);
	
	/**
	 * 锟斤拷锟斤拷说锟斤拷锟斤拷锟斤拷莘锟斤拷锟絠d锟斤拷询图片
	 * @return 图片锟叫憋拷
	 * @author 
	 */
	public List<Picture> findPictureByShareId(long share_id);
	
	
	

}
