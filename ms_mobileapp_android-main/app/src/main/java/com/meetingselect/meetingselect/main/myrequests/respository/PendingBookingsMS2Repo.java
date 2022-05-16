package com.meetingselect.meetingselect.main.myrequests.respository;

import android.util.Log;

import com.meetingselect.meetingselect.data.api.JSONAPIHolder;
import com.meetingselect.meetingselect.data.model.PendingBookingsModelMS2.PendingBookingsMS2ResponseModel;
import com.meetingselect.meetingselect.data.model.PendingBookingsModelMS2.PendingMS2PostModel;

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

public class PendingBookingsMS2Repo {

    private static final String TAG = "PendingBookingsMS2";

    private final JSONAPIHolder mJSONAPIHolder;

    private final BehaviorSubject<PendingBookingsMS2ResponseModel> mPendingBookingsResponseModelMS2BehaviourSubject = BehaviorSubject.create();

    public PendingBookingsMS2Repo() {

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


    public void getPendingBookingsMS2(String token, String meetingName, int pageIndex, int pageSize) {

        PendingMS2PostModel pendingMS2PostModel = new PendingMS2PostModel(meetingName, pageIndex, pageSize);

        Call<PendingBookingsMS2ResponseModel> call = mJSONAPIHolder.getPendingBookingsMS2(token, pendingMS2PostModel);

        call.enqueue(new Callback<PendingBookingsMS2ResponseModel>() {
            @Override
            public void onResponse(Call<PendingBookingsMS2ResponseModel> call, Response<PendingBookingsMS2ResponseModel> response) {
                if (!response.isSuccessful()) {

                    Log.d(TAG, "onResponse: " + response.code());
                    return;
                }

                PendingBookingsMS2ResponseModel pendingBookingsMS2ResponseModel = response.body();

                mPendingBookingsResponseModelMS2BehaviourSubject.onNext(pendingBookingsMS2ResponseModel);

            }

            @Override
            public void onFailure(Call<PendingBookingsMS2ResponseModel> call, Throwable t) {
                if (t instanceof IOException) {
                    Log.e(TAG, "onFailure: ", t);
                }
            }
        });
    }

    public BehaviorSubject<PendingBookingsMS2ResponseModel> getmPendingBookingsResponseModelMS2BehaviourSubject() {
        return mPendingBookingsResponseModelMS2BehaviourSubject;
    }
}
