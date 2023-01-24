package main.java.pacsim;

public class PosicionJugador extends Casillero {

	private static final String VALOR = " J ";

	@Override
	public String mostrarCasillero() {
		return VALOR;
	}

	@Override
	public void recibirMovimiento(Pac unPac) {
		System.out.println("Posicion del jugador");

	}

	@Override
	public boolean puedoMover() {
		return true;
	}

	@Override
	public void agregarProvision(Provision provision) {
	}

	@Override
	public boolean esSalida() {
		return false;
	}

	@Override
	public void agregarMina() {
	}

}
