package com.meetingselect.meetingselect.data.model.InstantBookingTwoPointZeroModel;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class InstantBookingTwoPointZeroRequestModel{

	@SerializedName("BookingId")
	private String bookingId;

	@SerializedName("BookingExternalId")
	private String bookingExternalId;

	@SerializedName("BookingNumber")
	private String bookingNumber;

	@SerializedName("BookingDate")
	private String bookingDate;

	@SerializedName("MeetingName")
	private String meetingName;
	@SerializedName("ArrivalDate")
	private String arrivalDate;

	@SerializedName("DepartureDate")
	private String departureDate;

	@SerializedName("Attendees")
	private int attendees;

	@SerializedName("Venue")
	private Venue venue;

	@SerializedName("Room")
	private Room room;

	@SerializedName("Amenities")
	private List<Object> amenities;

	@SerializedName("TaxLines")
	private List<TaxLinesItem> taxLines;

	@SerializedName("CurrencyCode")
	private String currencyCode;

	@SerializedName("TotalPriceExcludingTax")
	private int totalPriceExcludingTax;

	@SerializedName("TotalTax")
	private int totalTax;

	@SerializedName("TotalPriceIncludingTax")
	private int totalPriceIncludingTax;

	@SerializedName("PlannerId")
	private int plannerId;

	@SerializedName("CompanyReferences")
	private List<Object> companyReferences;

	@SerializedName("BillingAddress")
	private BillingAddress billingAddress;

	public InstantBookingTwoPointZeroRequestModel(String bookingId,
												  String bookingExternalId,
												  String bookingNumber,
												  String bookingDate,
												  String meetingName,
												  String arrivalDate,
												  String departureDate,
												  int attendees,
												  Venue venue,
												  Room room,
												  List<Object> amenities,
												  List<TaxLinesItem> taxLines,
												  String currencyCode,
												  int totalPriceExcludingTax,
												  int totalTax,
												  int totalPriceIncludingTax,
												  int plannerId,
												  List<Object> companyReferences,
												  BillingAddress billingAddress) {
		this.bookingId = bookingId;
		this.bookingExternalId = bookingExternalId;
		this.bookingNumber = bookingNumber;
		this.bookingDate = bookingDate;
		this.meetingName = meetingName;
		this.arrivalDate = arrivalDate;
		this.departureDate = departureDate;
		this.attendees = attendees;
		this.venue = venue;
		this.room = room;
		this.amenities = amenities;
		this.taxLines = taxLines;
		this.currencyCode = currencyCode;
		this.totalPriceExcludingTax = totalPriceExcludingTax;
		this.totalTax = totalTax;
		this.totalPriceIncludingTax = totalPriceIncludingTax;
		this.plannerId = plannerId;
		this.companyReferences = companyReferences;
		this.billingAddress = billingAddress;
	}

	public void setBookingDate(String bookingDate){
		this.bookingDate = bookingDate;
	}

	public String getBookingDate(){
		return bookingDate;
	}

	public void setVenue(Venue venue){
		this.venue = venue;
	}

	public Venue getVenue(){
		return venue;
	}

	public void setBookingExternalId(String bookingExternalId){
		this.bookingExternalId = bookingExternalId;
	}

	public String getBookingExternalId(){
		return bookingExternalId;
	}

	public void setAttendees(int attendees){
		this.attendees = attendees;
	}

	public int getAttendees(){
		return attendees;
	}

	public void setCurrencyCode(String currencyCode){
		this.currencyCode = currencyCode;
	}

	public String getCurrencyCode(){
		return currencyCode;
	}

	public void setBillingAddress(BillingAddress billingAddress){
		this.billingAddress = billingAddress;
	}

	public BillingAddress getBillingAddress(){
		return billingAddress;
	}

	public void setTaxLines(List<TaxLinesItem> taxLines){
		this.taxLines = taxLines;
	}

	public List<TaxLinesItem> getTaxLines(){
		return taxLines;
	}

	public void setTotalPriceIncludingTax(int totalPriceIncludingTax){
		this.totalPriceIncludingTax = totalPriceIncludingTax;
	}

	public int getTotalPriceIncludingTax(){
		return totalPriceIncludingTax;
	}

	public void setArrivalDate(String arrivalDate){
		this.arrivalDate = arrivalDate;
	}

	public String getArrivalDate(){
		return arrivalDate;
	}

	public void setTotalTax(int totalTax){
		this.totalTax = totalTax;
	}

	public int getTotalTax(){
		return totalTax;
	}

	public void setBookingId(String bookingId){
		this.bookingId = bookingId;
	}

	public String getBookingId(){
		return bookingId;
	}

	public void setTotalPriceExcludingTax(int totalPriceExcludingTax){
		this.totalPriceExcludingTax = totalPriceExcludingTax;
	}

	public int getTotalPriceExcludingTax(){
		return totalPriceExcludingTax;
	}

	public void setMeetingName(String meetingName){
		this.meetingName = meetingName;
	}

	public String getMeetingName(){
		return meetingName;
	}

	public void setDepartureDate(String departureDate){
		this.departureDate = departureDate;
	}

	public String getDepartureDate(){
		return departureDate;
	}

	public void setRoom(Room room){
		this.room = room;
	}

	public Room getRoom(){
		return room;
	}

	public void setPlannerId(int plannerId){
		this.plannerId = plannerId;
	}

	public int getPlannerId(){
		return plannerId;
	}

	public void setBookingNumber(String bookingNumber){
		this.bookingNumber = bookingNumber;
	}

	public String getBookingNumber(){
		return bookingNumber;
	}

	public void setAmenities(List<Object> amenities){
		this.amenities = amenities;
	}

	public List<Object> getAmenities(){
		return amenities;
	}

	public void setCompanyReferences(List<Object> companyReferences){
		this.companyReferences = companyReferences;
	}

	public List<Object> getCompanyReferences(){
		return companyReferences;
	}
}