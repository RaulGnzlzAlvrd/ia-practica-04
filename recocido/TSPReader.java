package recocido;

import java.util.LinkedList;
import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Clase que para sacar los datos de archivos .tsp
 * @author Raúl González
 * @version 1.0
 */
public class TSPReader {
	private static final String ROW_SERPARTOR = " ";
	private static final String COORD_SECTION = "NODE_COORD_SECTION";

	/**
	 * Saca las coordenadas de un archivo de ciudades y la convierte en una lista de Ciudad.
	 * 
	 * Para el formato del archivo se puede visitar la siguiente página:
	 * http://www.math.uwaterloo.ca/tsp/world/dj38.tsp
	 * @param filePath Path del archivo donde se está sacando los datos de las ciudades
	 * @return La lista con las ciudades que se leyeron.
	 */
	public static LinkedList<Ciudad> readFile(String filePath) throws IOException {
	  File file = new File(filePath);
	  if(!file.exists()) {
	    throw new IOException("Archivo no encontrado.");
	  }

	  LinkedList<Ciudad> listaCiudades = new LinkedList<>();
	  BufferedReader fileReader = new BufferedReader(new FileReader(file));
	  String row;
	  Ciudad ciudad;
	  while (!(row = fileReader.readLine()).contains(COORD_SECTION)) {}
	  while ((row = fileReader.readLine()) != null) {
	    String[] data = row.split(ROW_SERPARTOR);
	    float x = Float.parseFloat(data[1]); 
	    float y = Float.parseFloat(data[2]); 
	    int id = Integer.parseInt(data[0]); 
	    ciudad = new Ciudad(x, y, id);
	    listaCiudades.add(ciudad);
	  }
	  fileReader.close();
	  return listaCiudades;
	}
}