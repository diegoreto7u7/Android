package com.dam2.reto.ui.ubicacion;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.dam2.reto.R;
import com.dam2.reto.databinding.FragmentDashboardBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class DashboardFragment extends Fragment implements OnMapReadyCallback {

    private FragmentDashboardBinding binding;
    private MapView mapView;
    private GoogleMap googleMap;

    // Coordenadas de la Torre Iberdrola
    private static final LatLng TORRE_IBERDROLA = new LatLng(43.26798194009465, -2.938611777307392); // Cambia por las coordenadas correctas si es necesario

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Inicializar MapView
        mapView = binding.mapView;
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        return root;
    }

    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;
        googleMap.addMarker(new MarkerOptions().position(TORRE_IBERDROLA).title("Torre Iberdrola"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(TORRE_IBERDROLA, 15));
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
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
