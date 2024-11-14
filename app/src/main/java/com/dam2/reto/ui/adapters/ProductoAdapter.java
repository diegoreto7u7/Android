package com.dam2.reto.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dam2.reto.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder> {

    private final List<Map<String, Object>> productos;
    private final Context context;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int productId);
        void onAddToCartClick(int productId);
    }

    public ProductoAdapter(Context context, List<Map<String, Object>> productos, OnItemClickListener listener) {
        this.context = context;
        this.productos = productos != null ? new ArrayList<>(productos) : new ArrayList<>();
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
        Map<String, Object> producto = productos.get(position);
        holder.nombreProductoTextView.setText((String) producto.get("nombre_producto"));
        holder.precioProductoTextView.setText("Precio: €" + producto.get("precio_venta"));

        String imagen = (String) producto.get("imagen");
        if (imagen != null && !imagen.isEmpty()) {
            holder.progressBar.setVisibility(View.VISIBLE);
            Picasso.get()
                    .load(imagen)
                    .error(R.drawable.kirby1)
                    .into(holder.imagenProductoImageView, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {
                            holder.progressBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError(Exception e) {
                            holder.progressBar.setVisibility(View.GONE);
                        }
                    });
        } else {
            holder.imagenProductoImageView.setImageResource(R.drawable.hola);
            holder.progressBar.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(v -> {
            Object idObj = producto.get("id");
            int productId = idObj instanceof Double ? ((Double) idObj).intValue() : (Integer) idObj;
            listener.onItemClick(productId);
        });

        holder.addToCartButton.setOnClickListener(v -> {
            Object idObj = producto.get("id");
            int productId = idObj instanceof Double ? ((Double) idObj).intValue() : (Integer) idObj;
            listener.onAddToCartClick(productId);
        });
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    public static class ProductoViewHolder extends RecyclerView.ViewHolder {
        TextView nombreProductoTextView;
        TextView precioProductoTextView;
        ImageView imagenProductoImageView;
        ProgressBar progressBar;
        ImageButton addToCartButton;

        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreProductoTextView = itemView.findViewById(R.id.nombreProductoTextView);
            precioProductoTextView = itemView.findViewById(R.id.precioProductoTextView);
            imagenProductoImageView = itemView.findViewById(R.id.imagenProductoImageView);
            progressBar = itemView.findViewById(R.id.progressBar);
            addToCartButton = itemView.findViewById(R.id.addToCartButton);
        }
    }
}