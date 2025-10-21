package org.example;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class FiguraFactory {
    private static final Map<String, Class<?>> figurasRegistradas = new HashMap<>();

    static {
        // Registrar figuras nativas
        figurasRegistradas.put("Circulo", Circulo.class);
        figurasRegistradas.put("Cuadrado", Cuadrado.class);
        figurasRegistradas.put("Triangulo", Triangulo.class);
    }

    /**
     * Crea una instancia de figura geométrica dinámicamente
     * @param nombreClase Nombre de la clase (ej: "Circulo")
     * @param parametros Parámetros del constructor
     * @return Instancia de FiguraGeometrica
     */
    public static FiguraGeometrica crearFigura(String nombreClase, Object... parametros) throws Exception {
        Class<?> clazz = figurasRegistradas.get(nombreClase);

        if (clazz == null) {
            throw new IllegalArgumentException("Figura no registrada: " + nombreClase);
        }

        // Obtener tipos de los parámetros
        Class<?>[] tiposParametros = new Class<?>[parametros.length];
        for (int i = 0; i < parametros.length; i++) {
            tiposParametros[i] = getParameterType(parametros[i]);
        }

        // Buscar el constructor adecuado
        Constructor<?> constructor = clazz.getConstructor(tiposParametros);

        // Crear instancia
        return (FiguraGeometrica) constructor.newInstance(parametros);
    }

    /**
     * Registra una nueva clase de figura (para Groovy o clases dinámicas)
     */
    public static void registrarFigura(String nombre, Class<?> clazz) {
        figurasRegistradas.put(nombre, clazz);
    }

    private static Class<?> getParameterType(Object param) {
        if (param instanceof Double) return double.class;
        if (param instanceof Float) return double.class;
        if (param instanceof Integer) return int.class;
        if (param instanceof Long) return long.class;
        if (param instanceof Boolean) return boolean.class;
        return param.getClass();
    }

    public static Map<String, Class<?>> getFigurasRegistradas() {
        return new HashMap<>(figurasRegistradas);
    }
}

