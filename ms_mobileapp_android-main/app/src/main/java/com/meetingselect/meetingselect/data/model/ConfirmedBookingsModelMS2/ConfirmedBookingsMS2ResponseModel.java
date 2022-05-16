package com.meetingselect.meetingselect.data.model.ConfirmedBookingsModelMS2;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ConfirmedBookingsMS2ResponseModel{

	@SerializedName("List")
	private List<ListItem> list;

	@SerializedName("Count")
	private int count;

	public List<ListItem> getList(){
		return list;
	}

	public int getCount(){
		return count;
	}
}