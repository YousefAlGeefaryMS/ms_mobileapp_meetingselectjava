package com.meetingselect.meetingselect.data.model.InstantBookingTwoPointZeroModel;

import com.google.gson.annotations.SerializedName;

public class Venue{

	@SerializedName("Chain")
	private String chain;

	@SerializedName("Name")
	private String name;

	@SerializedName("Address")
	private Address address;

	@SerializedName("Email")
	private String email;

	@SerializedName("Phone")
	private String phone;

	public Venue(String chain, String name, Address address, String email, String phone) {
		this.chain = chain;
		this.name = name;
		this.address = address;
		this.email = email;
		this.phone = phone;
	}

	public void setChain(String chain){
		this.chain = chain;
	}

	public String getChain(){
		return chain;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setAddress(Address address){
		this.address = address;
	}

	public Address getAddress(){
		return address;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return phone;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}
}