package com.dam2.reto.ui.modelo;

public class ReparacionRequest {
    private String idProducto;
    private String descripcionUsuario;

    public ReparacionRequest(String idProducto, String descripcionUsuario) {
        this.idProducto = idProducto;
        this.descripcionUsuario = descripcionUsuario;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getDescripcionUsuario() {
        return descripcionUsuario;
    }

    public void setDescripcionUsuario(String descripcionUsuario) {
        this.descripcionUsuario = descripcionUsuario;
    }
}