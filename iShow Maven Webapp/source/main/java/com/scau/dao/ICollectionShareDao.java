package com.scau.dao;

import java.util.List;

public interface ICollectionShareDao {
	
	
	/**
	 * 锟斤拷锟斤拷说锟斤拷锟斤拷锟斤拷锟斤拷一锟斤拷锟斤拷录
	 * @return 锟斤拷影锟斤拷锟斤拷锟斤拷锟�
	 * @author 
	 */
	public int addCollectionShare(long share_id,long user_id);
	
	
	/**
	 * 锟斤拷锟斤拷说锟斤拷锟斤拷删锟斤拷一锟斤拷锟斤拷录
	 * @return 锟斤拷影锟斤拷锟斤拷锟斤拷锟�
	 * @author
	 */
	public int deleteCollectionShare(long share_id,long user_id);
	
	
	/**
	 * 锟斤拷锟斤拷说锟斤拷锟斤拷锟斤拷锟斤拷没锟絠d统锟狡革拷锟矫伙拷锟秸藏的凤拷锟斤拷锟斤拷
	 * @return int
	 * @author 
	 */
	public int countCollectionShare(long user_id);
	
	
	public List<Long> getCollectShareIdsByUserId(long user_id);  
	
	public boolean checkCollectShare(long share_id,long user_id);
	
	
	
	
}
