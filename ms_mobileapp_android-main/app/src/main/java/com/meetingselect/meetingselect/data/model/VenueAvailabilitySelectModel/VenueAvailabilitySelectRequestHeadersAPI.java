package com.meetingselect.meetingselect.data.model.VenueAvailabilitySelectModel;

import com.google.gson.annotations.SerializedName;

public class VenueAvailabilitySelectRequestHeadersAPI {

	@SerializedName("LocationId")
	private int locationId;

	@SerializedName("SearchId")
	private int searchId;

	@SerializedName("SpaceId")
	private int spaceId;

	@SerializedName("CartType")
	private String cartType;

	@SerializedName("CurrencyId")
	private int currencyId;

	@SerializedName("Crc")
	private int crc;

	@SerializedName("PricePerSeat")
	private double pricePerSeat;

	@SerializedName("Hash")
	private String hash;


	public VenueAvailabilitySelectRequestHeadersAPI(int locationId, int searchId, int spaceId, String cartType, int currencyId, int crc, double pricePerSeat, String hash) {
		this.locationId = locationId;
		this.searchId = searchId;
		this.spaceId = spaceId;
		this.cartType = cartType;
		this.currencyId = currencyId;
		this.crc = crc;
		this.pricePerSeat = pricePerSeat;
		this.hash = hash;
	}

	public int getSearchId(){
		return searchId;
	}

	public String getCartType(){
		return cartType;
	}

	public int getCrc(){
		return crc;
	}

	public int getLocationId(){
		return locationId;
	}

	public int getSpaceId(){
		return spaceId;
	}

	public double getPricePerSeat(){
		return pricePerSeat;
	}

	public String getHash(){
		return hash;
	}

	public int getCurrencyId(){
		return currencyId;
	}
}