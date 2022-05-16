package com.meetingselect.meetingselect.data.model.SavedBookingsMS2;

import com.google.gson.annotations.SerializedName;

public class SavedBookingsMS2PostModel{

	@SerializedName("MeetingName")
	private String meetingName;
	@SerializedName("PageIndex")
	private int pageIndex;
	@SerializedName("PageSize")
	private int pageSize;

	public SavedBookingsMS2PostModel(String meetingName, int pageIndex, int pageSize) {
		this.meetingName = meetingName;
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
	}

	public int getPageSize(){
		return pageSize;
	}

	public String getMeetingName(){
		return meetingName;
	}

	public int getPageIndex(){
		return pageIndex;
	}
}