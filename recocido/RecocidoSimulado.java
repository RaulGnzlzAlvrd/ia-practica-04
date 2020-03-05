package recocido;

import java.util.Random;

/**
 * Interface con los metodos necesarios para implementar el metodo
 * de recocido simulado junto con la solucion a un problema particular.
 *
 * @author Benjamin Torres
 * @version 0.1
 */
public class RecocidoSimulado {

  private float temperaturaInicial;
  private float factorDeEnfriamiento;
  private float temperaturaFinal;
  
  private int iteraciones;
  private float temperatura;

  Solucion solucionInicial; // Solución con la que inicia la iteración.
  Solucion mejorSolucion; // La mejor solución obtenidad a lo largo de todas las iteraciones.

  Random rnd = new Random();

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
        solucion = seleccionarSiguienteSolucion(solucion);
        actualizarTemperatura();
      }
    }
    return mejorSolucion;
  }
}
