package com.scau.dao;

import java.util.List;

import com.scau.entity.Keyword;



public interface IKeywordDao {
	/**
	 * 锟斤拷锟斤拷说锟斤拷锟斤拷锟斤拷锟斤拷一锟斤拷锟截硷拷锟斤拷
	 * 锟斤拷锟借定锟斤拷锟截革拷)
	 * @return Keyword
	 * @author 
	 */
	public Keyword addKeyword(String key);
	
	/**
	 * 锟斤拷锟斤拷说锟斤拷锟斤拷删锟斤拷一锟斤拷锟截硷拷锟斤拷
	 * @return 锟斤拷影锟斤拷锟斤拷锟斤拷锟�
	 * @author 
	 */
	public int deleteKeyword(String key);
	
	/**
	 * 锟斤拷锟斤拷说锟斤拷锟斤拷锟斤拷锟斤拷一锟斤拷锟截硷拷锟斤拷锟角凤拷锟斤拷锟�
	 * @return boolean
	 * @author 
	 */
	public boolean checkKeyword(String key);
	
	/**
	 * 锟斤拷锟斤拷说锟斤拷锟斤拷锟斤拷锟介传锟斤拷锟斤拷址锟斤拷欠锟斤拷丶锟斤拷锟�
	 * @return boolean
	 * @author 
	 */
	public boolean checkKeywordInString(String str);
	
	/**
	 * 锟斤拷锟斤拷说锟斤拷锟斤拷全锟斤拷锟截硷拷锟斤拷
	 * @return 锟截硷拷锟斤拷锟叫憋拷
	 * @author 
	 */
	public List<Keyword> findKeyword();
	
	/**
	 * 关键字个数
	 */
	public int countKeyword();
	
	

}
