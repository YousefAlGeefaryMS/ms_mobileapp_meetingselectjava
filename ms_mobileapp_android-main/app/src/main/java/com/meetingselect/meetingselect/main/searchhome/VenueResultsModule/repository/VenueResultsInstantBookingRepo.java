package com.meetingselect.meetingselect.main.searchhome.VenueResultsModule.repository;

import android.util.Log;


import com.meetingselect.meetingselect.data.api.JSONAPIHolder;
import com.meetingselect.meetingselect.data.model.VenueResultsInstantBookingModel.VenueResultsInstantBookingResponseModel;


import java.io.IOException;

import io.reactivex.subjects.BehaviorSubject;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VenueResultsInstantBookingRepo {

    private static final String TAG = "VenueResultsIns";


    private JSONAPIHolder mJsonAPIHolder;

    private final BehaviorSubject<VenueResultsInstantBookingResponseModel> mVenueResultsInstantBookingBehaviorSubject = BehaviorSubject.create();


    public VenueResultsInstantBookingRepo() {

//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        OkHttpClient client = new OkHttpClient.Builder()
//                .addInterceptor(interceptor).build();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("https://api.d1.meetingselect.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mJsonAPIHolder = retrofit.create(JSONAPIHolder.class);


    }

    public void getVenueResultsInstantBooking (String cityname) {

        Log.d(TAG, "getVenueResultsInstantBooking: ");

        Call<VenueResultsInstantBookingResponseModel> call = mJsonAPIHolder.getVenueResultsInstantBookingGET(cityname);

        call.enqueue(new Callback<VenueResultsInstantBookingResponseModel>() {
            @Override
            public void onResponse(Call<VenueResultsInstantBookingResponseModel> call, Response<VenueResultsInstantBookingResponseModel> response) {

                VenueResultsInstantBookingResponseModel responseModel = response.body();


                mVenueResultsInstantBookingBehaviorSubject.onNext(responseModel);
            }

            @Override
            public void onFailure(Call<VenueResultsInstantBookingResponseModel> call, Throwable t) {
                if(t instanceof IOException) {
                    Log.e(TAG, "onFailure: ", t);
                }

                mVenueResultsInstantBookingBehaviorSubject.onNext(null);

            }
        });

    }

    public BehaviorSubject<VenueResultsInstantBookingResponseModel> getmVenueResultsInstantBookingBehaviorSubject() {
        return mVenueResultsInstantBookingBehaviorSubject;
    }
}
