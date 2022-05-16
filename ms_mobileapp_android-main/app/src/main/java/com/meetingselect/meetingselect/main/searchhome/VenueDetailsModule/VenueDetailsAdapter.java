package com.meetingselect.meetingselect.main.searchhome.VenueDetailsModule;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;
import com.meetingselect.meetingselect.R;
import com.meetingselect.meetingselect.data.model.VenueDetailsModel.RoomsAvailability.LocationsItem;
import com.meetingselect.meetingselect.data.model.VenueDetailsModel.RoomsAvailability.SpacesItem;
import com.meetingselect.meetingselect.data.model.VenueDetailsModel.RoomsAvailability.VenueDetailsResponseRoomsAvailableModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class VenueDetailsAdapter extends RecyclerView.Adapter<VenueDetailsAdapter.ViewHolder> {

    private static final String TAG = "VenueDetailsAdapter";
    private final Context context;
    private onRoomClicked onRoomClicked;
    private MutableLiveData<VenueDetailsResponseRoomsAvailableModel> mVenueDetailsRoomsMutableLiveData = new MutableLiveData<>();
    private List<ConstraintLayout> ConstraintLayoutList = new ArrayList<>();
    private View view;

    public VenueDetailsAdapter(Context context, onRoomClicked onRoomClicked) {
        this.onRoomClicked = onRoomClicked;
        this.context = context;
    }

    public void updateData(MutableLiveData<VenueDetailsResponseRoomsAvailableModel> mVenueDetailsRoomsBehaviorSubject) {
        this.mVenueDetailsRoomsMutableLiveData = mVenueDetailsRoomsBehaviorSubject;
        VenueDetailsResponseRoomsAvailableModel spacesItems = mVenueDetailsRoomsMutableLiveData.getValue();

        notifyDataSetChanged();
    }

    @NonNull
    @NotNull
    @Override
    public VenueDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.venue_detailrooms_recyclerview, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull VenueDetailsAdapter.ViewHolder holder, int position) {

        VenueDetailsResponseRoomsAvailableModel spacesItems = mVenueDetailsRoomsMutableLiveData.getValue();
        ConstraintLayoutList.add(holder.RoomLayout);

        if(!(spacesItems.getLocations().size() == 0)) {

            holder.RoomUnavailable.setVisibility(View.VISIBLE);
            holder.AttendeesNumberLayout.setVisibility(View.GONE);

            if(!(spacesItems.getLocations().get(0).getSpaces().get(position).getHash().isEmpty())) {
                holder.RoomUnavailable.setVisibility(View.GONE);
                holder.AttendeesNumberLayout.setVisibility(View.VISIBLE);

            }

                Glide.with(context)
                    .asBitmap()
                    .load("https://az691754.vo.msecnd.net/website-staging/" +  spacesItems.getLocations().get(0).getLocationId() + "/" + spacesItems.getLocations().get(0).getSpaces().get(position).getSpaceImage())
                    .into(holder.RoomImage);

            holder.RoomName.setText(spacesItems.getLocations().get(0).getSpaces().get(position).getSpaceName());
            holder.RoomDescription.setText(Html.fromHtml(spacesItems.getLocations().get(0).getSpaces().get(position).getSpaceDescription()));
            holder.ParticipantNumbers.setText("Participants " + spacesItems.getLocations().get(0).getSpaces().get(position).getMinSeats() + " - " + spacesItems.getLocations().get(0).getSpaces().get(position).getMaxSeats());
            holder.SeatPrice.setText("€ " + spacesItems.getLocations().get(0).getSpaces().get(position).getPrice());
            holder.RoomLayout.setTag(position);

            holder.RoomLayout.setOnClickListener(v -> {

                if(!(spacesItems.getLocations().get(0).getSpaces().get(position).getHash().isEmpty())) {
                    String TotalPrice = String.valueOf(spacesItems.getLocations().get(0).getSpaces().get(position).getPriceTotal());
                    onRoomClicked.onRoomClicked(TotalPrice, position);


                    for(ConstraintLayout constraintLayoutList : ConstraintLayoutList) {
//                holder.RoomWhiteCheckMark.setVisibility(View.VISIBLE);
//                holder.RoomCheckMarkImage.setVisibility(View.VISIBLE);
                        constraintLayoutList.getViewById(R.id.venues_checkmark_venuedetailsrooms).setVisibility(View.INVISIBLE);
                        constraintLayoutList.getViewById(R.id.venues_whitebackgroundcheckmark_venuedetailsrooms).setVisibility(View.INVISIBLE);

                    }

                    holder.RoomLayout.getViewById(R.id.venues_whitebackgroundcheckmark_venuedetailsrooms).setVisibility(View.VISIBLE);
                    holder.RoomLayout.getViewById(R.id.venues_checkmark_venuedetailsrooms).setVisibility(View.VISIBLE);

                }

            });
        }

    }

    @Override
    public int getItemCount() {
        try{
            return mVenueDetailsRoomsMutableLiveData.getValue().getLocations().get(0).getSpaces().size();

        } catch (IndexOutOfBoundsException e) {
            return 0;
        } catch (NullPointerException o) {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ConstraintLayout RoomLayout, RoomUnavailable, AttendeesNumberLayout;
        private TextView RoomName, RoomDescription, ParticipantNumbers, SeatPrice;
        private ShapeableImageView RoomImage;
        public ViewHolder(@NonNull @NotNull View itemView) {

            super(itemView);

            RoomLayout = itemView.findViewById(R.id.venue_details_recyclerview_constraintlayout);
            RoomImage = itemView.findViewById(R.id.venues_image_venuedetailsrooms);
            RoomName = itemView.findViewById(R.id.venue_name_venuedetailsrooms);
            RoomDescription = itemView.findViewById(R.id.venue_description_venuedetailsrooms);
            ParticipantNumbers = itemView.findViewById(R.id.venue_numberofparticipants_venuedetailsrooms);
            SeatPrice = itemView.findViewById(R.id.venue_seatprice_venuedetailsrooms);
            RoomUnavailable = itemView.findViewById(R.id.venue_roomunavailablechangeroom_constraintlayout);
            AttendeesNumberLayout = itemView.findViewById(R.id.venue_constraintlayout_attendeesnumber);

        }
    }


    public interface onRoomClicked {
        void onRoomClicked(String TotalPrice, int spacePosition);
    }


}
