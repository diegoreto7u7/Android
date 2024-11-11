package com.dam2.reto.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dam2.reto.R;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Map;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder> {

    private final List<Map<String, Object>> productos;
    private final Context context;

    public ProductoAdapter(Context context, List<Map<String, Object>> productos) {
        this.context = context;
        this.productos = productos;
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
        holder.precioProductoTextView.setText("Precio: $" + producto.get("precio_venta"));
        holder.descripcionTextView.setText((String) producto.get("descripcion"));

        String imagenUrl = (String) producto.get("imagenes");
        if (imagenUrl != null && !imagenUrl.isEmpty()) {
            String[] imagenes = new Gson().fromJson(imagenUrl, String[].class);
            if (imagenes.length > 0) {
                Picasso.get().load(imagenes[0]).into(holder.imagenProductoImageView);
            }
        } else {
            holder.imagenProductoImageView.setImageResource(R.drawable.ic_launcher_background);
        }
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    public static class ProductoViewHolder extends RecyclerView.ViewHolder {
        TextView nombreProductoTextView;
        TextView precioProductoTextView;
        TextView descripcionTextView;
        ImageView imagenProductoImageView;

        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreProductoTextView = itemView.findViewById(R.id.nombreProductoTextView);
            precioProductoTextView = itemView.findViewById(R.id.precioProductoTextView);
            descripcionTextView = itemView.findViewById(R.id.descripcionTextView);
            imagenProductoImageView = itemView.findViewById(R.id.imagenProductoImageView);
        }
    }
}