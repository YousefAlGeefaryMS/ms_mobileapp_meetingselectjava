package com.meetingselect.meetingselect.data.model.VenueResultsModel.RFPModel;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class VenueResultsResponseModel{

	@SerializedName("Total")
	private int total;

	@SerializedName("CoordinatesForMap")
	private List<CoordinatesForMapItem> coordinatesForMap;

	@SerializedName("Venues")
	private List<VenuesItem> venues;

	public int getTotal(){
		return total;
	}

	public List<CoordinatesForMapItem> getCoordinatesForMap(){
		return coordinatesForMap;
	}

	public List<VenuesItem> getVenues(){
		return venues;
	}
}