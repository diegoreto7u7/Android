package com.dam2.reto.ui.galeria;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.dam2.reto.R;
import com.dam2.reto.databinding.FragmentNotificationsBinding;

import java.util.List;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;
    private NotificationsViewModel notificationsViewModel;
    private RecyclerView recyclerViewGalerias;
    private List<String> currentImages;
    private int currentIndex = 0;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel = new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerViewGalerias = binding.recyclerViewGalerias;
        setupRecyclerView();

        binding.buttonCloseCarousel.setOnClickListener(v -> closeCarousel());
        binding.buttonNextImage.setOnClickListener(v -> nextImage());
        binding.buttonPrevImage.setOnClickListener(v -> prevImage());

        return root;
    }

    private void setupRecyclerView() {
        // Observa el ViewModel para recibir los datos de las galerías
        notificationsViewModel.getGalerias().observe(getViewLifecycleOwner(), galerias -> {
            recyclerViewGalerias.setAdapter(new GaleriaAdapter(getContext(), galerias, new GaleriaAdapter.OnGalleryClickListener() {
                @Override
                public void onGalleryClick(List<String> images) {
                    openCarousel(images);
                }
            }));
        });
    }

    private void openCarousel(List<String> images) {
        currentImages = images;
        currentIndex = 0;
        binding.carouselContainer.setVisibility(View.VISIBLE);
        showImage();
    }

    private void closeCarousel() {
        binding.carouselContainer.setVisibility(View.GONE);
    }

    private void nextImage() {
        if (currentImages != null && !currentImages.isEmpty()) {
            currentIndex = (currentIndex + 1) % currentImages.size();
            showImage();
        }
    }

    private void prevImage() {
        if (currentImages != null && !currentImages.isEmpty()) {
            currentIndex = (currentIndex - 1 + currentImages.size()) % currentImages.size();
            showImage();
        }
    }

    private void showImage() {
        if (currentImages != null && !currentImages.isEmpty()) {
            // Carga la imagen actual en el ImageView del carrusel
            binding.carouselImage.setImageURI(Uri.parse(currentImages.get(currentIndex)));
            // Si estás cargando desde una URL, usa una biblioteca como Glide o Picasso
            // Glide.with(this).load(currentImages.get(currentIndex)).into(binding.carouselImage);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
