package com.scau.dao;

import java.util.Date;
import java.util.List;

public interface IPointPraiseDao {
	
	
	
	/**
	 * 閿熸枻鎷烽敓鏂ゆ嫹璇撮敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷蜂竴閿熸枻鎷烽敓鏂ゆ嫹閿熺潾纭锋嫹褰�
	 * @return 閿熸枻鎷峰奖閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟�	 * @author 
	 */
	public int addPointPraise(long Share_id,long User_id,Date date);
	
	
	/**
	 * 閿熸枻鎷烽敓鏂ゆ嫹璇撮敓鏂ゆ嫹閿熸枻鎷峰垹閿熸枻鎷蜂竴閿熸枻鎷烽敓鏂ゆ嫹閿熺潾纭锋嫹褰�
	 * @return 閿熸枻鎷峰奖閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟�	 * @author 
	 */
	public int deletePointPraise(long Share_id,long User_id);
	
	
	/**
	 * 閿熸枻鎷烽敓鏂ゆ嫹璇撮敓鏂ゆ嫹閿熸枻鎷风粺閿熸枻鎷蜂竴閿熸枻鎷烽敓鐭紮鎷烽敓鏂ゆ嫹閿熺潾鐨勫嚖鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹
	 * @return int
	 * @author 
	 */
	public int countSharePointByUserId(long user_id);
	
	public List<Long> getPointPraiseUserIds(long share_id);
	
	public List<Long> getPointPraiseByUserId(long user_id);
	
	public boolean checkPointPraise(long share_id,long user_id);
	
}
