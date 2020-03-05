package recocido;
   
import java.io.IOException;
import java.util.LinkedList;

/**
 * Clase para ejecutar un proceso de optimizacion usando recocido simulado
 * @author Benjamin Torres Saavedra
 * @version 0.1
 */
public class Main{
   	public static void main(String[] args) {
        try {
            String filePath = "Djibouti.tsp"; // Nombre del archivo tsp con la lista de coordenadas de ciudades.
            LinkedList<Ciudad> listaCiudades = TSPReader.readFile(filePath);
            Solucion solucionInicial = new Solucion(listaCiudades);
            int iteraciones = 100; // Número de iteraciones que se van a hacer.
            float temperaturaInicial = (float) Math.sqrt(listaCiudades.size());
            float temperaturaFinal = (float) Math.pow(10, -8);
            float factorDeEnfriamiento = 0.995f;

        	RecocidoSimulado recocido = new RecocidoSimulado(
                solucionInicial, 
                iteraciones, 
                temperaturaInicial,
                temperaturaFinal,
                factorDeEnfriamiento
            );
        	Solucion solucion = recocido.ejecutar();

            /*
            LinkedList<Float>[] historial = recocido.getHistorial();
            for(LinkedList<Float> iteracion : historial) {
                System.out.println("----------------------------------------------------");
                System.out.println("Nueva Iteración");
                for(Float valor : iteracion) {
                    System.out.println(valor);
                }
            }
            */
            System.out.println(solucion);
        } catch (IOException ioe) {
        	System.out.println("No se pudo leer el archivo.\n" + ioe);
        }
   	}
}
