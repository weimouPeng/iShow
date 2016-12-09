package com.scau.entity;

import java.util.Date;

public class Share {
	private long share_id;
	private long publisher_id;
	private Date release_time;
	private String describe;
	private int point_of_praise;
	private int comment_number;
	private int state;
	
	
	
	
	
	
	public Share() {
		super();
	}
	public Share(long share_id, long publisher_id, Date release_time,
			String describe, int point_of_praise, int comment_number, int state) {
		super();
		this.share_id = share_id;
		this.publisher_id = publisher_id;
		this.release_time = release_time;
		this.describe = describe;
		this.point_of_praise = point_of_praise;
		this.comment_number = comment_number;
		this.state = state;
	}
	public long getShare_id() {
		return share_id;
	}
	public void setShare_id(long l) {
		this.share_id = l;
	}
	public long getPublisher_id() {
		return publisher_id;
	}
	public void setPublisher_id(long l) {
		this.publisher_id = l;
	}
	public Date getRelease_time() {
		return release_time;
	}
	public void setRelease_time(Date release_time) {
		this.release_time = release_time;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public int getPoint_of_praise() {
		return point_of_praise;
	}
	public void setPoint_of_praise(int point_of_praise) {
		this.point_of_praise = point_of_praise;
	}
	public int getComment_number() {
		return comment_number;
	}
	public void setComment_number(int comment_number) {
		this.comment_number = comment_number;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
	
	

}
