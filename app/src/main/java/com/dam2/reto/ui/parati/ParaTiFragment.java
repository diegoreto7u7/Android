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
    private ProductoAdapter videojuegosAdapter;
    private ProductoAdapter consolasAdapter;
    private ProductoAdapter smartphonesAdapter;
    private RecyclerView recyclerView;
    private ProductoAdapter adapter;
    private List<Map<String, Object>> productos;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_para_ti, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new ProductoAdapter(getContext(), productos, new ProductoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int productId) {
                NavController navController = Navigation.findNavController(view);
                if (navController.getCurrentDestination().getId() == R.id.paraTiFragment) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("product_id", productId);
                    navController.navigate(R.id.action_paraTiFragment_to_detalleFragment, bundle);
                }
            }

            @Override
            public void onAddToCartClick(int productId) {
                // Handle add to cart click
            }
        });

        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Context context = requireContext();
        ParaTiViewModelFactory factory = new ParaTiViewModelFactory(context);
        mViewModel = new ViewModelProvider(this, factory).get(ParaTiViewModel.class);

        // Configuración de RecyclerView para videojuegos
        RecyclerView recyclerViewVideojuegos = view.findViewById(R.id.recyclerViewVideojuegos);
        recyclerViewVideojuegos.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mViewModel.getVideojuegos().observe(getViewLifecycleOwner(), productos -> {
            videojuegosAdapter = new ProductoAdapter(getContext(), productos, new ProductoAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int productId) {
                    NavController navController = Navigation.findNavController(view);
                    if (navController.getCurrentDestination().getId() == R.id.paraTiFragment) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("product_id", productId);
                        navController.navigate(R.id.action_paraTiFragment_to_detalleFragment, bundle);
                    }
                }

                @Override
                public void onAddToCartClick(int productId) {
                    // Handle add to cart click
                }
            });
            recyclerViewVideojuegos.setAdapter(videojuegosAdapter);
        });

        // Configuración de RecyclerView para consolas
        RecyclerView recyclerViewConsolas = view.findViewById(R.id.recyclerViewConsolas);
        recyclerViewConsolas.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mViewModel.getConsolas().observe(getViewLifecycleOwner(), productos -> {
            consolasAdapter = new ProductoAdapter(getContext(), productos, new ProductoAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int productId) {
                    NavController navController = Navigation.findNavController(view);
                    if (navController.getCurrentDestination().getId() == R.id.paraTiFragment) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("product_id", productId);
                        navController.navigate(R.id.action_paraTiFragment_to_detalleFragment, bundle);
                    }
                }

                @Override
                public void onAddToCartClick(int productId) {
                    // Handle add to cart click
                }
            });
            recyclerViewConsolas.setAdapter(consolasAdapter);
        });

        // Configuración de RecyclerView para smartphones
        RecyclerView recyclerViewSmartphones = view.findViewById(R.id.recyclerViewSmartphones);
        recyclerViewSmartphones.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mViewModel.getSmartphones().observe(getViewLifecycleOwner(), productos -> {
            smartphonesAdapter = new ProductoAdapter(getContext(), productos, new ProductoAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int productId) {
                    NavController navController = Navigation.findNavController(view);
                    if (navController.getCurrentDestination().getId() == R.id.paraTiFragment) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("product_id", productId);
                        navController.navigate(R.id.action_paraTiFragment_to_detalleFragment, bundle);
                    }
                }

                @Override
                public void onAddToCartClick(int productId) {
                    // Handle add to cart click
                }
            });
            recyclerViewSmartphones.setAdapter(smartphonesAdapter);
        });
    }
}