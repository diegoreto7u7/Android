package com.dam2.reto.ui.consolas;

import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.dam2.reto.R;
import com.dam2.reto.ui.adapters.ProductoAdapter;

public class ConsolasFragment extends Fragment {

    private ConsolasViewModel mViewModel;
    private ProductoAdapter topConsolasAdapter;
    private ProductoAdapter todasConsolasAdapter;

    public static ConsolasFragment newInstance() {
        return new ConsolasFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_consolas, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        requireActivity().setTitle("Consolas");

        // Inicializa el ViewModel con el Factory antes de usarlo
        ConsolasViewModelFactory factory = new ConsolasViewModelFactory(requireContext());
        mViewModel = new ViewModelProvider(this, factory).get(ConsolasViewModel.class);

        // Asegúrate de que el ViewModel no sea nulo antes de configurar el RecyclerView
        if (mViewModel != null) {
            setupTopConsolasRecyclerView(view);
            setupTodasConsolasRecyclerView(view);
        }
    }


    private void setupTopConsolasRecyclerView(View view) {
        RecyclerView recyclerViewTopConsolas = view.findViewById(R.id.recyclerViewTopConsolas);
        recyclerViewTopConsolas.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        // Observa solo si el ViewModel está inicializado
        if (mViewModel != null) {
            mViewModel.getTopConsolas().observe(getViewLifecycleOwner(), productos -> {
                topConsolasAdapter = new ProductoAdapter(getContext(), productos, new ProductoAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int productId) {
                        // Acción al hacer clic en una consola destacada
                    }

                    @Override
                    public void onAddToCartClick(int productId) {
                        // Acción al agregar una consola destacada al carrito
                    }
                });
                recyclerViewTopConsolas.setAdapter(topConsolasAdapter);
            });
        }
    }

    private void setupTodasConsolasRecyclerView(View view) {
        RecyclerView recyclerViewTodasConsolas = view.findViewById(R.id.recyclerViewTodasConsolas);
        recyclerViewTodasConsolas.setLayoutManager(new GridLayoutManager(getContext(), 2)); // Cambia a 3 o más en tablets
        mViewModel.getTodasConsolas().observe(getViewLifecycleOwner(), productos -> {
            todasConsolasAdapter = new ProductoAdapter(getContext(), productos, new ProductoAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int productId) {
                    // Acción al hacer clic en una consola
                }

                @Override
                public void onAddToCartClick(int productId) {
                    // Acción al agregar una consola al carrito
                }
            });
            recyclerViewTodasConsolas.setAdapter(todasConsolasAdapter);
        });
    }
}
