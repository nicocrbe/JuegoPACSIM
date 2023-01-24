package main.java.pacsim;

import java.util.*;

public class Normal extends Casillero {

	public Mina mina;

	public boolean hayMina = false;

	public List<Provision> listaDeProvisiones = new ArrayList<Provision>();

	private static final String VALOR = "   ";

	public Normal() {

	}

	public void agregarMina() {

		this.mina = Mina.getMina();
		hayMina = true;
	}

	public void agregarProvision(Provision provision) {
		this.listaDeProvisiones.add(provision);
	}

	public void recibirMovimiento(Pac unPac) {

		if (listaDeProvisiones.size() > 0) {
			for (Provision provision : listaDeProvisiones) {

				provision.efecto(unPac);
			}

		}

		if (hayMina) {

			mina.efecto(unPac);

		}

	}

	public boolean puedoMover() {

		return true;
	}

	@Override
	public String mostrarCasillero() {
		return VALOR;
	}

	@Override
	public boolean esSalida() {
		return false;
	}

}
