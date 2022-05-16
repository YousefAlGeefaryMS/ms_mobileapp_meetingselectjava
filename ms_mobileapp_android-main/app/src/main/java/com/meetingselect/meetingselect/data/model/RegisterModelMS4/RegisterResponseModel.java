package com.meetingselect.meetingselect.data.model.RegisterModelMS4;

import com.google.gson.annotations.SerializedName;

public class RegisterResponseModel{

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