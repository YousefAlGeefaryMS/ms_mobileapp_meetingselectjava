package com.meetingselect.meetingselect.data.model.VenueResultsInstantBookingModelMS4.ResponseModel;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ItemsItem{

	@SerializedName("unitPrice")
	private Object unitPrice;

	@SerializedName("isAvailable")
	private boolean isAvailable;

	@SerializedName("address")
	private Address address;

	@SerializedName("sourcedBy")
	private String sourcedBy;

	@SerializedName("rating")
	private double rating;

	@SerializedName("description")
	private String description;

	@SerializedName("ratingCount")
	private int ratingCount;

	@SerializedName("hasWorkSpace")
	private boolean hasWorkSpace;

	@SerializedName("imageUrls")
	private List<String> imageUrls;

	@SerializedName("name")
	private String name;

	@SerializedName("additionalInfo")
	private AdditionalInfo additionalInfo;

	@SerializedName("id")
	private String id;

	@SerializedName("instantBookable")
	private boolean instantBookable;

	public Object getUnitPrice(){
		return unitPrice;
	}

	public boolean isIsAvailable(){
		return isAvailable;
	}

	public Address getAddress(){
		return address;
	}

	public String getSourcedBy(){
		return sourcedBy;
	}

	public double getRating(){
		return rating;
	}

	public String getDescription(){
		return description;
	}

	public int getRatingCount(){
		return ratingCount;
	}

	public boolean isHasWorkSpace(){
		return hasWorkSpace;
	}

	public List<String> getImageUrls(){
		return imageUrls;
	}

	public String getName(){
		return name;
	}

	public AdditionalInfo getAdditionalInfo(){
		return additionalInfo;
	}

	public String getId(){
		return id;
	}

	public boolean isInstantBookable(){
		return instantBookable;
	}
}