package com.meetingselect.meetingselect.data.model.VenueDetailsModel.RoomsAvailability;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class LocationsItem{

	@SerializedName("Email")
	private String email;

	@SerializedName("Address")
	private String address;

	@SerializedName("LocationId")
	private int locationId;

	@SerializedName("UrlName")
	private String urlName;

	@SerializedName("Latitude")
	private double latitude;

	@SerializedName("City")
	private String city;

	@SerializedName("Image")
	private String image;

	@SerializedName("Longitude")
	private double longitude;

	@SerializedName("Name")
	private String name;

	@SerializedName("SearchId")
	private int searchId;

	@SerializedName("OpenMinutes")
	private int openMinutes;

	@SerializedName("State")
	private String state;

	@SerializedName("Phone")
	private String phone;

	@SerializedName("Spaces")
	private List<SpacesItem> spaces;

	@SerializedName("IsAvailable")
	private boolean isAvailable;

	@SerializedName("Postalcode")
	private String postalcode;

	@SerializedName("Country")
	private String country;

	@SerializedName("Features")
	private List<Object> features;

	@SerializedName("EventTypes")
	private List<Object> eventTypes;

	@SerializedName("ReviewCount")
	private int reviewCount;

	@SerializedName("ReviewScore")
	private double reviewScore;

	@SerializedName("Warnings")
	private List<String> warnings;

	@SerializedName("Distance")
	private int distance;

	@SerializedName("CloseMinutes")
	private int closeMinutes;

	public String getEmail(){
		return email;
	}

	public String getAddress(){
		return address;
	}

	public int getLocationId(){
		return locationId;
	}

	public String getUrlName(){
		return urlName;
	}

	public double getLatitude(){
		return latitude;
	}

	public String getCity(){
		return city;
	}

	public String getImage(){
		return image;
	}

	public double getLongitude(){
		return longitude;
	}

	public String getName(){
		return name;
	}

	public int getSearchId(){
		return searchId;
	}

	public int getOpenMinutes(){
		return openMinutes;
	}

	public String getState(){
		return state;
	}

	public String getPhone(){
		return phone;
	}

	public List<SpacesItem> getSpaces(){
		return spaces;
	}

	public boolean isIsAvailable(){
		return isAvailable;
	}

	public String getPostalcode(){
		return postalcode;
	}

	public String getCountry(){
		return country;
	}

	public List<Object> getFeatures(){
		return features;
	}

	public List<Object> getEventTypes(){
		return eventTypes;
	}

	public int getReviewCount(){
		return reviewCount;
	}

	public double getReviewScore(){
		return reviewScore;
	}

	public List<String> getWarnings(){
		return warnings;
	}

	public int getDistance(){
		return distance;
	}

	public int getCloseMinutes(){
		return closeMinutes;
	}
}