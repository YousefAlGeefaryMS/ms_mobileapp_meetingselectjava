package com.meetingselect.meetingselect.data.model.VenueResultsInstantBookingModelMS4.ResponseModel;

import com.google.gson.annotations.SerializedName;

public class MeetingRooms{

	@SerializedName("thirtyFivePlus")
	private int thirtyFivePlus;

	@SerializedName("twoPlus")
	private int twoPlus;

	@SerializedName("sevenPlus")
	private int sevenPlus;

	@SerializedName("tenPlus")
	private int tenPlus;

	@SerializedName("twentyPlus")
	private int twentyPlus;

	@SerializedName("fivePlus")
	private int fivePlus;

	@SerializedName("onePlus")
	private int onePlus;

	@SerializedName("fiftyPlus")
	private int fiftyPlus;

	@SerializedName("threeePlus")
	private int threeePlus;

	@SerializedName("fourPlus")
	private int fourPlus;

	public int getThirtyFivePlus(){
		return thirtyFivePlus;
	}

	public int getTwoPlus(){
		return twoPlus;
	}

	public int getSevenPlus(){
		return sevenPlus;
	}

	public int getTenPlus(){
		return tenPlus;
	}

	public int getTwentyPlus(){
		return twentyPlus;
	}

	public int getFivePlus(){
		return fivePlus;
	}

	public int getOnePlus(){
		return onePlus;
	}

	public int getFiftyPlus(){
		return fiftyPlus;
	}

	public int getThreeePlus(){
		return threeePlus;
	}

	public int getFourPlus(){
		return fourPlus;
	}
}