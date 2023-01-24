package main.java.excepciones;

@SuppressWarnings("serial")
public class DatosNulosException extends Exception {
	public DatosNulosException(){
		super("Deben ingresarse valores en el archivo de configuracion");
	}
}
