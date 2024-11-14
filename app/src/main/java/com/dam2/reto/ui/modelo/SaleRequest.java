package com.dam2.reto.ui.modelo;

import java.util.List;

public class SaleRequest {
    private List<Producto> productos;

    public SaleRequest(List<Producto> productos) {
        this.productos = productos;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}

