package com.meetingselect.meetingselect.data.model.InstantBookingTwoPointZeroModel;

import com.google.gson.annotations.SerializedName;

public class InstantBookingTwoPointZeroResponseModel{

	@SerializedName("ExceptionType")
	private String exceptionType;

	@SerializedName("ExceptionMessage")
	private String exceptionMessage;

	@SerializedName("Message")
	private String message;

	@SerializedName("StackTrace")
	private String stackTrace;

	public String getExceptionType(){
		return exceptionType;
	}

	public String getExceptionMessage(){
		return exceptionMessage;
	}

	public String getMessage(){
		return message;
	}

	public String getStackTrace(){
		return stackTrace;
	}
}