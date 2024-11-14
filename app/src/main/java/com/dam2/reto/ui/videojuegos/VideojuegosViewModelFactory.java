package com.dam2.reto.ui.videojuegos;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class VideojuegosViewModelFactory implements ViewModelProvider.Factory {
    private final Context context;

    public VideojuegosViewModelFactory(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(VideojuegosViewModel.class)) {
            return (T) new VideojuegosViewModel(context);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}