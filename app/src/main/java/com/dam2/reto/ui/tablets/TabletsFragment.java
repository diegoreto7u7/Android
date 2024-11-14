package com.dam2.reto.ui.tablets;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dam2.reto.R;
import com.dam2.reto.ui.adapters.ProductoAdapter;
import com.dam2.reto.ui.modelo.Producto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TabletsFragment extends Fragment {

    private TabletsViewModel mViewModel;
    private ProductoAdapter adapter;
    private RecyclerView recyclerView;
    private List<Producto> productos;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tablets, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewSmartphones);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new ProductoAdapter(getContext(), productos, new ProductoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int productId) {
                // Handle item click
            }

            @Override
            public void onAddToCartClick(Producto producto) {
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
        TabletsViewModelFactory factory = new TabletsViewModelFactory(context);
        mViewModel = new ViewModelProvider(this, factory).get(TabletsViewModel.class);

        mViewModel.getTablets().observe(getViewLifecycleOwner(), productosMap -> {
            List<Producto> productos = new ArrayList<>();
            for (Map<String, Object> map : productosMap) {
                Producto producto = new Producto();
                producto.setId(map.get("id") != null ? parseDouble(map.get("id")).intValue() : 0);
                producto.setNombreProducto((String) map.get("nombre"));
                producto.setPrecioVenta(map.get("precio") != null ? parseDouble(map.get("precio")) : 0.0);
                producto.setPrecioAlquiler(map.get("precio_alquiler") != null ? parseDouble(map.get("precio_alquiler")) : 0.0);
                producto.setDescripcion((String) map.get("descripcion"));
                productos.add(producto);
            }
            adapter.updateProductos(productos);
        });
    }

    private Double parseDouble(Object value) {
        if (value instanceof Double) {
            return (Double) value;
        } else if (value instanceof String) {
            try {
                return Double.parseDouble((String) value);
            } catch (NumberFormatException e) {
                return 0.0;
            }
        } else {
            return 0.0;
        }
    }
}