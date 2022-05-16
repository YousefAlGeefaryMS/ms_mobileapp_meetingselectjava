package com.meetingselect.meetingselect.data.model.VenueResultsInstantBookingModelMS4.ResponseModel;

import com.google.gson.annotations.SerializedName;

public class LargestMeetingRooms{

	@SerializedName("fiveHundredPlus")
	private int fiveHundredPlus;

	@SerializedName("fiveThousandPlus")
	private int fiveThousandPlus;

	@SerializedName("unit")
	private Object unit;

	@SerializedName("twoFiftyPlus")
	private int twoFiftyPlus;

	@SerializedName("twoThousandPlus")
	private int twoThousandPlus;

	@SerializedName("hundredPlus")
	private int hundredPlus;

	@SerializedName("tenPlus")
	private int tenPlus;

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

	public Object getUnit(){
		return unit;
	}

	public int getTwoFiftyPlus(){
		return twoFiftyPlus;
	}

	public int getTwoThousandPlus(){
		return twoThousandPlus;
	}

	public int getHundredPlus(){
		return hundredPlus;
	}

	public int getTenPlus(){
		return tenPlus;
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