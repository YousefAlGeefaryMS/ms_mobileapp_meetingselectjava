package com.meetingselect.meetingselect.main.searchhome.VenueInstantBookingProcess;

import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.meetingselect.meetingselect.R;
import com.meetingselect.meetingselect.data.api.JSONAPIHolder;
import com.meetingselect.meetingselect.data.model.FinalizeCartModel.FinalizeCartResponseModel;
import com.meetingselect.meetingselect.data.model.InstantBookingTwoPointZeroModel.BillingAddress;
import com.meetingselect.meetingselect.data.model.InstantBookingTwoPointZeroModel.InstantBookingTwoPointZeroRequestModel;
import com.meetingselect.meetingselect.data.model.InstantBookingTwoPointZeroModel.InstantBookingTwoPointZeroResponseModel;
import com.meetingselect.meetingselect.data.model.InstantBookingTwoPointZeroModel.Room;
import com.meetingselect.meetingselect.data.model.InstantBookingTwoPointZeroModel.TaxLinesItem;
import com.meetingselect.meetingselect.data.model.InstantBookingTwoPointZeroModel.Venue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.subjects.BehaviorSubject;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FinishingConfirmingBookingRepo {

    private static final String TAG = "FinsihBookingRepo";

    private JSONAPIHolder jsonapiHolder;

    private JSONAPIHolder jsonapiHolderTwoPointZero;

    private final BehaviorSubject<FinalizeCartResponseModel> mFinalizeCartModel = BehaviorSubject.create();

    public FinishingConfirmingBookingRepo() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("https://api-staging.cyberdigma.nl/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonapiHolder = retrofit.create(JSONAPIHolder.class);


        Retrofit retrofitSendBookingTwoPointZero = new Retrofit.Builder()
                .client(client)
                .baseUrl("https://api-test.meetingselect.com:443/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonapiHolderTwoPointZero = retrofitSendBookingTwoPointZero.create(JSONAPIHolder.class);


    }

    public void getFinalizeCart(String CartKey) {

        Call<FinalizeCartResponseModel> call = jsonapiHolder.getFinalizeCartAPI(CartKey);

        call.enqueue(new Callback<FinalizeCartResponseModel>() {
            @Override
            public void onResponse(Call<FinalizeCartResponseModel> call, Response<FinalizeCartResponseModel> response) {
                if(!response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.code());
                    return;
                }

                FinalizeCartResponseModel finalizeCartResponseModel = response.body();

                mFinalizeCartModel.onNext(finalizeCartResponseModel);

                Log.d(TAG, "onResponse: " + finalizeCartResponseModel.getKey());


            }

            @Override
            public void onFailure(Call<FinalizeCartResponseModel> call, Throwable t) {

                if(t instanceof IOException) {
                    Log.e(TAG, "onFailureRooms: ", t);
                }

            }
        });
    }


    public void sendBookingToTwoPointZero(String bookingId,
                                          String bookingExternalId,
                                          String bookingNumber,
                                          String bookingDate,
                                          String meetingName,
                                          String arrivalDate,
                                          String departureDate,
                                          int attendees,
                                          Venue venue,
                                          Room room,
                                          List<Object> amenities,
                                          List<TaxLinesItem> taxLines,
                                          String currencyCode,
                                          int totalPriceExcludingTax,
                                          int totalTax,
                                          int totalPriceIncludingTax,
                                          int plannerId,
                                          List<Object> companyReferences,
                                          BillingAddress billingAddress) {

        Log.d(TAG, "sendBookingToTwoPointZero: Called");


        InstantBookingTwoPointZeroRequestModel instantBookingTwoPointZeroRequestModel = new InstantBookingTwoPointZeroRequestModel(bookingId, bookingExternalId, bookingNumber, bookingDate, meetingName, arrivalDate, departureDate, attendees, venue, room, amenities, taxLines, currencyCode, totalPriceExcludingTax, totalTax, totalPriceIncludingTax, plannerId, companyReferences, billingAddress);

        Call<ResponseBody> call = jsonapiHolderTwoPointZero.sendBookingToTwoPointZero(instantBookingTwoPointZeroRequestModel);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(!response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.code());
                    return;
                } else {

                    Log.d(TAG, "onResponse: " + response.code());





                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }




    public BehaviorSubject<FinalizeCartResponseModel> getmFinalizeCartModel() {
        return mFinalizeCartModel;
    }


}
