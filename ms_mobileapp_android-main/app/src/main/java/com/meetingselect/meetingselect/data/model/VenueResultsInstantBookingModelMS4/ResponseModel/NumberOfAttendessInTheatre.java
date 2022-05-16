package com.meetingselect.meetingselect.data.model.VenueResultsInstantBookingModelMS4.ResponseModel;

import com.google.gson.annotations.SerializedName;

public class NumberOfAttendessInTheatre{

	@SerializedName("twoThousand")
	private int twoThousand;

	@SerializedName("hundred")
	private int hundred;

	@SerializedName("tenThousand")
	private int tenThousand;

	@SerializedName("fifty")
	private int fifty;

	@SerializedName("sixThousand")
	private int sixThousand;

	@SerializedName("greaterThanOneFifty")
	private int greaterThanOneFifty;

	@SerializedName("fourThousand")
	private int fourThousand;

	@SerializedName("twentyFive")
	private int twentyFive;

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

	public int getTwoThousand(){
		return twoThousand;
	}

	public int getHundred(){
		return hundred;
	}

	public int getTenThousand(){
		return tenThousand;
	}

	public int getFifty(){
		return fifty;
	}

	public int getSixThousand(){
		return sixThousand;
	}

	public int getGreaterThanOneFifty(){
		return greaterThanOneFifty;
	}

	public int getFourThousand(){
		return fourThousand;
	}

	public int getTwentyFive(){
		return twentyFive;
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
}