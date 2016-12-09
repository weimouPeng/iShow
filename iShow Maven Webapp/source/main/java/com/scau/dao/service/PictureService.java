package com.scau.dao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scau.dao.IPictureDao;
import com.scau.entity.Picture;



@Service
public class PictureService {
	@Autowired
	private IPictureDao dao;
	
	public Picture addPicture(long share_id, String image_path){
		return dao.addPicture(share_id, image_path);
	}
	
	
	public int deletePicture(long serial_number){
		//É¾³ýÂ·¾¶ÏÂµÄÍ¼Æ¬
		return dao.deletePicture(serial_number);
	}
	
	public List<Picture> findPictureByShareId(long share_id){
		return dao.findPictureByShareId(share_id);
	}
	
	

}
