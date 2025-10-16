package org.example;

import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // Crear lista de figuras geométricas
        List<FiguraGeometrica> figuras = new ArrayList<>();

        // Crear figuras con valores hardcodeados
        Circulo circulo1 = new Circulo(5.0);
        Circulo circulo2 = new Circulo(3.5);

        Cuadrado cuadrado1 = new Cuadrado(4.0);
        Cuadrado cuadrado2 = new Cuadrado(7.5);

        Triangulo triangulo1 = new Triangulo(6.0, 4.0, 5.0, 5.0, 6.0);
        Triangulo triangulo2 = new Triangulo(8.0, 6.0, 10.0, 8.0, 6.0);

        // Agregar todas las figuras a la lista
        figuras.add(circulo1);
        figuras.add(circulo2);
        figuras.add(cuadrado1);
        figuras.add(cuadrado2);
        figuras.add(triangulo1);
        figuras.add(triangulo2);

        // Imprimir la lista de figuras
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║   LISTA DE FIGURAS GEOMÉTRICAS             ║");
        System.out.println("╚════════════════════════════════════════════╝\n");

        for (int i = 0; i < figuras.size(); i++) {
            System.out.println("Figura #" + (i + 1));
            System.out.println(figuras.get(i));
            System.out.println();
        }

        // Mostrar resumen
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║   RESUMEN                                  ║");
        System.out.println("╚════════════════════════════════════════════╝");
        System.out.println("Total de figuras: " + figuras.size());
    }
}
