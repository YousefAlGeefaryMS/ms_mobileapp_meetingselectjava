package com.meetingselect.meetingselect.data.model.PendingBookingsModelMS2;

import com.google.gson.annotations.SerializedName;

public class PendingMS2PostModel{

	@SerializedName("RFPID")
	private String RFPID;
	@SerializedName("PageIndex")
	private int pageIndex;
	@SerializedName("PageSize")
	private int pageSize;

	public PendingMS2PostModel(String RFPID, int pageIndex, int pageSize) {
		this.RFPID = RFPID;
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
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