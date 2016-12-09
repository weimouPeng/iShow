package com.scau.entity;

import java.util.Date;

public class Comment {

	private long serial_number;
	private long share_id;
	private long user_id;
	private Date comment_time;
	private long be_commented_id;
	private String comment_content;
	
	
	
	
	public Comment() {
		super();
	}
	public Comment(int serial_number, int share_id, int user_id,
			Date comment_time, int be_commented_id, String comment_content) {
		super();
		this.serial_number = serial_number;
		this.share_id = share_id;
		this.user_id = user_id;
		this.comment_time = comment_time;
		this.be_commented_id = be_commented_id;
		this.comment_content = comment_content;
	}
	
	public long getSerial_number() {
		return serial_number;
	}
	public void setSerial_number(long serial_number) {
		this.serial_number = serial_number;
	}
	public long getShare_id() {
		return share_id;
	}
	public void setShare_id(long share_id) {
		this.share_id = share_id;
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public Date getComment_time() {
		return comment_time;
	}
	public void setComment_time(Date comment_time) {
		this.comment_time = comment_time;
	}
	public long getBe_commented_id() {
		return be_commented_id;
	}
	public void setBe_commented_id(long be_commented_id) {
		this.be_commented_id = be_commented_id;
	}
	public String getComment_content() {
		return comment_content;
	}
	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}
	
	

	
	
}
