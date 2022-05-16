package com.meetingselect.meetingselect.main.searchhome.SearchVenueModule.repository;

import android.util.Log;

import com.meetingselect.meetingselect.data.api.JSONAPIHolder;
import com.meetingselect.meetingselect.data.model.SearchLocationModel.LocationSearchResponseModel;
import com.meetingselect.meetingselect.data.model.SearchLocationModel.LocationSearchPostModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.subjects.BehaviorSubject;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LocationSearchRepo {

    private static final String TAG = "LocationSearchRepo";

    private final JSONAPIHolder mJsonApiHolder;

    private final BehaviorSubject<LocationSearchResponseModel> mLocationSearchResponseModelBehaviourSubject = BehaviorSubject.create();

    public LocationSearchRepo() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://test-ms4-apigateway.azurewebsites.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        mJsonApiHolder = retrofit.create(JSONAPIHolder.class);

    }

    public void getLocationSearchResultsRepository(String location) {

        List<String> locationList = new ArrayList<>();
        locationList.add(location);

        Log.d(TAG, "getLocationSearchResultsRepository: " + location);

        LocationSearchPostModel locationSearchPostModel = new LocationSearchPostModel(locationList, 15, 1);

        Call<LocationSearchResponseModel> call = mJsonApiHolder.getLocationResults(locationSearchPostModel);

        call.enqueue(new Callback<LocationSearchResponseModel>() {
            @Override
            public void onResponse(Call<LocationSearchResponseModel> call, Response<LocationSearchResponseModel> response) {
                if(!response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.code());
                    return;
                }

                LocationSearchResponseModel locationSearchResponseModel = response.body();

                mLocationSearchResponseModelBehaviourSubject.onNext(locationSearchResponseModel);
            //                Log.d(TAG, "onResponse: " + locationSearchResponseModel.getData().size());

                try {
                    Log.d(TAG, "onResponse: " + locationSearchResponseModel.getData().get(0).getName());

                } catch (NullPointerException e) {

                    Log.e(TAG, "onResponse: ", e);
                    Log.d(TAG, "onResponse: No Results");
                }
            }

            @Override
            public void onFailure(Call<LocationSearchResponseModel> call, Throwable t) {
                if(t instanceof IOException) {
                    Log.e(TAG, "onFailure: ", t);
                }

                mLocationSearchResponseModelBehaviourSubject.onNext(null);

            }
        });
    }

    public BehaviorSubject<LocationSearchResponseModel> getmLocationSearchResponseModelBehaviourSubject() {
        return mLocationSearchResponseModelBehaviourSubject;
    }
}
