package com.example.adibhanafi.event4you.Fragment;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adibhanafi.event4you.Other.PlacesRecyclerViewAdapter;
import com.example.adibhanafi.event4you.R;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.PlaceLikelihood;
import com.google.android.gms.location.places.PlaceLikelihoodBufferResponse;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NearbyFragment extends Fragment {

    private View NearbyView;
    public NearbyFragment() {
        // Required empty public constructor
    }
    public static final String TAG = "CurrentLocNearByPlaces";
    private static final int LOC_REQ_CODE = 1;

    protected GeoDataClient geoDataClient;
    protected PlaceDetectionClient placeDetectionClient;
    protected RecyclerView recyclerView;
    private PlacesRecyclerViewAdapter defaultAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        NearbyView = inflater.inflate(R.layout.fragment_nearby, container, false);
        recyclerView = NearbyView.findViewById(R.id.places_lst);

        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), 1, true));
        List<Place> _DefaultAdapter = new ArrayList<>();

        defaultAdapter = new PlacesRecyclerViewAdapter( _DefaultAdapter , getContext());
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), 1));
        recyclerView.setAdapter(defaultAdapter);

        placeDetectionClient = Places.getPlaceDetectionClient(recyclerView.getContext());

        getCurrentPlaceItems();

        return NearbyView;
    }


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        recyclerView = recyclerView.findViewById();
//
//        LinearLayoutManager recyclerLayoutManager =
//                new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(recyclerLayoutManager);
//
//        DividerItemDecoration dividerItemDecoration =
//                new DividerItemDecoration(recyclerView.getContext(),
//                        recyclerLayoutManager.getOrientation());
//        recyclerView.addItemDecoration(dividerItemDecoration);
//
//        placeDetectionClient = Places.getPlaceDetectionClient(this, null);
//
//        getCurrentPlaceItems();
//
//    }

    private void getCurrentPlaceItems() {
        if (isLocationAccessPermitted()) {
            getCurrentPlaceData();
        } else {
            requestLocationAccessPermission();
        }
    }

    @SuppressLint("MissingPermission")
    private void getCurrentPlaceData() {
        Task<PlaceLikelihoodBufferResponse> placeResult = placeDetectionClient.
                getCurrentPlace(null);
        placeResult.addOnCompleteListener(new OnCompleteListener<PlaceLikelihoodBufferResponse>() {
            @Override
            public void onComplete(@NonNull Task<PlaceLikelihoodBufferResponse> task) {
                Log.d(TAG, "current location places info");
                List<Place> placesList = new ArrayList<Place>();
                PlaceLikelihoodBufferResponse likelyPlaces = task.getResult();
                for (PlaceLikelihood placeLikelihood : likelyPlaces) {
                    placesList.add(placeLikelihood.getPlace().freeze());
                }
                likelyPlaces.release();

                Log.d(TAG, placesList.toString());
                PlacesRecyclerViewAdapter recyclerViewAdapter = new
                        PlacesRecyclerViewAdapter(placesList,
                        getContext());
                recyclerView.setAdapter(recyclerViewAdapter);
                recyclerViewAdapter.notifyDataSetChanged();
            }
        });
    }

    private boolean isLocationAccessPermitted() {
        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return false;
        } else {
            return true;
        }
    }

    private void requestLocationAccessPermission() {
        ActivityCompat.requestPermissions(this.getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                LOC_REQ_CODE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == LOC_REQ_CODE) {
            if (resultCode == -1) {
                getCurrentPlaceData();
            }
        }
    }

}
