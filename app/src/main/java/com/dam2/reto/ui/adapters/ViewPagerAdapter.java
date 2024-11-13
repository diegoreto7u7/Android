package com.dam2.reto.ui.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.dam2.reto.ui.cesta.CestaFragment;
import com.dam2.reto.ui.consolas.ConsolasFragment;
import com.dam2.reto.ui.reparacion.ReparacionFragment;
import com.dam2.reto.ui.tablets.TabletsFragment;
import com.dam2.reto.ui.videojuegos.VideojuegosFragment;
import com.dam2.reto.ui.parati.ParaTiFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(FragmentActivity fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new ParaTiFragment();
            case 1:
                return new VideojuegosFragment();
            case 2:
                return new ConsolasFragment();
            case 3:
                return new TabletsFragment();
            case 4:
                return new CestaFragment();
            case 5:
                return new ReparacionFragment();
            default:
                return new ParaTiFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 6;
    }
}