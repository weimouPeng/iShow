package com.scau.dao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scau.dao.IKeywordDao;
import com.scau.entity.Keyword;





@Service
public class KeywordService {
	@Autowired
	private IKeywordDao dao;
	
	
	
	public Keyword addKeyword(String key){
		if(dao.checkKeyword(key)==true){
			return null;
		}
		return dao.addKeyword(key);
	}
	
	public int deleteKeyword(String key){
		return dao.deleteKeyword(key);
	}
	
	public boolean checkKeywordInString(String str){
		if(str!=null){
			return dao.checkKeywordInString(str);
		}
		return false;
	}
	
	public List<Keyword> findKeyword(){
		return dao.findKeyword();
	}
	
	
	public int countKeyword(){
		return dao.countKeyword();
	}
	
	

}
