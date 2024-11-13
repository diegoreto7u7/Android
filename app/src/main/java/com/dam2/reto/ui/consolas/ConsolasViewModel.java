package com.dam2.reto.ui.consolas;

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
    private final MutableLiveData<List<Map<String, Object>>> consolas = new MutableLiveData<>();
    public ConsolasViewModel() {
        loadConsolas();
    }
    public LiveData<List<Map<String, Object>>> getConsolas() {
        return consolas;
    }
    private void loadConsolas() {
        API api = RetrofitInstance.getRetrofitInstance().create(API.class);
        api.getConsolas().enqueue(new Callback<List<Map<String, Object>>>() {
            @Override
            public void onResponse(Call<List<Map<String, Object>>> call, Response<List<Map<String, Object>>> response) {
                if (response.isSuccessful()) {
                    consolas.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Map<String, Object>>> call, Throwable t) {
                consolas.setValue(new ArrayList<>());
            }
        });
    }
    // TODO: Implement the ViewModel

}