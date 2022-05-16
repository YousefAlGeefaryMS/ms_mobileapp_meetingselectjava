package com.meetingselect.meetingselect.data.model.InstantBookingTwoPointZeroModel;

import com.google.gson.annotations.SerializedName;

public class Room{

	@SerializedName("Name")
	private String name;

	@SerializedName("Layout")
	private String layout;

	@SerializedName("CurrencyCode")
	private String currencyCode;

	@SerializedName("UnitPrice")
	private double unitPrice;

	@SerializedName("ChargeUnit")
	private String chargeUnit;

	@SerializedName("Amount")
	private int amount;

	@SerializedName("Price")
	private double price;

	public Room(String name, String layout, String currencyCode, double unitPrice, String chargeUnit, int amount, double price) {
		this.name = name;
		this.layout = layout;
		this.currencyCode = currencyCode;
		this.unitPrice = unitPrice;
		this.chargeUnit = chargeUnit;
		this.amount = amount;
		this.price = price;
	}

	public void setCurrencyCode(String currencyCode){
		this.currencyCode = currencyCode;
	}

	public String getCurrencyCode(){
		return currencyCode;
	}

	public void setChargeUnit(String chargeUnit){
		this.chargeUnit = chargeUnit;
	}

	public String getChargeUnit(){
		return chargeUnit;
	}

	public void setUnitPrice(double unitPrice){
		this.unitPrice = unitPrice;
	}

	public double getUnitPrice(){
		return unitPrice;
	}

	public void setLayout(String layout){
		this.layout = layout;
	}

	public String getLayout(){
		return layout;
	}

	public void setPrice(double price){
		this.price = price;
	}

	public double getPrice(){
		return price;
	}

	public void setAmount(int amount){
		this.amount = amount;
	}

	public int getAmount(){
		return amount;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}
}