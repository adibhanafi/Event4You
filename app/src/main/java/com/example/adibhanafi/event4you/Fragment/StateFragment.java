package com.example.adibhanafi.event4you.Fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.adibhanafi.event4you.Other.EventData;
import com.example.adibhanafi.event4you.Other.EventAdapter;
import com.example.adibhanafi.event4you.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;


/**
 * A simple {@link Fragment} subclass.
 */
public class StateFragment extends Fragment {
    private FirebaseFirestore db;
    //    private DocumentReference eventRef = db.collection("State").document("Kuala Lumpur");
    private Query query;
    private View eventView;
    private RecyclerView recyclerView;
    private FirestoreRecyclerAdapter<EventData, EventViewHolder> adapter;


    public StateFragment() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        eventView = inflater.inflate(R.layout.fragment_state, container, false);

        db = FirebaseFirestore.getInstance();
        query = db.collection("State").document("Kuala Lumpur").collection("Event");

        recyclerView = eventView.findViewById(R.id.recyclerViewState);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        setUpRecyclerView();

        // Inflate the layout for this fragment
        return eventView;
    }

//    private void setUpRecyclerView() {
//
//        FirestoreRecyclerOptions<EventData> options = new FirestoreRecyclerOptions.Builder<EventData>().setQuery(query, EventData.class).build();
//
//        adapter = new EventAdapter(options);
//
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerView.setAdapter(adapter);
//    }

    @Override
    public void onStart() {
        super.onStart();

        FirestoreRecyclerOptions<EventData> options = new FirestoreRecyclerOptions.Builder<EventData>()
                .setQuery(query, EventData.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<EventData, EventViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull EventViewHolder holder, int position, @NonNull EventData model) {
                holder.tvEventName.setText(model.getName());
                holder.tvEventLocation.setText(model.getAddress());
                holder.tvEventDate.setText(model.getDate());
            }

            @NonNull
            @Override
            public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_event, parent, false);
                EventViewHolder viewHolder = new EventViewHolder(view);

                return viewHolder;
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();

        if (adapter != null) {
            adapter.stopListening();
        }
    }

    private class EventViewHolder extends RecyclerView.ViewHolder {
        private View view;
        private TextView tvEventName, tvEventDate, tvEventLocation;

        EventViewHolder(View itemView) {
            super(itemView);

            tvEventName = itemView.findViewById(R.id.tvEventName);
            tvEventDate = itemView.findViewById(R.id.tvEventDate);
            tvEventLocation = itemView.findViewById(R.id.tvEventLocation);

            view = itemView;
        }

//        void setEventName(String eventName) {
//            TextView tvEventName = view.findViewById(R.id.tvEventName);
//            tvEventName.setText(eventName);
//        }
//
//        void setEventDate(String eventDate) {
//            TextView tvEventDate = view.findViewById(R.id.tvEventDate);
//            tvEventDate.setText(eventDate);
//        }
//
//        void setEventLocation(String eventLocation) {
//            TextView tvEventLocation = view.findViewById(R.id.tvEventLocation);
//            tvEventLocation.setText(eventLocation);
//        }
    }
}
