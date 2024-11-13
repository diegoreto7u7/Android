package com.dam2.reto.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dam2.reto.R;
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
        // Inflar el layout del producto
        View view = LayoutInflater.from(context).inflate(R.layout.item_producto, parent, false);
        return new ProductoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        Map<String, Object> producto = productos.get(position);

        holder.nombreProductoTextView.setText((String) producto.get("nombre_producto"));
        holder.precioProductoTextView.setText("Precio: €" + producto.get("precio_venta"));

        List<String> imagenes = (List<String>) producto.get("imagenes");
        if (imagenes != null && !imagenes.isEmpty()) {
            holder.progressBar.setVisibility(View.VISIBLE); // Mostrar el ProgressBar antes de cargar
            Picasso.get()
                    .load(imagenes.get(0))
                    .error(R.drawable.kirby1)
                    .into(holder.imagenProductoImageView, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {
                            holder.progressBar.setVisibility(View.GONE); // Ocultar el ProgressBar al cargar
                        }

                        @Override
                        public void onError(Exception e) {
                            holder.progressBar.setVisibility(View.GONE); // Ocultar el ProgressBar en caso de error
                        }
                    });
        } else {
            holder.imagenProductoImageView.setImageResource(R.drawable.kirby1);
            holder.progressBar.setVisibility(View.GONE); // Ocultar el ProgressBar si no hay imagen
        }
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    // ViewHolder para los elementos del producto
    public static class ProductoViewHolder extends RecyclerView.ViewHolder {
        TextView nombreProductoTextView;
        TextView precioProductoTextView;
        ImageView imagenProductoImageView;
        ProgressBar progressBar;

        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreProductoTextView = itemView.findViewById(R.id.nombreProductoTextView);
            precioProductoTextView = itemView.findViewById(R.id.precioProductoTextView);
            imagenProductoImageView = itemView.findViewById(R.id.imagenProductoImageView);
            progressBar = itemView.findViewById(R.id.progressBar); // Añadimos el ProgressBar
        }
    }
}
