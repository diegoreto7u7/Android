package com.dam2.reto.ui.detalle;

import android.content.Context;
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

    public void loadProductoById(int id, Context context) {
        Call<Map<String, Object>> call = RetrofitInstance.getAPI(context).getProductoById(id);
        call.enqueue(new Callback<Map<String, Object>>() {
            @Override
            public void onResponse(Call<Map<String, Object>> call, Response<Map<String, Object>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Map<String, Object> responseBody = response.body();
                    Producto productoObj = new Producto();
                    productoObj.setId(((Double) responseBody.get("id")).intValue());
                    productoObj.setNombreProducto((String) responseBody.get("nombre"));
                    productoObj.setPrecioVenta((Double) responseBody.get("precio"));
                    productoObj.setPrecioAlquiler((Double) responseBody.get("precio_alquiler"));
                    productoObj.setDescripcion((String) responseBody.get("descripcion"));
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
