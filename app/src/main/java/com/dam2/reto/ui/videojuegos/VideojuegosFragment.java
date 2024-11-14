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

public class VideojuegosFragment extends Fragment {

    private VideojuegosViewModel mViewModel;
    private ProductoAdapter videojuegosAdapter;

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

        RecyclerView recyclerViewVideojuegos = view.findViewById(R.id.recyclerViewVideojuegos);
        if (recyclerViewVideojuegos != null) {
            recyclerViewVideojuegos.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            mViewModel.getVideojuegos().observe(getViewLifecycleOwner(), productos -> {
                videojuegosAdapter = new ProductoAdapter(getContext(), productos, new ProductoAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int productId) {
                        navigateToProductDetail(productId);
                    }

                    @Override
                    public void onAddToCartClick(int productId) {
                        addToCart(productId);
                    }
                });
                recyclerViewVideojuegos.setAdapter(videojuegosAdapter);
            });
        }
    }

    private void navigateToProductDetail(int productId) {
        // Implement navigation to product detail page
    }

    private void addToCart(int productId) {
        // Implement logic to add product to cart
    }
}