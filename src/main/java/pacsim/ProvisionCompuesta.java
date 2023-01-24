package main.java.pacsim;

import java.util.ArrayList;
import java.util.List;

public class ProvisionCompuesta extends Provision {

	List<Provision> provisiones;

	public String tipo = "Compuesta";

	public ProvisionCompuesta() {
		this.provisiones = new ArrayList<Provision>();
	}

	@Override
	public void add(Provision provision) {

		this.provisiones.add(provision);

	}

	@Override
	public void remove(Provision provision) {

		this.provisiones.remove(provision);

	}

	@Override
	public void efecto(Pac unPac) {

		for (Provision p : provisiones) {

			p.efecto(unPac);

		}

	}

	public int getSize() {
		return provisiones.size();
	}

	@Override
	public String getTipo() {
		return tipo;
	}
}
