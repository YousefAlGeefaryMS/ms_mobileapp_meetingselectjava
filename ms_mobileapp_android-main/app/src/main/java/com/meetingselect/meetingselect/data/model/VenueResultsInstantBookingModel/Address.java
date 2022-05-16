package com.meetingselect.meetingselect.data.model.VenueResultsInstantBookingModel;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Address{

	@SerializedName("zipCode")
	private String zipCode;

	@SerializedName("emailAddress")
	private String emailAddress;

	@SerializedName("phoneNumber")
	private String phoneNumber;

	@SerializedName("province")
	private Object province;

	@SerializedName("city")
	private String city;

	@SerializedName("countryCode")
	private String countryCode;

	@SerializedName("latitude")
	private double latitude;

	@SerializedName("addressLines")
	private List<String> addressLines;

	@SerializedName("countryName")
	private String countryName;

	@SerializedName("longitude")
	private double longitude;

	public String getZipCode(){
		return zipCode;
	}

	public String getEmailAddress(){
		return emailAddress;
	}

	public String getPhoneNumber(){
		return phoneNumber;
	}

	public Object getProvince(){
		return province;
	}

	public String getCity(){
		return city;
	}

	public String getCountryCode(){
		return countryCode;
	}

	public double getLatitude(){
		return latitude;
	}

	public List<String> getAddressLines(){
		return addressLines;
	}

	public String getCountryName(){
		return countryName;
	}

	public double getLongitude(){
		return longitude;
	}
}