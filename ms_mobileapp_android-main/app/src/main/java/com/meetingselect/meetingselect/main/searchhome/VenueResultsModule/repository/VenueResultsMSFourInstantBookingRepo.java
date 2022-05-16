package com.meetingselect.meetingselect.main.searchhome.VenueResultsModule.repository;

import android.util.Log;

import com.meetingselect.meetingselect.data.api.JSONAPIHolder;
import com.meetingselect.meetingselect.data.model.VenueResultsInstantBookingModelMS4.ResponseModel.VenueResultInstantBookingResponseModel;
import com.meetingselect.meetingselect.data.model.VenueResultsInstantBookingModelMS4.PostModel.Duration;
import com.meetingselect.meetingselect.data.model.VenueResultsInstantBookingModelMS4.PostModel.VenueResultsInstantBookingPostModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.subjects.BehaviorSubject;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VenueResultsMSFourInstantBookingRepo {

    private static final String TAG = "VenueResultsIBMS4";

    private final JSONAPIHolder mJsonAPIHolder;

    private final BehaviorSubject<VenueResultInstantBookingResponseModel> mVenueResultsInstantBookingResponseModelBehaviourSubjectRepository = BehaviorSubject.create();


    public VenueResultsMSFourInstantBookingRepo() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("https://test-ms4-apigateway.azurewebsites.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mJsonAPIHolder = retrofit.create(JSONAPIHolder.class);

    }

    public void getVenueResultsInstantBookingMS4(String locationName, int attendeeNumber, String dateTime, int days) {
        List<String> locationNames = new ArrayList<>();
        locationNames.add(locationName);
        Duration duration = new Duration(days, 0, 0);
        VenueResultsInstantBookingPostModel venueResultsInstantBookingPostModel = new VenueResultsInstantBookingPostModel(
                2,
                "en",
                locationNames,
                attendeeNumber,
                true,
                0,
                15,
                "Meeting",
                dateTime,
                duration,
                false
                );

        Call<VenueResultInstantBookingResponseModel> call = mJsonAPIHolder.getVenueResultsInstantBookingMS4(venueResultsInstantBookingPostModel);

        call.enqueue(new Callback<VenueResultInstantBookingResponseModel>() {
            @Override
            public void onResponse(Call<VenueResultInstantBookingResponseModel> call, Response<VenueResultInstantBookingResponseModel> response) {
                if(!response.isSuccessful()) {

                    Log.d(TAG, "onResponse: " + response.code());
                    return;
                }

                VenueResultInstantBookingResponseModel responseModel = response.body();

                mVenueResultsInstantBookingResponseModelBehaviourSubjectRepository.onNext(responseModel);

//                Log.d(TAG, "onResponse: " + responseModel.getData().getSingleCityVenues().getItems().get(0).getDescription());
            }

            @Override
            public void onFailure(Call<VenueResultInstantBookingResponseModel> call, Throwable t) {
                if(t instanceof IOException) {
                    Log.e(TAG, "onFailure: ", t);
                }

                Log.e(TAG, "onFailure: ", t);

            }
        });

    }


    public BehaviorSubject<VenueResultInstantBookingResponseModel> getmVenueResultsInstantBookingResponseModelBehaviourSubjectRepository() {
        return mVenueResultsInstantBookingResponseModelBehaviourSubjectRepository;
    }
}
