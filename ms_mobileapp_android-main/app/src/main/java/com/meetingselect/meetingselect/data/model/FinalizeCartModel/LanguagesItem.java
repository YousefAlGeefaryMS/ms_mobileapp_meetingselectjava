package com.meetingselect.meetingselect.data.model.FinalizeCartModel;

import com.google.gson.annotations.SerializedName;

public class LanguagesItem{

	@SerializedName("Changes")
	private String changes;

	@SerializedName("Language")
	private String language;

	@SerializedName("Terms")
	private String terms;

	@SerializedName("Id")
	private int id;

	@SerializedName("TermsId")
	private int termsId;

	public String getChanges(){
		return changes;
	}

	public String getLanguage(){
		return language;
	}

	public String getTerms(){
		return terms;
	}

	public int getId(){
		return id;
	}

	public int getTermsId(){
		return termsId;
	}
}