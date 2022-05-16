package com.meetingselect.meetingselect.data.model.AcceptConfirmMS2Model;

import com.google.gson.annotations.SerializedName;

public class RFPDateDetailsEntitiesItem{

	@SerializedName("RFPIDEntity")
	private Object rFPIDEntity;

	@SerializedName("RFPID")
	private Object rFPID;

	@SerializedName("ArrivalDate")
	private String arrivalDate;

	@SerializedName("DetailsID")
	private Object detailsID;

	@SerializedName("DepartureDate")
	private String departureDate;

	@SerializedName("DateType")
	private int dateType;

	@SerializedName("DateTypeEntity")
	private Object dateTypeEntity;

	public Object getRFPIDEntity(){
		return rFPIDEntity;
	}

	public Object getRFPID(){
		return rFPID;
	}

	public String getArrivalDate(){
		return arrivalDate;
	}

	public Object getDetailsID(){
		return detailsID;
	}

	public String getDepartureDate(){
		return departureDate;
	}

	public int getDateType(){
		return dateType;
	}

	public Object getDateTypeEntity(){
		return dateTypeEntity;
	}
}