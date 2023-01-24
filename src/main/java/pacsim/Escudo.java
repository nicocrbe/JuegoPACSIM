package main.java.pacsim;

public class Escudo  extends Provision {
	
	private int escudoDeFuerza = 1;
	
	private String tipo = "Escudo";

	public Escudo() {

	}

	
	@Override
	public void efecto(Pac unPac) {
		
		System.out.println("Encontrate un Escudo, tu escudo actual es :  " + unPac.getEscudo());

		unPac.aumentarEscudo(escudoDeFuerza);
		 
		System.out.println("Sumado te queda : " + unPac.getEscudo());

	}
	
	@Override
	public String getTipo() {
		return tipo;
	}

}
