package com.meetingselect.meetingselect.data.model.VenueAvailabilitySelectModel.VenueAvailabilityResponseModel;

import com.google.gson.annotations.SerializedName;

public class CartTerms{

	@SerializedName("AcceptedOn")
	private String acceptedOn;

	@SerializedName("Terms")
	private Terms terms;

	@SerializedName("AcceptedBy")
	private int acceptedBy;

	@SerializedName("CartId")
	private int cartId;

	@SerializedName("Id")
	private int id;

	@SerializedName("TermsId")
	private int termsId;

	@SerializedName("Accepted")
	private boolean accepted;

	public String getAcceptedOn(){
		return acceptedOn;
	}

	public Terms getTerms(){
		return terms;
	}

	public int getAcceptedBy(){
		return acceptedBy;
	}

	public int getCartId(){
		return cartId;
	}

	public int getId(){
		return id;
	}

	public int getTermsId(){
		return termsId;
	}

	public boolean isAccepted(){
		return accepted;
	}
}