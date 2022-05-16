package com.meetingselect.meetingselect.data.model.VenueResultsInstantBookingModelMS4.ResponseModel;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class AdditionalInfo{

	@SerializedName("reviewScore")
	private int reviewScore;

	@SerializedName("sustainable")
	private String sustainable;

	@SerializedName("restaurantRoom")
	private int restaurantRoom;

	@SerializedName("noOfMeetingRooms")
	private int noOfMeetingRooms;

	@SerializedName("venueTypes")
	private List<VenueTypesItem> venueTypes;

	@SerializedName("nearby")
	private Object nearby;

	@SerializedName("maxCabaretCapacity")
	private List<Integer> maxCabaretCapacity;

	@SerializedName("meetingPackages")
	private Object meetingPackages;

	@SerializedName("facilitiesAndAmenities")
	private List<FacilitiesAndAmenitiesItem> facilitiesAndAmenities;

	@SerializedName("ceilingHeight")
	private List<Integer> ceilingHeight;

	@SerializedName("dedicatedExhibitSpace")
	private int dedicatedExhibitSpace;

	@SerializedName("roomCapacities")
	private Object roomCapacities;

	@SerializedName("maxBoardroomCapacity")
	private List<Integer> maxBoardroomCapacity;

	@SerializedName("distanceSeating")
	private boolean distanceSeating;

	@SerializedName("maxUShapeCapacity")
	private List<Integer> maxUShapeCapacity;

	@SerializedName("distances")
	private List<DistancesItem> distances;

	@SerializedName("noOfParkingSpaces")
	private Object noOfParkingSpaces;

	@SerializedName("maxDinnerCapacity")
	private List<Integer> maxDinnerCapacity;

	@SerializedName("greenKey")
	private String greenKey;

	@SerializedName("spaceTypes")
	private Object spaceTypes;

	@SerializedName("meetingRoomFacilities")
	private Object meetingRoomFacilities;

	@SerializedName("airport")
	private String airport;

	@SerializedName("maxReceptionCapacity")
	private List<Integer> maxReceptionCapacity;

	@SerializedName("maxSchoolCapacity")
	private List<Integer> maxSchoolCapacity;

	@SerializedName("maxTheatreCapacity")
	private List<Integer> maxTheatreCapacity;

	@SerializedName("socialCommunityResponse")
	private Object socialCommunityResponse;

	@SerializedName("maxCarreCapacity")
	private List<Integer> maxCarreCapacity;

	@SerializedName("locations")
	private List<LocationsItem> locations;

	@SerializedName("routeDescription")
	private Object routeDescription;

	@SerializedName("largestMeetingRoom")
	private int largestMeetingRoom;

	@SerializedName("noOfHotelRooms")
	private Object noOfHotelRooms;

	public int getReviewScore(){
		return reviewScore;
	}

	public String getSustainable(){
		return sustainable;
	}

	public int getRestaurantRoom(){
		return restaurantRoom;
	}

	public int getNoOfMeetingRooms(){
		return noOfMeetingRooms;
	}

	public List<VenueTypesItem> getVenueTypes(){
		return venueTypes;
	}

	public Object getNearby(){
		return nearby;
	}

	public List<Integer> getMaxCabaretCapacity(){
		return maxCabaretCapacity;
	}

	public Object getMeetingPackages(){
		return meetingPackages;
	}

	public List<FacilitiesAndAmenitiesItem> getFacilitiesAndAmenities(){
		return facilitiesAndAmenities;
	}

	public List<Integer> getCeilingHeight(){
		return ceilingHeight;
	}

	public int getDedicatedExhibitSpace(){
		return dedicatedExhibitSpace;
	}

	public Object getRoomCapacities(){
		return roomCapacities;
	}

	public List<Integer> getMaxBoardroomCapacity(){
		return maxBoardroomCapacity;
	}

	public boolean isDistanceSeating(){
		return distanceSeating;
	}

	public List<Integer> getMaxUShapeCapacity(){
		return maxUShapeCapacity;
	}

	public List<DistancesItem> getDistances(){
		return distances;
	}

	public Object getNoOfParkingSpaces(){
		return noOfParkingSpaces;
	}

	public List<Integer> getMaxDinnerCapacity(){
		return maxDinnerCapacity;
	}

	public String getGreenKey(){
		return greenKey;
	}

	public Object getSpaceTypes(){
		return spaceTypes;
	}

	public Object getMeetingRoomFacilities(){
		return meetingRoomFacilities;
	}

	public String getAirport(){
		return airport;
	}

	public List<Integer> getMaxReceptionCapacity(){
		return maxReceptionCapacity;
	}

	public List<Integer> getMaxSchoolCapacity(){
		return maxSchoolCapacity;
	}

	public List<Integer> getMaxTheatreCapacity(){
		return maxTheatreCapacity;
	}

	public Object getSocialCommunityResponse(){
		return socialCommunityResponse;
	}

	public List<Integer> getMaxCarreCapacity(){
		return maxCarreCapacity;
	}

	public List<LocationsItem> getLocations(){
		return locations;
	}

	public Object getRouteDescription(){
		return routeDescription;
	}

	public int getLargestMeetingRoom(){
		return largestMeetingRoom;
	}

	public Object getNoOfHotelRooms(){
		return noOfHotelRooms;
	}
}