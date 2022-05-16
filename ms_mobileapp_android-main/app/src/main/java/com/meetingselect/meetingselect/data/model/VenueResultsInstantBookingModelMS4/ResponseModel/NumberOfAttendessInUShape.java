package com.meetingselect.meetingselect.data.model.VenueResultsInstantBookingModelMS4.ResponseModel;

import com.google.gson.annotations.SerializedName;

public class NumberOfAttendessInUShape{

	@SerializedName("hundred")
	private int hundred;

	@SerializedName("twentyFive")
	private int twentyFive;

	@SerializedName("fifty")
	private int fifty;

	@SerializedName("oneFifty")
	private int oneFifty;

	@SerializedName("lessThanTwentyFive")
	private int lessThanTwentyFive;

	@SerializedName("greaterThanOneFifty")
	private int greaterThanOneFifty;

	public int getHundred(){
		return hundred;
	}

	public int getTwentyFive(){
		return twentyFive;
	}

	public int getFifty(){
		return fifty;
	}

	public int getOneFifty(){
		return oneFifty;
	}

	public int getLessThanTwentyFive(){
		return lessThanTwentyFive;
	}

	public int getGreaterThanOneFifty(){
		return greaterThanOneFifty;
	}
}