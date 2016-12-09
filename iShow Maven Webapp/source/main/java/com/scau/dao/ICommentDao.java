package com.scau.dao;

import java.util.List;

import com.scau.entity.Comment;



public interface ICommentDao {

	/**
	 * 锟斤拷锟斤拷说锟斤拷锟斤拷锟斤拷锟斤拷一锟斤拷锟斤拷锟斤拷
	 * 锟斤拷锟斤拷锟桔碉拷锟斤拷趴锟斤拷锟斤拷锟铰硷拷锟斤拷锟斤拷SELECT LAST_INSERT_ID()锟矫碉拷锟斤拷
	 * @return Comment
	 * @author 
	 */
	public Comment addComment(Comment comment);
	
	
	/**
	 *  锟斤拷锟斤拷说锟斤拷锟斤拷删锟斤拷一锟斤拷锟斤拷锟斤拷
	 *  @return 锟斤拷影锟斤拷锟斤拷锟斤拷锟�
	 *  @author 
	 */
	public int deleteComment(long serial_number);
	
	
	/**
	 * 锟斤拷锟斤拷说锟斤拷锟斤拷锟斤拷锟斤拷锟脚诧拷询锟斤拷锟斤拷
	 * @return Comment
	 * @author 
	 */
	public Comment findCommentBySerial(long serial_number);
	
	
	/**
	 * 锟斤拷锟斤拷说锟斤拷锟斤拷锟斤拷锟斤拷没锟絠d统锟狡革拷锟矫伙拷锟斤拷锟桔癸拷姆锟斤拷锟斤拷锟斤拷锟斤拷
	 * 锟斤拷要注锟斤拷一锟斤拷锟矫伙拷锟斤拷锟斤拷锟斤拷锟桔讹拷锟酵伙拷锟斤拷?要去锟截革拷锟斤拷
	 * @return int
	 * @author 
	 */
	public int countCommentByUserId(long user_id);
	
	
	/**
	 * 锟斤拷锟斤拷说锟斤拷锟斤拷锟斤拷莘锟斤拷锟絠d统锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
	 * @return int
	 * @author 
	 */
	public int countCommentByShareId(long share_id);
	
	
	
	/**
	 * 锟斤拷锟斤拷说锟斤拷锟斤拷锟斤拷莘锟斤拷锟絠d锟斤拷询锟斤拷锟斤拷
	 * 锟斤拷锟斤拷页锟斤拷
	 * @return Comment锟叫憋拷
	 * @author 
	 */
	public List<Comment> findCommentByShareId(long share_id);
	
	public List<Comment> findCommentByUserId(long user_id);
	
	
	
}
