package recocido;

import java.util.LinkedList;
import java.util.Iterator;
import java.util.Random;

/**
 * Interface para representar soluciones que pueden ser usadas
 * en el metodo de recocido simulado
 *
 * @author Benjamin Torres
 * @version 0.1
 */
public class Solucion {
  LinkedList<Ciudad> listaCiudades; // El orden de la lista define el orden en que se recorren las ciudades.
  float valor; // Valor de la solucion actual

  Random rnd = new Random(); // Auxiliar para tomar deciciones al azar.

  /**
   * Metodo constructor de una solucion a un problema, inicializa con una lista de Ciudades.
   */
  public Solucion(LinkedList<Ciudad> listaCiudades) {
    this.listaCiudades = new LinkedList<Ciudad>(listaCiudades);
    this.valor = evaluar();
  }

  /**
   * Metodo constructor de una solucion a un problema, inicializa con otra solucion.
   */
  public Solucion(Solucion solucion) {
    this.listaCiudades = new LinkedList<Ciudad>(solucion.listaCiudades);
    this.valor = evaluar();
  }

  /**
   * Genera, a partir de una aproximacion de solucion una
   * nueva dentro de la vecindad actual
   * @return genera una solucion nueva basada en la que llama al método
   */
  public Solucion siguienteSolucion() {
    Solucion siguiente = new Solucion(listaCiudades);
    siguiente.swapRandom();
    return siguiente;
  }

  /**
   * Asigna un valor a la solucion que invoca el metodo
   * @return evaluacion de la solucion
   */
  public float evaluar() {
    valor = listaCiudades.getFirst().calculaDistancia(listaCiudades.getLast());
    LinkedList<Ciudad> copia = (LinkedList<Ciudad>) listaCiudades.clone();
    for(int i = 0; i < listaCiudades.size() - 1; i++) {
      Ciudad c1 = copia.get(0);
      Ciudad c2 = copia.get(1);
      copia.pop();
      valor += c1.calculaDistancia(c2);
    }
    return valor;
  }
  
  /**
   * Metodo para imprimir a una solucion
   * @return Representacion de cadena para la solucion
   */
  public String toString() {
    String s = "Solución: \n";
    for(Ciudad ciudad : listaCiudades)
      s += ciudad.toString() + "\n";
    s += "Valor: " + valor;
    return s;
  }

  /**
   * Mezcla la lista de ciudades de la Solucion (this)
   */
  public void shuffle() {
    LinkedList<Ciudad> nuevaLista = new LinkedList<>();
    int size = listaCiudades.size();
    while(size > 0) {
      int index = rnd.nextInt(size);
      Ciudad ciudad = listaCiudades.get(index);
      nuevaLista.add(ciudad);
      listaCiudades.remove(ciudad);
      size--;
    }
    listaCiudades = nuevaLista;

    valor = evaluar();
  }

  /**
   * Intercambia dos ciudades aleatorias en la lista de ciudades
   */
  private void swapRandom() {
    int tamanio = listaCiudades.size();
    int index1 = rnd.nextInt(tamanio);
    int index2 = -1;
    while(index2 == index1 || index2 == -1) {
      index2 = rnd.nextInt(tamanio);
    }
    Ciudad c1 = listaCiudades.get(index1);
    listaCiudades.set(index1, listaCiudades.get(index2));
    listaCiudades.set(index2, c1);

    valor = evaluar();
  }
}
