package com.meetingselect.meetingselect.main.myrequests.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.meetingselect.meetingselect.R;
import com.meetingselect.meetingselect.data.model.PendingBookingsModelMS2.PendingBookingsMS2ResponseModel;
import com.meetingselect.meetingselect.data.model.VenueDetailsModel.VenueDetailsResponseModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PendingBookingsAdapter extends RecyclerView.Adapter<PendingBookingsAdapter.ViewHolder> {



    private Context context;
    private onPendingBookingClicked onPendingBookingClicked;
    private static final String TAG = "SavedBookingsAdapter";
    private MutableLiveData<PendingBookingsMS2ResponseModel> mPendingBookingsResponseBodyMutableLiveData;
    private MutableLiveData<VenueDetailsResponseModel> mPendingBookingsImagesMutableLiveData;


    public PendingBookingsAdapter(Context context, MutableLiveData<PendingBookingsMS2ResponseModel> mPendingBookingsResponseBodyMutableLiveData,
                                  MutableLiveData<VenueDetailsResponseModel> mPendingBookingsImagesMutableLiveData, onPendingBookingClicked onPendingBookingClicked) {
        this.context = context;
        this.mPendingBookingsResponseBodyMutableLiveData = mPendingBookingsResponseBodyMutableLiveData;
        this.mPendingBookingsImagesMutableLiveData = mPendingBookingsImagesMutableLiveData;
        this.onPendingBookingClicked = onPendingBookingClicked;
    }

    public void updateData(MutableLiveData<PendingBookingsMS2ResponseModel> mPendingBookingsResponseBodyMutableLiveData,
                           MutableLiveData<VenueDetailsResponseModel> mPendingBookingsImagesMutableLiveData) {
        this.mPendingBookingsResponseBodyMutableLiveData = mPendingBookingsResponseBodyMutableLiveData;
        this.mPendingBookingsImagesMutableLiveData = mPendingBookingsImagesMutableLiveData;
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

        PendingBookingsMS2ResponseModel response = mPendingBookingsResponseBodyMutableLiveData.getValue();

        holder.mMeetingNameTextView.setText(response.getList().get(position).getMeetingName());

        try {
            holder.mVenueNameTextview.setText(response.getList().get(position).getRfpVenues().get(0).getVenueName());

        } catch (IndexOutOfBoundsException e) {
            Log.d(TAG, "onBindViewHolder: " + e);
        }
        holder.mBookerNameTextView.setText(response.getList().get(position).getUserFirstName() + " " + response.getList().get(position).getUserLastName());
        holder.mRFPIDTextView.setText(String.valueOf(response.getList().get(position).getRFPID()));
        holder.mArrivalDateTextView.setText(getFormattedDate(response.getList().get(position).getArrivalDate()));
        holder.mDepartureDateTextView.setText(getFormattedDate(response.getList().get(position).getDepartureDate()));


//        holder.mBookingStatusTextView.setText(String.valueOf(response.getList().get(position).getStatus()));
        holder.mBookingStatusTextView.setText(checkStatusOfBooking(String.valueOf(response.getList().get(position).getStatus())));

        if (response.getList().get(position).getProposals().size() == 0) {
            holder.mRightArrowImage.setVisibility(View.INVISIBLE);
            holder.mSeeMoreInfoTextView.setVisibility(View.INVISIBLE);
        } else {
            holder.mRightArrowImage.setVisibility(View.VISIBLE);
            holder.mSeeMoreInfoTextView.setVisibility(View.VISIBLE);
        }
        holder.mMainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Log.d(TAG, "onClick: " + response.getList().get(position).getProposals().size());

                if (!(response.getList().get(position).getProposals().size() == 0)) {
                    ArrayList<String> proposalIDs = new ArrayList<>();
                    for (int i = 0; i < response.getList().get(position).getProposals().size(); i++) {

                        String proposalID = String.valueOf(response.getList().get(position).getProposals().get(0).getProposalID());
                        proposalIDs.add(proposalID);
                    }
                    onPendingBookingClicked.onPendingBookingClicked(proposalIDs);
                } else {
                    Toast.makeText(context, "No proposals available for viewing at the moment.", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    @Override
    public int getItemCount() {
        return mPendingBookingsResponseBodyMutableLiveData.getValue().getList().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final ConstraintLayout mMainLayout;
        private final ImageView mRightArrowImage;
        private final TextView mMeetingNameTextView, mBookerNameTextView, mRFPIDTextView, mArrivalDateTextView, mDepartureDateTextView, mBookingStatusTextView, mSeeMoreInfoTextView, mVenueNameTextview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mMainLayout = itemView.findViewById(R.id.myrequestsrv_mainheadlayout_constraintlayout);
//            mVenueImage = itemView.findViewById(R.id.myrequestsrv_image_venuedetailsrooms);
            mVenueNameTextview = itemView.findViewById(R.id.myrequestsrv_venuename_textview);
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


    public interface onPendingBookingClicked {
        void onPendingBookingClicked(ArrayList<String> proposalIDs);
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
