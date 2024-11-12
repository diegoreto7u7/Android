package com.dam2.reto.ui.parati;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dam2.reto.R;
import com.dam2.reto.ui.adapters.ProductoAdapter;

import java.util.List;
import java.util.Map;

public class ParaTiFragment extends Fragment {

    private ParaTiViewModel mViewModel;
    private ProductoAdapter videojuegosAdapter;
    private ProductoAdapter consolasAdapter;
    private ProductoAdapter smartphonesAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_para_ti, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mViewModel = new ViewModelProvider(this).get(ParaTiViewModel.class);

        // Configuración de RecyclerView para videojuegos
        RecyclerView recyclerViewVideojuegos = view.findViewById(R.id.recyclerViewVideojuegos);
        recyclerViewVideojuegos.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mViewModel.getVideojuegos().observe(getViewLifecycleOwner(), productos -> {
            videojuegosAdapter = new ProductoAdapter(getContext(), productos);
            recyclerViewVideojuegos.setAdapter(videojuegosAdapter);
        });

        // Configuración de RecyclerView para consolas
        RecyclerView recyclerViewConsolas = view.findViewById(R.id.recyclerViewConsolas);
        recyclerViewConsolas.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mViewModel.getConsolas().observe(getViewLifecycleOwner(), productos -> {
            consolasAdapter = new ProductoAdapter(getContext(), productos);
            recyclerViewConsolas.setAdapter(consolasAdapter);
        });

        // Configuración de RecyclerView para smartphones
        RecyclerView recyclerViewSmartphones = view.findViewById(R.id.recyclerViewSmartphones);
        recyclerViewSmartphones.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mViewModel.getSmartphones().observe(getViewLifecycleOwner(), productos -> {
            smartphonesAdapter = new ProductoAdapter(getContext(), productos);
            recyclerViewSmartphones.setAdapter(smartphonesAdapter);
        });
    }
}