package com.scau.dao;



import java.util.List;

import com.scau.entity.User;



public interface IUserDao {
	
	/**
	 * 不需要实现删除
	 */
	
	

	/**
	 * 方法说明：根据传入的User增加一个用户记录
	 * （昵称、邮箱、密码、头像路径设定合法，不需要检验，邮箱设定不重复）
	 * @return 增加user_id后的用户（可以用SELECT LAST_INSERT_ID();或根据邮箱查找）
	 * @author 
	 */
	public User addUser(User user);
	
	
	
	/**
	 * 方法说明：根据传入的User修改一个用户记录
	 * （ID、邮箱不能修改，昵称、密码、权限、头像可以修改）
	 * @return 受影响的行数
	 * @author 
	 */
	public int updateUser(User user);
	
	
	/**
	 * 方法说明：根据传入的User查找一个用户记录
	 * （登录功能，需要实现用ID+password和mailbox+password两种方式）
	 * @return 验证成功返回包含完整信息的User，失败则返回null
	 * @author
	 */
	public User checkUser(User user);
	
	
	/**
	 * 方法说明：查找传入的邮箱mailbox是否已经存在数据库
	 * @return boolean，1为存在，0为不存在
	 * @author
	 */
	public boolean checkMailbox(String mailbox);
	
	
	/**
	 * 方法说明：根据用户ID查找一个用户记录
	 * @return ID存在返回完整信息的User，不存在则null
	 * @author
	 */
	public User findUserById(long id);
	
	
	
	/**
	 * 方法说明：查询用户总数
	 * @return int
	 * @author 
	 */
	public long countUser();
	
	
	
	/**
	 * 方法说明：查询用户
	 * （分页查询，begin为起始点，size为查询的数量）
	 * @return 用户列表
	 * @author 
	 */
	public List<User> findUserByPage(int begin,int size);
	
	
	/**
	 * 方法说明：根据ID查询该用户收藏的用户
	 * （User与CollectionUser表联合查询，分页
	 *   总数在ICollectionUserDao得到）
	 * @return 用户列表
	 * @author 
	 */
	public List<User> findUserCollectionUser(int id,int begin,int size);
	
	public User findUserByMail(String mailbox);
	
	public long getNumsOfBannedUser();
	
	public List<User> recommendUsers(long id);
	
	public List<User> getAllUsers();
	
	public boolean setPhoto(long user_id,String path);
	
	public boolean updateNickname(long user_id,String nickname);
}
