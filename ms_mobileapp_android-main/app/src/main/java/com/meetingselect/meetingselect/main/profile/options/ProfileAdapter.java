package com.meetingselect.meetingselect.main.profile.options;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.meetingselect.meetingselect.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {

    private Context context;
    private ArrayList<String> OptionsList = new ArrayList<>();
    private int selectedPos = RecyclerView.NO_POSITION;
    private onOptionClicked onOptionClicked;

    public ProfileAdapter(Context context, onOptionClicked onOptionClicked) {
        this.context = context;
        this.onOptionClicked = onOptionClicked;
    }

    public void updateData(ArrayList<String> OptionsList) {
        this.OptionsList = OptionsList;
        notifyDataSetChanged();
    }

    @NonNull
    @NotNull
    @Override
    public ProfileAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_options_recyclerview, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ProfileAdapter.ViewHolder holder, int position) {
        holder.OptionName.setText(OptionsList.get(position));

        holder.itemView.setSelected(selectedPos == position);

        holder.adapterLayout.setOnClickListener(v -> {

            if (holder.getLayoutPosition() == RecyclerView.NO_POSITION) return;

            notifyItemChanged(selectedPos);
            selectedPos = holder.getLayoutPosition();
            notifyItemChanged(selectedPos);
            onOptionClicked.onOptionClicked(OptionsList.get(position));

        });
    }

    @Override
    public int getItemCount() {
        return OptionsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout adapterLayout;
        TextView OptionName;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            OptionName = itemView.findViewById(R.id.profile_optionname_string_recyclerview);
            adapterLayout = itemView.findViewById(R.id.profile_options_recyclerview_layout);
        }
    }


    public interface onOptionClicked {
        void onOptionClicked(String option);
    }
}
