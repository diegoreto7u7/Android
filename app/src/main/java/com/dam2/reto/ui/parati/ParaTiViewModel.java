package com.dam2.reto.ui.parati;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dam2.reto.ui.retrofit.API;
import com.dam2.reto.ui.retrofit.RetrofitInstance;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ParaTiViewModel extends ViewModel {

    private MutableLiveData<List<Map<String, Object>>> videojuegos;

    public ParaTiViewModel() {
        videojuegos = new MutableLiveData<>();
        loadVideojuegos();
    }

    public LiveData<List<Map<String, Object>>> getVideojuegos() {
        return videojuegos;
    }

    private void loadVideojuegos() {
        API api = RetrofitInstance.getAPI();
        Call<List<Map<String, Object>>> call = api.getVideojuegos();
        call.enqueue(new Callback<List<Map<String, Object>>>() {
            @Override
            public void onResponse(Call<List<Map<String, Object>>> call, Response<List<Map<String, Object>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    videojuegos.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Map<String, Object>>> call, Throwable t) {
                // Handle failure
            }
        });
    }
}