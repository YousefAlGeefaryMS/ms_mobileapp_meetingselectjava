package com.meetingselect.meetingselect.data.model.SearchLocationModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchLocationModel {

    @SerializedName("Id")
    @Expose
    public String Id;

    @SerializedName("DisplayName")
    @Expose
    public String DisplayName;

    @SerializedName("VenueNameURLRewrite")
    @Expose
    public String VenueNameURLWrite;

    @SerializedName("Latitude")
    @Expose
    public Double lat;

    @SerializedName("Longitude")
    @Expose
    public Double lon;


    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getDisplayName() {
        return DisplayName;
    }

    public void setDisplayName(String displayName) {
        DisplayName = displayName;
    }

    public String getVenueNameURLWrite() {
        return VenueNameURLWrite;
    }

    public void setVenueNameURLWrite(String venueNameURLWrite) {
        VenueNameURLWrite = venueNameURLWrite;
    }

    public String getLat() {
        String latitude = String.valueOf(lat);

        return latitude;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public String getLon() {

        String longitude = String.valueOf(lon);

        return longitude;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }
}
