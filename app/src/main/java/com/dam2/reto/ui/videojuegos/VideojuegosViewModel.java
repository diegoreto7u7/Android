package com.dam2.reto.ui.videojuegos;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dam2.reto.ui.retrofit.API;
import com.dam2.reto.ui.retrofit.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideojuegosViewModel extends ViewModel {
    private final MutableLiveData<List<Map<String, Object>>> topVideojuegos = new MutableLiveData<>();
    private final MutableLiveData<List<Map<String, Object>>> todosVideojuegos = new MutableLiveData<>();
    private final Context context;

    public VideojuegosViewModel(Context context) {
        this.context = context;
        loadVideojuegos();
    }

    public LiveData<List<Map<String, Object>>> getTopVideojuegos() {
        return topVideojuegos;
    }

    public LiveData<List<Map<String, Object>>> getTodosVideojuegos() {
        return todosVideojuegos;
    }

    private void loadVideojuegos() {
        API api = RetrofitInstance.getRetrofitInstance(context).create(API.class);
        api.getVideojuegos().enqueue(new Callback<List<Map<String, Object>>>() {
            @Override
            public void onResponse(Call<List<Map<String, Object>>> call, Response<List<Map<String, Object>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Map<String, Object>> videojuegosList = response.body();
                    todosVideojuegos.setValue(videojuegosList);

                    // Seleccionar los primeros 6 videojuegos como destacados
                    List<Map<String, Object>> destacados = videojuegosList.size() > 6 ? videojuegosList.subList(0, 6) : videojuegosList;
                    topVideojuegos.setValue(destacados);
                } else {
                    // En caso de respuesta no exitosa, asignar lista vacía
                    topVideojuegos.setValue(new ArrayList<>());
                    todosVideojuegos.setValue(new ArrayList<>());
                }
            }

            @Override
            public void onFailure(Call<List<Map<String, Object>>> call, Throwable t) {
                // En caso de fallo, asignar listas vacías
                topVideojuegos.setValue(new ArrayList<>());
                todosVideojuegos.setValue(new ArrayList<>());
            }
        });
    }
}
