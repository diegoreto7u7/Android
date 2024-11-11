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
    private RecyclerView recyclerView;
    private ProductoAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_para_ti, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mViewModel = new ViewModelProvider(this).get(ParaTiViewModel.class);

        recyclerView = view.findViewById(R.id.recyclerViewProductos);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Observar los datos de videojuegos
        mViewModel.getVideojuegos().observe(getViewLifecycleOwner(), new Observer<List<Map<String, Object>>>() {
            @Override
            public void onChanged(List<Map<String, Object>> videojuegos) {
                if (videojuegos != null) {
                    adapter = new ProductoAdapter(getContext(), videojuegos);
                    recyclerView.setAdapter(adapter);
                }
            }
        });

        // Puedes añadir observadores similares para smartphones y consolas, o usar una lista combinada
    }
}
