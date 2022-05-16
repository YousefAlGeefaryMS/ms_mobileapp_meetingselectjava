package com.meetingselect.meetingselect.data.model.AccountInformationMS2;

import com.google.gson.annotations.SerializedName;

public class AccountInformationMS2ResponseModel{

	@SerializedName("LastModifiedDate")
	private String lastModifiedDate;

	@SerializedName("PlannerOrSupplier")
	private int plannerOrSupplier;

	@SerializedName("PostalState")
	private int postalState;

	@SerializedName("Email")
	private String email;

	@SerializedName("CompanyID")
	private int companyID;

	@SerializedName("Gender")
	private int gender;

	@SerializedName("IsStrategicManagerForEmail")
	private Object isStrategicManagerForEmail;

	@SerializedName("FunctionName")
	private String functionName;

	@SerializedName("CompanyName")
	private String companyName;

	@SerializedName("VenuesLinked")
	private Object venuesLinked;

	@SerializedName("Department")
	private String department;

	@SerializedName("PostalCountry")
	private int postalCountry;

	@SerializedName("AverageBudget")
	private double averageBudget;

	@SerializedName("CostLocation")
	private String costLocation;

	@SerializedName("FaxNumber")
	private Object faxNumber;

	@SerializedName("MEID")
	private Object mEID;

	@SerializedName("AverageAttendies")
	private int averageAttendies;

	@SerializedName("IsOffline")
	private boolean isOffline;

	@SerializedName("DivisionID")
	private int divisionID;

	@SerializedName("Role")
	private int role;

	@SerializedName("PostalAddress")
	private String postalAddress;

	@SerializedName("PostalCode2")
	private String postalCode2;

	@SerializedName("CreatedDate")
	private String createdDate;

	@SerializedName("LastName")
	private String lastName;

	@SerializedName("PostalCode1")
	private String postalCode1;

	@SerializedName("ChainID")
	private Object chainID;

	@SerializedName("PostalCity")
	private String postalCity;

	@SerializedName("VisitingCountry")
	private int visitingCountry;

	@SerializedName("Website")
	private String website;

	@SerializedName("PostalNumber")
	private String postalNumber;

	@SerializedName("VisitingNumber")
	private String visitingNumber;

	@SerializedName("TempUser")
	private Object tempUser;

	@SerializedName("UserID")
	private int userID;

	@SerializedName("MeetingsPerYear")
	private int meetingsPerYear;

	@SerializedName("Division")
	private String division;

	@SerializedName("DepartmentID")
	private int departmentID;

	@SerializedName("Password")
	private Object password;

	@SerializedName("MostlyMeetingType")
	private int mostlyMeetingType;

	@SerializedName("UserName")
	private String userName;

	@SerializedName("VisitingCity")
	private Object visitingCity;

	@SerializedName("FirstName")
	private String firstName;

	@SerializedName("CreatedBySiteId")
	private int createdBySiteId;

	@SerializedName("VisitingState")
	private int visitingState;

	@SerializedName("ReceiveNewsletter")
	private boolean receiveNewsletter;

	@SerializedName("Title")
	private String title;

	@SerializedName("VisitingAddress")
	private String visitingAddress;

	@SerializedName("InitialLogin")
	private Object initialLogin;

	@SerializedName("PhoneNumber")
	private String phoneNumber;

	@SerializedName("TempGUID")
	private Object tempGUID;

	@SerializedName("ReceiveOffers")
	private boolean receiveOffers;

	public String getLastModifiedDate(){
		return lastModifiedDate;
	}

	public int getPlannerOrSupplier(){
		return plannerOrSupplier;
	}

	public int getPostalState(){
		return postalState;
	}

	public String getEmail(){
		return email;
	}

	public int getCompanyID(){
		return companyID;
	}

	public int getGender(){
		return gender;
	}

	public Object getIsStrategicManagerForEmail(){
		return isStrategicManagerForEmail;
	}

	public String getFunctionName(){
		return functionName;
	}

	public String getCompanyName(){
		return companyName;
	}

	public Object getVenuesLinked(){
		return venuesLinked;
	}

	public String getDepartment(){
		return department;
	}

	public int getPostalCountry(){
		return postalCountry;
	}

	public double getAverageBudget(){
		return averageBudget;
	}

	public String getCostLocation(){
		return costLocation;
	}

	public Object getFaxNumber(){
		return faxNumber;
	}

	public Object getMEID(){
		return mEID;
	}

	public int getAverageAttendies(){
		return averageAttendies;
	}

	public boolean isIsOffline(){
		return isOffline;
	}

	public int getDivisionID(){
		return divisionID;
	}

	public int getRole(){
		return role;
	}

	public String getPostalAddress(){
		return postalAddress;
	}

	public String getPostalCode2(){
		return postalCode2;
	}

	public String getCreatedDate(){
		return createdDate;
	}

	public String getLastName(){
		return lastName;
	}

	public String getPostalCode1(){
		return postalCode1;
	}

	public Object getChainID(){
		return chainID;
	}

	public String getPostalCity(){
		return postalCity;
	}

	public int getVisitingCountry(){
		return visitingCountry;
	}

	public String getWebsite(){
		return website;
	}

	public String getPostalNumber(){
		return postalNumber;
	}

	public String getVisitingNumber(){
		return visitingNumber;
	}

	public Object getTempUser(){
		return tempUser;
	}

	public int getUserID(){
		return userID;
	}

	public int getMeetingsPerYear(){
		return meetingsPerYear;
	}

	public String getDivision(){
		return division;
	}

	public int getDepartmentID(){
		return departmentID;
	}

	public Object getPassword(){
		return password;
	}

	public int getMostlyMeetingType(){
		return mostlyMeetingType;
	}

	public String getUserName(){
		return userName;
	}

	public Object getVisitingCity(){
		return visitingCity;
	}

	public String getFirstName(){
		return firstName;
	}

	public int getCreatedBySiteId(){
		return createdBySiteId;
	}

	public int getVisitingState(){
		return visitingState;
	}

	public boolean isReceiveNewsletter(){
		return receiveNewsletter;
	}

	public String getTitle(){
		return title;
	}

	public String getVisitingAddress(){
		return visitingAddress;
	}

	public Object getInitialLogin(){
		return initialLogin;
	}

	public String getPhoneNumber(){
		return phoneNumber;
	}

	public Object getTempGUID(){
		return tempGUID;
	}

	public boolean isReceiveOffers(){
		return receiveOffers;
	}
}