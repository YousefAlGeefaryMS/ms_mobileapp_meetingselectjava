package com.meetingselect.meetingselect.data.model.AcceptConfirmMS2Model;

import com.google.gson.annotations.SerializedName;

public class ProposalAttachmentsItem{

	@SerializedName("Category")
	private String category;

	@SerializedName("RfpID")
	private int rfpID;

	@SerializedName("ProposalID")
	private Object proposalID;

	@SerializedName("AttachmentDescription")
	private String attachmentDescription;

	@SerializedName("ID")
	private int iD;

	@SerializedName("AttachmentFileName_deleted")
	private Object attachmentFileNameDeleted;

	@SerializedName("VenueID")
	private int venueID;

	@SerializedName("CancellationPaymentPolicy")
	private String cancellationPaymentPolicy;

	@SerializedName("URL")
	private String uRL;

	public String getCategory(){
		return category;
	}

	public int getRfpID(){
		return rfpID;
	}

	public Object getProposalID(){
		return proposalID;
	}

	public String getAttachmentDescription(){
		return attachmentDescription;
	}

	public int getID(){
		return iD;
	}

	public Object getAttachmentFileNameDeleted(){
		return attachmentFileNameDeleted;
	}

	public int getVenueID(){
		return venueID;
	}

	public String getCancellationPaymentPolicy(){
		return cancellationPaymentPolicy;
	}

	public String getURL(){
		return uRL;
	}
}