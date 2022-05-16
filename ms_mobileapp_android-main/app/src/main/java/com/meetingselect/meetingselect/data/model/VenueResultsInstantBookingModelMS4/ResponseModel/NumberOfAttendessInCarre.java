package com.meetingselect.meetingselect.data.model.VenueResultsInstantBookingModelMS4.ResponseModel;

import com.google.gson.annotations.SerializedName;

public class NumberOfAttendessInCarre{

	@SerializedName("twoThousand")
	private int twoThousand;

	@SerializedName("hundred")
	private int hundred;

	@SerializedName("twentyFive")
	private int twentyFive;

	@SerializedName("fifty")
	private int fifty;

	@SerializedName("oneThousand")
	private int oneThousand;

	@SerializedName("oneFifty")
	private int oneFifty;

	@SerializedName("fiveHundred")
	private int fiveHundred;

	@SerializedName("lessThanTwentyFive")
	private int lessThanTwentyFive;

	@SerializedName("twoHundred")
	private int twoHundred;

	@SerializedName("greaterThanOneFifty")
	private int greaterThanOneFifty;

	public int getTwoThousand(){
		return twoThousand;
	}

	public int getHundred(){
		return hundred;
	}

	public int getTwentyFive(){
		return twentyFive;
	}

	public int getFifty(){
		return fifty;
	}

	public int getOneThousand(){
		return oneThousand;
	}

	public int getOneFifty(){
		return oneFifty;
	}

	public int getFiveHundred(){
		return fiveHundred;
	}

	public int getLessThanTwentyFive(){
		return lessThanTwentyFive;
	}

	public int getTwoHundred(){
		return twoHundred;
	}

	public int getGreaterThanOneFifty(){
		return greaterThanOneFifty;
	}
}