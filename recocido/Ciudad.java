package recocido;

/**
 * Clase que modela una ciudad con sus coordenadas (x,y) en el plando.
 * @author Raúl González
 * @version 1.0
 */
public class Ciudad {
	float x; // Coordenada x
	float y; // Coordenada y
	int numeroCiudad; // Identificador de la ciudad

	/**
	 * Constructor de la Ciudad basandose en las coordendas e identificador
	 * @param x Coordenada X de la ciudad
	 * @param y Coordenada Y de la ciudad
	 * @param numeroCiudad Identificador de la ciudad
	 */
	public Ciudad(float x, float y, int numeroCiudad) {
		this.x = x;
		this.y = y;
		this.numeroCiudad = numeroCiudad;
	}

	/**
	 * Obtiene la distancia entre la Ciudad (this) y la ciudad pasada como parámetro
	 * @param ciudad La ciudad de la que se quiere sacar su distancia.
	 * @return La distancia entre las dos ciudades
	 */
	public float calculaDistancia(Ciudad otra) {
		float x1 = this.x;
		float y1 = this.y;
		float x2 = otra.x;
		float y2 = otra.y;
		return (float) (Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)));
	}

	/**
	 * Regresa la representación en String de la Ciudad
	 * @return La ciudad en formato "numeroCiudad: (x,y)"
	 */
	@Override
	public String toString(){
		return this.numeroCiudad + ": (" + this.x + "," + this.y + ")";
	}
}