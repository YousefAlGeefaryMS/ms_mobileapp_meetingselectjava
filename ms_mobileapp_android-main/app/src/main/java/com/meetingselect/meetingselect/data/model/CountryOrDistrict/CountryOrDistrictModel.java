package com.meetingselect.meetingselect.data.model.CountryOrDistrict;

import java.util.ArrayList;

public class CountryOrDistrictModel extends ArrayList<CountryOrDistrictModel> {

    private int CountryID;

    private String CountryCode;

    private String CountryName;

    private int RegionID;

    private String CountryCodeISO2;

    public int getCountryID() {
        return CountryID;
    }

    public void setCountryID(int countryID) {
        CountryID = countryID;
    }

    public String getCountryCode() {
        return CountryCode;
    }

    public void setCountryCode(String countryCode) {
        CountryCode = countryCode;
    }

    public String getCountryName() {
        return CountryName;
    }

    public void setCountryName(String countryName) {
        CountryName = countryName;
    }

    public int getRegionID() {
        return RegionID;
    }

    public void setRegionID(int regionID) {
        RegionID = regionID;
    }

    public String getCountryCodeISO2() {
        return CountryCodeISO2;
    }

    public void setCountryCodeISO2(String countryCodeISO2) {
        CountryCodeISO2 = countryCodeISO2;
    }

    @Override
    public String toString() {
        return "CountryOrDistrictModel{" +
                "CountryID=" + CountryID +
                ", CountryCode='" + CountryCode + '\'' +
                ", CountryName='" + CountryName + '\'' +
                ", RegionID=" + RegionID +
                ", CountryCodeISO2='" + CountryCodeISO2 + '\'' +
                '}';
    }
}
