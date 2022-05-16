package com.meetingselect.meetingselect.main.searchhome.AccountInformationRepo;


import android.util.Log;

import com.meetingselect.meetingselect.data.api.JSONAPIHolder;
import com.meetingselect.meetingselect.data.model.AccountInformation.AccountInformationResponseModel;

import java.io.IOException;

import io.reactivex.subjects.BehaviorSubject;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AccountInformationRepo {

    private static final String TAG = "AccountInfoRepo";

    private final JSONAPIHolder mJsonAPIHolder;

    private final BehaviorSubject<AccountInformationResponseModel> mAccountInformationBehaviourSubjectRepo = BehaviorSubject.create();

    public AccountInformationRepo() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("https://test-ms4-apigateway.azurewebsites.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mJsonAPIHolder = retrofit.create(JSONAPIHolder.class);
    }

    public void getAccountInformationRepo(String emailAddress) {

        Log.d(TAG, "getAccountInformationRepo: " + emailAddress);

        Call<AccountInformationResponseModel> call = mJsonAPIHolder.getMSAccountInformation(emailAddress);


        call.enqueue(new Callback<AccountInformationResponseModel>() {
            @Override
            public void onResponse(Call<AccountInformationResponseModel> call, Response<AccountInformationResponseModel> response) {
                if(!response.isSuccessful()) {
                    Log.d(TAG, "ResponseCode: " + response.code());
                    return;
                }


                AccountInformationResponseModel accountInformationRepo = response.body();


                if (accountInformationRepo.getMessage() != null) {

                    Log.d(TAG, "onResponse: " + accountInformationRepo.getMessage());
                }

                mAccountInformationBehaviourSubjectRepo.onNext(accountInformationRepo);


            }

            @Override
            public void onFailure(Call<AccountInformationResponseModel> call, Throwable t) {
                if(t instanceof IOException) {
                    Log.e(TAG, "onFailure: ", t);
                }
            }
        });
    }

    public BehaviorSubject<AccountInformationResponseModel> getmAccountInformationBehaviourSubjectRepo() {
        return mAccountInformationBehaviourSubjectRepo;
    }
}
