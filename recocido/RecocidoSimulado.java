package recocido;

import java.util.Random;
import java.util.LinkedList;

/**
 * Interface con los metodos necesarios para implementar el metodo
 * de recocido simulado junto con la solucion a un problema particular.
 *
 * @author Benjamin Torres
 * @version 0.1
 */
public class RecocidoSimulado {

  private float temperaturaInicial; // Guarda la temperatura que se tendrá al inicio de cada iteración
  private float temperaturaFinal; // Guarda la temperatura a la que se detendrá cada iteración
  private float factorDeEnfriamiento; // Guarda el factor por el que se va a multiplicar la temperatura.
  
  private int iteraciones; // Número de iteraciones para la ejecución.
  private float temperatura; // Temperatura que se modificará a lo largo de cada iteración

  Solucion solucionInicial; // Solución con la que inicia la iteración.
  Solucion mejorSolucion; // La mejor solución obtenidad a lo largo de todas las iteraciones.

  LinkedList<Float>[] historial; // Lista con el historial de los valores de las soluciones que se han calculado.
  
  Random rnd = new Random(); // Auxiliar para decisiones al azar.

  /**
   * Inicializa los valores necesarios para realizar 
   * recocido simulado durante un numero determinado de iteraciones
   * @param solucionInicial La una solución cualquiera para el problema.
   * @param iteraciones El numero de iteraciones que se va a ejecutar el algoritmo
   * @param temperaturaInicial La temperatura con la que empezará cada iteración
   * @param temperaturaFinal La temperatura con la que finalizará cada iteración
   * @param factorDeEnfriamiento El factor por el que se va a multiplicar la temperatura en cada iteración (Entre cero y uno)
   */
  public RecocidoSimulado(
    Solucion solucionInicial, 
    int iteraciones, 
    float temperaturaInicial, 
    float temperaturaFinal, 
    float factorDeEnfriamiento
  ) {
    this.solucionInicial = solucionInicial;
    mejorSolucion = solucionInicial;
    this.iteraciones = iteraciones;
    this.temperaturaInicial = temperaturaInicial;
    this.temperaturaFinal = temperaturaFinal;
    this.factorDeEnfriamiento = factorDeEnfriamiento;

    historial = new LinkedList[iteraciones];
    for(int i = 0; i < iteraciones; i++)
      if(historial[i] == null)
        historial[i] = new LinkedList<Float>();
  }

  /**
   * Funcion que actualiza la temperatura en base a
   * la anterior y el factor de enfriamiento usado.
   */
  public void actualizarTemperatura() {
    temperatura *= factorDeEnfriamiento;
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
      temperatura = temperaturaInicial;
      while(temperatura > temperaturaFinal){
        historial[i].add(solucion.valor);
        solucion = seleccionarSiguienteSolucion(solucion);
        actualizarTemperatura();
      }
    }
    return mejorSolucion;
  }

  /**
   * Regresa el historial de los valores de cada solución.
   * Es un array que contien en la posición i una lista con los valores calculados en la iteración i.
   *
   * @return Array con las listas correspondientes a cada iteración. 
   */
  public LinkedList<Float>[] getHistorial() {
    return historial;
  }
}
