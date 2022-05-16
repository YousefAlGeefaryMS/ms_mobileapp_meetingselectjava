package com.meetingselect.meetingselect.data.model.LoginModelMS4;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("accessToken")
	private String accessToken;

	@SerializedName("expiresIn")
	private int expiresIn;

	@SerializedName("refreshExpiresIn")
	private int refreshExpiresIn;

	@SerializedName("refreshToken")
	private String refreshToken;

	@SerializedName("tokenType")
	private String tokenType;

	@SerializedName("notBeforePolicy")
	private int notBeforePolicy;

	@SerializedName("sessionState")
	private String sessionState;

	@SerializedName("scope")
	private String scope;


	public int getExpiresIn(){
		return expiresIn;
	}

	public int getNotBeforePolicy(){
		return notBeforePolicy;
	}

	public String getSessionState(){
		return sessionState;
	}

	public String getScope(){
		return scope;
	}

	public String getAccessToken(){
		return accessToken;
	}

	public String getTokenType(){
		return tokenType;
	}

	public int getRefreshExpiresIn(){
		return refreshExpiresIn;
	}

	public String getRefreshToken(){
		return refreshToken;
	}
}