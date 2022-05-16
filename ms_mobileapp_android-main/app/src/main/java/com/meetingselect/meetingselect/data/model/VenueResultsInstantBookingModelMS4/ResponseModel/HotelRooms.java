package com.meetingselect.meetingselect.data.model.VenueResultsInstantBookingModelMS4.ResponseModel;

import com.google.gson.annotations.SerializedName;

public class HotelRooms{

	@SerializedName("fiveHundredPlus")
	private int fiveHundredPlus;

	@SerializedName("twoFiftyPlus")
	private int twoFiftyPlus;

	@SerializedName("hundredPlus")
	private int hundredPlus;

	@SerializedName("fivePlus")
	private int fivePlus;

	@SerializedName("twentyFivePlus")
	private int twentyFivePlus;

	@SerializedName("fiftyPlus")
	private int fiftyPlus;

	public int getFiveHundredPlus(){
		return fiveHundredPlus;
	}

	public int getTwoFiftyPlus(){
		return twoFiftyPlus;
	}

	public int getHundredPlus(){
		return hundredPlus;
	}

	public int getFivePlus(){
		return fivePlus;
	}

	public int getTwentyFivePlus(){
		return twentyFivePlus;
	}

	public int getFiftyPlus(){
		return fiftyPlus;
	}
}