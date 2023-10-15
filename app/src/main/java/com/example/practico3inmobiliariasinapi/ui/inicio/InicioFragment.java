package com.example.practico3inmobiliariasinapi.ui.inicio;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.practico3inmobiliariasinapi.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class InicioFragment extends Fragment {
    private static final LatLng INMOBILIARIA = new LatLng(-33.2507732, -66.2269307);
    private OnMapReadyCallback callback = new OnMapReadyCallback() {
        @Override
        public void onMapReady(GoogleMap googleMap) {
            googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            CameraPosition pos = new CameraPosition.Builder()
                    .target(INMOBILIARIA)
                    .zoom(17f)
                    .build();
            googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(pos));
            googleMap.addMarker(new MarkerOptions()
                    .position(INMOBILIARIA)
                    .title("Inmobiliaria La Punta"));
        }
    };


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_inicio, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapa);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
}