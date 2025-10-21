package org.example;

import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        try {
            // Crear lista de figuras geométricas
            List<FiguraGeometrica> figuras = new ArrayList<>();

            System.out.println("╔════════════════════════════════════════════╗");
            System.out.println("║   SISTEMA DINÁMICO DE FIGURAS              ║");
            System.out.println("╚════════════════════════════════════════════╝\n");

            // ===== PARTE 1: INSTANCIACIÓN DINÁMICA =====
            System.out.println("▶ INSTANCIACIÓN DINÁMICA DE FIGURAS NATIVAS:");
            System.out.println("─".repeat(50));

            // Crear figuras dinámicamente usando el Factory
            FiguraGeometrica circulo1 = FiguraFactory.crearFigura("Circulo", 5.0);
            FiguraGeometrica circulo2 = FiguraFactory.crearFigura("Circulo", 3.5);
            FiguraGeometrica cuadrado1 = FiguraFactory.crearFigura("Cuadrado", 4.0);
            FiguraGeometrica cuadrado2 = FiguraFactory.crearFigura("Cuadrado", 7.5);
            FiguraGeometrica triangulo1 = FiguraFactory.crearFigura("Triangulo", 6.0, 4.0, 5.0, 5.0, 6.0);
            FiguraGeometrica triangulo2 = FiguraFactory.crearFigura("Triangulo", 8.0, 6.0, 10.0, 8.0, 6.0);

            figuras.add(circulo1);
            figuras.add(circulo2);
            figuras.add(cuadrado1);
            figuras.add(cuadrado2);
            figuras.add(triangulo1);
            figuras.add(triangulo2);

            System.out.println("✓ Creadas 6 figuras nativas dinámicamente\n");

            // ===== PARTE 2: CARGAR FIGURAS DESDE GROOVY =====
            System.out.println("▶ CARGANDO FIGURAS DESDE GROOVY:");
            System.out.println("─".repeat(50));

            // Cargar Pentágono desde archivo .txt
            FiguraExternaService.cargarFiguraDesdeArchivo("Pentagono.txt", "Pentagono");
            FiguraGeometrica pentagono = FiguraFactory.crearFigura("Pentagono", 5.0);
            figuras.add(pentagono);

            // Cargar Hexágono desde archivo .txt
            FiguraExternaService.cargarFiguraDesdeArchivo("Hexagono.txt", "Hexagono");
            FiguraGeometrica hexagono = FiguraFactory.crearFigura("Hexagono", 6.0);
            figuras.add(hexagono);

            System.out.println();

            // ===== PARTE 3: DEMOSTRACIÓN DE CÓDIGO GROOVY EN TIEMPO DE EJECUCIÓN =====
            System.out.println("▶ DEFINIENDO ROMBO DIRECTAMENTE EN GROOVY:");
            System.out.println("─".repeat(50));

            String codigoRombo = """
                package org.example
                
                class Rombo extends FiguraGeometrica {
                    private double diagonalMayor
                    private double diagonalMenor
                    
                    Rombo(double diagonalMayor, double diagonalMenor) {
                        super("Rombo")
                        this.diagonalMayor = diagonalMayor
                        this.diagonalMenor = diagonalMenor
                    }
                    
                    @Override
                    double calcularArea() {
                        return (diagonalMayor * diagonalMenor) / 2
                    }
                    
                    @Override
                    double calcularPerimetro() {
                        double lado = Math.sqrt(Math.pow(diagonalMayor/2, 2) + Math.pow(diagonalMenor/2, 2))
                        return 4 * lado
                    }
                    
                    @Override
                    String obtenerDatos() {
                        return String.format("Diagonal Mayor: %.2f, Diagonal Menor: %.2f", diagonalMayor, diagonalMenor)
                    }
                }
                """;

            GroovyFiguraLoader.cargarDesdeGroovy(codigoRombo, "Rombo");
            FiguraGeometrica rombo = FiguraFactory.crearFigura("Rombo", 8.0, 6.0);
            figuras.add(rombo);
            System.out.println("✓ Rombo definido y compilado en tiempo de ejecución\n");

            // ===== MOSTRAR TODAS LAS FIGURAS =====
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
            System.out.println("Figuras nativas: 3 (Circulo, Cuadrado, Triangulo)");
            System.out.println("Figuras dinámicas: " + (figuras.size() - 6) + " (Pentagono, Hexagono, Rombo)");
            System.out.println("\n✓ Sistema funcionando correctamente!");

        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
