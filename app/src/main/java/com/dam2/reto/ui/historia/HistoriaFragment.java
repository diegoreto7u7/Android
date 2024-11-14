package com.dam2.reto.ui.historia;

import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.dam2.reto.R;

public class HistoriaFragment extends Fragment {

    private HistoriaViewModel mViewModel;
    private ImageView logoImageView;
    private ImageView historyImageView;
    private ImageView evolutionImageView;

    public static HistoriaFragment newInstance() {
        return new HistoriaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_historia, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        requireActivity().setTitle("Historia");

        // Inicialización de los ImageView
        logoImageView = view.findViewById(R.id.logoImageView);
        historyImageView = view.findViewById(R.id.historyImageView);
        evolutionImageView = view.findViewById(R.id.evolutionImageView);

        // Cargar imágenes desde URLs usando Glide
        Glide.with(this)
                .load("https://example.com/path/to/your/logo.png")
                .into(logoImageView);

        Glide.with(this)
                .load("https://www.legadodelpixel.es/wp-content/uploads/2019/09/DSC03308-1024x768.jpg")
                .into(historyImageView);

        Glide.with(this)
                .load("https://cdn.hobbyconsolas.com/sites/navi.axelspringer.es/public/media/image/2020/04/tienda-videojuegos-1923073.jpg?tf=3840x")
                .into(evolutionImageView);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HistoriaViewModel.class);
    }
}
