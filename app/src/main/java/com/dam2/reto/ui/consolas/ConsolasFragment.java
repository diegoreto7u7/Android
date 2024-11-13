package com.dam2.reto.ui.consolas;

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

public class ConsolasFragment extends Fragment {

    private ConsolasViewModel mViewModel;
    private ProductoAdapter consolasAdapter;

    public static ConsolasFragment newInstance() {
        return new ConsolasFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_consolas, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        requireActivity().setTitle("Consolas");
        super.onViewCreated(view, savedInstanceState);

        mViewModel = new ViewModelProvider(this).get(ConsolasViewModel.class);

        // Configuración de RecyclerView para consolas
        RecyclerView recyclerViewConsolas = view.findViewById(R.id.recyclerViewConsolas);
        recyclerViewConsolas.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mViewModel.getConsolas().observe(getViewLifecycleOwner(), productos -> {
            consolasAdapter = new ProductoAdapter(getContext(), productos);
            recyclerViewConsolas.setAdapter(consolasAdapter);
        });

    }

}