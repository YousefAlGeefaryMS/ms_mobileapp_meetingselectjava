package com.meetingselect.meetingselect.data.model.VenueResultsModel.RFPModel;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class RateModelItem{

	@SerializedName("RateStandard")
	private Object rateStandard;

	@SerializedName("CompanyIds")
	private List<Object> companyIds;

	@SerializedName("Target")
	private int target;

	@SerializedName("CorporateFromDate")
	private Object corporateFromDate;

	@SerializedName("Category")
	private int category;

	@SerializedName("RateFrom")
	private int rateFrom;

	@SerializedName("Content")
	private String content;

	@SerializedName("RateMeetingselect")
	private double rateMeetingselect;

	@SerializedName("VenueDefaultCorporateId")
	private Object venueDefaultCorporateId;

	@SerializedName("CompanyChainIds")
	private Object companyChainIds;

	@SerializedName("CorporateToDate")
	private Object corporateToDate;

	public Object getRateStandard(){
		return rateStandard;
	}

	public List<Object> getCompanyIds(){
		return companyIds;
	}

	public int getTarget(){
		return target;
	}

	public Object getCorporateFromDate(){
		return corporateFromDate;
	}

	public int getCategory(){
		return category;
	}

	public int getRateFrom(){
		return rateFrom;
	}

	public String getContent(){
		return content;
	}

	public double getRateMeetingselect(){
		return rateMeetingselect;
	}

	public Object getVenueDefaultCorporateId(){
		return venueDefaultCorporateId;
	}

	public Object getCompanyChainIds(){
		return companyChainIds;
	}

	public Object getCorporateToDate(){
		return corporateToDate;
	}
}