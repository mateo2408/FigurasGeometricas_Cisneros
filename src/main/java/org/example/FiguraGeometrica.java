package org.example;

public abstract class FiguraGeometrica implements Area, Perimetro {
    protected String nombre;

    public FiguraGeometrica(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public abstract String obtenerDatos();

    @Override
    public String toString() {
        return String.format("=== %s ===\n%s\nÁrea: %.2f\nPerímetro: %.2f",
                nombre, obtenerDatos(), calcularArea(), calcularPerimetro());
    }
}
