package com.scau.entity;

public class User {
	
	/**
	 * ˵�����û�ʵ���ࣨ��Ӧ��ݿ�user�?
	 * @author Lonestar
	 */
	private long user_id;
	private String nickname;
	private String password;
	private String mailbox;
	private int jurisdiction;
	private String head_portrait;
	
	
	
	public User(int user_id, String nickname, String password, String mailbox,
			int jurisdiction, String head_portrait) {
		super();
		this.user_id = user_id;
		this.nickname = nickname;
		this.password = password;
		this.mailbox = mailbox;
		this.jurisdiction = jurisdiction;
		this.head_portrait = head_portrait;
	}
	
	
	public User() {
		super();
	}


	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMailbox() {
		return mailbox;
	}
	public void setMailbox(String mailbox) {
		this.mailbox = mailbox;
	}
	public int getJurisdiction() {
		return jurisdiction;
	}
	public void setJurisdiction(int jurisdiction) {
		this.jurisdiction = jurisdiction;
	}
	public String getHead_portrait() {
		return head_portrait;
	}
	public void setHead_portrait(String head_portrait) {
		this.head_portrait = head_portrait;
	}

}
