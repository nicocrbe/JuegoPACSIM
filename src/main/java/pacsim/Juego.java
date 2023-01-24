package main.java.pacsim;

import java.io.IOException;

public class Juego {
	private static Juego unJuego;
	public Tablero tablero;
	public Pac unPac;
	public Casillero casillero;
	public boolean posicionValida = true;
	public LectorAdaptado unLector;

	private boolean gano;
	public String archivoDeEntrada;

	private Juego(String archivoDeEntrada) throws IOException {

		LectorAdaptado unLector = new LectorAdaptado(archivoDeEntrada);
		gano = false;

		int mapaMinas[][] = unLector.leerMinas();

		int mapaPared[][] = unLector.leerParedes();

		int mapaProvision[][] = unLector.leerUbicacionProvisiones();

		int xEntrada = unLector.leerXYEntrada().get(0);
		int yEntrada = unLector.leerXYEntrada().get(1);

		int xSalida = unLector.leerXYSalida().get(0);
		int ySalida = unLector.leerXYSalida().get(1);

		int alto = unLector.leerFilasColumnas().get(1);
		int ancho = unLector.leerFilasColumnas().get(0);

		tablero = Tablero.getTablero(alto, ancho, mapaMinas, mapaProvision, mapaPared, xEntrada, yEntrada, xSalida,
				ySalida);
		unPac = Pac.getPac(xEntrada, yEntrada);

		tablero.setPosJugador(unPac.getPosicionX(), unPac.getPosicionY());

	}

	public static Juego getJuego(String archivoDeEntrada) throws IOException {
		if (unJuego == null) {
			unJuego = new Juego(archivoDeEntrada);
		}
		return unJuego;
	}

	public Casillero casilleroAMover(String movimiento) {

		if (movimiento.equalsIgnoreCase("Arriba")
				&& comprobarPosicion(unPac.getPosicionX() - 1, unPac.getPosicionY())) {

			casillero = tablero.getCasillero(unPac.getPosicionX() - 1, unPac.getPosicionY());
			casillero.setPosicionX(unPac.getPosicionX() - 1);
			casillero.setPosicionY(unPac.getPosicionY());

		}
		if (movimiento.equalsIgnoreCase("Abajo") && comprobarPosicion(unPac.getPosicionX() + 1, unPac.getPosicionY())) {

			casillero = tablero.getCasillero(unPac.getPosicionX() + 1, unPac.getPosicionY());
			casillero.setPosicionX(unPac.getPosicionX() + 1);
			casillero.setPosicionY(unPac.getPosicionY());

		}

		if (movimiento.equalsIgnoreCase("Izquierda")
				&& comprobarPosicion(unPac.getPosicionX(), unPac.getPosicionY() - 1)) {

			casillero = tablero.getCasillero(unPac.getPosicionX(), unPac.getPosicionY() - 1);
			casillero.setPosicionX(unPac.getPosicionX());
			casillero.setPosicionY(unPac.getPosicionY() - 1);
		}

		if (movimiento.equalsIgnoreCase("Derecha")
				&& comprobarPosicion(unPac.getPosicionX(), unPac.getPosicionY() + 1)) {

			casillero = tablero.getCasillero(unPac.getPosicionX(), unPac.getPosicionY() + 1);
			casillero.setPosicionX(unPac.getPosicionX());
			casillero.setPosicionY(unPac.getPosicionY() + 1);

		}

		return casillero;
	}

	public boolean termino() {
		if (unPac.getVida() == 0 || gano == true) {
			return true;
		} else {

			return false;
		}
	}

	public void gano() {
		gano = true;

	}

	public void mover(Casillero casillero) {
		if (casillero.puedoMover()) {
			System.out.println();
			casillero.recibirMovimiento(unPac);
			tablero.setPosJugador(casillero.getPosicionX(), casillero.getPosicionY());
			tablero.setLibre(unPac.getPosicionX(), unPac.getPosicionY());

			unPac.moverPac(casillero.getPosicionX(), casillero.getPosicionY());

			unJuego.mostrarTablero();

		} else {
			System.out.println();
			casillero.recibirMovimiento(unPac);

			unJuego.mostrarTablero();
		}
	}

	public void mostrarPuntuacion() {
		if (gano == true) {
			System.out.println("GANASTE! Tu puntuacion es de: " + unPac.getVida() + " vidas.");
		} else {
			System.out.println("HAS PERDIDO! Te quedaste sin vidas.");
		}
	}

	public void mostrarTablero() {
		System.out.println("\r\n" + "==================== PacSim ==================");

		System.out.println();

		Casillero[][] tableroCompleto = tablero.obtenerTablero();

		for (int i = 0; i < tableroCompleto.length; i++) {

			System.out.print("|");

			for (int j = 0; j < tableroCompleto[i].length; j++) {
				System.out.print(tableroCompleto[i][j].mostrarCasillero());
			}

			System.out.print("|");
			System.out.println();
		}

	}

	public boolean comprobarPosicion(int posicionX, int posicionY) {

		try {

			posicionValida = true;

			if (posicionX < 0 || posicionY < 0 || posicionY > tablero.getAncho() - 1
					|| posicionX > tablero.getAlto() - 1) {

				posicionValida = false;
				System.out.println("No se realizo movimiento, llegaste al limite del tablero");

			}

		} catch (Exception e) {

		}
		return posicionValida;

	}

	public void resetearJuego() {
		unJuego = null;
		Tablero.resetTablero();
		Pac.resetPac();
	}
}
