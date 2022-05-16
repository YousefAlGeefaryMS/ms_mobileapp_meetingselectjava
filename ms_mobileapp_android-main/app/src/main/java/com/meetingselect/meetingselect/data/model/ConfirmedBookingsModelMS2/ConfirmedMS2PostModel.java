package com.meetingselect.meetingselect.data.model.ConfirmedBookingsModelMS2;

import com.google.gson.annotations.SerializedName;

public class ConfirmedMS2PostModel{

	@SerializedName("RFPID")
	private String RFPID;

	@SerializedName("PageSize")
	private int pageSize;

	@SerializedName("PageIndex")
	private int pageIndex;

	public ConfirmedMS2PostModel(String RFPID, int pageSize, int pageIndex) {
		this.RFPID = RFPID;
		this.pageSize = pageSize;
		this.pageIndex = pageIndex;
	}

	public int getPageSize(){
		return pageSize;
	}

	public String getRFPID(){
		return RFPID;
	}

	public int getPageIndex(){
		return pageIndex;
	}
}