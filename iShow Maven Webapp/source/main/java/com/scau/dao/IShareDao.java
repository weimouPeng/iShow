package com.scau.dao;

import java.util.Date;
import java.util.List;

import com.scau.entity.Share;
import com.scau.entity.User;



public interface IShareDao {
	
	/**
	 * 锟斤拷锟斤拷说锟斤拷锟斤拷锟斤拷锟斤拷一锟斤拷锟斤拷锟斤拷锟铰�
	 * (id锟斤拷锟斤拷锟斤拷录锟斤拷锟斤拷锟絊ELECT LAST_INSERT_ID()锟斤拷锟截ｏ拷
	 * @return 锟斤拷锟斤拷锟絠d锟侥凤拷锟斤拷锟斤拷
	 * @author 
	 */
	public Share addShare(Share share);
	
	
	
	/**
	 * 锟斤拷锟斤拷说锟斤拷锟斤拷删锟斤拷一锟斤拷锟斤拷锟斤拷锟铰�
	 * 锟斤拷锟斤拷锟絠d删锟斤拷
	 * @return 锟斤拷影锟斤拷锟斤拷锟斤拷锟�
	 * @author 
	 */
	public int deleteShare(Share share);
	
	
	/**
	 * 锟斤拷锟斤拷说锟斤拷锟斤拷锟睫革拷一锟斤拷锟斤拷锟斤拷锟铰�
	 * 锟斤拷锟斤拷锟絠d锟睫革拷状态state锟斤拷锟斤拷锟斤拷锟睫改ｏ拷
	 * @return 锟斤拷影锟斤拷锟斤拷锟斤拷锟�
	 * @author 
	 */
	public int updateShare(Share share);
	
	
	/**
	 * 锟斤拷锟斤拷说锟斤拷锟斤拷锟斤拷莘锟斤拷锟絠d锟斤拷询锟斤拷锟斤拷
	 * @return Share
	 * @author 
	 */
	public Share findShareById(long share_id);
	
	
	/**
	 * 锟斤拷锟斤拷说锟斤拷锟斤拷统锟狡凤拷锟斤拷锟斤拷锟斤拷
	 * 锟斤拷锟斤拷要锟斤拷锟阶刺瑂tate锟斤拷锟介，0为锟斤拷1为锟矫伙拷锟缴硷拷锟斤拷锟斤拷鄣锟斤拷蓿锟�为锟矫伙拷锟斤拷锟缴硷拷也锟斤拷锟斤拷锟斤拷锟斤拷
	 *   锟斤拷state为3时锟斤拷统锟斤拷锟矫伙拷锟缴硷拷模锟揭诧拷锟斤拷锟�锟斤拷1锟斤拷锟斤拷state为4时锟斤拷统锟斤拷全锟斤拷锟斤拷
	 *   只锟斤拷要实锟斤拷state为3锟侥硷拷锟缴ｏ拷锟斤拷同锟斤拷
	 *   锟斤拷锟斤拷锟斤拷锟斤拷姆锟斤拷锟斤拷叩锟阶刺拷锟斤拷锟阶刺憋拷锟揭诧拷锟揭ワ拷锟斤拷梅锟斤拷?
	 * @return 锟斤拷锟斤拷
	 * @author 
	 */
	public int countShare(int state);
	
	
	/**
	 * 锟斤拷锟斤拷说锟斤拷锟斤拷锟斤拷锟斤拷锟叫憋拷
	 * 锟斤拷锟斤拷要锟斤拷锟阶刺瑂tate锟斤拷锟介，0为锟斤拷1为锟矫伙拷锟缴硷拷锟斤拷锟斤拷郏锟�为锟矫伙拷锟斤拷锟缴硷拷也锟斤拷锟斤拷锟斤拷锟斤拷
	 *   锟斤拷state为3时锟斤拷锟斤拷锟斤拷锟矫伙拷锟缴硷拷模锟揭诧拷锟斤拷锟�锟斤拷1锟斤拷锟斤拷state为4时锟斤拷锟斤拷锟斤拷全锟斤拷锟斤拷
	 * 锟斤拷锟斤拷时锟斤拷锟斤拷锟斤拷锟斤拷锟铰碉拷锟斤拷前锟斤拷锟斤拷页锟斤拷begin为锟斤拷始锟姐，size为锟斤拷锟斤拷锟斤拷
	 * 锟斤拷锟斤拷锟斤拷锟斤拷姆锟斤拷锟斤拷叩锟阶刺拷锟斤拷锟阶刺憋拷锟揭诧拷锟揭ワ拷锟斤拷梅锟斤拷?
	 * @return Share锟叫憋拷
	 * @author 
	 */
	public List<Share> findShare(int state,int begin,int size);
	
	
	/**
	 * 锟斤拷锟斤拷说锟斤拷锟斤拷锟斤拷锟斤拷没锟絠d统锟狡凤拷锟斤拷锟斤拷
	 * @return int
	 * @author
	 */
	public int countShareByUserId(long user_id,int oneself);
	
