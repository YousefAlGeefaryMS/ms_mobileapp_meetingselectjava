package com.meetingselect.meetingselect.data.model.VenueDetailsModel.RoomsAvailability;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class SpacesItem{

	@SerializedName("SpaceName")
	private String spaceName;

	@SerializedName("SpaceDescription")
	private String spaceDescription;

	@SerializedName("M2")
	private int m2;

	@SerializedName("Hash")
	private String hash;

	@SerializedName("CurrencyId")
	private int currencyId;

	@SerializedName("CurrencySymbol")
	private String currencySymbol;

	@SerializedName("StartDate")
	private String startDate;

	@SerializedName("SpacePrice")
	private Object spacePrice;

	@SerializedName("MaxSeats")
	private int maxSeats;

	@SerializedName("MinSeats")
	private int minSeats;

	@SerializedName("TaxPercentage")
	private double taxPercentage;

	@SerializedName("PriceTotal")
	private double priceTotal;

	@SerializedName("SpaceId")
	private int spaceId;

	@SerializedName("SpaceAvailable")
	private boolean spaceAvailable;

	@SerializedName("SpaceInternalName")
	private String spaceInternalName;

	@SerializedName("EndDate")
	private String endDate;

	@SerializedName("SettingId")
	private int settingId;

	@SerializedName("Seats")
	private int seats;

	@SerializedName("CurrencyIso")
	private String currencyIso;

	@SerializedName("Price")
	private double price;

	@SerializedName("Crc")
	private int crc;

	@SerializedName("SpaceImage")
	private String spaceImage;

	@SerializedName("EventTypes")
	private List<Object> eventTypes;

	@SerializedName("Id")
	private String id;

	@SerializedName("StartMinutes")
	private int startMinutes;

	@SerializedName("Warnings")
	private List<String> warnings;

	@SerializedName("EndMinutes")
	private int endMinutes;

	public String getSpaceName(){
		return spaceName;
	}

	public String getSpaceDescription(){
		return spaceDescription;
	}

	public int getM2(){
		return m2;
	}

	public String getHash(){
		return hash;
	}

	public int getCurrencyId(){
		return currencyId;
	}

	public String getCurrencySymbol(){
		return currencySymbol;
	}

	public String getStartDate(){
		return startDate;
	}

	public Object getSpacePrice(){
		return spacePrice;
	}

	public int getMaxSeats(){
		return maxSeats;
	}

	public int getMinSeats(){
		return minSeats;
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

	public boolean isSpaceAvailable(){
		return spaceAvailable;
	}

	public String getSpaceInternalName(){
		return spaceInternalName;
	}

	public String getEndDate(){
		return endDate;
	}

	public int getSettingId(){
		return settingId;
	}

	public int getSeats(){
		return seats;
	}

	public String getCurrencyIso(){
		return currencyIso;
	}

	public double getPrice(){
		return price;
	}

	public int getCrc(){
		return crc;
	}

	public String getSpaceImage(){
		return spaceImage;
	}

	public List<Object> getEventTypes(){
		return eventTypes;
	}

	public String getId(){
		return id;
	}

	public int getStartMinutes(){
		return startMinutes;
	}

	public List<String> getWarnings(){
		return warnings;
	}

	public int getEndMinutes(){
		return endMinutes;
	}
}