package com.dam2.reto.ui.cesta;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dam2.reto.R;
import com.dam2.reto.ui.login.LoginActivity;

public class CestaFragment extends Fragment {

    private CestaViewModel mViewModel;

    public static CestaFragment newInstance() {
        return new CestaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Verificar si el usuario está autenticado antes de inflar la vista
        if (!isAuthenticated()) {
            // Si no está autenticado, redirigir al login
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
            getActivity().finish(); // Cierra el fragmento actual y la actividad
            return null; // Detiene la ejecución del método y no inflará el layout
        }

        return inflater.inflate(R.layout.fragment_cesta, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CestaViewModel.class);
        // TODO: Use the ViewModel
    }

    // Método para verificar si el usuario está autenticado
    private boolean isAuthenticated() {
        SharedPreferences preferences = requireActivity().getSharedPreferences("user_data", getActivity().MODE_PRIVATE);
        String token = preferences.getString("auth_token", null);
        return token != null;
    }

}
