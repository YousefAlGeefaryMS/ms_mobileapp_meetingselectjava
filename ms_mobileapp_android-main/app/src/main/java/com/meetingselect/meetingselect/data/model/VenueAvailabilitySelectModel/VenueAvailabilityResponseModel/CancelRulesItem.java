package com.meetingselect.meetingselect.data.model.VenueAvailabilitySelectModel.VenueAvailabilityResponseModel;

import com.google.gson.annotations.SerializedName;

public class CancelRulesItem{

	@SerializedName("MinSeats")
	private int minSeats;

	@SerializedName("MeetingtypeId")
	private int meetingtypeId;

	@SerializedName("Percentage")
	private double percentage;

	@SerializedName("MaxSeats")
	private int maxSeats;

	@SerializedName("Id")
	private int id;

	@SerializedName("TermsId")
	private int termsId;

	@SerializedName("HoursUntilStart")
	private int hoursUntilStart;

	public int getMinSeats(){
		return minSeats;
	}

	public int getMeetingtypeId(){
		return meetingtypeId;
	}

	public double getPercentage(){
		return percentage;
	}

	public int getMaxSeats(){
		return maxSeats;
	}

	public int getId(){
		return id;
	}

	public int getTermsId(){
		return termsId;
	}

	public int getHoursUntilStart(){
		return hoursUntilStart;
	}
}