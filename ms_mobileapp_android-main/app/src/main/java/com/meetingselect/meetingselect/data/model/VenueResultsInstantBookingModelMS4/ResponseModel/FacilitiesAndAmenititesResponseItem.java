package com.meetingselect.meetingselect.data.model.VenueResultsInstantBookingModelMS4.ResponseModel;

import com.google.gson.annotations.SerializedName;

public class FacilitiesAndAmenititesResponseItem{

	@SerializedName("name")
	private String name;

	@SerializedName("count")
	private int count;

	@SerializedName("id")
	private int id;

	public String getName(){
		return name;
	}

	public int getCount(){
		return count;
	}

	public int getId(){
		return id;
	}
}