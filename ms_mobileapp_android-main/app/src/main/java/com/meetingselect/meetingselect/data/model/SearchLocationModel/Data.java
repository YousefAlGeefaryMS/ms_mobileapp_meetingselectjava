
package com.meetingselect.meetingselect.data.model.SearchLocationModel;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Data {

    @SerializedName("id")
    private Object mId;
    @SerializedName("isVenue")
    private Boolean mIsVenue;
    @SerializedName("name")
    private String mName;
    @SerializedName("supplier")
    private String mSupplier;

    public Object getId() {
        return mId;
    }

    public void setId(Object id) {
        mId = id;
    }

    public Boolean getIsVenue() {
        return mIsVenue;
    }

    public void setIsVenue(Boolean isVenue) {
        mIsVenue = isVenue;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getSupplier() {
        return mSupplier;
    }

    public void setSupplier(String supplier) {
        mSupplier = supplier;
    }

}
