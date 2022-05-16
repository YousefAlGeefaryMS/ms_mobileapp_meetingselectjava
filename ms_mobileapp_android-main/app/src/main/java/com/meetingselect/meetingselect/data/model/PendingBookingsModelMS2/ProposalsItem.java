package com.meetingselect.meetingselect.data.model.PendingBookingsModelMS2;

import com.google.gson.annotations.SerializedName;

public class ProposalsItem{

	@SerializedName("Status")
	private int status;

	@SerializedName("VenueId")
	private int venueId;

	@SerializedName("ProposalId")
	private int proposalID;

	public int getProposalID() {
		return proposalID;
	}

	public int getStatus(){
		return status;
	}

	public int getVenueId(){
		return venueId;
	}
}