package com.dam2.reto.ui.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import java.util.List;
import java.util.Map;

public interface API {
    @GET("/admin/productos/{tipo}")
    Call<List<Map<String, Object>>> getProductosByType(@Path("tipo") String tipo);
    @POST("/auth")
    Call<Map<String, String>> authenticateUser(@Body Map<String, String> credentials);

    @GET("/admin/productos/videojuegos")
    Call<List<Map<String, Object>>> getVideojuegos();

    @GET("/admin/productos/smartphones")
    Call<List<Map<String, Object>>> getSmartphones();

    @GET("/admin/productos/consolas")
    Call<List<Map<String, Object>>> getConsolas();

    @GET("/admin/producto/{id}")
    Call<Map<String, Object>> getProductoById(@Path("id") int id);

}
