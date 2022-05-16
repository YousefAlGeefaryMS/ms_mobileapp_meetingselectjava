package com.meetingselect.meetingselect.data.model.VenueResultsModel.RFPModel;

import com.google.gson.annotations.SerializedName;

public class PicturesItem{

	@SerializedName("OrderDateTime")
	private Object orderDateTime;

	@SerializedName("SmallImageUrl")
	private String smallImageUrl;

	@SerializedName("ThumbImagelUrl")
	private String thumbImagelUrl;

	@SerializedName("MiddleImageUrl")
	private String middleImageUrl;

	@SerializedName("BigImageUrl")
	private String bigImageUrl;

	public Object getOrderDateTime(){
		return orderDateTime;
	}

	public String getSmallImageUrl(){
		return smallImageUrl;
	}

	public String getThumbImagelUrl(){
		return thumbImagelUrl;
	}

	public String getMiddleImageUrl(){
		return middleImageUrl;
	}

	public String getBigImageUrl(){
		return bigImageUrl;
	}
}