package com.meetingselect.meetingselect.data.model.ConfirmedBookingsModelMS2;

import com.google.gson.annotations.SerializedName;

public class ListItem{

	@SerializedName("Status")
	private int status;

	@SerializedName("InstantBookingExternalID")
	private Object instantBookingExternalID;

	@SerializedName("LinkedUserName")
	private String linkedUserName;

	@SerializedName("VenueName")
	private String venueName;

	@SerializedName("FirstName")
	private String firstName;

	@SerializedName("AmountMoreThanCutoff")
	private boolean amountMoreThanCutoff;

	@SerializedName("CompanyCutoffAmount")
	private double companyCutoffAmount;

	@SerializedName("OrderNo")
	private String orderNo;

	@SerializedName("MeetingRoomFacility")
	private Object meetingRoomFacility;

	@SerializedName("LinkedUserManualEntry")
	private String linkedUserManualEntry;

	@SerializedName("MoreThanCutoffAmount")
	private Object moreThanCutoffAmount;

	@SerializedName("RFPID")
	private int rFPID;

	@SerializedName("ChainName")
	private String chainName;

	@SerializedName("ArrivalDate")
	private String arrivalDate;

	@SerializedName("UserID")
	private int userID;

	@SerializedName("LinkedUserId")
	private int linkedUserId;

	@SerializedName("MeetingType")
	private Object meetingType;

	@SerializedName("IsInstantBooking")
	private boolean isInstantBooking;

	@SerializedName("MeetingName")
	private String meetingName;

	@SerializedName("DepartureDate")
	private String departureDate;

	@SerializedName("SendDate")
	private String sendDate;

	@SerializedName("LastName")
	private String lastName;

	@SerializedName("IsHotelBooking")
	private boolean isHotelBooking;

	@SerializedName("VenueID")
	private int venueID;

	@SerializedName("ProposalID")
	private String proposalID;

	public int getStatus(){
		return status;
	}

	public Object getInstantBookingExternalID(){
		return instantBookingExternalID;
	}

	public String getLinkedUserName(){
		return linkedUserName;
	}

	public String getVenueName(){
		return venueName;
	}

	public String getFirstName(){
		return firstName;
	}

	public boolean isAmountMoreThanCutoff(){
		return amountMoreThanCutoff;
	}

	public double getCompanyCutoffAmount(){
		return companyCutoffAmount;
	}

	public String getOrderNo(){
		return orderNo;
	}

	public Object getMeetingRoomFacility(){
		return meetingRoomFacility;
	}

	public String getLinkedUserManualEntry(){
		return linkedUserManualEntry;
	}

	public Object getMoreThanCutoffAmount(){
		return moreThanCutoffAmount;
	}

	public int getRFPID(){
		return rFPID;
	}

	public String getChainName(){
		return chainName;
	}

	public String getArrivalDate(){
		return arrivalDate;
	}

	public int getUserID(){
		return userID;
	}

	public int getLinkedUserId(){
		return linkedUserId;
	}

	public Object getMeetingType(){
		return meetingType;
	}

	public boolean isIsInstantBooking(){
		return isInstantBooking;
	}

	public String getMeetingName(){
		return meetingName;
	}

	public String getDepartureDate(){
		return departureDate;
	}

	public String getSendDate(){
		return sendDate;
	}

	public String getLastName(){
		return lastName;
	}

	public boolean isIsHotelBooking(){
		return isHotelBooking;
	}

	public int getVenueID(){
		return venueID;
	}

	public String getProposalID() {
		return proposalID;
	}
}