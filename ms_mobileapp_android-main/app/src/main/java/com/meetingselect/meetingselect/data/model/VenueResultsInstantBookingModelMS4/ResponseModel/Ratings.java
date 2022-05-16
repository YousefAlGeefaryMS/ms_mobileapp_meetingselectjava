package com.meetingselect.meetingselect.data.model.VenueResultsInstantBookingModelMS4.ResponseModel;

import com.google.gson.annotations.SerializedName;

public class Ratings{

	@SerializedName("twStar")
	private int twStar;

	@SerializedName("threeStar")
	private int threeStar;

	@SerializedName("oneStar")
	private int oneStar;

	@SerializedName("fourStar")
	private int fourStar;

	@SerializedName("notRated")
	private int notRated;

	@SerializedName("fiveStar")
	private int fiveStar;

	public int getTwStar(){
		return twStar;
	}

	public int getThreeStar(){
		return threeStar;
	}

	public int getOneStar(){
		return oneStar;
	}

	public int getFourStar(){
		return fourStar;
	}

	public int getNotRated(){
		return notRated;
	}

	public int getFiveStar(){
		return fiveStar;
	}
}