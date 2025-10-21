package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FiguraExternaService {

    /**
     * Descarga y carga una figura desde un servicio externo
     * @param urlFigura URL del archivo .txt con el código Groovy
     * @param nombreClase Nombre de la clase a registrar
     */
    public static void cargarFiguraExterna(String urlFigura, String nombreClase) throws Exception {
        String codigoGroovy = descargarDesdeURL(urlFigura);
        GroovyFiguraLoader.cargarDesdeGroovy(codigoGroovy, nombreClase);
        System.out.println("✓ Figura '" + nombreClase + "' cargada desde servicio externo");
    }

    /**
     * Carga una figura desde un archivo local .txt
     * @param rutaArchivo Ruta del archivo .txt con el código Groovy
     * @param nombreClase Nombre de la clase a registrar
     */
    public static void cargarFiguraDesdeArchivo(String rutaArchivo, String nombreClase) throws Exception {
        String codigoGroovy = new String(Files.readAllBytes(Paths.get(rutaArchivo)));
        GroovyFiguraLoader.cargarDesdeGroovy(codigoGroovy, nombreClase);
        System.out.println("✓ Figura '" + nombreClase + "' cargada desde archivo: " + rutaArchivo);
    }

    private static String descargarDesdeURL(String urlString) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
        conexion.setRequestMethod("GET");
        conexion.setConnectTimeout(5000);
        conexion.setReadTimeout(5000);

        BufferedReader lector = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
        StringBuilder contenido = new StringBuilder();
        String linea;

        while ((linea = lector.readLine()) != null) {
            contenido.append(linea).append("\n");
        }

        lector.close();
        conexion.disconnect();

        return contenido.toString();
    }
}

