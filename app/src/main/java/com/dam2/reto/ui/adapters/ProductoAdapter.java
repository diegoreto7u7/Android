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
import com.squareup.picasso.Callback;
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

        // Establecer nombre y precio del producto
        holder.nombreProductoTextView.setText((String) producto.get("nombre_producto"));
        holder.precioProductoTextView.setText("Compra: €" + producto.get("precio_venta"));

        // Cargar imagen con Picasso y mostrar ProgressBar mientras carga
        holder.progressBar.setVisibility(View.VISIBLE);
        Picasso.get()
                .load((String) producto.get("imagen"))
                .placeholder(R.drawable.ic_launcher_background)  // Placeholder mientras carga
                .error(R.drawable.ic_launcher_background)        // Imagen de error
                .into(holder.imagenProductoImageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        holder.progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Exception e) {
                        holder.progressBar.setVisibility(View.GONE);
                    }
                });

        // Configurar eventos de clic
        holder.itemView.setOnClickListener(v -> {
            Object idObject = producto.get("id");
            int id = (idObject instanceof Double) ? ((Double) idObject).intValue() : (Integer) idObject;
            listener.onItemClick(id);
        });
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    public static class ProductoViewHolder extends RecyclerView.ViewHolder {
        ImageView imagenProductoImageView;
        TextView nombreProductoTextView;
        TextView precioProductoTextView;
        ImageButton addToCartButton;
        ProgressBar progressBar;

        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            imagenProductoImageView = itemView.findViewById(R.id.imagenProductoImageView);
            nombreProductoTextView = itemView.findViewById(R.id.nombreProductoTextView);
            precioProductoTextView = itemView.findViewById(R.id.precioProductoTextView);
            addToCartButton = itemView.findViewById(R.id.addToCartButton);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }
}
