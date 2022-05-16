package com.meetingselect.meetingselect.data.model.InstantBookingTwoPointZeroModel;

import com.google.gson.annotations.SerializedName;

public class BillingAddress{

	@SerializedName("Address")
	private String address;

	@SerializedName("ZipCode")
	private String zipCode;

	@SerializedName("City")
	private String city;

	@SerializedName("CountryCode")
	private String countryCode;

	@SerializedName("CountryName")
	private String countryName;


	public BillingAddress(String address, String zipCode, String city, String countryCode, String countryName) {
		this.address = address;
		this.zipCode = zipCode;
		this.city = city;
		this.countryCode = countryCode;
		this.countryName = countryName;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return address;
	}

	public void setCountryName(String countryName){
		this.countryName = countryName;
	}

	public String getCountryName(){
		return countryName;
	}

	public void setZipCode(String zipCode){
		this.zipCode = zipCode;
	}

	public String getZipCode(){
		return zipCode;
	}

	public void setCity(String city){
		this.city = city;
	}

	public String getCity(){
		return city;
	}

	public void setCountryCode(String countryCode){
		this.countryCode = countryCode;
	}

	public String getCountryCode(){
		return countryCode;
	}
}