package com.meetingselect.meetingselect.main.searchhome.VenueResultsModule;

import static com.meetingselect.meetingselect.R.drawable.ic_baseline_offline_bolt_24;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.imageview.ShapeableImageView;
import com.meetingselect.meetingselect.R;
import com.meetingselect.meetingselect.data.model.VenueResultsInstantBookingModel.VenueResultsInstantBookingResponseModel;
import com.meetingselect.meetingselect.data.model.VenueResultsInstantBookingModelMS4.ResponseModel.VenueResultInstantBookingResponseModel;

import java.util.Objects;

public class VenueResultsInstantBookingAdapter extends RecyclerView.Adapter<VenueResultsInstantBookingAdapter.ViewHolder> {
    
    private Context context;
    private static final String TAG = "VenueResultsInsBAdapter";
    public onVenueResultInstantBookingClicked onVenueResultInstantBookingClicked;
    private MutableLiveData<VenueResultInstantBookingResponseModel> mVenueResultsInstantBookingMS4ResponseMutableLiveDataAdapter = new MutableLiveData<>();


    public VenueResultsInstantBookingAdapter(Context context, onVenueResultInstantBookingClicked onVenueResultInstantBookingClicked) {
        this.context = context;
        this.onVenueResultInstantBookingClicked = onVenueResultInstantBookingClicked;
    }

    public void updateData(MutableLiveData<VenueResultInstantBookingResponseModel> mVenueResultsInstantBookingMS4ResponseMutableLiveDataAdapter) {
        this.mVenueResultsInstantBookingMS4ResponseMutableLiveDataAdapter = mVenueResultsInstantBookingMS4ResponseMutableLiveDataAdapter;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.venue_results_recyclerview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        VenueResultInstantBookingResponseModel resultInstantBookingResponseModel = mVenueResultsInstantBookingMS4ResponseMutableLiveDataAdapter.getValue();

        try {

            Glide.with(context)
                    .asBitmap()
                    .load(resultInstantBookingResponseModel.getData().getSingleCityVenues().getItems().get(position).getImageUrls().get(0))
                    .apply(new RequestOptions().override(1200, 500))
                    .into(holder.VenueImage);

            holder.VenueName.setText(resultInstantBookingResponseModel.getData().getSingleCityVenues().getItems().get(position).getName());
            holder.VenueAddress.setText(resultInstantBookingResponseModel.getData().getSingleCityVenues().getItems().get(position).getAddress().getAddressLines().get(0));

            holder.VenueInstantBookableOrNot.setText("Instant Booking");
            holder.VenueInstantBookableOrNotIMG.setImageResource(ic_baseline_offline_bolt_24);

            holder.VenueAddToCartButton.setVisibility(View.INVISIBLE);
            holder.VenueBookNowButton.setVisibility(View.VISIBLE);

            holder.adapterLayout.setOnClickListener(v -> {

                String venueName = resultInstantBookingResponseModel.getData().getSingleCityVenues().getItems().get(position).getName();
                String venueguid = resultInstantBookingResponseModel.getData().getSingleCityVenues().getItems().get(position).getId();

                onVenueResultInstantBookingClicked.onVenueResultInstantBookingClicked(venueName, venueguid);
            });
        }  catch (IndexOutOfBoundsException e) {

            Log.d(TAG, "onBindViewHolder: ");
        }



    }

    @Override
    public int getItemCount() {
        return mVenueResultsInstantBookingMS4ResponseMutableLiveDataAdapter.getValue().getData().getSingleCityVenues().getTotalCount();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private final ConstraintLayout adapterLayout;
        private final TextView VenueName;
        private final TextView VenueAddress;
        private final TextView VenueInstantBookableOrNot;
        private final ShapeableImageView VenueImage;
        private final ImageView VenueInstantBookableOrNotIMG;
        private final Button VenueBookNowButton;
        private final Button VenueAddToCartButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            adapterLayout = itemView.findViewById(R.id.venue_details_recyclerview_constraintlayout);
            VenueName = itemView.findViewById(R.id.venue_name_venuedetailsrooms);
            VenueAddress = itemView.findViewById(R.id.venue_address_venuedetailsrooms);
            VenueImage = itemView.findViewById(R.id.venues_image_venuedetailsrooms);
            VenueInstantBookableOrNot = itemView.findViewById(R.id.instantTV_venueresults);
            VenueInstantBookableOrNotIMG = itemView.findViewById(R.id.instantimg_venueresults);
            VenueBookNowButton = itemView.findViewById(R.id.venue_booknow_venueresults);
            VenueAddToCartButton = itemView.findViewById(R.id.venue_addtocarttbutton_venueresults);


        }
    }

    public interface onVenueResultInstantBookingClicked {
        void onVenueResultInstantBookingClicked(String venueName, String venueGuid);
    }
}
