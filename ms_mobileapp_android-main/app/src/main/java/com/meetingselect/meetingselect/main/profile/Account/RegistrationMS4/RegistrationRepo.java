package com.meetingselect.meetingselect.main.profile.Account.RegistrationMS4;

import android.util.Log;

import com.meetingselect.meetingselect.data.api.JSONAPIHolder;
import com.meetingselect.meetingselect.data.model.RegisterModelMS4.RegisterPostModel;
import com.meetingselect.meetingselect.data.model.RegisterModelMS4.RegisterResponseModel;

import java.io.IOException;

import io.reactivex.subjects.BehaviorSubject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistrationRepo {

    private static final String TAG = "RegistrationRepo";

    private JSONAPIHolder mJsonApiHolder;

    private final BehaviorSubject<RegisterResponseModel> mRegisterResults = BehaviorSubject.create();


    public RegistrationRepo() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://test-ms4-apigateway.azurewebsites.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mJsonApiHolder = retrofit.create(JSONAPIHolder.class);


    }

    // String fullName, String companyName, String email, String password, String countrycode, String phoneNumber

    public void getRegisterUserRepo(String fullName, String companyName, String email, String password, String countrycode, String phoneNumber) {

        RegisterPostModel registerPostModel = new RegisterPostModel(1, fullName, companyName, email, password, countrycode, phoneNumber, true, false);


        Call<RegisterResponseModel> call = mJsonApiHolder.getRegisterUser(registerPostModel);

        call.enqueue(new Callback<RegisterResponseModel>() {
            @Override
            public void onResponse(Call<RegisterResponseModel> call, Response<RegisterResponseModel> response) {
                if(!response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.code());
                    return;
                }

                RegisterResponseModel responseModel = response.body();

                Log.d(TAG, "onResponse: " + responseModel.getData());
                Log.d(TAG, "onResponse: " + responseModel.getMessage());
                Log.d(TAG, "onResponse: " + responseModel.getStatusCode());

                mRegisterResults.onNext(responseModel);

            }

            @Override
            public void onFailure(Call<RegisterResponseModel> call, Throwable t) {
                if(t instanceof IOException) {
                    Log.e(TAG, "onFailure: ", t);
                }

            }
        });
    }

    public BehaviorSubject<RegisterResponseModel> getmRegisterResults() {
        return mRegisterResults;
    }
}