	/**
	 * 锟斤拷锟斤拷说锟斤拷锟斤拷锟斤拷锟斤拷没锟絠d锟斤拷询锟斤拷锟斤拷
	 * 锟斤拷时锟斤拷锟斤拷锟斤拷锟斤拷锟铰碉拷锟斤拷前锟斤拷锟斤拷页锟斤拷
	 * @return 锟斤拷锟斤拷锟叫憋拷
	 * @author 
	 */
	public List<Share> findShareByUserId(long user_id,int begin,int size,int oneself);
	
	
	/**
	 * 锟斤拷锟斤拷说锟斤拷锟斤拷锟斤拷锟斤拷址锟斤拷询锟斤拷统锟狡斤拷锟斤拷锟斤拷锟�
	 * 锟斤拷锟揭筹拷锟斤拷锟斤拷址锟侥凤拷锟斤拷锟斤拷锟斤拷锟斤拷要锟斤拷锟絪tate锟斤拷锟介，同锟较ｏ拷
	 * 锟斤拷锟斤拷锟斤拷锟斤拷姆锟斤拷锟斤拷叩锟阶刺拷锟斤拷锟阶刺憋拷锟揭诧拷锟揭ワ拷锟斤拷梅锟斤拷?
	 * @return int
	 * @author 
	 */
	public int countShareByKey(int state,String key);
	
