package com.dam2.reto.ui.videojuegos;

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
    private final MutableLiveData<List<Map<String, Object>>> smartphones = new MutableLiveData<>();
    // TODO: Implement the ViewModel
    public VideojuegosViewModel() {
        loadSmartphones();
    }
    public LiveData<List<Map<String, Object>>> getSmartphones() {
        return smartphones;
    }

    private void loadSmartphones() {
        API api = RetrofitInstance.getRetrofitInstance().create(API.class);
        api.getSmartphones().enqueue(new Callback<List<Map<String, Object>>>() {
            @Override
            public void onResponse(Call<List<Map<String, Object>>> call, Response<List<Map<String, Object>>> response) {
                if (response.isSuccessful()) {
                    smartphones.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Map<String, Object>>> call, Throwable t) {
                smartphones.setValue(new ArrayList<>());
            }
        });
    }
}