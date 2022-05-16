package com.meetingselect.meetingselect.main.searchhome.VenueResultsModule.repository;

import android.util.Log;

import com.meetingselect.meetingselect.data.api.JSONAPIHolder;
import com.meetingselect.meetingselect.data.model.VenueResultsModel.RFPModel.Filters;
import com.meetingselect.meetingselect.data.model.VenueResultsModel.RFPModel.VenueResultsPostModel;
import com.meetingselect.meetingselect.data.model.VenueResultsModel.RFPModel.VenueResultsResponseModel;

import java.io.IOException;
import java.util.List;

import io.reactivex.subjects.BehaviorSubject;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VenueResultsRepo {

    private static final String TAG = "VenueResultsRepo";

    private JSONAPIHolder mJsonAPIHolder;

    private final BehaviorSubject<VenueResultsResponseModel> mVenueResultsBehaviorSubject = BehaviorSubject.create();


    public VenueResultsRepo() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("https://api-test.meetingselect.com:443/venuesearch/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mJsonAPIHolder = retrofit.create(JSONAPIHolder.class);

    }

    public void getVenueResults(String latitude, String longitude, String isInstantBooking, String language) {


        Filters filters = new Filters(latitude, longitude, 0, 8, isInstantBooking);
        VenueResultsPostModel venueResultsPostModel = new VenueResultsPostModel(1, 10, language, filters);

        Log.d(TAG, "sent: " + venueResultsPostModel.toString());

        Call<VenueResultsResponseModel> call = mJsonAPIHolder.getVenueResultsPOST(venueResultsPostModel);

        call.enqueue(new Callback<VenueResultsResponseModel>() {
            @Override
            public void onResponse(Call<VenueResultsResponseModel> call, Response<VenueResultsResponseModel> response) {
                if (!response.isSuccessful()) {
                    Log.d(TAG, "response code " + response.code());
                    return;
                }

                VenueResultsResponseModel venueResultsResponseBody = response.body();


                mVenueResultsBehaviorSubject.onNext(venueResultsResponseBody);
                mVenueResultsBehaviorSubject.onComplete();

                Log.d(TAG, "---");

            }

            @Override
            public void onFailure(Call<VenueResultsResponseModel> call, Throwable t) {
                if (t instanceof IOException) {
                    Log.e(TAG, "onFailure: " + t);

                    mVenueResultsBehaviorSubject.onNext(null);

                }


            }
        });

    }






    public BehaviorSubject<VenueResultsResponseModel> getmVenueResultsBehaviorSubject() {
        return mVenueResultsBehaviorSubject;
    }


}
