package com.meetingselect.meetingselect.data.model.RegisterModelMS4;

import com.google.gson.annotations.SerializedName;

public class RegisterPostModel{

	@SerializedName("userTypeId")
	private int userTypeId;

	@SerializedName("fullName")
	private String fullName;

	@SerializedName("companyName")
	private String companyName;

	@SerializedName("email")
	private String email;

	@SerializedName("password")
	private String password;

	@SerializedName("countryCode")
	private String countryCode;

	@SerializedName("phoneNumber")
	private String phoneNumber;

	@SerializedName("termsAndCondition")
	private boolean termsAndCondition;

	@SerializedName("newsletters")
	private boolean newsletters;

	public RegisterPostModel(int userTypeId, String fullName, String companyName, String email, String password, String countryCode, String phoneNumber, boolean termsAndCondition, boolean newsletters) {
		this.userTypeId = userTypeId;
		this.fullName = fullName;
		this.companyName = companyName;
		this.email = email;
		this.password = password;
		this.countryCode = countryCode;
		this.phoneNumber = phoneNumber;
		this.termsAndCondition = termsAndCondition;
		this.newsletters = newsletters;
	}

	public int getUserTypeId(){
		return userTypeId;
	}

	public String getPassword(){
		return password;
	}

	public boolean isNewsletters(){
		return newsletters;
	}

	public String getPhoneNumber(){
		return phoneNumber;
	}

	public String getCountryCode(){
		return countryCode;
	}

	public String getCompanyName(){
		return companyName;
	}

	public String getFullName(){
		return fullName;
	}

	public boolean isTermsAndCondition(){
		return termsAndCondition;
	}

	public String getEmail(){
		return email;
	}
}