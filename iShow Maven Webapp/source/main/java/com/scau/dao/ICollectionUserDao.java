package com.scau.dao;

import java.util.List;

public interface ICollectionUserDao {
	/**
	 * 閿熸枻鎷烽敓鏂ゆ嫹璇撮敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷蜂竴閿熸枻鎷烽敓鏂ゆ嫹褰�
	 * @return 閿熸枻鎷峰奖閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟�	 * @author 
	 */
	public int addCollectionUser(long user_id,long be_collectioned_id);
	
	
	/**
	 * 閿熸枻鎷烽敓鏂ゆ嫹璇撮敓鏂ゆ嫹閿熸枻鎷峰垹閿熸枻鎷蜂竴閿熸枻鎷烽敓鏂ゆ嫹褰�
	 * @return 閿熸枻鎷峰奖閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟�	 * @author
	 */
	public int deleteCollectionUser(long user_id,long be_collectioned_id);
	
	
	/**
	 * 閿熸枻鎷烽敓鏂ゆ嫹璇撮敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷锋病閿熺禒d缁熼敓鐙￠潻鎷烽敓鐭紮鎷烽敓绉歌棌纰夋嫹閿熺煫浼欐嫹閿熸枻鎷�
	 * @return long
	 * @author 
	 */
	public int countCollectionUser(long user_id);
	
	
	public List<Long> UserCollectionOtherUserID(long user_id);
	
	public boolean checkCollectionUser(long user_id,long other_id);
	
	
	
}
