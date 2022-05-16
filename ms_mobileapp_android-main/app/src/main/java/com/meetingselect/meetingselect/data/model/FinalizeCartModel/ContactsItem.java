package com.meetingselect.meetingselect.data.model.FinalizeCartModel;

import com.google.gson.annotations.SerializedName;

public class ContactsItem{

	@SerializedName("ProfileId")
	private int profileId;

	@SerializedName("Type")
	private String type;

	@SerializedName("ReceiveEmail")
	private boolean receiveEmail;

	@SerializedName("Email")
	private String email;

	@SerializedName("ReservationId")
	private int reservationId;

	@SerializedName("Phone")
	private String phone;

	@SerializedName("Id")
	private int id;

	@SerializedName("Name")
	private String name;

	public int getProfileId(){
		return profileId;
	}

	public String getType(){
		return type;
	}

	public boolean isReceiveEmail(){
		return receiveEmail;
	}

	public String getEmail(){
		return email;
	}

	public int getReservationId(){
		return reservationId;
	}

	public String getPhone(){
		return phone;
	}

	public int getId(){
		return id;
	}

	public String getName(){
		return name;
	}
}