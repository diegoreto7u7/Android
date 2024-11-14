package com.dam2.reto.ui.parati;

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

public class ParaTiViewModel extends ViewModel {

    private final MutableLiveData<List<Map<String, Object>>> videojuegos = new MutableLiveData<>();
    private final MutableLiveData<List<Map<String, Object>>> consolas = new MutableLiveData<>();
    private final MutableLiveData<List<Map<String, Object>>> smartphones = new MutableLiveData<>();
    private final Context context;

    public ParaTiViewModel(Context context) {
        this.context = context;
        loadVideojuegos(context);
        loadConsolas(context);
        loadSmartphones(context);
    }

    public LiveData<List<Map<String, Object>>> getVideojuegos() {
        return videojuegos;
    }

    public LiveData<List<Map<String, Object>>> getConsolas() {
        return consolas;
    }

    public LiveData<List<Map<String, Object>>> getSmartphones() {
        return smartphones;
    }

    private void loadVideojuegos(Context context) {
        API api = RetrofitInstance.getRetrofitInstance(context).create(API.class);
        api.getVideojuegos().enqueue(new Callback<List<Map<String, Object>>>() {
            @Override
            public void onResponse(Call<List<Map<String, Object>>> call, Response<List<Map<String, Object>>> response) {
                if (response.isSuccessful()) {
                    videojuegos.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Map<String, Object>>> call, Throwable t) {
                videojuegos.setValue(new ArrayList<>());
            }
        });
    }

    private void loadConsolas(Context context) {
        API api = RetrofitInstance.getRetrofitInstance(context).create(API.class);
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

    private void loadSmartphones(Context context) {
        API api = RetrofitInstance.getRetrofitInstance(context).create(API.class);
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