package com.meetingselect.meetingselect.data.model.VenueResultsInstantBookingModelMS4.ResponseModel;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("singleCityVenues")
	private SingleCityVenues singleCityVenues;

	@SerializedName("multiCityVenues")
	private Object multiCityVenues;

	@SerializedName("advancedFiltersResponse")
	private AdvancedFiltersResponse advancedFiltersResponse;

	public SingleCityVenues getSingleCityVenues(){
		return singleCityVenues;
	}

	public Object getMultiCityVenues(){
		return multiCityVenues;
	}

	public AdvancedFiltersResponse getAdvancedFiltersResponse(){
		return advancedFiltersResponse;
	}
}