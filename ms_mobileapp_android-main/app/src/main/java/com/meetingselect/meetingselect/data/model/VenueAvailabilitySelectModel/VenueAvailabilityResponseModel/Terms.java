package com.meetingselect.meetingselect.data.model.VenueAvailabilitySelectModel.VenueAvailabilityResponseModel;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Terms{

	@SerializedName("Changes")
	private String changes;

	@SerializedName("Status")
	private int status;

	@SerializedName("LocationId")
	private int locationId;

	@SerializedName("Text")
	private String text;

	@SerializedName("Languages")
	private List<LanguagesItem> languages;

	@SerializedName("ChannelId")
	private int channelId;

	@SerializedName("Type")
	private String type;

	@SerializedName("Version")
	private String version;

	@SerializedName("CancelRules")
	private List<CancelRulesItem> cancelRules;

	@SerializedName("ItemName")
	private String itemName;

	@SerializedName("Id")
	private int id;

	@SerializedName("CreatedOn")
	private long createdOn;

	@SerializedName("Key")
	private String key;

	public String getChanges(){
		return changes;
	}

	public int getStatus(){
		return status;
	}

	public int getLocationId(){
		return locationId;
	}

	public String getText(){
		return text;
	}

	public List<LanguagesItem> getLanguages(){
		return languages;
	}

	public int getChannelId(){
		return channelId;
	}

	public String getType(){
		return type;
	}

	public String getVersion(){
		return version;
	}

	public List<CancelRulesItem> getCancelRules(){
		return cancelRules;
	}

	public String getItemName(){
		return itemName;
	}

	public int getId(){
		return id;
	}

	public long getCreatedOn(){
		return createdOn;
	}

	public String getKey(){
		return key;
	}
}