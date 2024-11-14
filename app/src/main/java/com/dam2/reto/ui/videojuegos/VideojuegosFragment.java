package com.dam2.reto.ui.videojuegos;

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

public class VideojuegosFragment extends Fragment {

    private VideojuegosViewModel mViewModel;
    private ProductoAdapter topVideojuegosAdapter;
    private ProductoAdapter todosVideojuegosAdapter;

    public static VideojuegosFragment newInstance() {
        return new VideojuegosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_videojuegos, container, false);
        VideojuegosViewModelFactory factory = new VideojuegosViewModelFactory(requireContext());
        mViewModel = new ViewModelProvider(this, factory).get(VideojuegosViewModel.class);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Configurar RecyclerView para Top Videojuegos
        setupTopVideojuegosRecyclerView(view);

        // Configurar RecyclerView para Todos los Videojuegos
        setupTodosVideojuegosRecyclerView(view);
    }

    private void setupTopVideojuegosRecyclerView(View view) {
        RecyclerView recyclerViewTopVideojuegos = view.findViewById(R.id.recyclerViewTopVideojuegos);
        if (recyclerViewTopVideojuegos != null) {
            recyclerViewTopVideojuegos.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            mViewModel.getTopVideojuegos().observe(getViewLifecycleOwner(), productos -> {
                topVideojuegosAdapter = new ProductoAdapter(getContext(), productos, new ProductoAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int productId) {
                        navigateToProductDetail(productId);
                    }

                    @Override
                    public void onAddToCartClick(int productId) {
                        addToCart(productId);
                    }
                });
                recyclerViewTopVideojuegos.setAdapter(topVideojuegosAdapter);
            });
        }
    }

    private void setupTodosVideojuegosRecyclerView(View view) {
        RecyclerView recyclerViewTodosVideojuegos = view.findViewById(R.id.recyclerViewTodosVideojuegos);
        if (recyclerViewTodosVideojuegos != null) {
            // Cambia el número de columnas según el tamaño de la pantalla, si es necesario
            recyclerViewTodosVideojuegos.setLayoutManager(new GridLayoutManager(getContext(), 2));
            mViewModel.getTodosVideojuegos().observe(getViewLifecycleOwner(), productos -> {
                todosVideojuegosAdapter = new ProductoAdapter(getContext(), productos, new ProductoAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int productId) {
                        navigateToProductDetail(productId);
                    }

                    @Override
                    public void onAddToCartClick(int productId) {
                        addToCart(productId);
                    }
                });
                recyclerViewTodosVideojuegos.setAdapter(todosVideojuegosAdapter);
            });
        }
    }

    private void navigateToProductDetail(int productId) {
        // Implementar la navegación a la página de detalles del producto
    }

    private void addToCart(int productId) {
        // Implementar la lógica para agregar el producto al carrito
    }
}
