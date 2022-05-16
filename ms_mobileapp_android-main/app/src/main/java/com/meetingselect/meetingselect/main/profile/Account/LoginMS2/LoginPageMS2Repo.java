package com.meetingselect.meetingselect.main.profile.Account.LoginMS2;

import android.util.Log;

import com.meetingselect.meetingselect.data.api.JSONAPIHolder;
import com.meetingselect.meetingselect.data.model.LoginModelMS2.LoginPostModelMS2;
import com.meetingselect.meetingselect.data.model.LoginModelMS2.LoginResponseModelMS2;

import java.io.IOException;

import io.reactivex.exceptions.OnErrorNotImplementedException;
import io.reactivex.subjects.BehaviorSubject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginPageMS2Repo {

    private static final String TAG = "LoginPageMs2Repo";

    private final JSONAPIHolder mJsonAPIHolder;

    private final BehaviorSubject<LoginResponseModelMS2> mLoginMS2ResponseModelBehaviourSubjectRepo = BehaviorSubject.create();

    public LoginPageMS2Repo() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api-test.meetingselect.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mJsonAPIHolder = retrofit.create(JSONAPIHolder.class);
    }

    public void getLoginMS2Repo(String username, String password) {

        LoginPostModelMS2 loginPostModelMS2 = new LoginPostModelMS2(3, username, password);

        Call<LoginResponseModelMS2> call = mJsonAPIHolder.getLoginMS2User(loginPostModelMS2);

        call.enqueue(new Callback<LoginResponseModelMS2>() {
            @Override
            public void onResponse(Call<LoginResponseModelMS2> call, Response<LoginResponseModelMS2> response) {
                if(!response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.code());
                    return;
                }

                LoginResponseModelMS2 loginResponseModelMS2 = response.body();

                mLoginMS2ResponseModelBehaviourSubjectRepo.onNext(loginResponseModelMS2);

            }

            @Override
            public void onFailure(Call<LoginResponseModelMS2> call, Throwable t) {
                if(t instanceof IOException) {
                    Log.e(TAG, "onFailure: ", t);
                }

                Log.e(TAG, "onFailure: ", t);
            }
        });
    }

    public BehaviorSubject<LoginResponseModelMS2> getmLoginMS2ResponseModelBehaviourSubjectRepo() {
        return mLoginMS2ResponseModelBehaviourSubjectRepo;
    }
}
