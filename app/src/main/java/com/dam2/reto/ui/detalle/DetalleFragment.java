package com.dam2.reto.ui.detalle;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dam2.reto.R;
import com.dam2.reto.ui.modelo.Producto;

public class DetalleFragment extends Fragment {

    private DetalleViewModel mViewModel;
    private TextView nombreProductoTextView, precioVentaTextView, precioAlquilerTextView, descripcionTextView;

    private static final String ARG_PRODUCT_ID = "product_id";

    public static DetalleFragment newInstance(int productId) {
        DetalleFragment fragment = new DetalleFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PRODUCT_ID, productId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalle, container, false);

        nombreProductoTextView = view.findViewById(R.id.nombreProductoTextView);
        precioVentaTextView = view.findViewById(R.id.precioVentaTextView);
        precioAlquilerTextView = view.findViewById(R.id.precioAlquilerTextView);
        descripcionTextView = view.findViewById(R.id.descripcionTextView);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DetalleViewModel.class);

        if (getArguments() != null) {
            int productId = getArguments().getInt(ARG_PRODUCT_ID);
            mViewModel.loadProductoById(productId, getContext());
        }

        mViewModel.getProducto().observe(getViewLifecycleOwner(), new Observer<Producto>() {
            @Override
            public void onChanged(Producto producto) {
                nombreProductoTextView.setText(producto.getNombreProducto());
                precioVentaTextView.setText("Precio Venta: €" + producto.getPrecioVenta());
                precioAlquilerTextView.setText("Precio Alquiler: €" + producto.getPrecioAlquiler());
                descripcionTextView.setText(producto.getDescripcion());
            }
        });
    }
}
