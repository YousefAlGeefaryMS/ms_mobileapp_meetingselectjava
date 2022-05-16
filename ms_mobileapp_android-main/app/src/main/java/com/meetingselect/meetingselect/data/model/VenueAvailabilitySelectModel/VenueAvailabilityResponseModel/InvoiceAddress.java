package com.meetingselect.meetingselect.data.model.VenueAvailabilitySelectModel.VenueAvailabilityResponseModel;

import com.google.gson.annotations.SerializedName;

public class InvoiceAddress{

	@SerializedName("Company")
	private String company;

	@SerializedName("Email")
	private String email;

	@SerializedName("Address")
	private String address;

	@SerializedName("State")
	private String state;

	@SerializedName("SendTo")
	private String sendTo;

	@SerializedName("PoNumber")
	private String poNumber;

	@SerializedName("PostalCode")
	private String postalCode;

	@SerializedName("Country")
	private String country;

	@SerializedName("PhoneNumber")
	private String phoneNumber;

	@SerializedName("CartId")
	private int cartId;

	@SerializedName("Id")
	private int id;

	@SerializedName("City")
	private String city;

	public String getCompany(){
		return company;
	}

	public String getEmail(){
		return email;
	}

	public String getAddress(){
		return address;
	}

	public String getState(){
		return state;
	}

	public String getSendTo(){
		return sendTo;
	}

	public String getPoNumber(){
		return poNumber;
	}

	public String getPostalCode(){
		return postalCode;
	}

	public String getCountry(){
		return country;
	}

	public String getPhoneNumber(){
		return phoneNumber;
	}

	public int getCartId(){
		return cartId;
	}

	public int getId(){
		return id;
	}

	public String getCity(){
		return city;
	}
}