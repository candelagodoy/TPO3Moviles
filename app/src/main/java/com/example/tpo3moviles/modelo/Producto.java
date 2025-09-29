package com.example.tpo3moviles.modelo;

import androidx.annotation.Nullable;

import java.util.Objects;

public class Producto {
    private String codigo;
    private String descripcion;
    private double precio;


    public Producto(String codigo, String descripcion, double precio) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof Producto)){
            return false;
        }
        Producto p = (Producto) obj;
        if(p.getCodigo().equals(this.codigo)){
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return codigo.hashCode();
    }
}
