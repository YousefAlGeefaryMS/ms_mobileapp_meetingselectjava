package com.meetingselect.meetingselect.data.model.LoginModelMS4;

import com.google.gson.annotations.SerializedName;

public class LoginPostModel{

	@SerializedName("email")
	private String email;

	@SerializedName("password")
	private String password;


	public LoginPostModel(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public String getEmail(){
		return email;
	}
}