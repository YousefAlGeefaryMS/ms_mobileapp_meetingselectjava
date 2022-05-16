package com.meetingselect.meetingselect.data.model.VenueResultsInstantBookingModelMS4.ResponseModel;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class SingleCityVenues{

	@SerializedName("offset")
	private int offset;

	@SerializedName("limit")
	private int limit;

	@SerializedName("totalCount")
	private int totalCount;

	@SerializedName("items")
	private List<ItemsItem> items;

	public int getOffset(){
		return offset;
	}

	public int getLimit(){
		return limit;
	}

	public int getTotalCount(){
		return totalCount;
	}

	public List<ItemsItem> getItems(){
		return items;
	}
}