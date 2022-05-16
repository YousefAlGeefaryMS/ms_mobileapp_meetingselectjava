package com.meetingselect.meetingselect.main.myrequests.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.meetingselect.meetingselect.R;
import com.meetingselect.meetingselect.data.model.ConfirmedBookingsModelMS2.ConfirmedBookingsMS2ResponseModel;
import com.meetingselect.meetingselect.data.model.PendingBookingsModelMS2.PendingBookingsMS2ResponseModel;
import com.meetingselect.meetingselect.data.model.VenueDetailsModel.VenueDetailsResponseModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ConfirmedBookingsAdapter extends RecyclerView.Adapter<ConfirmedBookingsAdapter.ViewHolder> {

    private onConfirmedBookingClicked onConfirmedBookingClicked;
    private Context context;
    private static final String TAG = "SavedBookingsAdapter";
    private MutableLiveData<ConfirmedBookingsMS2ResponseModel> mConfirmedBookingsResponseBodyMutableLiveData;
    private MutableLiveData<VenueDetailsResponseModel> mConfirmedBookingsImagesMutableLiveData = new MutableLiveData<>();



    public ConfirmedBookingsAdapter(Context context, onConfirmedBookingClicked onConfirmedBookingClicked) {
        this.context = context;
        this.onConfirmedBookingClicked = onConfirmedBookingClicked;
    }

    public void updateData(MutableLiveData<ConfirmedBookingsMS2ResponseModel> mConfirmedBookingsResponseBodyMutableLiveData, MutableLiveData<VenueDetailsResponseModel> mConfirmedBookingsImagesMutableLiveData) {
        this.mConfirmedBookingsResponseBodyMutableLiveData = mConfirmedBookingsResponseBodyMutableLiveData;
        this.mConfirmedBookingsImagesMutableLiveData = mConfirmedBookingsImagesMutableLiveData;
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
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        ConfirmedBookingsMS2ResponseModel response = mConfirmedBookingsResponseBodyMutableLiveData.getValue();

        holder.mRightArrowImage.setVisibility(View.VISIBLE);
        holder.mSeeMoreInfoTextView.setVisibility(View.VISIBLE);

        holder.mMeetingNameTextView.setText(response.getList().get(position).getMeetingName());
        holder.mBookerNameTextView.setText(response.getList().get(position).getFirstName() + " " + response.getList().get(position).getLastName());
        holder.mRFPIDTextView.setText(String.valueOf(response.getList().get(position).getRFPID()));
        holder.mArrivalDateTextView.setText(getFormattedDate(response.getList().get(position).getArrivalDate()));
        holder.mDepartureDateTextView.setText(getFormattedDate(response.getList().get(position).getDepartureDate()));
//        holder.mBookingStatusTextView.setText(String.valueOf(response.getList().get(position).getStatus()));
        holder.mBookingStatusTextView.setText(checkStatusOfBooking(String.valueOf(response.getList().get(position).getStatus())));

        holder.mMainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String proposalID = response.getList().get(position).getProposalID();

                onConfirmedBookingClicked.onConfirmedBookingClicked(proposalID);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mConfirmedBookingsResponseBodyMutableLiveData.getValue().getList().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final ConstraintLayout mMainLayout;
        private final ImageView mRightArrowImage;
        private final TextView mMeetingNameTextView, mBookerNameTextView, mRFPIDTextView, mArrivalDateTextView, mDepartureDateTextView, mBookingStatusTextView, mSeeMoreInfoTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mMainLayout = itemView.findViewById(R.id.myrequestsrv_mainheadlayout_constraintlayout);
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

        Calendar cal=Calendar.getInstance();
        SimpleDateFormat month_date = new SimpleDateFormat("MMM");

        cal.set(Calendar.MONTH, Integer.parseInt(monthNumber));
        String month_name = month_date.format(cal.getTime());

        Log.e("",""+month_name);



        return month_name;
    }

    public interface onConfirmedBookingClicked {

        void onConfirmedBookingClicked(String proposalID);
    }

    private String checkStatusOfBooking(String statusNumber) {
        String status = "null";

        if (statusNumber.equals("1")) {
            status = "OK";
        } else if (statusNumber.equals("2")) {
            status = "No Show";
        } else if (statusNumber.equals("3")) {
            status = "Cancelled by Planner";
        } else if (statusNumber.equals("4")) {
            status = "Cancelled by Venue";
        }
        return status;
    }


}
