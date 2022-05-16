package com.meetingselect.meetingselect.data.model.VenueDetailsModel;

import com.google.gson.annotations.SerializedName;

public class PicturesItem{

	@SerializedName("OriginalUrl")
	private String originalUrl;

	@SerializedName("SmallImageUrl")
	private String smallImageUrl;

	@SerializedName("PictureID")
	private String pictureID;

	@SerializedName("IsPromoImage")
	private boolean isPromoImage;

	@SerializedName("ThumbImagelUrl")
	private Object thumbImagelUrl;

	@SerializedName("MiddleImageUrl")
	private String middleImageUrl;

	@SerializedName("BigImageUrl")
	private String bigImageUrl;

	public String getOriginalUrl(){
		return originalUrl;
	}

	public String getSmallImageUrl(){
		return smallImageUrl;
	}

	public String getPictureID(){
		return pictureID;
	}

	public boolean isIsPromoImage(){
		return isPromoImage;
	}

	public Object getThumbImagelUrl(){
		return thumbImagelUrl;
	}

	public String getMiddleImageUrl(){
		return middleImageUrl;
	}

	public String getBigImageUrl(){
		return bigImageUrl;
	}
}