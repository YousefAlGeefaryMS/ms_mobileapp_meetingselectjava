package com.meetingselect.meetingselect.data.model.FinalizeCartModel;

import com.google.gson.annotations.SerializedName;

public class InvoiceAddress{

	@SerializedName("Email")
	private String email;

	@SerializedName("Address")
	private String address;

	@SerializedName("ReservationId")
	private int reservationId;

	@SerializedName("SendTo")
	private String sendTo;

	@SerializedName("PoNumber")
	private String poNumber;

	@SerializedName("Postalcode")
	private String postalcode;

	@SerializedName("Country")
	private String country;

	@SerializedName("PhoneNumber")
	private String phoneNumber;

	@SerializedName("Id")
	private int id;

	@SerializedName("City")
	private String city;

	@SerializedName("PoNumberRequired")
	private boolean poNumberRequired;

	public String getEmail(){
		return email;
	}

	public String getAddress(){
		return address;
	}

	public int getReservationId(){
		return reservationId;
	}

	public String getSendTo(){
		return sendTo;
	}

	public String getPoNumber(){
		return poNumber;
	}

	public String getPostalcode(){
		return postalcode;
	}

	public String getCountry(){
		return country;
	}

	public String getPhoneNumber(){
		return phoneNumber;
	}

	public int getId(){
		return id;
	}

	public String getCity(){
		return city;
	}

	public boolean isPoNumberRequired(){
		return poNumberRequired;
	}
}