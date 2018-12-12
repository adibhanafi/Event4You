package com.example.adibhanafi.event4you.Fragment;


import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.adibhanafi.event4you.R;
import com.example.adibhanafi.event4you.Other.StateData;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private View homeView;
    private RecyclerView homeStateLists;
    private DatabaseReference stateRef;
    private String cuba;
    private StateFragment StateFragment;

    public HomeFragment() {
        // Required empty public constructor

    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        homeView = inflater.inflate(R.layout.fragment_home, container, false);

        homeStateLists = homeView.findViewById(R.id.recyclerViewHome);
        homeStateLists.setLayoutManager(new LinearLayoutManager(getContext()));

        stateRef = FirebaseDatabase.getInstance().getReference().child("State");

        return homeView;
    }

    @Override
    public void onStart() {
        super.onStart();
        StateFragment = new StateFragment();


        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<StateData>()
                .setQuery(stateRef, StateData.class)
                .build();

        FirebaseRecyclerAdapter<StateData, StateViewHolder> adapter
                = new FirebaseRecyclerAdapter<StateData, StateViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final StateViewHolder holder, int position, @NonNull StateData model) {
                cuba = getRef(position).getKey();
                stateRef.child(cuba).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String StateName = dataSnapshot.child("Name").getValue().toString();

                        holder.stateName.setText(StateName);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                final String key = getRef(position).getKey();
                holder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setFragment(getContext(), StateFragment);
                    }
                });


            }

            @NonNull
            @Override
            public StateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
                StateViewHolder viewHolder = new StateViewHolder(view);


                return viewHolder;
            }
        };
        homeStateLists.setAdapter(adapter);
        adapter.startListening();
    }

    private void setFragment(Context context, Fragment Fragment) {

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        FragmentTransaction replace = fragmentTransaction.replace(R.id.main_frame, Fragment);
        fragmentTransaction.commit();
    }

    public static class StateViewHolder extends RecyclerView.ViewHolder {
        TextView stateName;
        View mView;

        public StateViewHolder(View itemView) {
            super(itemView);

            stateName = itemView.findViewById(R.id.text_view_state);
            mView = itemView;
        }
    }
}
