package com.dam2.reto.ui.tablets;

import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.dam2.reto.R;
import com.dam2.reto.ui.adapters.ProductoAdapter;

public class TabletsFragment extends Fragment {

    private TabletsViewModel mViewModel;
    private ProductoAdapter topTabletsAdapter;
    private ProductoAdapter todasTabletsAdapter;

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
        requireActivity().setTitle("Tablets");
        super.onViewCreated(view, savedInstanceState);

        TabletsViewModelFactory factory = new TabletsViewModelFactory(requireContext());
        mViewModel = new ViewModelProvider(this, factory).get(TabletsViewModel.class);

        // Configurar RecyclerView para Top Tablets
        setupTopTabletsRecyclerView(view);

        // Configurar RecyclerView para Todas las Tablets
        setupTodasTabletsRecyclerView(view);
    }

    private void setupTopTabletsRecyclerView(View view) {
        RecyclerView recyclerViewTopTablets = view.findViewById(R.id.recyclerViewTopTablets);
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

    private void setupTodasTabletsRecyclerView(View view) {
        RecyclerView recyclerViewTodasTablets = view.findViewById(R.id.recyclerViewTodasTablets);
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
