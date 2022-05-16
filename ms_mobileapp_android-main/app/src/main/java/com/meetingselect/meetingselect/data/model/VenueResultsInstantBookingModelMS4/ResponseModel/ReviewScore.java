package com.meetingselect.meetingselect.data.model.VenueResultsInstantBookingModelMS4.ResponseModel;

import com.google.gson.annotations.SerializedName;

public class ReviewScore{

	@SerializedName("nine")
	private int nine;

	@SerializedName("six")
	private int six;

	@SerializedName("seven")
	private int seven;

	@SerializedName("ten")
	private int ten;

	@SerializedName("eight")
	private int eight;

	public int getNine(){
		return nine;
	}

	public int getSix(){
		return six;
	}

	public int getSeven(){
		return seven;
	}

	public int getTen(){
		return ten;
	}

	public int getEight(){
		return eight;
	}
}