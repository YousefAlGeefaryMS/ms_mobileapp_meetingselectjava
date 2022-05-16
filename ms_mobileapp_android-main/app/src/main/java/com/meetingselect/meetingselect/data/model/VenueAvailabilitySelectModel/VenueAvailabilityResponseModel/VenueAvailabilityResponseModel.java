package com.meetingselect.meetingselect.data.model.VenueAvailabilitySelectModel.VenueAvailabilityResponseModel;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class VenueAvailabilityResponseModel{

	@SerializedName("ProfileId")
	private int profileId;

	@SerializedName("CurrencySymbol")
	private String currencySymbol;

	@SerializedName("Name")
	private String name;

	@SerializedName("TotalInclTax")
	private double totalInclTax;

	@SerializedName("StartDate")
	private String startDate;

	@SerializedName("CompanyName")
	private String companyName;

	@SerializedName("LocationVatNumber")
	private String locationVatNumber;

	@SerializedName("PublicationStatus")
	private String publicationStatus;

	@SerializedName("LocationCocNumber")
	private String locationCocNumber;

	@SerializedName("Tags")
	private String tags;

	@SerializedName("CreatedBy")
	private int createdBy;

	@SerializedName("CartType")
	private String cartType;

	@SerializedName("CartTerms")
	private CartTerms cartTerms;

	@SerializedName("UrlId")
	private int urlId;

	@SerializedName("WorkingOn")
	private String workingOn;

	@SerializedName("TenderId")
	private int tenderId;

	@SerializedName("LocationCocName")
	private String locationCocName;

	@SerializedName("Spaces")
	private List<SpacesItem> spaces;

	@SerializedName("StatusId")
	private int statusId;

	@SerializedName("Id")
	private int id;

	@SerializedName("CartKey")
	private String cartKey;

	@SerializedName("Options")
	private List<Object> options;

	@SerializedName("TaxTotals")
	private List<TaxTotalsItem> taxTotals;

	@SerializedName("LocationImage")
	private String locationImage;

	@SerializedName("MinutesToExpire")
	private int minutesToExpire;

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

	@SerializedName("SearchId")
	private int searchId;

	@SerializedName("Language")
	private String language;

	@SerializedName("MeetingTypeId")
	private int meetingTypeId;

	@SerializedName("IsBackend")
	private boolean isBackend;

	@SerializedName("CreatedOn")
	private long createdOn;

	@SerializedName("TotalSeats")
	private int totalSeats;

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

	@SerializedName("OfferExpiresOn")
	private String offerExpiresOn;

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

	@SerializedName("VoucherId")
	private int voucherId;

	@SerializedName("StartMinutes")
	private int startMinutes;

	@SerializedName("EndMinutes")
	private int endMinutes;

	@SerializedName("LanguageId")
	private int languageId;

	@SerializedName("LastStep")
	private int lastStep;

	public int getProfileId(){
		return profileId;
	}

	public String getCurrencySymbol(){
		return currencySymbol;
	}

	public String getName(){
		return name;
	}

	public double getTotalInclTax(){
		return totalInclTax;
	}

	public String getStartDate(){
		return startDate;
	}

	public String getCompanyName(){
		return companyName;
	}

	public String getLocationVatNumber(){
		return locationVatNumber;
	}

	public String getPublicationStatus(){
		return publicationStatus;
	}

	public String getLocationCocNumber(){
		return locationCocNumber;
	}

	public String getTags(){
		return tags;
	}

	public int getCreatedBy(){
		return createdBy;
	}

	public String getCartType(){
		return cartType;
	}

	public CartTerms getCartTerms(){
		return cartTerms;
	}

	public int getUrlId(){
		return urlId;
	}

	public String getWorkingOn(){
		return workingOn;
	}

	public int getTenderId(){
		return tenderId;
	}

	public String getLocationCocName(){
		return locationCocName;
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

	public String getCartKey(){
		return cartKey;
	}

	public List<Object> getOptions(){
		return options;
	}

	public List<TaxTotalsItem> getTaxTotals(){
		return taxTotals;
	}

	public String getLocationImage(){
		return locationImage;
	}

	public int getMinutesToExpire(){
		return minutesToExpire;
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

	public int getSearchId(){
		return searchId;
	}

	public String getLanguage(){
		return language;
	}

	public int getMeetingTypeId(){
		return meetingTypeId;
	}

	public boolean isIsBackend(){
		return isBackend;
	}

	public long getCreatedOn(){
		return createdOn;
	}

	public int getTotalSeats(){
		return totalSeats;
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

	public String getOfferExpiresOn(){
		return offerExpiresOn;
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

	public int getVoucherId(){
		return voucherId;
	}

	public int getStartMinutes(){
		return startMinutes;
	}

	public int getEndMinutes(){
		return endMinutes;
	}

	public int getLanguageId(){
		return languageId;
	}

	public int getLastStep(){
		return lastStep;
	}
}