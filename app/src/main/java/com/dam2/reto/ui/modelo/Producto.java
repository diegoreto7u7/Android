package com.dam2.reto.ui.modelo;

import android.os.Parcel;
import android.os.Parcelable;

public class Producto implements Parcelable {
    private int id;
    private String nombreProducto;
    private double precioVenta;
    private String estado;
    private double precioAlquiler;
    private String tipo;
    private String imagen;
    private String descripcion;
    private String marca;

    public Producto() {
        // No-argument constructor
    }

    public Producto(Parcel in) {
        id = in.readInt();
        nombreProducto = in.readString();
        precioVenta = in.readDouble();
        estado = in.readString();
        precioAlquiler = in.readDouble();
        tipo = in.readString();
        imagen = in.readString();
        descripcion = in.readString();
        marca = in.readString();
    }

    public static final Creator<Producto> CREATOR = new Creator<Producto>() {
        @Override
        public Producto createFromParcel(Parcel in) {
            return new Producto(in);
        }

        @Override
        public Producto[] newArray(int size) {
            return new Producto[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nombreProducto);
        dest.writeDouble(precioVenta);
        dest.writeString(estado);
        dest.writeDouble(precioAlquiler);
        dest.writeString(tipo);
        dest.writeString(imagen);
        dest.writeString(descripcion);
        dest.writeString(marca);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getPrecioAlquiler() {
        return precioAlquiler;
    }

    public void setPrecioAlquiler(double precioAlquiler) {
        this.precioAlquiler = precioAlquiler;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
}