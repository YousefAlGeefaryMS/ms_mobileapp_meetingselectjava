package com.meetingselect.meetingselect.main.searchhome.VenueInstantBookingProcess;

import android.util.Log;

import com.meetingselect.meetingselect.data.api.JSONAPIHolder;
import com.meetingselect.meetingselect.data.model.VenueAvailabilitySelectModel.VenueAvailabilityResponseModel.VenueAvailabilityResponseModel;
import com.meetingselect.meetingselect.data.model.VenueAvailabilitySelectModel.VenueAvailabilitySelectRequestHeadersAPI;

import java.io.IOException;

import io.reactivex.subjects.BehaviorSubject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VenueInstantBookingProcessRepo {

    private static final String TAG = "AvailabilitySelectRepo";

    private JSONAPIHolder jsonapiHolder;

    private final BehaviorSubject<VenueAvailabilityResponseModel> mVenueResultsInstantBookingResponseModel = BehaviorSubject.create();

    public VenueInstantBookingProcessRepo() {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api-staging.cyberdigma.nl/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonapiHolder = retrofit.create(JSONAPIHolder.class);

    }

    public void getAvailabilitySelectRepo(int LocationID, int SearchID, int SpaceID, int CurrencyID, int CRC, double PricePerSeat, String hash) {

        VenueAvailabilitySelectRequestHeadersAPI venueAvailabilitySelectRequestHeadersAPI = new VenueAvailabilitySelectRequestHeadersAPI(LocationID, SearchID, SpaceID, "RegularReservation", CurrencyID, CRC, PricePerSeat, hash);

        Call<VenueAvailabilityResponseModel> call = jsonapiHolder.getVenueAvailabilitySelectAPI(venueAvailabilitySelectRequestHeadersAPI);

        call.enqueue(new Callback<VenueAvailabilityResponseModel>() {
            @Override
            public void onResponse(Call<VenueAvailabilityResponseModel> call, Response<VenueAvailabilityResponseModel> response) {
                if(!response.isSuccessful()){
                    Log.d(TAG, "onResponse: " + response.code());
                    return;
                }

                VenueAvailabilityResponseModel venueAvailabilityResponseModel = response.body();

                mVenueResultsInstantBookingResponseModel.onNext(venueAvailabilityResponseModel);


                Log.d(TAG, "onResponse: " + venueAvailabilityResponseModel.getCartKey());
            }

            @Override
            public void onFailure(Call<VenueAvailabilityResponseModel> call, Throwable t) {
                if (t instanceof IOException) {
                    Log.e(TAG, "onFailure: " + t);

//                    mVenueResultsInstantBookingResponseModel.onNext(null);

                }
            }
        });


    }

    public BehaviorSubject<VenueAvailabilityResponseModel> getmVenueResultsInstantBookingResponseModel() {
        return mVenueResultsInstantBookingResponseModel;
    }
}
