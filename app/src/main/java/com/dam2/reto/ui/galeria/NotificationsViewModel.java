package com.dam2.reto.ui.galeria;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dam2.reto.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NotificationsViewModel extends ViewModel {

    private final MutableLiveData<List<Map<String, Object>>> galerias;

    public NotificationsViewModel() {
        galerias = new MutableLiveData<>();
        loadGalerias();
    }

    public LiveData<List<Map<String, Object>>> getGalerias() {
        return galerias;
    }

    private void loadGalerias() {
        List<Map<String, Object>> galleryList = new ArrayList<>();

        // Ejemplo de galerías usando recursos locales en drawable
        galleryList.add(createGallery(
                "Galería 1",
                "Descripción de la Galería 1",
                R.drawable.asd, // Miniatura
                List.of(R.drawable.asd, R.drawable.sda) // Imágenes en el carrusel
        ));

        galleryList.add(createGallery(
                "Galería 2",
                "Descripción de la Galería 2",
                R.drawable.esd, // Miniatura
                List.of(R.drawable.esd, R.drawable.ds) // Imágenes en el carrusel
        ));

        galerias.setValue(galleryList);
    }

    private Map<String, Object> createGallery(String name, String description, int thumbnail, List<Integer> images) {
        return Map.of(
                "name", name,
                "description", description,
                "thumbnail", thumbnail,
                "images", images
        );
    }
}
