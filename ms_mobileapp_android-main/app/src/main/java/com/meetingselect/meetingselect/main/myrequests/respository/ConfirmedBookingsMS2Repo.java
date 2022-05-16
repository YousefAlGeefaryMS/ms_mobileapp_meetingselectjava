package com.meetingselect.meetingselect.main.myrequests.respository;

import android.util.Log;

import com.meetingselect.meetingselect.data.api.JSONAPIHolder;
import com.meetingselect.meetingselect.data.model.ConfirmedBookingsModelMS2.ConfirmedBookingsMS2ResponseModel;
import com.meetingselect.meetingselect.data.model.ConfirmedBookingsModelMS2.ConfirmedMS2PostModel;

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

public class ConfirmedBookingsMS2Repo {

    private static final String TAG = "ConfirmedBookingsMS2";

    private final JSONAPIHolder mJSONAPIHolder;

    private final BehaviorSubject<ConfirmedBookingsMS2ResponseModel> mConfirmedBookingsResponseModelBehaviourSubject = BehaviorSubject.create();

    public ConfirmedBookingsMS2Repo() {

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


    public void getConfirmedBookingsMS2(String token, String meetingName, int pageIndex, int pageSize) {

        ConfirmedMS2PostModel confirmedMS2PostModel = new ConfirmedMS2PostModel(meetingName, pageSize, pageIndex);

        Call<ConfirmedBookingsMS2ResponseModel> call = mJSONAPIHolder.getConfirmedBookingsMS2(token, confirmedMS2PostModel);

        call.enqueue(new Callback<ConfirmedBookingsMS2ResponseModel>() {
            @Override
            public void onResponse(Call<ConfirmedBookingsMS2ResponseModel> call, Response<ConfirmedBookingsMS2ResponseModel> response) {
                if (!response.isSuccessful()) {

                    Log.d(TAG, "onResponse: " + response.code());
                    return;
                }

                ConfirmedBookingsMS2ResponseModel confirmedBookingsMS2ResponseModel = response.body();

                mConfirmedBookingsResponseModelBehaviourSubject.onNext(confirmedBookingsMS2ResponseModel);

            }

            @Override
            public void onFailure(Call<ConfirmedBookingsMS2ResponseModel> call, Throwable t) {
                if (t instanceof IOException) {
                    Log.e(TAG, "onFailure: ", t);
                }
            }
        });
    }

    public BehaviorSubject<ConfirmedBookingsMS2ResponseModel> getmConfirmedBookingsResponseModelBehaviourSubject() {
        return mConfirmedBookingsResponseModelBehaviourSubject;
    }
}
