package com.scau.entity;

public class Picture {
	private long serial_number;
	private long share_id;
	private String image_path;
	
	
	
	
	public Picture() {
		super();
	}
	public Picture(long serial_number, long share_id, String image_path) {
		super();
		this.serial_number = serial_number;
		this.share_id = share_id;
		this.image_path = image_path;
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
	public String getImage_path() {
		return image_path;
	}
	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}
	
	
	
	

}
