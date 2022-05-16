package com.meetingselect.meetingselect.data.model.FinalizeCartModel;

import com.google.gson.annotations.SerializedName;

public class ReservationTerms{

	@SerializedName("AcceptedOn")
	private String acceptedOn;

	@SerializedName("ReservationId")
	private int reservationId;

	@SerializedName("AcceptedBy")
	private int acceptedBy;

	@SerializedName("AcceptedByName")
	private String acceptedByName;

	@SerializedName("Id")
	private int id;

	@SerializedName("BookingTerm")
	private BookingTerm bookingTerm;

	@SerializedName("TermsId")
	private int termsId;

	@SerializedName("Accepted")
	private boolean accepted;

	public String getAcceptedOn(){
		return acceptedOn;
	}

	public int getReservationId(){
		return reservationId;
	}

	public int getAcceptedBy(){
		return acceptedBy;
	}

	public String getAcceptedByName(){
		return acceptedByName;
	}

	public int getId(){
		return id;
	}

	public BookingTerm getBookingTerm(){
		return bookingTerm;
	}

	public int getTermsId(){
		return termsId;
	}

	public boolean isAccepted(){
		return accepted;
	}
}