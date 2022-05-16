package com.meetingselect.meetingselect.data.model.VenueResultsInstantBookingModelMS4.ResponseModel;

import com.google.gson.annotations.SerializedName;

public class DedicaedExhibitSpaces{

	@SerializedName("fiveHundredPlus")
	private int fiveHundredPlus;

	@SerializedName("fiveThousandPlus")
	private int fiveThousandPlus;

	@SerializedName("twoFiftyPlus")
	private int twoFiftyPlus;

	@SerializedName("twoThousandFiveHundredPlus")
	private int twoThousandFiveHundredPlus;

	@SerializedName("hundredPlus")
	private int hundredPlus;

	@SerializedName("oneThousandPlus")
	private int oneThousandPlus;

	@SerializedName("fiftyPlus")
	private int fiftyPlus;

	@SerializedName("tenThousandPlus")
	private int tenThousandPlus;

	public int getFiveHundredPlus(){
		return fiveHundredPlus;
	}

	public int getFiveThousandPlus(){
		return fiveThousandPlus;
	}

	public int getTwoFiftyPlus(){
		return twoFiftyPlus;
	}

	public int getTwoThousandFiveHundredPlus(){
		return twoThousandFiveHundredPlus;
	}

	public int getHundredPlus(){
		return hundredPlus;
	}

	public int getOneThousandPlus(){
		return oneThousandPlus;
	}

	public int getFiftyPlus(){
		return fiftyPlus;
	}

	public int getTenThousandPlus(){
		return tenThousandPlus;
	}
}