package com.meetingselect.meetingselect.data.model.PendingBookingsModelMS2;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ListItem{

	@SerializedName("UserNameOfBehalfOfMe")
	private Object userNameOfBehalfOfMe;

	@SerializedName("RfpVenues")
	private List<RfpVenuesItem> rfpVenues;

	@SerializedName("SeriesId")
	private Object seriesId;

	@SerializedName("UserLastName")
	private String userLastName;

	@SerializedName("NoMatchChecked")
	private Object noMatchChecked;

	@SerializedName("Proposals")
	private List<ProposalsItem> proposals;

	@SerializedName("RFPID")
	private int rFPID;

	@SerializedName("Version")
	private int version;

	@SerializedName("UserID")
	private int userID;

	@SerializedName("LinkedUserId")
	private int linkedUserId;

	@SerializedName("DepartureDate")
	private String departureDate;

	@SerializedName("PDFUrl")
	private Object pDFUrl;

	@SerializedName("Status")
	private int status;

	@SerializedName("LinkedUserName")
	private String linkedUserName;

	@SerializedName("GUID")
	private String gUID;

	@SerializedName("UserFirstName")
	private String userFirstName;

	@SerializedName("MeetingRoomFacility")
	private Object meetingRoomFacility;

	@SerializedName("LinkedUserManualEntry")
	private String linkedUserManualEntry;

	@SerializedName("ArrivalDate")
	private String arrivalDate;

	@SerializedName("ReportFieldAnswers")
	private List<Object> reportFieldAnswers;

	@SerializedName("MeetingType")
	private Object meetingType;

	@SerializedName("SeriesMainRfpId")
	private Object seriesMainRfpId;

	@SerializedName("CreatedDate")
	private String createdDate;

	@SerializedName("MeetingName")
	private String meetingName;

	@SerializedName("IsHotelBooking")
	private boolean isHotelBooking;

	@SerializedName("IsNoMatch")
	private Object isNoMatch;

	public Object getUserNameOfBehalfOfMe(){
		return userNameOfBehalfOfMe;
	}

	public List<RfpVenuesItem> getRfpVenues(){
		return rfpVenues;
	}

	public Object getSeriesId(){
		return seriesId;
	}

	public String getUserLastName(){
		return userLastName;
	}

	public Object getNoMatchChecked(){
		return noMatchChecked;
	}

	public List<ProposalsItem> getProposals(){
		return proposals;
	}

	public int getRFPID(){
		return rFPID;
	}

	public int getVersion(){
		return version;
	}

	public int getUserID(){
		return userID;
	}

	public int getLinkedUserId(){
		return linkedUserId;
	}

	public String getDepartureDate(){
		return departureDate;
	}

	public Object getPDFUrl(){
		return pDFUrl;
	}

	public int getStatus(){
		return status;
	}

	public String getLinkedUserName(){
		return linkedUserName;
	}

	public String getGUID(){
		return gUID;
	}

	public String getUserFirstName(){
		return userFirstName;
	}

	public Object getMeetingRoomFacility(){
		return meetingRoomFacility;
	}

	public String getLinkedUserManualEntry(){
		return linkedUserManualEntry;
	}

	public String getArrivalDate(){
		return arrivalDate;
	}

	public List<Object> getReportFieldAnswers(){
		return reportFieldAnswers;
	}

	public Object getMeetingType(){
		return meetingType;
	}

	public Object getSeriesMainRfpId(){
		return seriesMainRfpId;
	}

	public String getCreatedDate(){
		return createdDate;
	}

	public String getMeetingName(){
		return meetingName;
	}

	public boolean isIsHotelBooking(){
		return isHotelBooking;
	}

	public Object getIsNoMatch(){
		return isNoMatch;
	}
}