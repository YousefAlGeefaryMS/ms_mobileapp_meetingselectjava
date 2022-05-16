package com.meetingselect.meetingselect.data.model.VenueResultsInstantBookingModelMS4.ResponseModel;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class AdvancedFiltersResponse{

	@SerializedName("meetingRooms")
	private MeetingRooms meetingRooms;

	@SerializedName("dedicaedExhibitSpaces")
	private DedicaedExhibitSpaces dedicaedExhibitSpaces;

	@SerializedName("reviewScore")
	private ReviewScore reviewScore;

	@SerializedName("hotelRooms")
	private HotelRooms hotelRooms;

	@SerializedName("numberOfAttendees")
	private NumberOfAttendees numberOfAttendees;

	@SerializedName("venueTypes")
	private List<VenueTypesItem> venueTypes;

	@SerializedName("largestMeetingRooms")
	private LargestMeetingRooms largestMeetingRooms;

	@SerializedName("ceilingHeights")
	private CeilingHeights ceilingHeights;

	@SerializedName("parkingSpace")
	private ParkingSpace parkingSpace;

	@SerializedName("ratings")
	private Ratings ratings;

	@SerializedName("minPrice")
	private int minPrice;

	@SerializedName("venueLocations")
	private List<VenueLocationsItem> venueLocations;

	@SerializedName("maxPrice")
	private int maxPrice;

	@SerializedName("facilitiesAndAmenititesResponse")
	private List<FacilitiesAndAmenititesResponseItem> facilitiesAndAmenititesResponse;

	@SerializedName("hotelStars")
	private HotelStars hotelStars;

	public MeetingRooms getMeetingRooms(){
		return meetingRooms;
	}

	public DedicaedExhibitSpaces getDedicaedExhibitSpaces(){
		return dedicaedExhibitSpaces;
	}

	public ReviewScore getReviewScore(){
		return reviewScore;
	}

	public HotelRooms getHotelRooms(){
		return hotelRooms;
	}

	public NumberOfAttendees getNumberOfAttendees(){
		return numberOfAttendees;
	}

	public List<VenueTypesItem> getVenueTypes(){
		return venueTypes;
	}

	public LargestMeetingRooms getLargestMeetingRooms(){
		return largestMeetingRooms;
	}

	public CeilingHeights getCeilingHeights(){
		return ceilingHeights;
	}

	public ParkingSpace getParkingSpace(){
		return parkingSpace;
	}

	public Ratings getRatings(){
		return ratings;
	}

	public int getMinPrice(){
		return minPrice;
	}

	public List<VenueLocationsItem> getVenueLocations(){
		return venueLocations;
	}

	public int getMaxPrice(){
		return maxPrice;
	}

	public List<FacilitiesAndAmenititesResponseItem> getFacilitiesAndAmenititesResponse(){
		return facilitiesAndAmenititesResponse;
	}

	public HotelStars getHotelStars(){
		return hotelStars;
	}
}