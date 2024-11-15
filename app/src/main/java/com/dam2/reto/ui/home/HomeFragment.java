package com.dam2.reto.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.dam2.reto.R;
import com.dam2.reto.ui.adapters.ViewPagerAdapter;
import com.dam2.reto.ui.login.LoginActivity;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class HomeFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_home, container, false);

    // Configuración del TabLayout y ViewPager2
    tabLayout = view.findViewById(R.id.tabLayout);
    viewPager = view.findViewById(R.id.viewPager);

    viewPagerAdapter = new ViewPagerAdapter(requireActivity());
    viewPager.setAdapter(viewPagerAdapter);

    // Añadir los subtabs al TabLayout con TabLayoutMediator
    new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
        switch (position) {
            case 0:
                tab.setText("Para ti");
                break;
            case 1:
                tab.setText("VideoJuegos");
                break;
            case 2:
                tab.setText("Consolas");
                break;
            case 3:
                tab.setText("Tablets y Smartphones");
                break;
            case 4:
                tab.setText("Reparaciones");
                break;
            case 5:
                tab.setText("Cesta");
                break;
        }
    }).attach();

    // Configuración de la barra de búsqueda
    androidx.appcompat.widget.SearchView searchView = view.findViewById(R.id.searchView);
    searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            // Acción de búsqueda
            Toast.makeText(requireContext(), "Buscando: " + query, Toast.LENGTH_SHORT).show();
            return true;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            // Actualización de la búsqueda en tiempo real
            return false;
        }
    });

    // Configuración del ImageButton como botón para redirigir a LoginActivity
    ImageButton profileIcon = view.findViewById(R.id.profileIcon);
    profileIcon.setOnClickListener(v -> {
        Intent intent = new Intent(requireActivity(), LoginActivity.class);
        startActivity(intent);
    });

    return view;
}

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        requireActivity().setTitle("Home");
    }
}
