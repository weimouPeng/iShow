package com.scau.entity;

public class Administrator {
	private long admin_id;
	private String password;
	private String nickname;
	
	
	
	public Administrator(long admin_id, String password, String nickname) {
		super();
		this.admin_id = admin_id;
		this.password = password;
		this.nickname = nickname;
	}
	
	
	
	public Administrator() {
		super();
	}



	
	public long getAdmin_id() {
		return admin_id;
	}



	public void setAdmin_id(long admin_id) {
		this.admin_id = admin_id;
	}



	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	

}
