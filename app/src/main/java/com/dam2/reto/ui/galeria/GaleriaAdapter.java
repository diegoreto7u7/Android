package com.dam2.reto.ui.galeria;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.dam2.reto.R;

import java.util.List;
import java.util.Map;

public class GaleriaAdapter extends RecyclerView.Adapter<GaleriaAdapter.GaleriaViewHolder> {

    private final List<Map<String, Object>> galerias;
    private final OnGalleryClickListener listener;
    private final Context context;

    public GaleriaAdapter(Context context, List<Map<String, Object>> galerias, OnGalleryClickListener listener) {
        this.context = context;
        this.galerias = galerias;
        this.listener = listener;
    }

    @NonNull
    @Override
    public GaleriaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_gallery, parent, false);
        return new GaleriaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GaleriaViewHolder holder, int position) {
        Map<String, Object> gallery = galerias.get(position);
        holder.galleryName.setText((String) gallery.get("name"));
        holder.galleryDescription.setText((String) gallery.get("description"));

        // Cargar la miniatura de la galería (thumbnail)
        String thumbnailUrl = (String) gallery.get("thumbnail");
        // Aquí puedes usar Glide o Picasso para cargar la imagen desde una URL, por ejemplo:
        // Glide.with(context).load(thumbnailUrl).into(holder.galleryThumbnail);

        // Configurar el clic en la galería para abrir el carrusel de imágenes
        List<String> images = (List<String>) gallery.get("images");
        holder.itemView.setOnClickListener(v -> listener.onGalleryClick(images));
    }

    @Override
    public int getItemCount() {
        return galerias.size();
    }

    public interface OnGalleryClickListener {
        void onGalleryClick(List<String> images);
    }

    static class GaleriaViewHolder extends RecyclerView.ViewHolder {
        TextView galleryName, galleryDescription;
        ImageView galleryThumbnail;

        public GaleriaViewHolder(@NonNull View itemView) {
            super(itemView);
            galleryName = itemView.findViewById(R.id.galleryName);
            galleryDescription = itemView.findViewById(R.id.galleryDescription);
            galleryThumbnail = itemView.findViewById(R.id.galleryThumbnail);
        }
    }
}
