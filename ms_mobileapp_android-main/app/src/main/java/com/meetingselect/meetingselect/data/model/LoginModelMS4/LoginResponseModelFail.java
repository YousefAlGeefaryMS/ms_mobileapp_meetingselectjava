package com.meetingselect.meetingselect.data.model.LoginModelMS4;

import com.google.gson.annotations.SerializedName;

public class LoginResponseModelFail{

	@SerializedName("data")
	private String data;

	@SerializedName("message")
	private String message;

	@SerializedName("statusCode")
	private int statusCode;

	public String getData(){
		return data;
	}

	public String getMessage(){
		return message;
	}

	public int getStatusCode(){
		return statusCode;
	}
}