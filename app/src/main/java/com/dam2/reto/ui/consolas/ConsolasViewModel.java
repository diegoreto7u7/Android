package com.dam2.reto.ui.consolas;

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

public class ConsolasViewModel extends ViewModel {
    private final MutableLiveData<List<Map<String, Object>>> topConsolas = new MutableLiveData<>();
    private final MutableLiveData<List<Map<String, Object>>> todasConsolas = new MutableLiveData<>();
    private final Context context;

    public ConsolasViewModel(Context context) {
        this.context = context;
        loadConsolas();
    }

    public LiveData<List<Map<String, Object>>> getTopConsolas() {
        return topConsolas;
    }

    public LiveData<List<Map<String, Object>>> getTodasConsolas() {
        return todasConsolas;
    }

    private void loadConsolas() {
        API api = RetrofitInstance.getRetrofitInstance(context).create(API.class);
        api.getConsolas().enqueue(new Callback<List<Map<String, Object>>>() {
            @Override
            public void onResponse(Call<List<Map<String, Object>>> call, Response<List<Map<String, Object>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Map<String, Object>> consolasList = response.body();
                    todasConsolas.setValue(consolasList);

                    // Seleccionar los primeros 6 elementos para "Top Consolas"
                    List<Map<String, Object>> destacados = consolasList.size() > 6 ? consolasList.subList(0, 6) : consolasList;
                    topConsolas.setValue(destacados);
                } else {
                    // Si la respuesta no es exitosa, asignar listas vacías
                    todasConsolas.setValue(new ArrayList<>());
                    topConsolas.setValue(new ArrayList<>());
                }
            }

            @Override
            public void onFailure(Call<List<Map<String, Object>>> call, Throwable t) {
                // En caso de fallo en la carga, asignar listas vacías
                todasConsolas.setValue(new ArrayList<>());
                topConsolas.setValue(new ArrayList<>());
            }
        });
    }
}
