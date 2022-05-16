package com.meetingselect.meetingselect.data.model.VenueResultsModel.RFPModel;

import com.google.gson.annotations.SerializedName;

public class CoordinatesForMapItem{

	@SerializedName("CompanyName")
	private String companyName;

	@SerializedName("LogoPath")
	private String logoPath;

	@SerializedName("CompanyId")
	private int companyId;

	@SerializedName("ShortDesc")
	private String shortDesc;

	@SerializedName("Latitude")
	private double latitude;

	@SerializedName("Longitude")
	private double longitude;

	@SerializedName("UrlTitle")
	private String urlTitle;

	public String getCompanyName(){
		return companyName;
	}

	public String getLogoPath(){
		return logoPath;
	}

	public int getCompanyId(){
		return companyId;
	}

	public String getShortDesc(){
		return shortDesc;
	}

	public double getLatitude(){
		return latitude;
	}

	public double getLongitude(){
		return longitude;
	}

	public String getUrlTitle(){
		return urlTitle;
	}
}