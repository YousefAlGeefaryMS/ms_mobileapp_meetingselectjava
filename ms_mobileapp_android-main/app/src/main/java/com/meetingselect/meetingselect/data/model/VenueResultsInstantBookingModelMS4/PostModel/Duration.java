
package com.meetingselect.meetingselect.data.model.VenueResultsInstantBookingModelMS4.PostModel;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Duration {

    @SerializedName("days")
    private int mDays;
    @SerializedName("hours")
    private int mHours;
    @SerializedName("minutes")
    private int mMinutes;

    public Duration(int mDays, int mHours, int mMinutes) {
        this.mDays = mDays;
        this.mHours = mHours;
        this.mMinutes = mMinutes;
    }

    public int getDays() {
        return mDays;
    }

    public void setDays(int days) {
        mDays = days;
    }

    public int getHours() {
        return mHours;
    }

    public void setHours(int hours) {
        mHours = hours;
    }

    public int getMinutes() {
        return mMinutes;
    }

    public void setMinutes(int minutes) {
        mMinutes = minutes;
    }

}
