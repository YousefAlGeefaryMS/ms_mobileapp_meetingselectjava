package com.meetingselect.meetingselect.data.model.VenueResultsInstantBookingModelMS4.ResponseModel;

import com.google.gson.annotations.SerializedName;

public class FacilitiesAndAmenitiesItem{

	@SerializedName("value")
	private String value;

	@SerializedName("key")
	private String key;

	public String getValue(){
		return value;
	}

	public String getKey(){
		return key;
	}
}