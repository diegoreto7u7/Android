package com.dam2.reto.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.dam2.reto.R;
import com.dam2.reto.databinding.FragmentHomeBinding;
import com.dam2.reto.ui.adapters.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class HomeFragment extends Fragment {


    private FragmentHomeBinding binding;
    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private ViewPagerAdapter viewPagerAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        TabLayout tabLayout = view.findViewById(R.id.tabLayout);
        ViewPager2 viewPager = view.findViewById(R.id.viewPager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(requireActivity());
        viewPager.setAdapter(adapter);


        new TabLayoutMediator(tabLayout,viewPager,(tab, position) -> {
            if (position == 0) {
                tab.setText("Para ti");
            } else if (position == 1){
                tab.setText("VideoJuegos");
            } else if (position == 2){
                tab.setText("Consolas");
            } else if (position ==3){
                tab.setText("Tablets y Smartphones");

            }
            else if (position == 4){
                tab.setText("Soporte Tecnico");
            }else if (position == 5){
                tab.setText("Cesta");
            }
        }).attach();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        requireActivity().setTitle("Home");
    }
}