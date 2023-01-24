package main.java.pacsim;

public class Entrada extends Casillero {

	private static final String VALOR = " E ";

	@Override
	public void recibirMovimiento(Pac unPac) {
		System.out.println("Es la entrada ");
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
		return false;
	}

	@Override
	public void agregarProvision(Provision provision) {
	}

}
