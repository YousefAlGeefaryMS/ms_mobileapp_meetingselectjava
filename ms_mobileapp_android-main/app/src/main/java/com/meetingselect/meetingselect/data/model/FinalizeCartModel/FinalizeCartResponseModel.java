package com.meetingselect.meetingselect.data.model.FinalizeCartModel;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class FinalizeCartResponseModel{

	@SerializedName("ProfileId")
	private int profileId;

	@SerializedName("TotalOpen")
	private double totalOpen;

	@SerializedName("AmountToPay")
	private double amountToPay;

	@SerializedName("CurrencySymbol")
	private String currencySymbol;

	@SerializedName("Name")
	private String name;

	@SerializedName("StartDate")
	private String startDate;

	@SerializedName("TotalInclTax")
	private double totalInclTax;

	@SerializedName("CompanyName")
	private String companyName;

	@SerializedName("MeetingtypeId")
	private int meetingtypeId;

	@SerializedName("TotalPaid")
	private double totalPaid;

	@SerializedName("ReservationTerms")
	private ReservationTerms reservationTerms;

	@SerializedName("HasInvoice")
	private int hasInvoice;

	@SerializedName("HoursUntilStart")
	private int hoursUntilStart;

	@SerializedName("Tags")
	private String tags;

	@SerializedName("CreatedBy")
	private int createdBy;

	@SerializedName("WorkingOn")
	private String workingOn;

	@SerializedName("TenderId")
	private int tenderId;

	@SerializedName("ChannelName")
	private String channelName;

	@SerializedName("Spaces")
	private List<SpacesItem> spaces;

	@SerializedName("StatusId")
	private int statusId;

	@SerializedName("Id")
	private int id;

	@SerializedName("SetId")
	private int setId;

	@SerializedName("Key")
	private String key;

	@SerializedName("CreatedByName")
	private String createdByName;

	@SerializedName("Options")
	private List<Object> options;

	@SerializedName("TaxTotals")
	private List<TaxTotalsItem> taxTotals;

	@SerializedName("QuoteId")
	private int quoteId;

	@SerializedName("Cancel")
	private Object cancel;

	@SerializedName("LocationImage")
	private String locationImage;

	@SerializedName("ProfileName")
	private String profileName;

	@SerializedName("TotalExclTax")
	private double totalExclTax;

	@SerializedName("ChannelId")
	private int channelId;

	@SerializedName("CurrencyId")
	private int currencyId;

	@SerializedName("LocationName")
	private String locationName;

	@SerializedName("CreatedOn")
	private long createdOn;

	@SerializedName("TotalSeats")
	private int totalSeats;

	@SerializedName("ExternalLinks")
	private List<ExternalLinksItem> externalLinks;

	@SerializedName("Notes")
	private List<Object> notes;

	@SerializedName("CompanyId")
	private int companyId;

	@SerializedName("Comments")
	private List<Object> comments;

	@SerializedName("LocationId")
	private int locationId;

	@SerializedName("VoucherName")
	private String voucherName;

	@SerializedName("EndDate")
	private String endDate;

	@SerializedName("DaysUntilStart")
	private int daysUntilStart;

	@SerializedName("Voucher")
	private Object voucher;

	@SerializedName("Type")
	private String type;

	@SerializedName("InvoiceAddress")
	private InvoiceAddress invoiceAddress;

	@SerializedName("CurrencyIso")
	private String currencyIso;

	@SerializedName("NoTax")
	private boolean noTax;

	@SerializedName("Contacts")
	private List<ContactsItem> contacts;

	@SerializedName("HasDepositInvoice")
	private int hasDepositInvoice;

	@SerializedName("VoucherId")
	private int voucherId;

	@SerializedName("ReservationSetIds")
	private List<Object> reservationSetIds;

	@SerializedName("StartMinutes")
	private int startMinutes;

	@SerializedName("LanguageId")
	private int languageId;

	@SerializedName("ToDos")
	private List<Object> toDos;

	@SerializedName("EndMinutes")
	private int endMinutes;

	public int getProfileId(){
		return profileId;
	}

	public double getTotalOpen(){
		return totalOpen;
	}

	public double getAmountToPay(){
		return amountToPay;
	}

	public String getCurrencySymbol(){
		return currencySymbol;
	}

	public String getName(){
		return name;
	}

	public String getStartDate(){
		return startDate;
	}

	public double getTotalInclTax(){
		return totalInclTax;
	}

	public String getCompanyName(){
		return companyName;
	}

	public int getMeetingtypeId(){
		return meetingtypeId;
	}

	public double getTotalPaid(){
		return totalPaid;
	}

	public ReservationTerms getReservationTerms(){
		return reservationTerms;
	}

	public int getHasInvoice(){
		return hasInvoice;
	}

	public int getHoursUntilStart(){
		return hoursUntilStart;
	}

	public String getTags(){
		return tags;
	}

	public int getCreatedBy(){
		return createdBy;
	}

	public String getWorkingOn(){
		return workingOn;
	}

	public int getTenderId(){
		return tenderId;
	}

	public String getChannelName(){
		return channelName;
	}

	public List<SpacesItem> getSpaces(){
		return spaces;
	}

	public int getStatusId(){
		return statusId;
	}

	public int getId(){
		return id;
	}

	public int getSetId(){
		return setId;
	}

	public String getKey(){
		return key;
	}

	public String getCreatedByName(){
		return createdByName;
	}

	public List<Object> getOptions(){
		return options;
	}

	public List<TaxTotalsItem> getTaxTotals(){
		return taxTotals;
	}

	public int getQuoteId(){
		return quoteId;
	}

	public Object getCancel(){
		return cancel;
	}

	public String getLocationImage(){
		return locationImage;
	}

	public String getProfileName(){
		return profileName;
	}

	public double getTotalExclTax(){
		return totalExclTax;
	}

	public int getChannelId(){
		return channelId;
	}

	public int getCurrencyId(){
		return currencyId;
	}

	public String getLocationName(){
		return locationName;
	}

	public long getCreatedOn(){
		return createdOn;
	}

	public int getTotalSeats(){
		return totalSeats;
	}

	public List<ExternalLinksItem> getExternalLinks(){
		return externalLinks;
	}

	public List<Object> getNotes(){
		return notes;
	}

	public int getCompanyId(){
		return companyId;
	}

	public List<Object> getComments(){
		return comments;
	}

	public int getLocationId(){
		return locationId;
	}

	public String getVoucherName(){
		return voucherName;
	}

	public String getEndDate(){
		return endDate;
	}

	public int getDaysUntilStart(){
		return daysUntilStart;
	}

	public Object getVoucher(){
		return voucher;
	}

	public String getType(){
		return type;
	}

	public InvoiceAddress getInvoiceAddress(){
		return invoiceAddress;
	}

	public String getCurrencyIso(){
		return currencyIso;
	}

	public boolean isNoTax(){
		return noTax;
	}

	public List<ContactsItem> getContacts(){
		return contacts;
	}

	public int getHasDepositInvoice(){
		return hasDepositInvoice;
	}

	public int getVoucherId(){
		return voucherId;
	}

	public List<Object> getReservationSetIds(){
		return reservationSetIds;
	}

	public int getStartMinutes(){
		return startMinutes;
	}

	public int getLanguageId(){
		return languageId;
	}

	public List<Object> getToDos(){
		return toDos;
	}

	public int getEndMinutes(){
		return endMinutes;
	}
}