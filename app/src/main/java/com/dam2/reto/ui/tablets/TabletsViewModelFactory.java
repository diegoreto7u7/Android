package com.dam2.reto.ui.tablets;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class TabletsViewModelFactory implements ViewModelProvider.Factory {
    private final Context context;

    public TabletsViewModelFactory(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(TabletsViewModel.class)) {
            return (T) new TabletsViewModel(context);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}