package com.example.adibhanafi.event4you.Other;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.adibhanafi.event4you.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class EventAdapter extends FirestoreRecyclerAdapter<EventData, EventAdapter.EventHolder> {

    public EventAdapter(FirestoreRecyclerOptions<EventData> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(EventHolder holder, int position, EventData model) {
        holder.textViewName.setText(model.getName());
        holder.textViewDate.setText(model.getDate());
        holder.textViewAddress.setText(model.getAddress());
    }

    @NonNull
    @Override
    public EventHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_list, parent, false);
        return new EventHolder(v);
    }

    public class EventHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        TextView textViewDate;
        TextView textViewAddress;

        public EventHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.text_view_name);
            textViewDate = itemView.findViewById(R.id.text_view_date);
            textViewAddress = itemView.findViewById(R.id.text_view_address);

        }
    }
}
