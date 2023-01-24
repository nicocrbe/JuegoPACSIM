package main.java.pacsim;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Lector {

	public int posicionDatosTablero;
    public int posicionMinas;
    public int posicionProvisiones;
    public int posicionUbicacioneProvisiones;
    
	private static final String SEPARADOR = "=";

	public ArrayList<String> datos = new ArrayList<String>();

	public ArrayList<String> datosTablero = new ArrayList<String>();

	public String datosMinas;

	public ArrayList<String> datosProvisiones = new ArrayList<String>();

	public ArrayList<String> ubicacionesProvisiones = new ArrayList<String>();

	public void leerDatos(String archivoDatos) throws IOException {

		FileReader reader = new FileReader(archivoDatos);
		BufferedReader bufReader = new BufferedReader(reader);
		String linea;
		String dato;
		while ((linea = bufReader.readLine()) != null) {

			String[] li = linea.split(SEPARADOR);
			if (linea.contains(SEPARADOR) && li.length > 1) {

				dato = li[1].trim();
				datos.add(dato);

			} else if (!linea.contains(SEPARADOR)) {

				dato = linea;
				datos.add(dato);

			}
		}
		posicionDelimitadora();
		bufReader.close();
	}
	
	private void posicionDelimitadora() {

        for (int i = 0; i < datos.size(); i++) {


            if (datos.get(i).equals("[TABLERO]")) {

                posicionDatosTablero = i;


            }

            if (datos.get(i).equals("[MINAS]")) {

                posicionMinas = i;
            }

            if (datos.get(i).equals("[PROVISIONES]")) {

                posicionProvisiones = i;
        

            }
            if (datos.get(i).equals("[UBICACION_PROVISIONES]")) {

                posicionUbicacioneProvisiones = i;

            }
        }
	}
	
	public ArrayList<String> getDatosTablero(){
		for(int i=1;i<posicionMinas;i++){
			datosTablero.add(datos.get(i));
		}
		return datosTablero;
	}
	
	public String getDatosMinas(){
		for(int i=posicionMinas+1;i<posicionProvisiones;i++){
			datosMinas = datos.get(i);
		}
		return datosMinas;
	}
	public ArrayList<String> getDatosProvisiones(){
		for(int i=posicionProvisiones+1;i<posicionUbicacioneProvisiones;i++){
			datosProvisiones.add(datos.get(i));
		}
		return datosProvisiones;
	}
	public ArrayList<String> getUbicacionProvisiones(){
		for(int i=posicionUbicacioneProvisiones+1;i<datos.size();i++){
			ubicacionesProvisiones.add(datos.get(i));
		}
		return ubicacionesProvisiones;
	}
}
