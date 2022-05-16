package com.meetingselect.meetingselect.data.model.VenueAvailabilitySelectModel.VenueAvailabilityResponseModel;

import com.google.gson.annotations.SerializedName;

public class ContactsItem{

	@SerializedName("ContactTypeId")
	private int contactTypeId;

	@SerializedName("Email")
	private String email;

	@SerializedName("Phone")
	private String phone;

	@SerializedName("CartId")
	private int cartId;

	@SerializedName("Id")
	private int id;

	@SerializedName("Name")
	private String name;

	public int getContactTypeId(){
		return contactTypeId;
	}

	public String getEmail(){
		return email;
	}

	public String getPhone(){
		return phone;
	}

	public int getCartId(){
		return cartId;
	}

	public int getId(){
		return id;
	}

	public String getName(){
		return name;
	}
}