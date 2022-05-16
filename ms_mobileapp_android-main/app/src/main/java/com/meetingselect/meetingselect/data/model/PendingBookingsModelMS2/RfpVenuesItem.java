package com.meetingselect.meetingselect.data.model.PendingBookingsModelMS2;

import com.google.gson.annotations.SerializedName;

public class RfpVenuesItem{

	@SerializedName("RFPID")
	private int rFPID;

	@SerializedName("ResponseStatus")
	private int responseStatus;

	@SerializedName("VenueID")
	private int venueID;

	@SerializedName("VenueName")
	private String venueName;

	public int getRFPID(){
		return rFPID;
	}

	public int getResponseStatus(){
		return responseStatus;
	}

	public int getVenueID(){
		return venueID;
	}

	public String getVenueName() {
		return venueName;
	}
}