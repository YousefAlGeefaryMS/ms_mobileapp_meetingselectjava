package com.meetingselect.meetingselect.data.model.VenueResultsInstantBookingModelMS4.ResponseModel;

import com.google.gson.annotations.SerializedName;

public class NumberOfAttendees{

	@SerializedName("numberOfAttendessInReception")
	private NumberOfAttendessInReception numberOfAttendessInReception;

	@SerializedName("numberOfAttendessInCabaret")
	private NumberOfAttendessInCabaret numberOfAttendessInCabaret;

	@SerializedName("numberOfAttendessInClassroom")
	private NumberOfAttendessInClassroom numberOfAttendessInClassroom;

	@SerializedName("numberOfAttendessInUShape")
	private NumberOfAttendessInUShape numberOfAttendessInUShape;

	@SerializedName("numberOfAttendessInDinner")
	private NumberOfAttendessInDinner numberOfAttendessInDinner;

	@SerializedName("numberOfAttendessInTheatre")
	private NumberOfAttendessInTheatre numberOfAttendessInTheatre;

	@SerializedName("numberOfAttendessInBoardroom")
	private NumberOfAttendessInBoardroom numberOfAttendessInBoardroom;

	@SerializedName("numberOfAttendessInCarre")
	private NumberOfAttendessInCarre numberOfAttendessInCarre;

	public NumberOfAttendessInReception getNumberOfAttendessInReception(){
		return numberOfAttendessInReception;
	}

	public NumberOfAttendessInCabaret getNumberOfAttendessInCabaret(){
		return numberOfAttendessInCabaret;
	}

	public NumberOfAttendessInClassroom getNumberOfAttendessInClassroom(){
		return numberOfAttendessInClassroom;
	}

	public NumberOfAttendessInUShape getNumberOfAttendessInUShape(){
		return numberOfAttendessInUShape;
	}

	public NumberOfAttendessInDinner getNumberOfAttendessInDinner(){
		return numberOfAttendessInDinner;
	}

	public NumberOfAttendessInTheatre getNumberOfAttendessInTheatre(){
		return numberOfAttendessInTheatre;
	}

	public NumberOfAttendessInBoardroom getNumberOfAttendessInBoardroom(){
		return numberOfAttendessInBoardroom;
	}

	public NumberOfAttendessInCarre getNumberOfAttendessInCarre(){
		return numberOfAttendessInCarre;
	}
}