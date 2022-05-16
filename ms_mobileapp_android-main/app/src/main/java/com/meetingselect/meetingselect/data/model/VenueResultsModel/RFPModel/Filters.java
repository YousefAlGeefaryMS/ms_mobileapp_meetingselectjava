package com.meetingselect.meetingselect.data.model.VenueResultsModel.RFPModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Filters {

    @SerializedName("Latitude")
    @Expose
    private String latitude;
    @SerializedName("Longitude")
    @Expose
    private String longitude;
    @SerializedName("DistanceFrom")
    @Expose
    private Integer distanceFrom;
    @SerializedName("DistanceTo")
    @Expose
    private Integer distanceTo;
    @SerializedName("SelectGreenKey")
    @Expose
    private Boolean selectGreenKey;
    @SerializedName("SelectSustainable")
    @Expose
    private Boolean selectSustainable;
    @SerializedName("SelectGreenKeyAndSustainable")
    @Expose
    private Boolean selectGreenKeyAndSustainable;
    @SerializedName("NumberOfGuestRooms")
    @Expose
    private Integer numberOfGuestRooms;
    @SerializedName("NumberOfMeetingRooms")
    @Expose
    private Integer numberOfMeetingRooms;
    @SerializedName("NumberOfAttendeesInTheater")
    @Expose
    private Integer numberOfAttendeesInTheater;
    @SerializedName("NumberOfAttendeesInSchool")
    @Expose
    private Integer numberOfAttendeesInSchool;
    @SerializedName("NumberOfAttendeesInReception")
    @Expose
    private Integer numberOfAttendeesInReception;
    @SerializedName("NumberOfAttendeesInDinner")
    @Expose
    private Integer numberOfAttendeesInDinner;
    @SerializedName("NumberOfAttendeesInCarre")
    @Expose
    private Integer numberOfAttendeesInCarre;
    @SerializedName("NumberOfAttendeesInCabaret")
    @Expose
    private Integer numberOfAttendeesInCabaret;
    @SerializedName("NumberOfAttendeesInUShape")
    @Expose
    private Integer numberOfAttendeesInUShape;
    @SerializedName("NumberOfAttendeesInBoardroom")
    @Expose
    private Integer numberOfAttendeesInBoardroom;
    @SerializedName("LargestMeetingRoom")
    @Expose
    private Integer largestMeetingRoom;
    @SerializedName("LargestMeetingRoomInFeet")
    @Expose
    private Boolean largestMeetingRoomInFeet;
    @SerializedName("StarClassifications")
    @Expose
    private List<Integer> starClassifications = null;
    @SerializedName("ChainId")
    @Expose
    private Integer chainId;
    @SerializedName("VenueTypes")
    @Expose
    private List<Integer> venueTypes = null;
    @SerializedName("VenueLocations")
    @Expose
    private List<Integer> venueLocations = null;
    @SerializedName("Facilities")
    @Expose
    private List<Integer> facilities = null;
    @SerializedName("DedicatedExhibitSpace")
    @Expose
    private Integer dedicatedExhibitSpace;
    @SerializedName("DedicatedExhibitSpaceInFeet")
    @Expose
    private Boolean dedicatedExhibitSpaceInFeet;
    @SerializedName("CeilingHeight")
    @Expose
    private Integer ceilingHeight;
    @SerializedName("CeilingHeightInFeet")
    @Expose
    private Boolean ceilingHeightInFeet;
    @SerializedName("NumberOfParkingSpaces")
    @Expose
    private Integer numberOfParkingSpaces;
    @SerializedName("ReviewScore")
    @Expose
    private Integer reviewScore;
    @SerializedName("CountryID")
    @Expose
    private Integer countryID;
    @SerializedName("RateItemName")
    @Expose
    private String rateItemName;
    @SerializedName("RateFrom")
    @Expose
    private Integer rateFrom;
    @SerializedName("RateTo")
    @Expose
    private Integer rateTo;
    @SerializedName("IsSearchInNetherands")
    @Expose
    private Boolean isSearchInNetherands;
    @SerializedName("HappenDate")
    @Expose
    private String happenDate;
    @SerializedName("IsOnlyHotel")
    @Expose
    private Boolean isOnlyHotel;
    @SerializedName("IsInstantBooking")
    @Expose
    private String isInstantBooking;
    @SerializedName("SocialCommunityResponse")
    @Expose
    private Boolean socialCommunityResponse;
    @SerializedName("DistanceSeating")
    @Expose
    private Boolean distanceSeating;
    @SerializedName("PlannerMS")
    @Expose
    private String plannerMS;

    public Filters(String latitude, String longitude, int distanceFrom, int distanceTo, String isInstantBooking) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.distanceFrom = distanceFrom;
        this.distanceTo = distanceTo;
        this.isInstantBooking = isInstantBooking;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Integer getDistanceFrom() {
        return distanceFrom;
    }

    public void setDistanceFrom(Integer distanceFrom) {
        this.distanceFrom = distanceFrom;
    }

    public Integer getDistanceTo() {
        return distanceTo;
    }

    public void setDistanceTo(Integer distanceTo) {
        this.distanceTo = distanceTo;
    }

    public Boolean getSelectGreenKey() {
        return selectGreenKey;
    }

    public void setSelectGreenKey(Boolean selectGreenKey) {
        this.selectGreenKey = selectGreenKey;
    }

    public Boolean getSelectSustainable() {
        return selectSustainable;
    }

    public void setSelectSustainable(Boolean selectSustainable) {
        this.selectSustainable = selectSustainable;
    }

    public Boolean getSelectGreenKeyAndSustainable() {
        return selectGreenKeyAndSustainable;
    }

    public void setSelectGreenKeyAndSustainable(Boolean selectGreenKeyAndSustainable) {
        this.selectGreenKeyAndSustainable = selectGreenKeyAndSustainable;
    }

    public Integer getNumberOfGuestRooms() {
        return numberOfGuestRooms;
    }

    public void setNumberOfGuestRooms(Integer numberOfGuestRooms) {
        this.numberOfGuestRooms = numberOfGuestRooms;
    }

    public Integer getNumberOfMeetingRooms() {
        return numberOfMeetingRooms;
    }

    public void setNumberOfMeetingRooms(Integer numberOfMeetingRooms) {
        this.numberOfMeetingRooms = numberOfMeetingRooms;
    }

    public Integer getNumberOfAttendeesInTheater() {
        return numberOfAttendeesInTheater;
    }

    public void setNumberOfAttendeesInTheater(Integer numberOfAttendeesInTheater) {
        this.numberOfAttendeesInTheater = numberOfAttendeesInTheater;
    }

    public Integer getNumberOfAttendeesInSchool() {
        return numberOfAttendeesInSchool;
    }

    public void setNumberOfAttendeesInSchool(Integer numberOfAttendeesInSchool) {
        this.numberOfAttendeesInSchool = numberOfAttendeesInSchool;
    }

    public Integer getNumberOfAttendeesInReception() {
        return numberOfAttendeesInReception;
    }

    public void setNumberOfAttendeesInReception(Integer numberOfAttendeesInReception) {
        this.numberOfAttendeesInReception = numberOfAttendeesInReception;
    }

    public Integer getNumberOfAttendeesInDinner() {
        return numberOfAttendeesInDinner;
    }

    public void setNumberOfAttendeesInDinner(Integer numberOfAttendeesInDinner) {
        this.numberOfAttendeesInDinner = numberOfAttendeesInDinner;
    }

    public Integer getNumberOfAttendeesInCarre() {
        return numberOfAttendeesInCarre;
    }

    public void setNumberOfAttendeesInCarre(Integer numberOfAttendeesInCarre) {
        this.numberOfAttendeesInCarre = numberOfAttendeesInCarre;
    }

    public Integer getNumberOfAttendeesInCabaret() {
        return numberOfAttendeesInCabaret;
    }

    public void setNumberOfAttendeesInCabaret(Integer numberOfAttendeesInCabaret) {
        this.numberOfAttendeesInCabaret = numberOfAttendeesInCabaret;
    }

    public Integer getNumberOfAttendeesInUShape() {
        return numberOfAttendeesInUShape;
    }

    public void setNumberOfAttendeesInUShape(Integer numberOfAttendeesInUShape) {
        this.numberOfAttendeesInUShape = numberOfAttendeesInUShape;
    }

    public Integer getNumberOfAttendeesInBoardroom() {
        return numberOfAttendeesInBoardroom;
    }

    public void setNumberOfAttendeesInBoardroom(Integer numberOfAttendeesInBoardroom) {
        this.numberOfAttendeesInBoardroom = numberOfAttendeesInBoardroom;
    }

    public Integer getLargestMeetingRoom() {
        return largestMeetingRoom;
    }

    public void setLargestMeetingRoom(Integer largestMeetingRoom) {
        this.largestMeetingRoom = largestMeetingRoom;
    }

    public Boolean getLargestMeetingRoomInFeet() {
        return largestMeetingRoomInFeet;
    }

    public void setLargestMeetingRoomInFeet(Boolean largestMeetingRoomInFeet) {
        this.largestMeetingRoomInFeet = largestMeetingRoomInFeet;
    }

    public List<Integer> getStarClassifications() {
        return starClassifications;
    }

    public void setStarClassifications(List<Integer> starClassifications) {
        this.starClassifications = starClassifications;
    }

    public Integer getChainId() {
        return chainId;
    }

    public void setChainId(Integer chainId) {
        this.chainId = chainId;
    }

    public List<Integer> getVenueTypes() {
        return venueTypes;
    }

    public void setVenueTypes(List<Integer> venueTypes) {
        this.venueTypes = venueTypes;
    }

    public List<Integer> getVenueLocations() {
        return venueLocations;
    }

    public void setVenueLocations(List<Integer> venueLocations) {
        this.venueLocations = venueLocations;
    }

    public List<Integer> getFacilities() {
        return facilities;
    }

    public void setFacilities(List<Integer> facilities) {
        this.facilities = facilities;
    }

    public Integer getDedicatedExhibitSpace() {
        return dedicatedExhibitSpace;
    }

    public void setDedicatedExhibitSpace(Integer dedicatedExhibitSpace) {
        this.dedicatedExhibitSpace = dedicatedExhibitSpace;
    }

    public Boolean getDedicatedExhibitSpaceInFeet() {
        return dedicatedExhibitSpaceInFeet;
    }

    public void setDedicatedExhibitSpaceInFeet(Boolean dedicatedExhibitSpaceInFeet) {
        this.dedicatedExhibitSpaceInFeet = dedicatedExhibitSpaceInFeet;
    }

    public Integer getCeilingHeight() {
        return ceilingHeight;
    }

    public void setCeilingHeight(Integer ceilingHeight) {
        this.ceilingHeight = ceilingHeight;
    }

    public Boolean getCeilingHeightInFeet() {
        return ceilingHeightInFeet;
    }

    public void setCeilingHeightInFeet(Boolean ceilingHeightInFeet) {
        this.ceilingHeightInFeet = ceilingHeightInFeet;
    }

    public Integer getNumberOfParkingSpaces() {
        return numberOfParkingSpaces;
    }

    public void setNumberOfParkingSpaces(Integer numberOfParkingSpaces) {
        this.numberOfParkingSpaces = numberOfParkingSpaces;
    }

    public Integer getReviewScore() {
        return reviewScore;
    }

    public void setReviewScore(Integer reviewScore) {
        this.reviewScore = reviewScore;
    }

    public Integer getCountryID() {
        return countryID;
    }

    public void setCountryID(Integer countryID) {
        this.countryID = countryID;
    }

    public String getRateItemName() {
        return rateItemName;
    }

    public void setRateItemName(String rateItemName) {
        this.rateItemName = rateItemName;
    }

    public Integer getRateFrom() {
        return rateFrom;
    }

    public void setRateFrom(Integer rateFrom) {
        this.rateFrom = rateFrom;
    }

    public Integer getRateTo() {
        return rateTo;
    }

    public void setRateTo(Integer rateTo) {
        this.rateTo = rateTo;
    }

    public Boolean getIsSearchInNetherands() {
        return isSearchInNetherands;
    }

    public void setIsSearchInNetherands(Boolean isSearchInNetherands) {
        this.isSearchInNetherands = isSearchInNetherands;
    }

    public String getHappenDate() {
        return happenDate;
    }

    public void setHappenDate(String happenDate) {
        this.happenDate = happenDate;
    }

    public Boolean getIsOnlyHotel() {
        return isOnlyHotel;
    }

    public void setIsOnlyHotel(Boolean isOnlyHotel) {
        this.isOnlyHotel = isOnlyHotel;
    }

    public String getIsInstantBooking() {
        return isInstantBooking;
    }

    public void setIsInstantBooking(String isInstantBooking) {
        this.isInstantBooking = isInstantBooking;
    }

    public Boolean getSocialCommunityResponse() {
        return socialCommunityResponse;
    }

    public void setSocialCommunityResponse(Boolean socialCommunityResponse) {
        this.socialCommunityResponse = socialCommunityResponse;
    }

    public Boolean getDistanceSeating() {
        return distanceSeating;
    }

    public void setDistanceSeating(Boolean distanceSeating) {
        this.distanceSeating = distanceSeating;
    }

    public String getPlannerMS() {
        return plannerMS;
    }

    public void setPlannerMS(String plannerMS) {
        this.plannerMS = plannerMS;
    }


}
