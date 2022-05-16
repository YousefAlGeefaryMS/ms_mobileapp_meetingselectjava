package com.meetingselect.meetingselect.data.model.LoginModelMS2;

import com.google.gson.annotations.SerializedName;

public class LoginPostModelMS2 {

	@SerializedName("SiteId")
	private int siteId;

	@SerializedName("UserName")
	private String userName;

	@SerializedName("Password")
	private String password;

	public LoginPostModelMS2(int siteId, String userName, String password) {
		this.siteId = siteId;
		this.userName = userName;
		this.password = password;
	}

	public int getSiteId(){
		return siteId;
	}

	public String getUserName(){
		return userName;
	}

	public String getPassword(){
		return password;
	}
}