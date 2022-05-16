package com.meetingselect.meetingselect.main.searchhome.SearchVenueModule;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.meetingselect.meetingselect.R;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public class SearchVenueAdapter extends RecyclerView.Adapter<SearchVenueAdapter.ViewHolder>{

    private OnItemClickedListener onItemClickedListener;
    private Context context;
    private List<String> mSearchLocationResult = Collections.emptyList();
    private static final String TAG = "SearchVenueAdapter";


    public SearchVenueAdapter(Context context, OnItemClickedListener OnItemClickedListener) {
        this.context = context;
        this.onItemClickedListener = OnItemClickedListener;
    }

    @NonNull
    @NotNull
    @Override
    public SearchVenueAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_location_recyclerview, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    public void updateData(List<String> mSearchLocationResult) {
        this.mSearchLocationResult = mSearchLocationResult;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull SearchVenueAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.LocationName.setText(mSearchLocationResult.get(position));

        holder.LocationName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedLocation = mSearchLocationResult.get(position);
                Log.d(TAG, "Selected Location " + selectedLocation);

                onItemClickedListener.onLocationClicked(selectedLocation);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mSearchLocationResult.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView LocationName;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            LocationName = itemView.findViewById(R.id.location_name);
        }

    }

    public interface OnItemClickedListener {
        void onLocationClicked(String locationName);
    }
}
