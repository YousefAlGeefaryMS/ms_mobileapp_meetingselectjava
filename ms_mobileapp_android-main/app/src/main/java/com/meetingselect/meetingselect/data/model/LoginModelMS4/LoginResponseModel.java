package com.meetingselect.meetingselect.data.model.LoginModelMS4;

import com.google.gson.annotations.SerializedName;

public class LoginResponseModel{

	@SerializedName("data")
	private Data data;

	@SerializedName("statusCode")
	private int statusCode;

	@SerializedName("message")
	private String message;


	public Data getData(){
		return data;
	}

	public String getMessage(){
		return message;
	}

	public int getStatusCode(){
		return statusCode;
	}
}