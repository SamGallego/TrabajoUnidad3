package com.example.trabajounidad3;

public class Plato {

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

    // Método para obtener el precio original
    public double getPrecioOriginal() {
        return precioOriginal;
    }

    // Método para establecer el precio original
    public void setPrecioOriginal(double precioOriginal) {
        this.precioOriginal = precioOriginal;
    }

    @Override
    public String toString() {
        return "Plato{" +
                "foto=" + foto +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", alergenos='" + alergenos + '\'' +
                ", precio=" + precio +
                '}';
    }
}
