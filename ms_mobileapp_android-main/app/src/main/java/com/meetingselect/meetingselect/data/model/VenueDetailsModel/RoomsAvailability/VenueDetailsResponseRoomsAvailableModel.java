package com.meetingselect.meetingselect.data.model.VenueDetailsModel.RoomsAvailability;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class VenueDetailsResponseRoomsAvailableModel{

	@SerializedName("TotalLocations")
	private int totalLocations;

	@SerializedName("SearchKey")
	private String searchKey;

	@SerializedName("Locations")
	private List<LocationsItem> locations;

	@SerializedName("AvailableLocations")
	private int availableLocations;

	public int getTotalLocations(){
		return totalLocations;
	}

	public String getSearchKey(){
		return searchKey;
	}

	public List<LocationsItem> getLocations(){
		return locations;
	}

	public int getAvailableLocations(){
		return availableLocations;
	}
}