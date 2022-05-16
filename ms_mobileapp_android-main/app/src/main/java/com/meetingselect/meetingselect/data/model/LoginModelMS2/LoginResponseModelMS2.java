package com.meetingselect.meetingselect.data.model.LoginModelMS2;

import com.google.gson.annotations.SerializedName;

public class LoginResponseModelMS2 {

	@SerializedName("UserName")
	private String userName;

	@SerializedName("ExpirationDate")
	private String expirationDate;

	@SerializedName("UserId")
	private String userId;

	@SerializedName("Token")
	private String token;

	public String getUserName(){
		return userName;
	}

	public String getExpirationDate(){
		return expirationDate;
	}

	public String getUserId(){
		return userId;
	}

	public String getToken(){
		return token;
	}
}