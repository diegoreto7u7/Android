package com.dam2.reto.ui.reparacion;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.dam2.reto.R;
import com.dam2.reto.ui.modelo.ReparacionRequest;
import com.dam2.reto.ui.modelo.ResponseMessage;
import com.dam2.reto.ui.retrofit.API;
import com.dam2.reto.ui.retrofit.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReparacionFragment extends Fragment {

    private ReparacionViewModel mViewModel;
    private EditText searchEditText;
    private ListView productListView;
    private EditText descripcionEditText;
    private Button crearReparacionButton;
    private List<String> productList;
    private ArrayAdapter<String> adapter;
    private String selectedProductId;

    public static ReparacionFragment newInstance() {
        return new ReparacionFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reparacion, container, false);

        searchEditText = view.findViewById(R.id.searchEditText);
        productListView = view.findViewById(R.id.productListView);
        descripcionEditText = view.findViewById(R.id.descripcionEditText);
        crearReparacionButton = view.findViewById(R.id.crearReparacionButton);

        productList = new ArrayList<>();
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, productList);
        productListView.setAdapter(adapter);

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchProducts(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        productListView.setOnItemClickListener((parent, view1, position, id) -> {
            String selectedProduct = productList.get(position);
            selectedProductId = selectedProduct.split(" - ")[0]; // Assuming the format is "id - name"
            searchEditText.setText(selectedProduct);
        });

        crearReparacionButton.setOnClickListener(v -> crearReparacion());

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ReparacionViewModel.class);
    }

    private void searchProducts(String query) {
        API api = RetrofitInstance.getRetrofitInstance(getContext()).create(API.class);
        Call<List<Map<String, Object>>> call = api.searchProducts(query);

        call.enqueue(new Callback<List<Map<String, Object>>>() {
            @Override
            public void onResponse(Call<List<Map<String, Object>>> call, Response<List<Map<String, Object>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    productList.clear();
                    for (Map<String, Object> product : response.body()) {
                        String productInfo = product.get("id") + " - " + product.get("nombreProducto");
                        productList.add(productInfo);
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(), "Error al buscar productos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Map<String, Object>>> call, Throwable t) {
                Toast.makeText(getContext(), "Error de red", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void crearReparacion() {
        String descripcion = descripcionEditText.getText().toString();

        if (selectedProductId == null || descripcion.isEmpty()) {
            Toast.makeText(getContext(), "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        ReparacionRequest request = new ReparacionRequest(selectedProductId, descripcion);
        API api = RetrofitInstance.getRetrofitInstance(getContext()).create(API.class);
        Call<ResponseMessage> call = api.crearReparacion(request);

        call.enqueue(new Callback<ResponseMessage>() {
            @Override
            public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getContext(), "Reparación creada con éxito", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Error al crear la reparación", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseMessage> call, Throwable t) {
                Toast.makeText(getContext(), "Error de red", Toast.LENGTH_SHORT).show();
            }
        });
    }
}