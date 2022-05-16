package com.meetingselect.meetingselect.main.profile.Account.LoginMS4;

import android.util.Log;

import com.meetingselect.meetingselect.data.api.JSONAPIHolder;
import com.meetingselect.meetingselect.data.model.LoginModelMS4.LoginPostModel;
import com.meetingselect.meetingselect.data.model.LoginModelMS4.LoginResponseModel;

import java.io.IOException;

import io.reactivex.subjects.BehaviorSubject;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginRepo {

    private static final String TAG = "LoginRepo";

    private JSONAPIHolder jsonapiHolder;

    private BehaviorSubject<LoginResponseModel> mLoginResponseModel = BehaviorSubject.create();

    public LoginRepo() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("https://test-ms4-apigateway.azurewebsites.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonapiHolder = retrofit.create(JSONAPIHolder.class);
    }

    public void getLoginUserRepo(String email, String password) {

        LoginPostModel loginPostModel = new LoginPostModel(email, password);

        Call<LoginResponseModel> call = jsonapiHolder.getLoginUser(loginPostModel);

        call.enqueue(new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                if(!response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.code());
                    return;
                }

                LoginResponseModel loginResponseModel = new LoginResponseModel();


                Log.d(TAG, "onResponse: " + loginResponseModel.getMessage());

                Log.d(TAG, "onResponse: Testing");
            }

            @Override
            public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                if(t instanceof IOException) {
                    Log.e(TAG, "onFailure: ", t);
                }

                Log.e(TAG, "onFailure: ", t);
            }
        });
    }
}
