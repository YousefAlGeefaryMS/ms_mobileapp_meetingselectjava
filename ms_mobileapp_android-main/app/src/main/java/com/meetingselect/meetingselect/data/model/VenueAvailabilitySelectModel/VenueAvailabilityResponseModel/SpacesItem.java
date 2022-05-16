package com.meetingselect.meetingselect.data.model.VenueAvailabilitySelectModel.VenueAvailabilityResponseModel;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class SpacesItem{

	@SerializedName("SpaceName")
	private String spaceName;

	@SerializedName("SettingName")
	private String settingName;

	@SerializedName("TaxId")
	private int taxId;

	@SerializedName("CurrencyId")
	private int currencyId;

	@SerializedName("CurrencySymbol")
	private String currencySymbol;

	@SerializedName("IsLinked")
	private int isLinked;

	@SerializedName("StartDate")
	private String startDate;

	@SerializedName("IsPackage")
	private boolean isPackage;

	@SerializedName("EditedOn")
	private String editedOn;

	@SerializedName("CartId")
	private int cartId;

	@SerializedName("PricePerSeat")
	private double pricePerSeat;

	@SerializedName("CreatedOn")
	private String createdOn;

	@SerializedName("DayParts")
	private int dayParts;

	@SerializedName("EditedBy")
	private int editedBy;

	@SerializedName("CreatedBy")
	private int createdBy;

	@SerializedName("TaxPercentage")
	private double taxPercentage;

	@SerializedName("PriceTotal")
	private double priceTotal;

	@SerializedName("SpaceId")
	private int spaceId;

	@SerializedName("SettingId")
	private int settingId;

	@SerializedName("EndDate")
	private String endDate;

	@SerializedName("Seats")
	private int seats;

	@SerializedName("CurrencyIso")
	private String currencyIso;

	@SerializedName("CalculationType")
	private String calculationType;

	@SerializedName("Crc")
	private int crc;

	@SerializedName("SpaceImage")
	private String spaceImage;

	@SerializedName("Id")
	private int id;

	@SerializedName("SettingIds")
	private List<Integer> settingIds;

	@SerializedName("StartMinutes")
	private int startMinutes;

	@SerializedName("EndMinutes")
	private int endMinutes;

	public String getSpaceName(){
		return spaceName;
	}

	public String getSettingName(){
		return settingName;
	}

	public int getTaxId(){
		return taxId;
	}

	public int getCurrencyId(){
		return currencyId;
	}

	public String getCurrencySymbol(){
		return currencySymbol;
	}

	public int getIsLinked(){
		return isLinked;
	}

	public String getStartDate(){
		return startDate;
	}

	public boolean isIsPackage(){
		return isPackage;
	}

	public String getEditedOn(){
		return editedOn;
	}

	public int getCartId(){
		return cartId;
	}

	public double getPricePerSeat(){
		return pricePerSeat;
	}

	public String getCreatedOn(){
		return createdOn;
	}

	public int getDayParts(){
		return dayParts;
	}

	public int getEditedBy(){
		return editedBy;
	}

	public int getCreatedBy(){
		return createdBy;
	}

	public double getTaxPercentage(){
		return taxPercentage;
	}

	public double getPriceTotal(){
		return priceTotal;
	}

	public int getSpaceId(){
		return spaceId;
	}

	public int getSettingId(){
		return settingId;
	}

	public String getEndDate(){
		return endDate;
	}

	public int getSeats(){
		return seats;
	}

	public String getCurrencyIso(){
		return currencyIso;
	}

	public String getCalculationType(){
		return calculationType;
	}

	public int getCrc(){
		return crc;
	}

	public String getSpaceImage(){
		return spaceImage;
	}

	public int getId(){
		return id;
	}

	public List<Integer> getSettingIds(){
		return settingIds;
	}

	public int getStartMinutes(){
		return startMinutes;
	}

	public int getEndMinutes(){
		return endMinutes;
	}
}