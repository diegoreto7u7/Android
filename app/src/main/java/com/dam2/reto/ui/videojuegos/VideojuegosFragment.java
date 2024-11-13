package com.dam2.reto.ui.videojuegos;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dam2.reto.R;
import com.dam2.reto.ui.adapters.ProductoAdapter;
import com.dam2.reto.ui.parati.ParaTiViewModel;
import com.dam2.reto.ui.tablets.TabletsFragment;
import com.dam2.reto.ui.tablets.TabletsViewModel;

public class VideojuegosFragment extends Fragment {

    private TabletsViewModel mViewModel;
    private ProductoAdapter smartphonesAdapter;

    public static TabletsFragment newInstance() {
        return new TabletsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tablets, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mViewModel = new ViewModelProvider(this).get(TabletsViewModel.class);

        // Configuración de RecyclerView para smartphones
        RecyclerView recyclerViewSmartphones = view.findViewById(R.id.recyclerViewSmartphones);
        recyclerViewSmartphones.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mViewModel.getSmartphones().observe(getViewLifecycleOwner(), productos -> {
            smartphonesAdapter = new ProductoAdapter(getContext(), productos);
            recyclerViewSmartphones.setAdapter(smartphonesAdapter);
        });
    }

}