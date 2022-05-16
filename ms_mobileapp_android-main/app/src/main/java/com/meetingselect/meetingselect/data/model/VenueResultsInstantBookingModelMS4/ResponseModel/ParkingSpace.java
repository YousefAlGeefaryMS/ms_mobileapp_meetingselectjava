package com.meetingselect.meetingselect.data.model.VenueResultsInstantBookingModelMS4.ResponseModel;

import com.google.gson.annotations.SerializedName;

public class ParkingSpace{

	@SerializedName("fiveHundredPlus")
	private int fiveHundredPlus;

	@SerializedName("twoThousandPlus")
	private int twoThousandPlus;

	@SerializedName("hundredPlus")
	private int hundredPlus;

	@SerializedName("threeThousandPlus")
	private int threeThousandPlus;

	@SerializedName("oneThousandPlus")
	private int oneThousandPlus;

	@SerializedName("fiftyPlus")
	private int fiftyPlus;

	public int getFiveHundredPlus(){
		return fiveHundredPlus;
	}

	public int getTwoThousandPlus(){
		return twoThousandPlus;
	}

	public int getHundredPlus(){
		return hundredPlus;
	}

	public int getThreeThousandPlus(){
		return threeThousandPlus;
	}

	public int getOneThousandPlus(){
		return oneThousandPlus;
	}

	public int getFiftyPlus(){
		return fiftyPlus;
	}
}