package org.example;

import groovy.lang.GroovyClassLoader;

public class GroovyFiguraLoader {

    private static final GroovyClassLoader loader = new GroovyClassLoader();

    /**
     * Carga y compila una clase desde código Groovy en 2 líneas
     * @param codigoGroovy Código fuente de la clase en Groovy
     * @param nombreClase Nombre de la clase a registrar
     */
    public static void cargarDesdeGroovy(String codigoGroovy, String nombreClase) throws Exception {
        loader.parseClass(codigoGroovy);
        Class<?> clazz = loader.loadClass("org.example." + nombreClase);
        FiguraFactory.registrarFigura(nombreClase, clazz);
    }
}

