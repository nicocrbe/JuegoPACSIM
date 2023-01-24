package main.java.pacsim;

public class Salida extends Casillero {

	private static final String VALOR = " S ";

	@Override
	public void recibirMovimiento(Pac unPac) {
		System.out.println("Encontraste la salida , GANASTE !!!");
	}

	@Override
	public boolean puedoMover() {
		return true;
	}
	
	@Override
	public String mostrarCasillero() {
		return VALOR;
	}

	@Override
	public void agregarMina() {
	}

	@Override
	public boolean esSalida() {
		return true;
	}

	@Override
	public void agregarProvision(Provision provision) {
	}

}
