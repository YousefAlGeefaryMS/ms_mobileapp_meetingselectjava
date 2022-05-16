package com.meetingselect.meetingselect.data.model.VenueResultsModel.RFPModel;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

public class VenueResultsPostModel {

    @SerializedName("PageIndex")
    @Expose
    private Integer pageIndex;
    @SerializedName("PageSize")
    @Expose
    private Integer pageSize;
    @SerializedName("RfpID")
    @Expose
    private Integer rfpID;
    @SerializedName("Language")
    @Expose
    private String language;
    @SerializedName("SortField")
    @Expose
    private String sortField;
    @SerializedName("SortDirection")
    @Expose
    private String sortDirection;
    @SerializedName("SelectedVenueID")
    @Expose
    private Integer selectedVenueID;
    @SerializedName("SearchIdentification")
    @Expose
    private String searchIdentification;
    @SerializedName("Filters")
    @Expose
    private Filters filters;
    @SerializedName("PartnerCompanyId")
    @Expose
    private Integer partnerCompanyId;
    @SerializedName("CdnInClientSide")
    @Expose
    private Integer cdnInClientSide;


    public VenueResultsPostModel(int pageIndex, int pageSize, String language, Filters filters) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.language = language;
        this.filters = filters;
    }

    @NonNull
    @NotNull
    @Override
    public String toString() {
        return pageIndex + " " + pageSize + " " + language + " " + filters.getLatitude() + " " + filters.getLongitude();
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getRfpID() {
        return rfpID;
    }

    public void setRfpID(Integer rfpID) {
        this.rfpID = rfpID;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public String getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(String sortDirection) {
        this.sortDirection = sortDirection;
    }

    public Integer getSelectedVenueID() {
        return selectedVenueID;
    }

    public void setSelectedVenueID(Integer selectedVenueID) {
        this.selectedVenueID = selectedVenueID;
    }

    public String getSearchIdentification() {
        return searchIdentification;
    }

    public void setSearchIdentification(String searchIdentification) {
        this.searchIdentification = searchIdentification;
    }

    public Filters getFilters() {
        return filters;
    }

    public void setFilters(Filters filters) {
        this.filters = filters;
    }

    public Integer getPartnerCompanyId() {
        return partnerCompanyId;
    }

    public void setPartnerCompanyId(Integer partnerCompanyId) {
        this.partnerCompanyId = partnerCompanyId;
    }

    public Integer getCdnInClientSide() {
        return cdnInClientSide;
    }

    public void setCdnInClientSide(Integer cdnInClientSide) {
        this.cdnInClientSide = cdnInClientSide;
    }




}
