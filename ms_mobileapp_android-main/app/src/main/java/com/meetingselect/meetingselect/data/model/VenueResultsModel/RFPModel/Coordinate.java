package com.meetingselect.meetingselect.data.model.VenueResultsModel.RFPModel;

import com.google.gson.annotations.SerializedName;

public class Coordinate{

	@SerializedName("Lon")
	private double lon;

	@SerializedName("Lat")
	private double lat;

	public double getLon(){
		return lon;
	}

	public double getLat(){
		return lat;
	}
}