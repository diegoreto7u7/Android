package com.dam2.reto.ui.cesta;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dam2.reto.ui.modelo.Producto;

import java.util.ArrayList;
import java.util.List;

public class CestaViewModel extends ViewModel {

    private final MutableLiveData<List<Producto>> cartItems = new MutableLiveData<>(new ArrayList<>());

    public LiveData<List<Producto>> getCartItems() {
        return cartItems;
    }

    public void addProductToCart(Producto product) {
        List<Producto> currentCart = cartItems.getValue();
        currentCart.add(product);
        cartItems.setValue(currentCart);
    }

    public void removeProductFromCart(Producto product) {
        List<Producto> currentCart = cartItems.getValue();
        currentCart.remove(product);
        cartItems.setValue(currentCart);
    }

    public double calculateTotal() {
        List<Producto> currentCart = cartItems.getValue();
        double total = 0.0;
        for (Producto product : currentCart) {
            total += product.getPrecioVenta();
        }
        return total;
    }
}
