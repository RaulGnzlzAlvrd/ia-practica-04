package recocido;

import static java.lang.System.out;   
import java.io.IOException; 

/**
 * Clase para ejecutar un proceso de optimizacion usando recocido simulado
 * @author Benjamin Torres Saavedra
 * @version 0.1
 */
public class Main{
   	public static void main(String[] args) {
    	int iteraciones = 1; // Número de iteraciones que se van a hacer.
        String filePath = "Tanzania.tsp"; // Nombre del archivo tsp con la lista de coordenadas de ciudades.
        try {
        	RecocidoSimulado recocido = new RecocidoSimulado(iteraciones, filePath);
        	Solucion solucion = recocido.ejecutar();
        	out.println(solucion);
        } catch (IOException ioe) {
        	out.println("No se pudo leer el archivo.\n" + ioe);
        }
   	}
}
