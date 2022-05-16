package com.meetingselect.meetingselect.data.model.VenueResultsInstantBookingModel;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ItemsItem{

	@SerializedName("address")
	private Address address;

	@SerializedName("imageUrls")
	private List<String> imageUrls;

	@SerializedName("name")
	private String name;

	@SerializedName("rating")
	private double rating;

	@SerializedName("description")
	private String description;

	@SerializedName("id")
	private String id;

	@SerializedName("ratingCount")
	private int ratingCount;

	@SerializedName("hasWorkSpace")
	private boolean hasWorkSpace;

	@SerializedName("instantBookable")
	private boolean instantBookable;

	public Address getAddress(){
		return address;
	}

	public List<String> getImageUrls(){
		return imageUrls;
	}

	public String getName(){
		return name;
	}

	public double getRating(){
		return rating;
	}

	public String getDescription(){
		return description;
	}

	public String getId(){
		return id;
	}

	public int getRatingCount(){
		return ratingCount;
	}

	public boolean isHasWorkSpace(){
		return hasWorkSpace;
	}

	public boolean isInstantBookable(){
		return instantBookable;
	}
}