	/**
	 * 锟斤拷锟斤拷说锟斤拷锟斤拷锟斤拷锟斤拷址锟斤拷询
	 * 锟斤拷锟揭筹拷锟斤拷锟斤拷址锟侥凤拷锟�锟斤拷要锟斤拷锟絪tate锟斤拷锟介，同锟较ｏ拷时锟斤拷锟斤拷锟斤拷,锟斤拷页锟斤拷
	 * 锟斤拷锟斤拷锟斤拷锟斤拷姆锟斤拷锟斤拷叩锟阶刺拷锟斤拷锟阶刺憋拷锟揭诧拷锟揭ワ拷锟斤拷梅锟斤拷?
	 * @return 锟斤拷锟斤拷锟叫憋拷
	 * @author 
	 */
	public List<Share> findShareByKey(int state,String key,int begin,int size);
	
	
	/**
	 * 锟斤拷锟斤拷说锟斤拷锟斤拷锟斤拷锟睫帮拷锟斤拷锟斤拷
	 * 锟斤拷锟斤拷锟斤拷days锟斤拷锟节碉拷锟斤拷锟斤拷锟斤拷姆锟斤拷锟斤拷斜锟斤拷锟斤拷锟斤拷锟斤拷锟揭拷锟斤拷锟絇ointPraise锟斤拷Share锟斤拷询
	 *   锟斤拷days为0时锟斤拷示锟斤拷锟揭碉拷锟届，锟斤拷days为锟斤拷锟斤拷时锟斤拷示锟斤拷锟斤拷全锟斤拷锟斤拷state锟斤拷锟介）
	 *   锟斤拷锟斤拷锟斤拷锟斤拷姆锟斤拷锟斤拷叩锟阶刺拷锟斤拷锟阶刺憋拷锟揭诧拷锟揭ワ拷锟斤拷梅锟斤拷?
	 * @return 锟斤拷锟斤拷
	 * @author 
	 */
	public int countShareByPointAndDays(Date date,int days,int state);
	
	
	/**
	 * 锟斤拷锟斤拷说锟斤拷锟斤拷锟斤拷锟睫帮拷
	 * 锟斤拷锟斤拷锟斤拷days锟斤拷锟节碉拷锟斤拷锟斤拷锟斤拷姆锟斤拷锟斤拷斜?锟斤拷要锟斤拷锟斤拷PointPraise锟斤拷Share锟斤拷询
	 *   锟斤拷days为0时锟斤拷示锟斤拷锟揭碉拷锟届，锟斤拷days为锟斤拷锟斤拷时锟斤拷示锟斤拷锟斤拷全锟斤拷
	 *   state锟斤拷锟介，时锟斤拷锟斤拷牛锟斤拷锟揭筹拷锟�
	 *   锟斤拷锟斤拷锟斤拷锟斤拷姆锟斤拷锟斤拷叩锟阶刺拷锟斤拷锟阶刺憋拷锟揭诧拷锟揭ワ拷锟斤拷梅锟斤拷?
	 * @return 锟斤拷锟斤拷锟叫憋拷
	 * @author 
	 */
	public List<Share> findShareByPointAndDays(Date date,int days,int state,int begin,int size);
	
	
	/**
	 * 锟斤拷锟斤拷说锟斤拷锟斤拷锟斤拷锟斤拷没锟絠d锟斤拷询锟斤拷锟矫伙拷锟斤拷锟睫癸拷姆锟斤拷锟�
	 * 锟斤拷锟斤拷要锟斤拷锟斤拷锟斤拷锟斤拷锟较诧拷询锟斤拷锟斤拷锟斤拷时锟斤拷锟斤拷锟津，凤拷锟�锟斤拷时锟斤拷锟斤拷牛锟斤拷锟斤拷锟揭猻tate锟斤拷锟斤拷
	 *   锟斤拷页锟斤拷锟斤拷锟斤拷通锟斤拷IPointPraiseDao.java锟斤拷茫锟�
	 * @return 锟斤拷锟斤拷锟叫憋拷
	 * @author 
	 */
	public List<Share> findShareByUserIdAndPoint(long user_id,int begin,int size);
	
	
	/**
	 * 锟斤拷锟斤拷说锟斤拷锟斤拷锟斤拷锟斤拷没锟絠d锟斤拷询锟斤拷锟矫伙拷锟秸藏的凤拷锟斤拷
	 * 锟斤拷锟斤拷要锟斤拷锟斤拷锟斤拷锟斤拷锟较诧拷询锟斤拷时锟斤拷锟斤拷锟津，诧拷锟斤拷要state锟斤拷锟斤拷
	 *   锟斤拷页锟斤拷锟斤拷锟斤拷通锟斤拷ICollectionShareDao.java锟斤拷茫锟�
	 * @return 锟斤拷锟斤拷锟叫憋拷
	 * @author 
	 */
	public List<Share> findShareByUserIdAndCollection(long user_id,int begin,int size);
	
	/**
	 * 锟斤拷锟斤拷说锟斤拷锟斤拷锟斤拷锟斤拷没锟絠d锟斤拷询锟斤拷锟矫伙拷锟斤拷锟桔的凤拷锟斤拷
	 * 锟斤拷锟斤拷要锟斤拷锟斤拷锟斤拷锟斤拷锟较诧拷询锟斤拷锟斤拷锟�锟斤拷时锟斤拷锟斤拷锟津，诧拷锟斤拷要state锟斤拷锟斤拷
	 *   一锟斤拷锟矫伙拷锟斤拷锟斤拷锟斤拷锟桔讹拷锟酵伙拷锟斤拷锟斤拷?应去锟斤拷锟截革拷
	 *   锟斤拷页锟斤拷锟斤拷锟斤拷通锟斤拷ICommentDao.java锟斤拷茫锟�
	 * @return 锟斤拷锟斤拷锟叫憋拷
	 * @author 
	 */
	public List<Share> findShareByUserIdAndComment(long user_id,int begin,int size);
	
	
	/**
	 * 
	 */
	public List<User> findUserByShareIdOfPoint(long share_id);
	

	
	
	
	

}
