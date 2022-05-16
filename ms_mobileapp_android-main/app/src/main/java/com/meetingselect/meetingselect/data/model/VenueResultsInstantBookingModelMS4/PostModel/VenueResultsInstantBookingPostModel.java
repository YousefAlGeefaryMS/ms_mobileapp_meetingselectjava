
package com.meetingselect.meetingselect.data.model.VenueResultsInstantBookingModelMS4.PostModel;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class VenueResultsInstantBookingPostModel {

    @SerializedName("widgetId")
    private int mWidgetId;
    @SerializedName("languageCode")
    private String mLanguageCode;
    @SerializedName("location")
    private List<String> mLocation;
    @SerializedName("attendees")
    private int mAttendees;
    @SerializedName("instantBookable")
    private Boolean mInstantBookable;
    @SerializedName("offset")
    private int mOffset;
    @SerializedName("limit")
    private int mLimit;
    @SerializedName("spaceType")
    private String mSpaceType;
    @SerializedName("when")
    private String mWhen;
    @SerializedName("duration")
    private Duration mDuration;
    @SerializedName("isFilterRequest")
    private Boolean mIsFilterRequest;

    public VenueResultsInstantBookingPostModel(int mWidgetId,
                                               String mLanguageCode,
                                               List<String> mLocation,
                                               int mAttendees,
                                               Boolean mInstantBookable,
                                               int mOffset,
                                               int mLimit,
                                               String mSpaceType,
                                               String mWhen,
                                               Duration mDuration,
                                               Boolean mIsFilterRequest) {
        this.mWidgetId = mWidgetId;
        this.mLanguageCode = mLanguageCode;
        this.mLocation = mLocation;
        this.mAttendees = mAttendees;
        this.mInstantBookable = mInstantBookable;
        this.mOffset = mOffset;
        this.mLimit = mLimit;
        this.mSpaceType = mSpaceType;
        this.mWhen = mWhen;
        this.mDuration = mDuration;
        this.mIsFilterRequest = mIsFilterRequest;
    }

    public int getAttendees() {
        return mAttendees;
    }

    public void setAttendees(int attendees) {
        mAttendees = attendees;
    }

    public Duration getDuration() {
        return mDuration;
    }

    public void setDuration(Duration duration) {
        mDuration = duration;
    }

    public Boolean getInstantBookable() {
        return mInstantBookable;
    }

    public void setInstantBookable(Boolean instantBookable) {
        mInstantBookable = instantBookable;
    }

    public Boolean getIsFilterRequest() {
        return mIsFilterRequest;
    }

    public void setIsFilterRequest(Boolean isFilterRequest) {
        mIsFilterRequest = isFilterRequest;
    }

    public String getLanguageCode() {
        return mLanguageCode;
    }

    public void setLanguageCode(String languageCode) {
        mLanguageCode = languageCode;
    }

    public int getLimit() {
        return mLimit;
    }

    public void setLimit(int limit) {
        mLimit = limit;
    }

    public List<String> getLocation() {
        return mLocation;
    }

    public void setLocation(List<String> location) {
        mLocation = location;
    }

    public int getOffset() {
        return mOffset;
    }

    public void setOffset(int offset) {
        mOffset = offset;
    }

    public String getSpaceType() {
        return mSpaceType;
    }

    public void setSpaceType(String spaceType) {
        mSpaceType = spaceType;
    }

    public String getWhen() {
        return mWhen;
    }

    public void setWhen(String when) {
        mWhen = when;
    }

    public int getWidgetId() {
        return mWidgetId;
    }

    public void setWidgetId(int widgetId) {
        mWidgetId = widgetId;
    }

}
