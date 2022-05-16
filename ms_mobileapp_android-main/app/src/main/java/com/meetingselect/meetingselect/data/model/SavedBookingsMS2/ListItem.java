package com.meetingselect.meetingselect.data.model.SavedBookingsMS2;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ListItem{

	@SerializedName("Status")
	private int status;

	@SerializedName("UserNameOfBehalfOfMe")
	private Object userNameOfBehalfOfMe;

	@SerializedName("RfpVenues")
	private List<RfpVenuesItem> rfpVenues;

	@SerializedName("LinkedUserName")
	private String linkedUserName;

	@SerializedName("SeriesId")
	private Object seriesId;

	@SerializedName("GUID")
	private String gUID;

	@SerializedName("UserLastName")
	private String userLastName;

	@SerializedName("UserFirstName")
	private String userFirstName;

	@SerializedName("MeetingRoomFacility")
	private Object meetingRoomFacility;

	@SerializedName("LinkedUserManualEntry")
	private String linkedUserManualEntry;

	@SerializedName("RFPID")
	private int rFPID;

	@SerializedName("ArrivalDate")
	private String arrivalDate;

	@SerializedName("Version")
	private int version;

	@SerializedName("ReportFieldAnswers")
	private List<Object> reportFieldAnswers;

	@SerializedName("UserID")
	private int userID;

	@SerializedName("LinkedUserId")
	private int linkedUserId;

	@SerializedName("MeetingType")
	private Object meetingType;

	@SerializedName("SeriesMainRfpId")
	private Object seriesMainRfpId;

	@SerializedName("CreatedDate")
	private String createdDate;

	@SerializedName("MeetingName")
	private String meetingName;

	@SerializedName("DepartureDate")
	private String departureDate;

	@SerializedName("IsHotelBooking")
	private boolean isHotelBooking;

	public int getStatus(){
		return status;
	}

	public Object getUserNameOfBehalfOfMe(){
		return userNameOfBehalfOfMe;
	}

	public List<RfpVenuesItem> getRfpVenues(){
		return rfpVenues;
	}

	public String getLinkedUserName(){
		return linkedUserName;
	}

	public Object getSeriesId(){
		return seriesId;
	}

	public String getGUID(){
		return gUID;
	}

	public String getUserLastName(){
		return userLastName;
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

	public int getRFPID(){
		return rFPID;
	}

	public String getArrivalDate(){
		return arrivalDate;
	}

	public int getVersion(){
		return version;
	}

	public List<Object> getReportFieldAnswers(){
		return reportFieldAnswers;
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

	public Object getSeriesMainRfpId(){
		return seriesMainRfpId;
	}

	public String getCreatedDate(){
		return createdDate;
	}

	public String getMeetingName(){
		return meetingName;
	}

	public String getDepartureDate(){
		return departureDate;
	}

	public boolean isIsHotelBooking(){
		return isHotelBooking;
	}
}