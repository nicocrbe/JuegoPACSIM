package main.java.pacsim;

public class Pared extends Casillero {

	private static final String VALOR = " X ";

	@Override
	public void recibirMovimiento(Pac unPac) {

		System.out.println("Hay una pared , no es posible mover");

	}

	@Override
	public boolean puedoMover() {

		return false;
	}

	@Override
	public String mostrarCasillero() {
		return VALOR;
	}

	@Override
	public boolean esSalida() {
		return false;
	}

	@Override
	public void agregarProvision(Provision provision) {
	}

	@Override
	public void agregarMina() {
	}

}
