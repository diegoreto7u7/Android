package com.dam2.reto.ui.parati;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
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
    private ProductoAdapter videojuegosAdapter, consolasAdapter, smartphonesAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_para_ti, container, false);

        mViewModel = new ViewModelProvider(this, new ParaTiViewModelFactory(requireContext())).get(ParaTiViewModel.class);

        // Configuración del RecyclerView para Videojuegos
        RecyclerView recyclerViewVideojuegos = view.findViewById(R.id.recyclerViewVideojuegos);
        recyclerViewVideojuegos.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mViewModel.getVideojuegos().observe(getViewLifecycleOwner(), productos -> {
            videojuegosAdapter = new ProductoAdapter(getContext(), productos, new ProductoAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int productId) {
                    // Acción para ver detalles
                }

                @Override
                public void onAddToCartClick(int productId) {
                    // Acción para agregar al carrito
                }
            });
            recyclerViewVideojuegos.setAdapter(videojuegosAdapter);
        });

        // Configuración del RecyclerView para Consolas
        RecyclerView recyclerViewConsolas = view.findViewById(R.id.recyclerViewConsolas);
        recyclerViewConsolas.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mViewModel.getConsolas().observe(getViewLifecycleOwner(), productos -> {
            consolasAdapter = new ProductoAdapter(getContext(), productos, new ProductoAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int productId) {
                    // Acción para ver detalles
                }

                @Override
                public void onAddToCartClick(int productId) {
                    // Acción para agregar al carrito
                }
            });
            recyclerViewConsolas.setAdapter(consolasAdapter);
        });

        // Configuración del RecyclerView para Smartphones
        RecyclerView recyclerViewSmartphones = view.findViewById(R.id.recyclerViewSmartphones);
        recyclerViewSmartphones.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mViewModel.getSmartphones().observe(getViewLifecycleOwner(), productos -> {
            smartphonesAdapter = new ProductoAdapter(getContext(), productos, new ProductoAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int productId) {
                    // Acción para ver detalles
                }

                @Override
                public void onAddToCartClick(int productId) {
                    // Acción para agregar al carrito
                }
            });
            recyclerViewSmartphones.setAdapter(smartphonesAdapter);
        });

        return view;
    }
}
