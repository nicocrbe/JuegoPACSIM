package main.java.excepciones;

@SuppressWarnings("serial")
public class ValoresNegativosException extends Exception {
	public ValoresNegativosException(){
		super("No pueden ingresarse valores negativos");
	}
}
