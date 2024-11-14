package com.dam2.reto.ui.consolas;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ConsolasViewModelFactory implements ViewModelProvider.Factory {
    private final Context context;

    public ConsolasViewModelFactory(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ConsolasViewModel.class)) {
            return (T) new ConsolasViewModel(context);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}