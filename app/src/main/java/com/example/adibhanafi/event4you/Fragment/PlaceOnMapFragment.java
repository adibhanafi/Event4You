package com.example.adibhanafi.event4you.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adibhanafi.event4you.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class PlaceOnMapFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private MapView mapView;
    private double lng;
    private double lat;
    private String name;
    private String address;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.map_layout, container, false);



        if (getArguments() != null) {
            lng = getArguments().getDouble("lng");
            lat = getArguments().getDouble("lat");
            name = getArguments().getString("name");
            address = getArguments().getString("address");
        }

//        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.gMap);
//        mapFragment.getMapAsync(this);
        try {
            MapsInitializer.initialize(this.getContext());
            mapView = view.findViewById(R.id.gMap);
            mapView.onCreate(savedInstanceState);
            mapView.getMapAsync(this);
        }catch (InflateException e){
            Log.e("PlaceOnMapFragment", "Inflated exception");
        }

        return view;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setMinZoomPreference(11);

        LatLng placeLoc = new LatLng(lat, lng);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(placeLoc)
                .title(name)
                .snippet(address)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));

        Marker m = mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(placeLoc));
    }
    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}
