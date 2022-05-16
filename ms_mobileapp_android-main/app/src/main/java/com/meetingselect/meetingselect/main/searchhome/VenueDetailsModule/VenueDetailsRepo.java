package com.meetingselect.meetingselect.main.searchhome.VenueDetailsModule;

import android.util.Log;

import com.meetingselect.meetingselect.data.api.JSONAPIHolder;
import com.meetingselect.meetingselect.data.model.VenueDetailsModel.RoomsAvailability.VenueDetailsResponseRoomsAvailableModel;
import com.meetingselect.meetingselect.data.model.VenueDetailsModel.VenueDetailsResponseModel;

import java.io.IOException;

import io.reactivex.subjects.BehaviorSubject;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VenueDetailsRepo {

    private static final String TAG = "VenueDetailsRepo";

    private final JSONAPIHolder mJsonApiHolder;

    private final JSONAPIHolder mJsonApiHolderRoomAvailability;

    private final BehaviorSubject<VenueDetailsResponseModel> mVenueDetailsBehaviorSubject = BehaviorSubject.create();
    private final BehaviorSubject<VenueDetailsResponseRoomsAvailableModel> mVenueDetailsRoomsBehaviorSubject = BehaviorSubject.create();

    public VenueDetailsRepo() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("https://api-test.meetingselect.com:443/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mJsonApiHolder = retrofit.create(JSONAPIHolder.class);



        // Room Availability API
        Retrofit retrofitRoomAvailability = new Retrofit.Builder()
                .baseUrl("https://api-staging.cyberdigma.nl/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mJsonApiHolderRoomAvailability = retrofitRoomAvailability.create(JSONAPIHolder.class);

    }


    public void getVenueDetails(String VenueID) {
        Call<VenueDetailsResponseModel> call = mJsonApiHolder.getVenueDetails(String.valueOf(VenueID));

        call.enqueue(new Callback<VenueDetailsResponseModel>() {
            @Override
            public void onResponse(Call<VenueDetailsResponseModel> call, Response<VenueDetailsResponseModel> response) {
                if(!response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.code());
                    return;
                }

                VenueDetailsResponseModel venueDetailsResponseModel = response.body();

                Log.d(TAG, "onResponse: " + venueDetailsResponseModel.getVenueName());

                mVenueDetailsBehaviorSubject.onNext(venueDetailsResponseModel);
            }

            @Override
            public void onFailure(Call<VenueDetailsResponseModel> call, Throwable t) {

                if(t instanceof IOException) {
                    Log.e(TAG, "onFailure: ", t);
                }

                Log.d(TAG, "onFailure: " + t);
            }
        });
    }

    public void getRoomDetails(String startDate, String endDate, int startTime, int endTime, int locationID, int seats, String language) {

        Call<VenueDetailsResponseRoomsAvailableModel> call = mJsonApiHolderRoomAvailability.getVenueDetailsRoomAvailability(
                startDate,
                endDate,
                startTime,
                endTime,
                1,
                1009,
                locationID,
                seats,
                language);

        // ?startDate=2021-08-25&endDate=2021-08-25&startTime=540&endTime=1020&meetingtype=1
        // &country=0&channel=1009&location=322&seats=1&space=0&setting=0&isBackend=false&tender=0&widgetId=-100&voucher=0&language=en



        call.enqueue(new Callback<VenueDetailsResponseRoomsAvailableModel>() {
            @Override
            public void onResponse(Call<VenueDetailsResponseRoomsAvailableModel> call, Response<VenueDetailsResponseRoomsAvailableModel> response) {
                if(!response.isSuccessful()) {
                    Log.d(TAG, "onResponseRooms: " + response.code() + response.message());

                    try {
                        Log.d(TAG, "onResponseRooms: " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    return;
                }

                VenueDetailsResponseRoomsAvailableModel venueDetailsResponseRoomsAvailableModel = response.body();


//                Log.d(TAG, "onResponseSearchKey: " + venueDetailsResponseRoomsAvailableModel.getSearchKey());
//                Log.d(TAG, "onResponseName: " + venueDetailsResponseRoomsAvailableModel.getLocations().get(0).getName());

//                for(SpacesItem post : venueDetailsResponseRoomsAvailableModel.getLocations().get(0).getSpaces()) {
//
//                    Log.d(TAG, "onResponse: " + post.getId());
//
//                }

                try {
                    mVenueDetailsRoomsBehaviorSubject.onNext(venueDetailsResponseRoomsAvailableModel);

                } catch (IndexOutOfBoundsException e) {
                    Log.e(TAG, "onResponse: ", e);
                    mVenueDetailsBehaviorSubject.onNext(null);
                }



//                Log.d(TAG, "onResponseNameSize: " + venueDetailsResponseRoomsAvailableModel.getLocations().get(0).getSpaces().size());

            }

            @Override
            public void onFailure(Call<VenueDetailsResponseRoomsAvailableModel> call, Throwable t) {
                if(t instanceof IOException) {
                    Log.e(TAG, "onFailureRooms: ", t);
                }
                mVenueDetailsRoomsBehaviorSubject.onNext(null);

                Log.d(TAG, "onFailureRooms: " + t);
            }
        });


    }



    public BehaviorSubject<VenueDetailsResponseModel> getmVenueDetailsBehaviorSubject() {
        return mVenueDetailsBehaviorSubject;
    }

    public BehaviorSubject<VenueDetailsResponseRoomsAvailableModel> getmVenueDetailsRoomsBehaviorSubject() {
        return mVenueDetailsRoomsBehaviorSubject;
    }


}
