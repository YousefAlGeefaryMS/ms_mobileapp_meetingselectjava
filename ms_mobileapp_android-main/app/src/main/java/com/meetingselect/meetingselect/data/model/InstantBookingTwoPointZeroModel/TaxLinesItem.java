package com.meetingselect.meetingselect.data.model.InstantBookingTwoPointZeroModel;

import com.google.gson.annotations.SerializedName;

public class TaxLinesItem{

	@SerializedName("Percentage")
	private int percentage;

	@SerializedName("CurrencyCode")
	private String currencyCode;

	@SerializedName("Price")
	private int price;

	public TaxLinesItem(int percentage, String currencyCode, int price) {
		this.percentage = percentage;
		this.currencyCode = currencyCode;
		this.price = price;
	}

	public void setCurrencyCode(String currencyCode){
		this.currencyCode = currencyCode;
	}

	public String getCurrencyCode(){
		return currencyCode;
	}

	public void setPercentage(int percentage){
		this.percentage = percentage;
	}

	public int getPercentage(){
		return percentage;
	}

	public void setPrice(int price){
		this.price = price;
	}

	public int getPrice(){
		return price;
	}
}