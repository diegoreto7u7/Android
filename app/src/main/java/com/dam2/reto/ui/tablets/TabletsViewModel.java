package com.dam2.reto.ui.tablets;

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

public class TabletsViewModel extends ViewModel {
    private final MutableLiveData<List<Map<String, Object>>> topTablets = new MutableLiveData<>();
    private final MutableLiveData<List<Map<String, Object>>> todasTablets = new MutableLiveData<>();
    private final Context context;

    public TabletsViewModel(Context context) {
        this.context = context;
        loadTablets();
    }

    public LiveData<List<Map<String, Object>>> getTopTablets() {
        return topTablets;
    }

    public LiveData<List<Map<String, Object>>> getTodasTablets() {
        return todasTablets;
    }

    private void loadTablets() {
        API api = RetrofitInstance.getRetrofitInstance(context).create(API.class);
        api.getSmartphones().enqueue(new Callback<List<Map<String, Object>>>() { // Cambiar a getTablets()
            @Override
            public void onResponse(Call<List<Map<String, Object>>> call, Response<List<Map<String, Object>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Map<String, Object>> tabletsList = response.body();
                    todasTablets.setValue(tabletsList);

                    // Seleccionar los primeros 6 elementos para "Top Tablets"
                    List<Map<String, Object>> destacados = tabletsList.size() > 6 ? tabletsList.subList(0, 6) : tabletsList;
                    topTablets.setValue(destacados);
                } else {
                    // Si la respuesta no es exitosa, asignar listas vacías
                    todasTablets.setValue(new ArrayList<>());
                    topTablets.setValue(new ArrayList<>());
                }
            }

            @Override
            public void onFailure(Call<List<Map<String, Object>>> call, Throwable t) {
                // En caso de fallo, asignar listas vacías
                todasTablets.setValue(new ArrayList<>());
                topTablets.setValue(new ArrayList<>());
            }
        });
    }
}
