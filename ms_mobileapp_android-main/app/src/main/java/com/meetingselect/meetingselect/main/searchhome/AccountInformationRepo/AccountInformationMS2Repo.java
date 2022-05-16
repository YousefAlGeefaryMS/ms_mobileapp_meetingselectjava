package com.meetingselect.meetingselect.main.searchhome.AccountInformationRepo;

import android.util.Log;

import com.meetingselect.meetingselect.data.api.JSONAPIHolder;
import com.meetingselect.meetingselect.data.model.AccountInformation.AccountInformationResponseModel;
import com.meetingselect.meetingselect.data.model.AccountInformationMS2.AccountInformationMS2ResponseModel;

import java.io.IOException;

import io.reactivex.subjects.BehaviorSubject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AccountInformationMS2Repo {

    private static final String TAG = "AccountInformationMS2";

    private final JSONAPIHolder mJsonAPIHolder;

    private final BehaviorSubject<AccountInformationMS2ResponseModel> mAccountInformationMS2ResponseModelRepoBehaviourSubject = BehaviorSubject.create();

    public AccountInformationMS2Repo() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api-test.meetingselect.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mJsonAPIHolder = retrofit.create(JSONAPIHolder.class);
    }

    public void getAccountInformationMS2(String token) {

        Call<AccountInformationMS2ResponseModel> call = mJsonAPIHolder.getAccountInformationMS2(token);

        call.enqueue(new Callback<AccountInformationMS2ResponseModel>() {
            @Override
            public void onResponse(Call<AccountInformationMS2ResponseModel> call, Response<AccountInformationMS2ResponseModel> response) {
                if(!response.isSuccessful()) {
                    Log.d(TAG, "ResponseCode: " + response.code());
                    return;
                }

                AccountInformationMS2ResponseModel accountInformationMS2ResponseModel = response.body();

                mAccountInformationMS2ResponseModelRepoBehaviourSubject.onNext(accountInformationMS2ResponseModel);


                Log.d(TAG, "onResponse: " + accountInformationMS2ResponseModel.getFirstName());
            }

            @Override
            public void onFailure(Call<AccountInformationMS2ResponseModel> call, Throwable t) {
                if(t instanceof IOException) {
                    Log.e(TAG, "onFailure: ", t);
                }
            }
        });


    }

    public BehaviorSubject<AccountInformationMS2ResponseModel> getmAccountInformationMS2ResponseModelRepoBehaviourSubject() {
        return mAccountInformationMS2ResponseModelRepoBehaviourSubject;
    }
}
