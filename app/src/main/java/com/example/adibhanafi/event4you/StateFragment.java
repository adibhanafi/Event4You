package com.example.adibhanafi.event4you;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;


/**
 * A simple {@link Fragment} subclass.
 */
public class StateFragment extends Fragment {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference eventRef = db.collection("State").document("Kuala Lumpur");

    private EventAdapter adapter;
    private View eventView;
    private RecyclerView recyclerView;


    public StateFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        eventView = inflater.inflate(R.layout.fragment_state, container, false);
        recyclerView = eventView.findViewById(R.id.recycler_view);
        setUpRecyclerView();



        // Inflate the layout for this fragment
        return eventView;
    }

    private void setUpRecyclerView(){

        FirestoreRecyclerOptions<Event> options = new FirestoreRecyclerOptions.Builder<Event>()

                .build();

        adapter = new EventAdapter(options);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
