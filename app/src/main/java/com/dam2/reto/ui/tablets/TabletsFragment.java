// TabletsFragment.java

package com.dam2.reto.ui.tablets;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dam2.reto.R;
import com.dam2.reto.ui.adapters.ProductoAdapter;

public class TabletsFragment extends Fragment {

    private ProductoAdapter topTabletsAdapter;
    private ProductoAdapter todasTabletsAdapter;
    private TabletsViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tablets, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(TabletsViewModel.class);
        setupTopTabletsRecyclerView(view);
        setupTodasTabletsRecyclerView(view);
    }

    private void setupTopTabletsRecyclerView(View view) {
        RecyclerView recyclerViewTopTablets = view.findViewById(R.id.recyclerViewTopTablets);
        if (recyclerViewTopTablets != null) {
            recyclerViewTopTablets.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            mViewModel.getTopTablets().observe(getViewLifecycleOwner(), productos -> {
                topTabletsAdapter = new ProductoAdapter(getContext(), productos, new ProductoAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int productId) {
                        // Acción al hacer clic en una tablet destacada
                    }

                    @Override
                    public void onAddToCartClick(int productId) {
                        // Acción al agregar una tablet destacada al carrito
                    }
                });
                recyclerViewTopTablets.setAdapter(topTabletsAdapter);
            });
        }
    }

    private void setupTodasTabletsRecyclerView(View view) {
        RecyclerView recyclerViewTodasTablets = view.findViewById(R.id.recyclerViewTodasTablets);
        if (recyclerViewTodasTablets != null) {
            recyclerViewTodasTablets.setLayoutManager(new GridLayoutManager(getContext(), 2)); // Cuadrícula con 2 columnas
            mViewModel.getTodasTablets().observe(getViewLifecycleOwner(), productos -> {
                todasTabletsAdapter = new ProductoAdapter(getContext(), productos, new ProductoAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int productId) {
                        // Acción al hacer clic en una tablet
                    }

                    @Override
                    public void onAddToCartClick(int productId) {
                        // Acción al agregar una tablet al carrito
                    }
                });
                recyclerViewTodasTablets.setAdapter(todasTabletsAdapter);
            });
        }
    }
}