package recocido;

import java.io.*;
import java.util.LinkedList;

/**
 * Interface con los metodos necesarios para implementar el metodo
 * de recocido simulado junto con la solucion a un problema particular.
 *
 * @author Benjamin Torres
 * @version 0.1
 */
public class RecocidoSimulado {
  float temperatura;
  float valor;
  float decaimiento;

  Solucion solucionActual; // Solución actual de la iteración.

  /**
   * Inicializa los valores necesarios para realizar 
   * recocido simulado durante un numero determinado de iteraciones
   * @param filePath El nombre del archivo del que va a sacar las coordenadas de las ciudades
   */
  public RecocidoSimulado(String filePath) throws IOException {
    inicializaLista(filePath);
    solucionActual.shuffle();
  }

  /**
   * Funcion que calcula una nueva temperatura en base a
   * la anterior y el decaemiento usado.
   * @param temperatura, float con el valor actual 
   * @param decaimiento, float que sera usado para hacer decaer el valor de temperatura
   * @return nueva temperatura
   */
  public float nuevaTemperatura(float temperatura,float decaimiento) {
    return 0;
  }

  /**
   * Genera y devuelve la solucion siguiente dependiendo de su valor
   * y de la probabilidad de elegir una solucion peor
   * @param Solucion que sera usada como base para elegir a la siguiente
   * @return Solucion nueva
   */
  public Solucion seleccionarSiguienteSolucion(Solucion s) {
    return new Solucion();
  }

  /**
   * Ejecuta el algoritmo con los parametros con los que fue inicializado
   * devuelve una solucion.
   * @param
   * @return Solucion al problema
   */
  public Solucion ejecutar() {
    return new Solucion();
  }

  /**
   * Saca las coordenadas de un archivo de ciudades y la convierte en una lista de Ciudad.
   * 
   * Para el formato del archivo se puede visitar la siguiente página:
   * http://www.math.uwaterloo.ca/tsp/world/dj38.tsp
   * @param filePath Path del archivo donde se está sacando los datos de las ciudades
   * @return
   */
  private void inicializaLista(String filePath) throws IOException {
    File file = new File(filePath);
    if(!file.exists()) {
      throw new IOException("Archivo no encontrado.");
    }

    LinkedList<Ciudad> listaCiudades = new LinkedList<>();
    BufferedReader fileReader = new BufferedReader(new FileReader(file));
    String row;
    Ciudad ciudad;
    while (!(row = fileReader.readLine()).contains("NODE_COORD_SECTION")) {}
    while ((row = fileReader.readLine()) != null) {
      String[] data = row.split(" ");
      double x = Double.parseDouble(data[1]); 
      double y = Double.parseDouble(data[2]); 
      int id = Integer.parseInt(data[0]); 
      ciudad = new Ciudad(x, y, id);
      listaCiudades.add(ciudad);
    }
    fileReader.close();
    solucionActual = new Solucion(listaCiudades);
  }
}
