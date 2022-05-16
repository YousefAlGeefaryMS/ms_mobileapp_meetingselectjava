package com.meetingselect.meetingselect.data.model.FinalizeCartModel;

import com.google.gson.annotations.SerializedName;

public class ExternalLinksItem{

	@SerializedName("ReservationId")
	private int reservationId;

	@SerializedName("Id")
	private int id;

	@SerializedName("LinkType")
	private String linkType;

	@SerializedName("Link")
	private String link;

	public int getReservationId(){
		return reservationId;
	}

	public int getId(){
		return id;
	}

	public String getLinkType(){
		return linkType;
	}

	public String getLink(){
		return link;
	}
}