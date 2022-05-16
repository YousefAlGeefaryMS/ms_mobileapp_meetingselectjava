package com.meetingselect.meetingselect.data.model.VenueResultsInstantBookingModelMS4.ResponseModel;

import com.google.gson.annotations.SerializedName;

public class VenueTypesItem{

	@SerializedName("value")
	private String value;

	@SerializedName("key")
	private String key;

	@SerializedName("name")
	private String name;

	@SerializedName("count")
	private int count;

	@SerializedName("id")
	private int id;

	public String getValue(){
		return value;
	}

	public String getKey(){
		return key;
	}

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