package recocido;

import java.io.*;
import java.util.LinkedList;
import java.util.Random;

/**
 * Interface con los metodos necesarios para implementar el metodo
 * de recocido simulado junto con la solucion a un problema particular.
 *
 * @author Benjamin Torres
 * @version 0.1
 */
public class RecocidoSimulado {
  private final float TEMPERATURA_INICIAL = (float) (Math.E + 10);
  private final float TEMPERATURA_FINAL = 0.1f;
  private final float FACTOR_DE_ENFRIAMIENTO = 0.99995f;

  float temperatura = TEMPERATURA_INICIAL;
  int iteraciones;

  Solucion solucionInicial; // Solución con la que inicia la iteración.
  Solucion mejorSolucion; // La mejor solución obtenidad a lo largo de todas las iteraciones.

  Random rnd = new Random();

  /**
   * Inicializa los valores necesarios para realizar 
   * recocido simulado durante un numero determinado de iteraciones
   * @param iteraciones El numero de iteraciones que se va a ejecutar el algoritmo
   * @param filePath El nombre del archivo del que va a sacar las coordenadas de las ciudades
   */
  public RecocidoSimulado(int iteraciones, String filePath) throws IOException {
    inicializaLista(filePath);
    mejorSolucion = solucionInicial;
    this.iteraciones = iteraciones;
  }

  /**
   * Funcion que actualiza la temperatura en base a
   * la anterior y el factor de enfriamiento usado.
   */
  public void actualizarTemperatura() {
    temperatura *= FACTOR_DE_ENFRIAMIENTO;
  }

  /**
   * Genera y devuelve la solucion siguiente dependiendo de su valor
   * y de la probabilidad de elegir una solucion peor
   * @param solucion que sera usada como base para elegir a la siguiente
   * @return Solucion nueva
   */
  public Solucion seleccionarSiguienteSolucion(Solucion actual) {
    Solucion siguiente = actual.siguienteSolucion();
    if (siguiente.valor < actual.valor) {
      actual = siguiente;
      if (siguiente.valor < mejorSolucion.valor) {
        mejorSolucion = siguiente;
      }
    } else if (probaAceptar(actual, siguiente) > rnd.nextFloat()) {
      actual = siguiente;
    }
    return actual;
  }

  /**
   * Calcula la probabilidad de aceptar una solucion peor  dependiendo de su diferencia 
   * en el valor de dicha solución con la solución actual.
   * @param actual La solución actual válida
   * @param siguiente La siguiente solución que es peor que la actual
   * @return La probabilidad de aceptar la solución peor
   */
  private float probaAceptar(Solucion actual, Solucion peor) {
    float delta = peor.valor - actual.valor;
    return (float) Math.pow(Math.E, (- delta) / temperatura);
  }

  /**
   * Ejecuta el algoritmo con los parametros con los que fue inicializado
   * devuelve una solucion.
   * @return Solucion al problema
   */
  public Solucion ejecutar() {
    for(int i = 0; i < iteraciones; i++) {
      Solucion solucion = new Solucion(solucionInicial);
      solucion.shuffle();
      temperatura = TEMPERATURA_INICIAL;
      while(temperatura > TEMPERATURA_FINAL){
        solucion = seleccionarSiguienteSolucion(solucion);
        actualizarTemperatura();
      }
    }
    return mejorSolucion;
  }

  /**
   * Saca las coordenadas de un archivo de ciudades y la convierte en una lista de Ciudad.
   * Posteriormente crea una Solucion a partir de esa lista.
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
      float x = Float.parseFloat(data[1]); 
      float y = Float.parseFloat(data[2]); 
      int id = Integer.parseInt(data[0]); 
      ciudad = new Ciudad(x, y, id);
      listaCiudades.add(ciudad);
    }
    fileReader.close();
    solucionInicial = new Solucion(listaCiudades);
  }
}
