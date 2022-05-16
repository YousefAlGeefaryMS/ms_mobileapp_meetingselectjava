package com.meetingselect.meetingselect.main.myrequests.adapters;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.meetingselect.meetingselect.R;
import com.meetingselect.meetingselect.data.model.SavedBookingsMS2.SavedBookingsMS2ResponseModel;
import com.meetingselect.meetingselect.data.model.VenueDetailsModel.VenueDetailsResponseModel;
import com.meetingselect.meetingselect.main.myrequests.MyRequests;
import com.meetingselect.meetingselect.main.searchhome.VenueDetailsModule.VenueDetailsRepo;
import com.meetingselect.meetingselect.mainviewmodel.MainViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class SavedBookingsAdapter extends RecyclerView.Adapter<SavedBookingsAdapter.ViewHolder> {

    private Context context;
    private static final String TAG = "SavedBookingsAdapter";
    private MutableLiveData<SavedBookingsMS2ResponseModel> mSavedBookingsResponseBodyMutableLiveData;
    private MutableLiveData<VenueDetailsResponseModel> mSavedBookingsVenueDetailsResponseBodyMutableLiveData;

    public SavedBookingsAdapter(Context context, MutableLiveData<SavedBookingsMS2ResponseModel> mSavedBookingsResponseBodyMutableLiveData, MutableLiveData<VenueDetailsResponseModel> mSavedBookingsVenueDetailsResponseBodyMutableLiveData) {
        this.context = context;
        this.mSavedBookingsResponseBodyMutableLiveData = mSavedBookingsResponseBodyMutableLiveData;
        this.mSavedBookingsVenueDetailsResponseBodyMutableLiveData = mSavedBookingsVenueDetailsResponseBodyMutableLiveData;


    }

    public void updateData(MutableLiveData<SavedBookingsMS2ResponseModel> mSavedBookingsResponseBodyMutableLiveData, MutableLiveData<VenueDetailsResponseModel> mSavedBookingsVenueDetailsResponseBodyMutableLiveData) {
        this.mSavedBookingsResponseBodyMutableLiveData = mSavedBookingsResponseBodyMutableLiveData;
        this.mSavedBookingsVenueDetailsResponseBodyMutableLiveData = mSavedBookingsVenueDetailsResponseBodyMutableLiveData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.myrequests_proposal_recyclerview, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SavedBookingsMS2ResponseModel response = mSavedBookingsResponseBodyMutableLiveData.getValue();
        VenueDetailsResponseModel venueDetailsResponseModel = mSavedBookingsVenueDetailsResponseBodyMutableLiveData.getValue();

        holder.mRightArrowImage.setVisibility(View.INVISIBLE);
        holder.mSeeMoreInfoTextView.setVisibility(View.INVISIBLE);

        if (!(response.getList().get(position).getRfpVenues().isEmpty())) {

            try {

                Log.d(TAG, "onBindViewHolder: " + venueDetailsResponseModel.getPictures().size());
                Log.d(TAG, "onBindViewHolder: " + venueDetailsResponseModel.getPictures().get(0).getOriginalUrl());


//                Glide.with(context)
//                        .asBitmap()
//                        .load(venueDetailsResponseModel.getPictures().get(0).getOriginalUrl())
////                .centerCrop()
//                        .apply(new RequestOptions().override(1200, 500))
//                        .into(holder.mVenueImage);


            } catch (NullPointerException e) {
                Log.e(TAG, "onBindViewHolder: ", e);
            }
        }


        holder.mMeetingNameTextView.setText(response.getList().get(position).getMeetingName());
        holder.mBookerNameTextView.setText(response.getList().get(position).getUserFirstName() + " " + response.getList().get(position).getUserLastName());
        holder.mRFPIDTextView.setText(String.valueOf(response.getList().get(position).getRFPID()));
        holder.mArrivalDateTextView.setText(getFormattedDate(response.getList().get(position).getArrivalDate()));
        holder.mDepartureDateTextView.setText(getFormattedDate(response.getList().get(position).getDepartureDate()));


        holder.mBookingStatusTextView.setText(checkStatusOfBooking(String.valueOf(response.getList().get(position).getStatus())));


    }

    @Override
    public int getItemCount() {
        return mSavedBookingsResponseBodyMutableLiveData.getValue().getList().size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //        private final ImageView mVenueImage, mRightArrowImage;
        private final ImageView mRightArrowImage;
        private final TextView mMeetingNameTextView, mBookerNameTextView, mRFPIDTextView, mArrivalDateTextView, mDepartureDateTextView, mBookingStatusTextView, mSeeMoreInfoTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

//            mVenueImage = itemView.findViewById(R.id.myrequestsrv_image_venuedetailsrooms);
            mMeetingNameTextView = itemView.findViewById(R.id.myrequestsrv_meetingname_textview);
            mBookerNameTextView = itemView.findViewById(R.id.myrequestsrv_bookername_textview);
            mRFPIDTextView = itemView.findViewById(R.id.myrequestsrv_rfpid_textview);
            mArrivalDateTextView = itemView.findViewById(R.id.myrequestsrv_arrivaldate_textview);
            mDepartureDateTextView = itemView.findViewById(R.id.myrequestsrv_departuredate_textview);
            mBookingStatusTextView = itemView.findViewById(R.id.myrequestsrv_bookingstatus_textview);
            mSeeMoreInfoTextView = itemView.findViewById(R.id.myrequests_moreinfotext_textview);
            mRightArrowImage = itemView.findViewById(R.id.myrequests_arrowright_imageview);

        }
    }


    private String getFormattedDate(String rawDate) {
        String[] splittedSelectedDateFromTime = rawDate.split("T");

        String[] splittedSelectedDateFromHyphen = splittedSelectedDateFromTime[0].split("-");

        Log.d(TAG, "getFormattedDate: " + splittedSelectedDateFromHyphen[0] + splittedSelectedDateFromHyphen[1] + splittedSelectedDateFromHyphen[2]);

        Log.d(TAG, "getFormattedDate: " + splittedSelectedDateFromHyphen[2] + " " + getMonthNumber(splittedSelectedDateFromHyphen[1]) + " " + splittedSelectedDateFromHyphen[0]);


        String finalFormattedDate = splittedSelectedDateFromHyphen[2] + " " + getMonthNumber(splittedSelectedDateFromHyphen[1]) + " " + splittedSelectedDateFromHyphen[0];

        return finalFormattedDate;
    }

    private String getMonthNumber(String monthNumber) {

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat month_date = new SimpleDateFormat("MMM");

        cal.set(Calendar.MONTH, Integer.parseInt(monthNumber));
        String month_name = month_date.format(cal.getTime());

        Log.e("", "" + month_name);


        return month_name;
    }

    private String checkStatusOfBooking(String statusNumber) {
        String status = "null";

        if (statusNumber.equals("1")) {
            status = "Saved";
        } else if (statusNumber.equals("2")) {
            status = "My Favourite";
        } else if (statusNumber.equals("3")) {
            status = "Sent";
        } else if (statusNumber.equals("4")) {
            status = "Cancelled";
        } else if (statusNumber.equals("5")) {
            status = "Accepted";
        } else if (statusNumber.equals("6")) {
            status = "Sent by Meetingselect";
        } else if (statusNumber.equals("7")) {
            status = "Sent after accepted";
        } else if (statusNumber.equals("8")) {
            status = "Waiting for approval";
        } else if (statusNumber.equals("9")) {
            status = "Approved";
        } else if (statusNumber.equals("10")) {
            status = "Sent After Waiting for Approval";
        } else if (statusNumber.equals("11")) {
            status = "Sent After Approved";
        }
        return status;
    }


}
