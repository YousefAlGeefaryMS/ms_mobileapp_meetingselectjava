package com.meetingselect.meetingselect.data.model.AcceptConfirmMS2Model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class AcceptConfirmResponseModel{

	@SerializedName("CompanyCurrency")
	private String companyCurrency;

	@SerializedName("GetVenueIDs")
	private String getVenueIDs;

	@SerializedName("RfpPdfUrl")
	private String rfpPdfUrl;

	@SerializedName("SeriesRfpIds")
	private List<Object> seriesRfpIds;

	@SerializedName("EmailOfConfirmationToAnother")
	private String emailOfConfirmationToAnother;

	@SerializedName("CostSortOther")
	private Object costSortOther;

	@SerializedName("DivisionId")
	private String divisionId;

	@SerializedName("AcceptVersionNumber")
	private int acceptVersionNumber;

	@SerializedName("RFPDateDetailsEntities")
	private List<RFPDateDetailsEntitiesItem> rFPDateDetailsEntities;

	@SerializedName("CurrencyCode")
	private String currencyCode;

	@SerializedName("CompanyName")
	private String companyName;

	@SerializedName("Department")
	private String department;

	@SerializedName("CostLocation")
	private String costLocation;

	@SerializedName("ProposalGUID")
	private String proposalGUID;

	@SerializedName("MEID")
	private boolean mEID;

	@SerializedName("SendEmailToHotel")
	private Object sendEmailToHotel;

	@SerializedName("ProposalAttachments")
	private List<ProposalAttachmentsItem> proposalAttachments;

	@SerializedName("DeclineReason")
	private Object declineReason;

	@SerializedName("Division")
	private String division;

	@SerializedName("IsIngCompanyUser")
	private boolean isIngCompanyUser;

	@SerializedName("ProposalPdfFile")
	private String proposalPdfFile;

	@SerializedName("Comment")
	private Object comment;

	@SerializedName("TotalEstimatedCostExclTax")
	private double totalEstimatedCostExclTax;

	@SerializedName("RfpId")
	private int rfpId;

	@SerializedName("NoticeBoardName")
	private Object noticeBoardName;

	@SerializedName("VenueName")
	private String venueName;

	@SerializedName("GetVenueNames")
	private String getVenueNames;

	@SerializedName("Fromsite")
	private String fromsite;

	@SerializedName("CommentForDecline")
	private Object commentForDecline;

	@SerializedName("SupplierPdfPath")
	private String supplierPdfPath;

	@SerializedName("CostCenterId")
	private int costCenterId;

	@SerializedName("PaymentPlanNote")
	private String paymentPlanNote;

	@SerializedName("TotalEstimatedCostExclTaxCompanyCurrency")
	private double totalEstimatedCostExclTaxCompanyCurrency;

	@SerializedName("SendConfirmationToAnotherEmail")
	private boolean sendConfirmationToAnotherEmail;

	@SerializedName("CostSortId")
	private int costSortId;

	@SerializedName("ProposalPdfUrl")
	private String proposalPdfUrl;

	@SerializedName("ProposalId")
	private int proposalId;

	@SerializedName("MeetingName")
	private String meetingName;

	@SerializedName("PaymentMethod")
	private String paymentMethod;

	@SerializedName("CostSortRaw")
	private String costSortRaw;

	@SerializedName("PaymentPlanOtherNote")
	private Object paymentPlanOtherNote;

	@SerializedName("CompanyCurrencySign")
	private String companyCurrencySign;

	@SerializedName("DepartmentId")
	private String departmentId;

	@SerializedName("RfpGUID")
	private String rfpGUID;

	@SerializedName("PaymentPlanId")
	private int paymentPlanId;

	@SerializedName("ContactName")
	private Object contactName;

	public String getCompanyCurrency(){
		return companyCurrency;
	}

	public String getGetVenueIDs(){
		return getVenueIDs;
	}

	public String getRfpPdfUrl(){
		return rfpPdfUrl;
	}

	public List<Object> getSeriesRfpIds(){
		return seriesRfpIds;
	}

	public String getEmailOfConfirmationToAnother(){
		return emailOfConfirmationToAnother;
	}

	public Object getCostSortOther(){
		return costSortOther;
	}

	public String getDivisionId(){
		return divisionId;
	}

	public int getAcceptVersionNumber(){
		return acceptVersionNumber;
	}

	public List<RFPDateDetailsEntitiesItem> getRFPDateDetailsEntities(){
		return rFPDateDetailsEntities;
	}

	public String getCurrencyCode(){
		return currencyCode;
	}

	public String getCompanyName(){
		return companyName;
	}

	public String getDepartment(){
		return department;
	}

	public String getCostLocation(){
		return costLocation;
	}

	public String getProposalGUID(){
		return proposalGUID;
	}

	public boolean isMEID(){
		return mEID;
	}

	public Object getSendEmailToHotel(){
		return sendEmailToHotel;
	}

	public List<ProposalAttachmentsItem> getProposalAttachments(){
		return proposalAttachments;
	}

	public Object getDeclineReason(){
		return declineReason;
	}

	public String getDivision(){
		return division;
	}

	public boolean isIsIngCompanyUser(){
		return isIngCompanyUser;
	}

	public String getProposalPdfFile(){
		return proposalPdfFile;
	}

	public Object getComment(){
		return comment;
	}

	public double getTotalEstimatedCostExclTax(){
		return totalEstimatedCostExclTax;
	}

	public int getRfpId(){
		return rfpId;
	}

	public Object getNoticeBoardName(){
		return noticeBoardName;
	}

	public String getVenueName(){
		return venueName;
	}

	public String getGetVenueNames(){
		return getVenueNames;
	}

	public String getFromsite(){
		return fromsite;
	}

	public Object getCommentForDecline(){
		return commentForDecline;
	}

	public String getSupplierPdfPath(){
		return supplierPdfPath;
	}

	public int getCostCenterId(){
		return costCenterId;
	}

	public String getPaymentPlanNote(){
		return paymentPlanNote;
	}

	public double getTotalEstimatedCostExclTaxCompanyCurrency(){
		return totalEstimatedCostExclTaxCompanyCurrency;
	}

	public boolean isSendConfirmationToAnotherEmail(){
		return sendConfirmationToAnotherEmail;
	}

	public int getCostSortId(){
		return costSortId;
	}

	public String getProposalPdfUrl(){
		return proposalPdfUrl;
	}

	public int getProposalId(){
		return proposalId;
	}

	public String getMeetingName(){
		return meetingName;
	}

	public String getPaymentMethod(){
		return paymentMethod;
	}

	public String getCostSortRaw(){
		return costSortRaw;
	}

	public Object getPaymentPlanOtherNote(){
		return paymentPlanOtherNote;
	}

	public String getCompanyCurrencySign(){
		return companyCurrencySign;
	}

	public String getDepartmentId(){
		return departmentId;
	}

	public String getRfpGUID(){
		return rfpGUID;
	}

	public int getPaymentPlanId(){
		return paymentPlanId;
	}

	public Object getContactName(){
		return contactName;
	}
}