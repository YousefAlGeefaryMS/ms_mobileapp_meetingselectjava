package com.meetingselect.meetingselect.data.model.VenueAvailabilitySelectModel.VenueAvailabilityResponseModel;

import com.google.gson.annotations.SerializedName;

public class TaxTotalsItem{

	@SerializedName("Percentage")
	private double percentage;

	@SerializedName("Total")
	private double total;

	public double getPercentage(){
		return percentage;
	}

	public double getTotal(){
		return total;
	}
}