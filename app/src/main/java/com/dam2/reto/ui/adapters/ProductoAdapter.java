package com.dam2.reto.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dam2.reto.R;
import com.dam2.reto.ui.modelo.Producto;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder> {
    private Context context;
    private List<Producto> productos;
    private OnItemClickListener listener;

    public ProductoAdapter(Context context, List<Producto> productos, OnItemClickListener listener) {
        this.context = context;
        this.productos = productos != null ? productos : new ArrayList<>();
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_producto, parent, false);
        return new ProductoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        Producto producto = productos.get(position);
        holder.nombreProductoTextView.setText(producto.getNombreProducto());
        holder.precioProductoTextView.setText(String.valueOf(producto.getPrecioVenta()));
        Picasso.get().load(producto.getImagen()).into(holder.imagenProductoImageView);
        holder.addToCartButton.setOnClickListener(v -> listener.onAddToCartClick(producto));
        holder.itemView.setOnClickListener(v -> listener.onItemClick(producto.getId()));
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    public void updateProductos(List<Producto> newProductos) {
        productos.clear();
        productos.addAll(newProductos);
        notifyDataSetChanged();
    }

    public static class ProductoViewHolder extends RecyclerView.ViewHolder {
        TextView nombreProductoTextView;
        TextView precioProductoTextView;
        ImageView imagenProductoImageView;
        ImageButton addToCartButton;

        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreProductoTextView = itemView.findViewById(R.id.nombreProductoTextView);
            precioProductoTextView = itemView.findViewById(R.id.precioProductoTextView);
            imagenProductoImageView = itemView.findViewById(R.id.imagenProductoImageView);
            addToCartButton = itemView.findViewById(R.id.addToCartButton);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int productId);
        void onAddToCartClick(Producto producto);
    }
}