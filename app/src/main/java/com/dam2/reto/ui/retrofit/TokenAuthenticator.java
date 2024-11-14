package com.dam2.reto.ui.retrofit;

import android.content.Context;

import java.util.Map;

import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import retrofit2.Call;
import retrofit2.Retrofit;

public class TokenAuthenticator implements Authenticator {
    private SessionManager sessionManager;
    private API apiService;

    public TokenAuthenticator(SessionManager sessionManager, API apiService) {
        this.sessionManager = sessionManager;
        this.apiService = apiService;
    }

    @Override
    public Request authenticate(Route route, Response response) {
        String currentToken = sessionManager.getAuthToken();

        // Make synchronous call to refresh the token
        Call<Map<String, String>> refreshCall = apiService.refreshToken("Bearer " + currentToken);
        try {
            retrofit2.Response<Map<String, String>> refreshResponse = refreshCall.execute();
            if (refreshResponse.isSuccessful()) {
                String newToken = refreshResponse.body().get("token");
                sessionManager.saveAuthToken(newToken);
                // Retry the failed request with the new token
                return response.request().newBuilder()
                        .header("Authorization", "Bearer " + newToken)
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
