package com.meetingselect.meetingselect.main.myrequests.respository;

import android.util.Log;

import com.meetingselect.meetingselect.data.api.JSONAPIHolder;
import com.meetingselect.meetingselect.data.model.AcceptConfirmMS2Model.AcceptConfirmResponseModel;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.subjects.BehaviorSubject;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AcceptConfirmBookingsMS2Repo {

    private static final String TAG = "AcceptConfirmBookings";

    private final JSONAPIHolder mJSONAPIHolder;

    private final BehaviorSubject<AcceptConfirmResponseModel> mAcceptConfirmBehaviourSubject = BehaviorSubject.create();

    public AcceptConfirmBookingsMS2Repo() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("https://api-test.meetingselect.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mJSONAPIHolder = retrofit.create(JSONAPIHolder.class);
    }

    public void getConfirmedBookings(String token, String proposalID) {

        Call<AcceptConfirmResponseModel> call = mJSONAPIHolder.getAcceptConfirmMS2(token, proposalID);

        call.enqueue(new Callback<AcceptConfirmResponseModel>() {
            @Override
            public void onResponse(Call<AcceptConfirmResponseModel> call, Response<AcceptConfirmResponseModel> response) {
                if(!response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.code());
                    return;
                }

                AcceptConfirmResponseModel acceptConfirmResponseModel = response.body();

                mAcceptConfirmBehaviourSubject.onNext(acceptConfirmResponseModel);


            }

            @Override
            public void onFailure(Call<AcceptConfirmResponseModel> call, Throwable t) {
                if (t instanceof IOException) {
                    Log.e(TAG, "onFailure: ", t);
                }

                Log.e(TAG, "onFailure: ", t);
            }
        });
    }

    public BehaviorSubject<AcceptConfirmResponseModel> getmAcceptConfirmBehaviourSubject() {
        return mAcceptConfirmBehaviourSubject;
    }
}
