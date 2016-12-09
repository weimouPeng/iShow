package com.scau.dao;

import com.scau.entity.Administrator;



public interface IAdministrationDao {

	/**
	 * ��ɾ������Ҫʵ��
	 */
	
	/**
	 * ����˵�������ҹ���Ա
	 * ����¼����admin_id��password��
	 * @return Administrator
	 * @author 
	 */
	public Administrator checkAdmin(Administrator admin);
	
	
	/**
	 * ����˵�������id���ҹ���Ա
	 * @return Administrator
	 * @author 
	 */
	public Administrator findAdmin(long id);
	
	
	/**
	 * ����˵�����޸Ĺ���Ա
	 * ��nickname��password��admin_id���ܸģ�
	 * @return �޸ĺ��Administrator��Ϣ
	 * @author 
	 */
	public Administrator updataAdmin(Administrator admin);
	
	
	public boolean banUser(long user_id);
		
	public boolean allowUser(long user_id);
	
	
	
	
}
