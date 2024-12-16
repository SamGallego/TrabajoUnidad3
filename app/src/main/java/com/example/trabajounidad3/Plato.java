package com.example.trabajounidad3;

import java.io.Serializable;

public class Plato implements Serializable {
    private int foto;
    private String nombre;
    private String descripcion;
    private String alergenos;
    private double precio;
    private double precioOriginal;

    public Plato(int foto, String nombre, String descripcion, String alergenos, double precio) {
        this.foto = foto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.alergenos = alergenos;
        this.precio = precio;
        this.precioOriginal = precio;
    }

    // Getters y setters
    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAlergenos() {
        return alergenos;
    }

    public void setAlergenos(String alergenos) {
        this.alergenos = alergenos;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getPrecioOriginal() {
        return precioOriginal;
    }

    public void setPrecioOriginal(double precioOriginal) {
        this.precioOriginal = precioOriginal;
    }
}
