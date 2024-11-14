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
    private final Context context;

    private final MutableLiveData<List<Map<String, Object>>> tablets = new MutableLiveData<>();
    public TabletsViewModel(Context context) {
        this.context = context;
        loadTablets(context);
    }
    public LiveData<List<Map<String, Object>>> getTablets() {
        return tablets;
    }
    private void loadTablets(Context context) {
        API api = RetrofitInstance.getRetrofitInstance(context).create(API.class);
        api.getSmartphones().enqueue(new Callback<List<Map<String, Object>>>() {
            @Override
            public void onResponse(Call<List<Map<String, Object>>> call, Response<List<Map<String, Object>>> response) {
                if (response.isSuccessful()) {
                    tablets.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Map<String, Object>>> call, Throwable t) {
                tablets.setValue(new ArrayList<>());
            }
        });
    }
}