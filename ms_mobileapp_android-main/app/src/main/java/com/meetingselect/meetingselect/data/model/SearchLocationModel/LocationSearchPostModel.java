
package com.meetingselect.meetingselect.data.model.SearchLocationModel;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class LocationSearchPostModel {

    @SerializedName("keyword")
    private List<String> mKeyword;
    @SerializedName("limit")
    private int mLimit;
    @SerializedName("widgetId")
    private int mWidgetId;


    public LocationSearchPostModel(List<String> mKeyword, int mLimit, int mWidgetId) {
        this.mKeyword = mKeyword;
        this.mLimit = mLimit;
        this.mWidgetId = mWidgetId;
    }

    public List<String> getKeyword() {
        return mKeyword;
    }

    public void setKeyword(List<String> keyword) {
        mKeyword = keyword;
    }

    public int getLimit() {
        return mLimit;
    }

    public void setLimit(int limit) {
        mLimit = limit;
    }

    public int getWidgetId() {
        return mWidgetId;
    }

    public void setWidgetId(int widgetId) {
        mWidgetId = widgetId;
    }

}
