package com.meetingselect.meetingselect.data.model.VenueDetailsModel.RoomsAvailability;

import com.google.gson.annotations.SerializedName;

public class SpacePrice{

	@SerializedName("BasePrice")
	private double basePrice;

	@SerializedName("GroupSizeModifier")
	private double groupSizeModifier;

	@SerializedName("LocationUtilizationTopValue")
	private double locationUtilizationTopValue;

	@SerializedName("GroupSizePercentage")
	private double groupSizePercentage;

	@SerializedName("PriceAfterYieldPercentage")
	private double priceAfterYieldPercentage;

	@SerializedName("ApplyDayPercentage")
	private boolean applyDayPercentage;

	@SerializedName("PriceAfterSpaceUtilization")
	private double priceAfterSpaceUtilization;

	@SerializedName("YieldDayPercentage")
	private double yieldDayPercentage;

	@SerializedName("YieldDayModifier")
	private double yieldDayModifier;

	@SerializedName("PriceAfterSpacePercentage")
	private double priceAfterSpacePercentage;

	@SerializedName("PriceAfterVoucherPercentage")
	private double priceAfterVoucherPercentage;

	@SerializedName("LocationUtilizationPercentage")
	private double locationUtilizationPercentage;

	@SerializedName("ApplyLocationUtilization")
	private boolean applyLocationUtilization;

	@SerializedName("LocationUtilizationBottomValue")
	private double locationUtilizationBottomValue;

	@SerializedName("SpaceUtilizationModifier")
	private double spaceUtilizationModifier;

	@SerializedName("NrOfDayParts")
	private int nrOfDayParts;

	@SerializedName("ApplySpaceUtilization")
	private boolean applySpaceUtilization;

	@SerializedName("ApplyGroupSize")
	private boolean applyGroupSize;

	@SerializedName("LocationUtilizationModifier")
	private double locationUtilizationModifier;

	@SerializedName("VoucherPercentage")
	private double voucherPercentage;

	@SerializedName("PriceAfterLocationUtilization")
	private double priceAfterLocationUtilization;

	@SerializedName("SpaceUtilizationPercentage")
	private double spaceUtilizationPercentage;

	@SerializedName("SpaceUtilizationBottomValue")
	private double spaceUtilizationBottomValue;

	@SerializedName("SpaceModifier")
	private double spaceModifier;

	@SerializedName("CalculationType")
	private String calculationType;

	@SerializedName("CalculatedPrice")
	private double calculatedPrice;

	@SerializedName("PriceAfterGroupSize")
	private double priceAfterGroupSize;

	@SerializedName("SpaceUtilizationTopValue")
	private double spaceUtilizationTopValue;

	@SerializedName("ApplySpacePercentage")
	private boolean applySpacePercentage;

	@SerializedName("SpacePercentage")
	private double spacePercentage;

	public double getBasePrice(){
		return basePrice;
	}

	public double getGroupSizeModifier(){
		return groupSizeModifier;
	}

	public double getLocationUtilizationTopValue(){
		return locationUtilizationTopValue;
	}

	public double getGroupSizePercentage(){
		return groupSizePercentage;
	}

	public double getPriceAfterYieldPercentage(){
		return priceAfterYieldPercentage;
	}

	public boolean isApplyDayPercentage(){
		return applyDayPercentage;
	}

	public double getPriceAfterSpaceUtilization(){
		return priceAfterSpaceUtilization;
	}

	public double getYieldDayPercentage(){
		return yieldDayPercentage;
	}

	public double getYieldDayModifier(){
		return yieldDayModifier;
	}

	public double getPriceAfterSpacePercentage(){
		return priceAfterSpacePercentage;
	}

	public double getPriceAfterVoucherPercentage(){
		return priceAfterVoucherPercentage;
	}

	public double getLocationUtilizationPercentage(){
		return locationUtilizationPercentage;
	}

	public boolean isApplyLocationUtilization(){
		return applyLocationUtilization;
	}

	public double getLocationUtilizationBottomValue(){
		return locationUtilizationBottomValue;
	}

	public double getSpaceUtilizationModifier(){
		return spaceUtilizationModifier;
	}

	public int getNrOfDayParts(){
		return nrOfDayParts;
	}

	public boolean isApplySpaceUtilization(){
		return applySpaceUtilization;
	}

	public boolean isApplyGroupSize(){
		return applyGroupSize;
	}

	public double getLocationUtilizationModifier(){
		return locationUtilizationModifier;
	}

	public double getVoucherPercentage(){
		return voucherPercentage;
	}

	public double getPriceAfterLocationUtilization(){
		return priceAfterLocationUtilization;
	}

	public double getSpaceUtilizationPercentage(){
		return spaceUtilizationPercentage;
	}

	public double getSpaceUtilizationBottomValue(){
		return spaceUtilizationBottomValue;
	}

	public double getSpaceModifier(){
		return spaceModifier;
	}

	public String getCalculationType(){
		return calculationType;
	}

	public double getCalculatedPrice(){
		return calculatedPrice;
	}

	public double getPriceAfterGroupSize(){
		return priceAfterGroupSize;
	}

	public double getSpaceUtilizationTopValue(){
		return spaceUtilizationTopValue;
	}

	public boolean isApplySpacePercentage(){
		return applySpacePercentage;
	}

	public double getSpacePercentage(){
		return spacePercentage;
	}
}