package recocido;

/**
 * Clase que modela una ciudad con sus coordenadas (x,y) en el plando.
 * @author Raúl González
 * @version 1.0
 */
public class Ciudad {
	double x; // Coordenada x
	double y; // Coordenada y
	int numeroCiudad; // Identificador de la ciudad

	public Ciudad() {}

	/**
	 * Constructor de la Ciudad basandose en las coordendas e identificador
	 * @param x Coordenada X de la ciudad
	 * @param y Coordenada Y de la ciudad
	 * @param numeroCiudad Identificador de la ciudad
	 */
	public Ciudad(double x, double y, int numeroCiudad) {
		this.x = x;
		this.y = y;
		this.numeroCiudad = numeroCiudad;
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