package com.meetingselect.meetingselect.data.api;

import com.meetingselect.meetingselect.data.model.AcceptConfirmMS2Model.AcceptConfirmResponseModel;
import com.meetingselect.meetingselect.data.model.AccountInformation.AccountInformationResponseModel;
import com.meetingselect.meetingselect.data.model.AccountInformationMS2.AccountInformationMS2ResponseModel;
import com.meetingselect.meetingselect.data.model.ConfirmedBookingsModelMS2.ConfirmedBookingsMS2ResponseModel;
import com.meetingselect.meetingselect.data.model.ConfirmedBookingsModelMS2.ConfirmedMS2PostModel;
import com.meetingselect.meetingselect.data.model.FinalizeCartModel.FinalizeCartResponseModel;
import com.meetingselect.meetingselect.data.model.InstantBookingTwoPointZeroModel.InstantBookingTwoPointZeroRequestModel;
import com.meetingselect.meetingselect.data.model.LoginModelMS2.LoginPostModelMS2;
import com.meetingselect.meetingselect.data.model.LoginModelMS2.LoginResponseModelMS2;
import com.meetingselect.meetingselect.data.model.LoginModelMS4.LoginPostModel;
import com.meetingselect.meetingselect.data.model.LoginModelMS4.LoginResponseModel;
import com.meetingselect.meetingselect.data.model.PendingBookingsModelMS2.PendingBookingsMS2ResponseModel;
import com.meetingselect.meetingselect.data.model.PendingBookingsModelMS2.PendingMS2PostModel;
import com.meetingselect.meetingselect.data.model.RegisterModelMS4.RegisterPostModel;
import com.meetingselect.meetingselect.data.model.RegisterModelMS4.RegisterResponseModel;
import com.meetingselect.meetingselect.data.model.SavedBookingsMS2.SavedBookingsMS2PostModel;
import com.meetingselect.meetingselect.data.model.SavedBookingsMS2.SavedBookingsMS2ResponseModel;
import com.meetingselect.meetingselect.data.model.SearchLocationModel.LocationSearchPostModel;
import com.meetingselect.meetingselect.data.model.SearchLocationModel.LocationSearchResponseModel;
import com.meetingselect.meetingselect.data.model.SearchLocationModel.SearchLocationModel;
import com.meetingselect.meetingselect.data.model.VenueAvailabilitySelectModel.VenueAvailabilityResponseModel.VenueAvailabilityResponseModel;
import com.meetingselect.meetingselect.data.model.VenueAvailabilitySelectModel.VenueAvailabilitySelectRequestHeadersAPI;
import com.meetingselect.meetingselect.data.model.VenueDetailsModel.RoomsAvailability.VenueDetailsResponseRoomsAvailableModel;
import com.meetingselect.meetingselect.data.model.VenueDetailsModel.VenueDetailsResponseModel;
import com.meetingselect.meetingselect.data.model.VenueResultsInstantBookingModel.VenueResultsInstantBookingResponseModel;
import com.meetingselect.meetingselect.data.model.VenueResultsInstantBookingModelMS4.PostModel.VenueResultsInstantBookingPostModel;
import com.meetingselect.meetingselect.data.model.VenueResultsInstantBookingModelMS4.ResponseModel.VenueResultInstantBookingResponseModel;
import com.meetingselect.meetingselect.data.model.VenueResultsModel.RFPModel.VenueResultsPostModel;
import com.meetingselect.meetingselect.data.model.VenueResultsModel.RFPModel.VenueResultsResponseModel;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JSONAPIHolder {

    // Search Location MS2

    @Headers("authorization: Bearer sb5zcvm2w25m0rxqytl8ul4ayjin4a77")
    @GET("api/venue/v2/Search")
    Call<List<SearchLocationModel>> getLocationList(@Query("criteria.keyword") String locationName);

    // New Search Location MS4
    @POST("venues/lookup")
    Call<LocationSearchResponseModel> getLocationResults(@Body LocationSearchPostModel post);

    // Getting VenueResults RFP
    @Headers("authorization: Bearer sb5zcvm2w25m0rxqytl8ul4ayjin4a77")
    @POST("api/venue/v2/Search")
    Call<VenueResultsResponseModel> getVenueResultsPOST(@Body VenueResultsPostModel post);

    // Getting VenueResults InstantBooking
    @GET("venues")
    Call<VenueResultsInstantBookingResponseModel> getVenueResultsInstantBookingGET(@Query("Location") String cityname);

    @POST("venues/searchvenues")
    Call<VenueResultInstantBookingResponseModel> getVenueResultsInstantBookingMS4(@Body VenueResultsInstantBookingPostModel post);

    // Get Venue Details
    @Headers("authorization: Bearer sb5zcvm2w25m0rxqytl8ul4ayjin4a77")
    @GET("api/venue/venueinformation")
    Call<VenueDetailsResponseModel> getVenueDetails(@Query("venueId") String venueID);

    // Get InstantBooking Rooms
    @Headers({"apiToken: 24249711", "profileToken: G47ae7wjnMENHpQdGLmn5c0XpvqB3t"})
    @GET("api/v2/availability")
    Call<VenueDetailsResponseRoomsAvailableModel> getVenueDetailsRoomAvailability(
            @Query("startDate") String startDate,
            @Query("endDate") String endDate,
            @Query("startTime") int startTime,
            @Query("endTime") int endTime,
            @Query("meetingType") int meetingType,
            @Query("channel") int channel,
            @Query("location") int location,
            @Query("seats") int seats,
            @Query("language") String language);

    // ?startDate=2021-08-25&endDate=2021-08-25&startTime=540&endTime=1020&meetingtype=1
    // &country=0&channel=1009&location=322&seats=1&space=0&setting=0&isBackend=false&tender=0&widgetId=-100&voucher=0&language=en

    // Availability Select API
    @Headers({"apiToken: 24249711", "profileToken: G47ae7wjnMENHpQdGLmn5c0XpvqB3t"})
    @POST("api/v1/availability/select")
    Call<VenueAvailabilityResponseModel> getVenueAvailabilitySelectAPI(@Body VenueAvailabilitySelectRequestHeadersAPI post);

    @Headers({"apiToken: 24249711", "profileToken: G47ae7wjnMENHpQdGLmn5c0XpvqB3t"})
    @POST("api/v1/cart/{CartKey}/finalize")
    Call<FinalizeCartResponseModel> getFinalizeCartAPI(@Path("CartKey") String cartKey);

    // Send booking to 2.0
    @Headers("authorization: Bearer sb5zcvm2w25m0rxqytl8ul4ayjin4a77")
    @POST("api/bookings/instant-bookings")
    Call<ResponseBody> sendBookingToTwoPointZero(@Body InstantBookingTwoPointZeroRequestModel body);


    // Login / Register Modules for MS4

    @POST("user/register")
    Call<RegisterResponseModel> getRegisterUser(@Body RegisterPostModel post);

    @POST("user/login")
    Call<LoginResponseModel> getLoginUser(@Body LoginPostModel post);

    @GET("user/getbyemail")
    Call<AccountInformationResponseModel> getMSAccountInformation(@Query("emailId") String emailaddress);


    // Login Module for MS2

    @POST("api/User/Login")
    Call<LoginResponseModelMS2> getLoginMS2User(@Body LoginPostModelMS2 body);

    // Get User Info Module from MS2

    @GET("api/User/UserInfo")
    Call<AccountInformationMS2ResponseModel> getAccountInformationMS2(@Header("token") String token);


    // Get Saved Proposal MS2
    @POST("api/User/SavedRequests")
    Call<SavedBookingsMS2ResponseModel> getSavedBookingsMS2(@Header("token") String token, @Body SavedBookingsMS2PostModel body);

    // Get Pending Proposals MS2
    @POST("api/User/PendingProposals")
    Call<PendingBookingsMS2ResponseModel> getPendingBookingsMS2(@Header("token") String token, @Body PendingMS2PostModel body);

    // Get Confirmed Proposals MS2
    @POST("api/User/ComfirmedBookings")
    Call<ConfirmedBookingsMS2ResponseModel> getConfirmedBookingsMS2(@Header("token") String token, @Body ConfirmedMS2PostModel body);

    // Get AcceptConfirm details for Confirmed Bookings
    @GET("api/proposals/{ProposalID}/AcceptConfirm")
    Call<AcceptConfirmResponseModel> getAcceptConfirmMS2(@Header("token") String token, @Path("ProposalID") String proposalID);
}
