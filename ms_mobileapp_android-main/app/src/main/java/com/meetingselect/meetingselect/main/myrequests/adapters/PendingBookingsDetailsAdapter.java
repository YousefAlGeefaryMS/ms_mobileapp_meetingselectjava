package com.meetingselect.meetingselect.main.myrequests.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.meetingselect.meetingselect.R;
import com.meetingselect.meetingselect.data.model.AcceptConfirmMS2Model.AcceptConfirmResponseModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class PendingBookingsDetailsAdapter extends RecyclerView.Adapter<PendingBookingsDetailsAdapter.ViewHolder> {

    private static final String TAG = "PendingBookingsDetailsAdapter";
    private Context context;
    private List<MutableLiveData<AcceptConfirmResponseModel>> mHolderOfResponses;
    private onPDFLinkClicked onPDFLinkClicked;

    public PendingBookingsDetailsAdapter(Context context, onPDFLinkClicked onPDFLinkClicked) {
        this.context = context;
        this.onPDFLinkClicked = onPDFLinkClicked;
    }

    public void updateData(List<MutableLiveData<AcceptConfirmResponseModel>> mHolderOfResponses) {
        this.mHolderOfResponses = mHolderOfResponses;
    }

    @NonNull
    @Override
    public PendingBookingsDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.myrequests_proposal_recyclerview, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PendingBookingsDetailsAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Log.d(TAG, "onBindViewHolder: " + mHolderOfResponses.get(position).getValue().getRfpId());

        holder.mRFPIDTextView.setText(String.valueOf(mHolderOfResponses.get(position).getValue().getRfpId()));
        holder.mMeetingNameTextView.setText(mHolderOfResponses.get(position).getValue().getMeetingName());
        holder.mVenueNameTextview.setText(mHolderOfResponses.get(position).getValue().getVenueName());
        holder.mBookerNameStringTextView.setText("Company Name");
        holder.mBookerNameTextView.setText(mHolderOfResponses.get(position).getValue().getCompanyName());
        holder.mArrivalDateTextView.setText(getFormattedDate(mHolderOfResponses.get(position).getValue().getRFPDateDetailsEntities().get(0).getArrivalDate()));
        holder.mDepartureDateTextView.setText(getFormattedDate(mHolderOfResponses.get(position).getValue().getRFPDateDetailsEntities().get(0).getDepartureDate()));
        holder.mBookingStatusTextView.setVisibility(View.GONE);
        holder.mRightArrowImage.setVisibility(View.GONE);


        holder.mBookingStatusStringTextView.setText("View RFP PDF");
        holder.mBookingStatusStringTextView.setTextColor(ContextCompat.getColor(context, R.color.meetingSelectBlue));
        holder.mBookingStatusStringTextView.setTypeface(null, Typeface.BOLD);
        holder.mBookingStatusStringTextView.setTextSize(14);

        holder.mBookingStatusStringTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG, "onClick: " + mHolderOfResponses.get(position).getValue().getRfpPdfUrl());

                onPDFLinkClicked.onPDFLinkClicked(mHolderOfResponses.get(position).getValue().getRfpPdfUrl());
            }
        });

        holder.mSeeMoreInfoTextView.setText("View Proposal PDF");
        holder.mSeeMoreInfoTextView.setTextColor(ContextCompat.getColor(context, R.color.meetingSelectBlue));
        holder.mSeeMoreInfoTextView.setTypeface(null, Typeface.BOLD);
        holder.mSeeMoreInfoTextView.setTextSize(14);

        holder.mSeeMoreInfoTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG, "onClick: " + mHolderOfResponses.get(position).getValue().getProposalPdfUrl());

                onPDFLinkClicked.onPDFLinkClicked(mHolderOfResponses.get(position).getValue().getProposalPdfUrl());

            }
        });



    }

    @Override
    public int getItemCount() {
        return mHolderOfResponses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final ConstraintLayout mMainLayout;
        private final ImageView mRightArrowImage;
        private final TextView mMeetingNameTextView, mBookerNameTextView, mRFPIDTextView, mArrivalDateTextView, mDepartureDateTextView, mBookingStatusTextView, mSeeMoreInfoTextView, mVenueNameTextview;
        private final TextView mBookerNameStringTextView, mBookingStatusStringTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mMainLayout = itemView.findViewById(R.id.myrequestsrv_mainheadlayout_constraintlayout);
//            mVenueImage = itemView.findViewById(R.id.myrequestsrv_image_venuedetailsrooms);
            mBookerNameStringTextView = itemView.findViewById(R.id.myrequestsrv_bookernamestring_textview);
            mVenueNameTextview = itemView.findViewById(R.id.myrequestsrv_venuename_textview);
            mMeetingNameTextView = itemView.findViewById(R.id.myrequestsrv_meetingname_textview);
            mBookerNameTextView = itemView.findViewById(R.id.myrequestsrv_bookername_textview);
            mRFPIDTextView = itemView.findViewById(R.id.myrequestsrv_rfpid_textview);
            mArrivalDateTextView = itemView.findViewById(R.id.myrequestsrv_arrivaldate_textview);
            mDepartureDateTextView = itemView.findViewById(R.id.myrequestsrv_departuredate_textview);
            mBookingStatusTextView = itemView.findViewById(R.id.myrequestsrv_bookingstatus_textview);
            mBookingStatusStringTextView = itemView.findViewById(R.id.myrequestsrv_bookingstatusstring_textview);

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

    public interface onPDFLinkClicked {
        void onPDFLinkClicked(String pdflink);
    }
}
