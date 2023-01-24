package main.java.excepciones;

@SuppressWarnings("serial")
public class FilasColumnasIgualesACeroException extends Exception {
	public FilasColumnasIgualesACeroException(){
		super("No pueden ingresarse filas y columnas iguales a 0");
	}
}
