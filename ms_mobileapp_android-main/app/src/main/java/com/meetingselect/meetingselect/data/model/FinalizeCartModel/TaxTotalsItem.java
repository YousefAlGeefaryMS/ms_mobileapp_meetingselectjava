package com.meetingselect.meetingselect.data.model.FinalizeCartModel;

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