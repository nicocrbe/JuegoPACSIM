package main.java.excepciones;

@SuppressWarnings("serial")
public class ValorFueraDelTableroException extends Exception {
	public ValorFueraDelTableroException(){
		super("No pueden setearse posiciones que sobrepasen N�filas*N�columnas");
	}
}
