package com.meetingselect.meetingselect.main.myrequests.respository;

import android.util.Log;

import com.meetingselect.meetingselect.data.api.JSONAPIHolder;
import com.meetingselect.meetingselect.data.model.SavedBookingsMS2.SavedBookingsMS2PostModel;
import com.meetingselect.meetingselect.data.model.SavedBookingsMS2.SavedBookingsMS2ResponseModel;

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

public class SavedBookingsMS2Repo {

    private static final String TAG = "SavedBookingsRepo";

    private final JSONAPIHolder mJSONAPIHolder;

    private final BehaviorSubject<SavedBookingsMS2ResponseModel> mSavedBookingsMS2ResponseBehaviourSubject = BehaviorSubject.create();

    public SavedBookingsMS2Repo() {

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

    public void getSavedBookingsMS2Repo(String token, String meetingName, int pageIndex, int pageSize) {

        SavedBookingsMS2PostModel savedBookingsMS2PostModel = new SavedBookingsMS2PostModel(meetingName, pageIndex, pageSize);

        Call<SavedBookingsMS2ResponseModel> call = mJSONAPIHolder.getSavedBookingsMS2(token, savedBookingsMS2PostModel);

        call.enqueue(new Callback<SavedBookingsMS2ResponseModel>() {
            @Override
            public void onResponse(Call<SavedBookingsMS2ResponseModel> call, Response<SavedBookingsMS2ResponseModel> response) {
                if (!response.isSuccessful()) {

                    Log.d(TAG, "onResponse: " + response.code());
                    return;
                }

                SavedBookingsMS2ResponseModel savedBookingsMS2ResponseModel = response.body();

                mSavedBookingsMS2ResponseBehaviourSubject.onNext(savedBookingsMS2ResponseModel);

            }

            @Override
            public void onFailure(Call<SavedBookingsMS2ResponseModel> call, Throwable t) {
                if (t instanceof IOException) {
                    Log.e(TAG, "onFailure: ", t);
                }

                Log.e(TAG, "onFailure: ", t);
            }
        });
    }

    public BehaviorSubject<SavedBookingsMS2ResponseModel> getmSavedBookingsMS2ResponseBehaviourSubject() {
        return mSavedBookingsMS2ResponseBehaviourSubject;
    }
}
