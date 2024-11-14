package com.dam2.reto.ui.tablets;

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

public class TabletsFragment extends Fragment {

    private TabletsViewModel mViewModel;
    private ProductoAdapter tabletsAdapter;

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

        // Configuración de RecyclerView para tablets
        RecyclerView recyclerViewTablets = view.findViewById(R.id.recyclerViewSmartphones);
        recyclerViewTablets.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mViewModel.getTablets().observe(getViewLifecycleOwner(), productos -> {
            tabletsAdapter = new ProductoAdapter(getContext(), productos, new ProductoAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int productId) {
                    // Handle item click
                }

                @Override
                public void onAddToCartClick(int productId) {
                    // Handle add to cart click
                }
            });
            recyclerViewTablets.setAdapter(tabletsAdapter);
        });
    }
}