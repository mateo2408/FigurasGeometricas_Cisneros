# Sistema Dinámico de Figuras Geométricas

## 📋 Descripción

Sistema que permite **instanciar figuras geométricas dinámicamente** a partir del nombre de la clase y parámetros en tiempo de ejecución. Soporta carga de nuevas figuras desde **código Groovy** compilado en runtime, permitiendo extender el sistema sin recompilar.

## 🎯 Características Principales

### 1. **Instanciación Dinámica**
```java
// Crear cualquier figura registrada dinámicamente
FiguraGeometrica circulo = FiguraFactory.crearFigura("Circulo", 5.0);
FiguraGeometrica cuadrado = FiguraFactory.crearFigura("Cuadrado", 4.0);
FiguraGeometrica triangulo = FiguraFactory.crearFigura("Triangulo", 6.0, 4.0, 5.0, 5.0, 6.0);
```

### 2. **Carga de Figuras desde Groovy** (2 líneas de código)
```java
// Cargar desde archivo .txt
FiguraExternaService.cargarFiguraDesdeArchivo("Pentagono.txt", "Pentagono");
FiguraGeometrica pentagono = FiguraFactory.crearFigura("Pentagono", 5.0);
```

### 3. **Compilación en Tiempo de Ejecución**
```java
// Definir una figura directamente en código Groovy
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
```

### 4. **Servicio Externo**
```java
// Cargar desde URL externa
FiguraExternaService.cargarFiguraExterna("https://ejemplo.com/figuras/Octogono.txt", "Octogono");
```

## 🏗️ Arquitectura

```
FiguraGeometrica (Clase abstracta)
├── Circulo (Nativa)
├── Cuadrado (Nativa)
├── Triangulo (Nativa)
└── [Figuras Dinámicas en Groovy]
    ├── Pentagono
    ├── Hexagono
    └── Rombo
```

### Componentes:

1. **FiguraFactory**: Factory pattern para instanciación dinámica mediante reflexión
2. **GroovyFiguraLoader**: Carga y compila clases Groovy en runtime (2 líneas)
3. **FiguraExternaService**: Consume servicios externos para obtener nuevas figuras

## 📦 Dependencias

```xml
<dependency>
    <groupId>org.apache.groovy</groupId>
    <artifactId>groovy</artifactId>
    <version>4.0.15</version>
</dependency>
```

## 🚀 Instalación

1. **Recargar dependencias Maven** en IntelliJ IDEA:
   - Click derecho en `pom.xml` → "Maven" → "Reload Project"
   - O usa el botón "Load Maven Changes" (icono 🔄 en la barra superior)

2. **Ejecutar el proyecto**:
   - Abre `Main.java`
   - Click derecho → "Run 'Main.main()'"

## 📄 Crear Nuevas Figuras

### Formato de archivo .txt (Groovy):

```groovy
package org.example

class NombreFigura extends FiguraGeometrica {
    private double parametro1
    private double parametro2
    
    NombreFigura(double parametro1, double parametro2) {
        super("Nombre Figura")
        this.parametro1 = parametro1
        this.parametro2 = parametro2
    }
    
    @Override
    double calcularArea() {
        // Tu fórmula aquí
        return area
    }
    
    @Override
    double calcularPerimetro() {
        // Tu fórmula aquí
        return perimetro
    }
    
    @Override
    String obtenerDatos() {
        return String.format("Datos: %.2f, %.2f", parametro1, parametro2)
    }
}
```

## 🔍 Ejemplos Incluidos

- **Pentágono** (`Pentagono.txt`): Polígono de 5 lados
- **Hexágono** (`Hexagono.txt`): Polígono de 6 lados
- **Rombo** (inline): Definido directamente en Main.java

## 💡 Ventajas

✅ **Sin recompilación**: Agrega figuras sin recompilar el proyecto
✅ **Extensible**: Sistema abierto para nuevas figuras
✅ **Dinámico**: Instanciación por nombre de clase
✅ **Integración simple**: Solo 2 líneas para cargar Groovy
✅ **Servicios externos**: Consume figuras desde URLs o archivos

## 🎓 Ejemplo de Uso Completo

```java
// 1. Cargar desde archivo
FiguraExternaService.cargarFiguraDesdeArchivo("Pentagono.txt", "Pentagono");

// 2. Instanciar dinámicamente
FiguraGeometrica fig = FiguraFactory.crearFigura("Pentagono", 5.0);

// 3. Usar como cualquier otra figura
System.out.println("Área: " + fig.calcularArea());
System.out.println("Perímetro: " + fig.calcularPerimetro());
```

## 📊 Salida Esperada

```
╔════════════════════════════════════════════╗
║   SISTEMA DINÁMICO DE FIGURAS              ║
╚════════════════════════════════════════════╝

▶ INSTANCIACIÓN DINÁMICA DE FIGURAS NATIVAS:
──────────────────────────────────────────────────
✓ Creadas 6 figuras nativas dinámicamente

▶ CARGANDO FIGURAS DESDE GROOVY:
──────────────────────────────────────────────────
✓ Figura 'Pentagono' cargada desde archivo: Pentagono.txt
✓ Figura 'Hexagono' cargada desde archivo: Hexagono.txt

▶ DEFINIENDO ROMBO DIRECTAMENTE EN GROOVY:
──────────────────────────────────────────────────
✓ Rombo definido y compilado en tiempo de ejecución

╔════════════════════════════════════════════╗
║   LISTA DE FIGURAS GEOMÉTRICAS             ║
╚════════════════════════════════════════════╝

[Lista de todas las figuras con sus cálculos]
```

## 🔧 Troubleshooting

**Error: Cannot resolve GroovyClassLoader**
- Solución: Recarga las dependencias Maven en IntelliJ IDEA

**Error: Figura no registrada**
- Solución: Verifica que la figura fue cargada antes de instanciarla

**Error al leer archivo .txt**
- Solución: Asegúrate que el archivo está en la raíz del proyecto

