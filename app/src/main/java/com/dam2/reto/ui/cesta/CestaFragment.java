package com.dam2.reto.ui.cesta;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dam2.reto.R;
import com.dam2.reto.ui.adapters.CestaAdapter;
import com.dam2.reto.ui.modelo.Producto;
import com.dam2.reto.ui.modelo.ResponseMessage;
import com.dam2.reto.ui.modelo.SaleRequest;
import com.dam2.reto.ui.retrofit.API;
import com.dam2.reto.ui.retrofit.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CestaFragment extends Fragment {

    private CestaViewModel mViewModel;
    private TextView totalTextView;
    private Button checkoutButton;
    private RecyclerView recyclerViewCesta;
    private CestaAdapter cestaAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cesta, container, false);

        totalTextView = view.findViewById(R.id.totalTextView);
        checkoutButton = view.findViewById(R.id.checkoutButton);
        recyclerViewCesta = view.findViewById(R.id.recyclerViewCesta);

        recyclerViewCesta.setLayoutManager(new LinearLayoutManager(getContext()));

        // Configuración del botón de checkout
        checkoutButton.setOnClickListener(v -> {
            if (isAuthenticated()) {
                makeSale();
            } else {
                Toast.makeText(getContext(), "Inicia sesión para completar la compra", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CestaViewModel.class);

        // Configurar el RecyclerView con el adaptador y observar cambios en los items de la cesta
        cestaAdapter = new CestaAdapter(mViewModel);
        recyclerViewCesta.setAdapter(cestaAdapter);

        mViewModel.getCartItems().observe(getViewLifecycleOwner(), cartItems -> {
            double total = mViewModel.calculateTotal();
            totalTextView.setText("Total: €" + total);
            cestaAdapter.setProductos(cartItems);
        });
    }

    private boolean isAuthenticated() {
        SharedPreferences preferences = requireActivity().getSharedPreferences("user_data", getActivity().MODE_PRIVATE);
        String token = preferences.getString("auth_token", null);
        return token != null;
    }

    private void makeSale() {
        SharedPreferences preferences = requireActivity().getSharedPreferences("user_data", getActivity().MODE_PRIVATE);
        String token = preferences.getString("auth_token", null);

        List<Producto> cartItems = mViewModel.getCartItems().getValue();
        if (cartItems != null && !cartItems.isEmpty()) {
            API api = RetrofitInstance.getRetrofitInstance(requireContext()).create(API.class);
            SaleRequest saleRequest = new SaleRequest(cartItems);

            Call<ResponseMessage> call = api.makeSale("Bearer " + token, saleRequest);
            call.enqueue(new Callback<ResponseMessage>() {
                @Override
                public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(getContext(), "Venta exitosa", Toast.LENGTH_SHORT).show();
                        mViewModel.clearCart();  // Limpia la cesta después de la venta
                        totalTextView.setText("Total: €0.00");
                    } else {
                        Toast.makeText(getContext(), "Error en la venta", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseMessage> call, Throwable t) {
                    Toast.makeText(getContext(), "Error en la venta", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(getContext(), "La cesta está vacía", Toast.LENGTH_SHORT).show();
        }
    }
}
