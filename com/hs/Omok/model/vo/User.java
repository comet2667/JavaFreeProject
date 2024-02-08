package com.hs.omok.model.vo;

import java.sql.Date;

public class User {
	private int userNo;
	private String userName;
	private String stone;
	private String result;
	private int count;
	private Date playDate;
	
	public User() {}
	
	public User(String userName, String stone, String result, int count) {
		super();
		this.userName = userName;
		this.stone = stone;
		this.result = result;
		this.count = count;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getStone() {
		return stone;
	}
	public void setStone(String stone) {
		this.stone = stone;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public Date getPlayDate() {
		return playDate;
	}

	public void setPlayDate(Date playDate) {
		this.playDate = playDate;
	}
	
	
}
