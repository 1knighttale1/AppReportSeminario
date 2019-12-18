package com.example.homes.Fragments;


import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.homes.ListActivity;
import com.example.homes.R;
import com.example.homes.SearchActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback {
    private View rootView;
    private GoogleMap mMap;



    public MapFragment() {
        // Required empty public constructor

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        rootView = inflater.inflate(R.layout.fragment_map, container,false);



        return rootView;
    }

    //Mapa de googleServices

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MapView map = rootView.findViewById(R.id.mapView2);

        if(map != null){
            map.onCreate(null);
            map.onResume();
            map.getMapAsync( this);
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        //-19.5678143,-65.7655377Michoza
        // Add a marker in Sydney and move the camera
        LatLng casa1 = new LatLng(-19.5678143, -65.7655377);
        //-19.560591,-65.7643413UATF
        LatLng casa2 = new LatLng(-19.56658,-65.76833);
        LatLng casa3 = new LatLng(-19.014136,-65.288828);
        LatLng casa4 = new LatLng(-19.017121,-65.292668);
        LatLng casa5 = new LatLng(-19.566085,-65.768759);

        mMap.addMarker(new MarkerOptions().position(casa1).title(""));
        mMap.addMarker(new MarkerOptions().position(casa2).title(""));
        mMap.addMarker(new MarkerOptions().position(casa3).title(""));
        mMap.addMarker(new MarkerOptions().position(casa4).title(""));
        mMap.addMarker(new MarkerOptions().position(casa5).title(""));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(potosi));
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(casa1)
                .zoom(15)
                .bearing(0)
                .tilt(45)
                .build();

        Geocoder geocoder = new Geocoder(rootView.getContext(), Locale.US);
        try {
           List<Address> direcciones = geocoder.getFromLocation(casa1.latitude, casa2.longitude, 1);
           if(direcciones.size()> 0){
               Toast.makeText(rootView.getContext(),""+ direcciones.get(0).getAddressLine(0),Toast.LENGTH_SHORT).show();

           }
        }catch (IOException illegalArgumentException){

        }
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        //click a la marca
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                return false;
            }
        });
    }


}
