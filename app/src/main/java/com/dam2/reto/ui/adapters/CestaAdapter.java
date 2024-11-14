package com.dam2.reto.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dam2.reto.R;
import com.dam2.reto.ui.cesta.CestaViewModel;
import com.dam2.reto.ui.modelo.Producto;

import java.util.ArrayList;
import java.util.List;

public class CestaAdapter extends RecyclerView.Adapter<CestaAdapter.CestaViewHolder> {

    private final List<Producto> productos = new ArrayList<>();
    private final CestaViewModel viewModel;

    public CestaAdapter(CestaViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void setProductos(List<Producto> productos) {
        this.productos.clear();
        this.productos.addAll(productos);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CestaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cesta, parent, false);
        return new CestaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CestaViewHolder holder, int position) {
        Producto producto = productos.get(position);
        holder.productNameTextView.setText(producto.getNombreProducto());
        holder.productPriceTextView.setText("€" + producto.getPrecioVenta());

        // Configuración del botón para eliminar el producto
        holder.removeButton.setOnClickListener(v -> viewModel.removeProductFromCart(producto));
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    public static class CestaViewHolder extends RecyclerView.ViewHolder {
        TextView productNameTextView;
        TextView productPriceTextView;
        Button removeButton;
        ImageView productImageView;

        public CestaViewHolder(@NonNull View itemView) {
            super(itemView);
            productNameTextView = itemView.findViewById(R.id.nombreProductoTextView);
            productPriceTextView = itemView.findViewById(R.id.precioProductoTextView);
            removeButton = itemView.findViewById(R.id.removeButton);
            productImageView = itemView.findViewById(R.id.imagenProductoImageView);
        }
    }
}
