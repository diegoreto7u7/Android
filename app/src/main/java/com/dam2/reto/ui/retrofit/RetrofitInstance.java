package com.dam2.reto.ui.retrofit;

import android.content.Context;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static final String BASE_URL = "https://adiapp.duckdns.org/";
    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance(Context context) {
        if (retrofit == null) {
            SessionManager sessionManager = new SessionManager(context);
            AuthInterceptor authInterceptor = new AuthInterceptor(sessionManager);

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(authInterceptor)
                    .authenticator(new TokenAuthenticator(sessionManager, null))
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static API getAPI(Context context) {
        return getRetrofitInstance(context).create(API.class);
    }
}