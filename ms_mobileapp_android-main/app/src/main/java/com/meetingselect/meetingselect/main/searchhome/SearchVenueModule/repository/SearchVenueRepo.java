package com.meetingselect.meetingselect.main.searchhome.SearchVenueModule.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.meetingselect.meetingselect.data.api.JSONAPIHolder;
import com.meetingselect.meetingselect.data.model.SearchLocationModel.SearchLocationModel;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import io.reactivex.subjects.BehaviorSubject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchVenueRepo {


    private static final String TAG = "SearchVenueRepo";

    private final JSONAPIHolder mJsonApiHolder;

    private final MutableLiveData<List<SearchLocationModel>> mSearchVenueResponseLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<SearchLocationModel>> mLatLon = new MutableLiveData<>();


    public SearchVenueRepo() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api-test.meetingselect.com:443/venuesearch/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mJsonApiHolder = retrofit.create(JSONAPIHolder.class);
    }

    public void getLocationResults(String location) {

        Log.d(TAG, "getLocationResults: " + location);


        Call<List<SearchLocationModel>> call = mJsonApiHolder.getLocationList(String.valueOf(location));

       call.enqueue(new Callback<List<SearchLocationModel>>() {
           @Override
           public void onResponse(Call<List<SearchLocationModel>> call, Response<List<SearchLocationModel>> response) {

               if (!response.isSuccessful()) {
                   Log.d(TAG, "ResponseSearchVenue: " + response.code());
                   return;
               }

               List<SearchLocationModel> searchLocationModelList = response.body();

//               Log.d(TAG, "onResponseBack: " + searchLocationModelList.size());
//
//               Log.d(TAG, "onResponseBack: " + searchLocationModelList.get(0).getId());

//                for (SearchLocationModel post : searchLocationModelList) {
//
//                    Log.d(TAG, "ID: " + post.getId());
//                    Log.d(TAG, "DisplayName: " + post.getDisplayName());
//                    Log.d(TAG, "VenueNameURLRewrite: " + post.getVenueNameURLWrite());
//                    Log.d(TAG, "Latitude: " + post.getLat());
//                    Log.d(TAG, "Longitude: " + post.getLon());
//                    Log.d(TAG, "  --------------------------------- ");
//
//
//                }


               mSearchVenueResponseLiveData.postValue(searchLocationModelList);
           }

           @Override
           public void onFailure(Call<List<SearchLocationModel>> call, Throwable t) {

               if(t instanceof IOException) {
                   Log.e(TAG, "onFailure: ", t);
               }

               mSearchVenueResponseLiveData.postValue(Collections.emptyList());
               Log.d(TAG, "onFailure: " + t.getMessage());

           }
       });


    }


    public MutableLiveData<List<SearchLocationModel>> getLocationResultsLiveData() {
        Log.d(TAG, "getLocationResultsLiveData: " + mSearchVenueResponseLiveData.getValue());
        return mSearchVenueResponseLiveData;
    }
}
