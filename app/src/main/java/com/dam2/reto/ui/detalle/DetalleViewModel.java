package com.dam2.reto.ui.detalle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dam2.reto.ui.modelo.Producto;
import com.dam2.reto.ui.retrofit.RetrofitInstance;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleViewModel extends ViewModel {
    private final MutableLiveData<Producto> producto = new MutableLiveData<>();

    public LiveData<Producto> getProducto() {
        return producto;
    }

    public void loadProductoById(int id) {
        Call<Map<String, Object>> call = RetrofitInstance.getAPI().getProductoById(id);
        call.enqueue(new Callback<Map<String, Object>>() {
            @Override
            public void onResponse(Call<Map<String, Object>> call, Response<Map<String, Object>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Convert the Map to Producto object
                    Map<String, Object> responseBody = response.body();
                    Producto productoObj = new Producto();
                    productoObj.setId((Integer) responseBody.get("id"));
                    productoObj.setNombreProducto((String) responseBody.get("nombre"));
                    productoObj.setPrecioVenta((Double) responseBody.get("precio"));
                    productoObj.setPrecioAlquiler((Double) responseBody.get("precio_alquiler"));
                    // Set other fields as necessary
                    producto.setValue(productoObj);
                } else {
                    // Handle error or product not found
                }
            }

            @Override
            public void onFailure(Call<Map<String, Object>> call, Throwable t) {
                // Handle connection failure
            }
        });
    }
}