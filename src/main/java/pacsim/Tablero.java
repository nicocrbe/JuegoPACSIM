package main.java.pacsim;

import java.io.IOException;
import java.util.*;

public class Tablero {

	private Casillero[][] tableroPac;
	private static Tablero unTablero;
	private int alto;
	private int ancho;
	private LectorAdaptado lector;
	public Casillero casillero;

	private Tablero(int alto, int ancho, int[][] mapaMinas, int[][] mapaProvisiones, int[][] mapaPared, int xEntrada,
			int yEntrada, int xSalida, int ySalida) throws IOException {

		this.lector = new LectorAdaptado("src/main/resources/Configuracion.txt");
		this.tableroPac = new Casillero[alto][ancho];
		this.alto = alto;
		this.ancho = ancho;
		llenarTablero(mapaMinas, mapaProvisiones, mapaPared, xEntrada, yEntrada, xSalida, ySalida);

	}

	public static Tablero getTablero(int alto, int ancho, int[][] mapaMinas, int[][] mapaProvisiones, int[][] mapaPared,
			int xEntrada, int yEntrada, int xSalida, int ySalida) throws IOException {

		if (unTablero == null) {
			unTablero = new Tablero(alto, ancho, mapaMinas, mapaProvisiones, mapaPared, xEntrada, yEntrada, xSalida,
					ySalida);
		}

		return unTablero;
	}

	private void llenarTablero(int[][] mapaMinas, int[][] mapaProvisiones, int[][] mapaPared, int xEntrada,
			int yEntrada, int xSalida, int ySalida) {

		llenarLibres();

		llenarMinas(mapaMinas);
		llenarProvisiones(mapaProvisiones);
		llenarParedes(mapaPared);

		setearEntradaYSalida(xEntrada, yEntrada, xSalida, ySalida);
	}

	private void setearEntradaYSalida(int xEntrada, int yEntrada, int xSalida, int ySalida) {

		this.tableroPac[xEntrada][yEntrada] = new Entrada();
		this.tableroPac[xSalida][ySalida] = new Salida();

	}

	public Casillero getCasillero(int xPosicion, int yPosicion) {

		for (int i = 0; i < tableroPac.length; i++) {

			for (int j = 0; j < tableroPac[i].length; j++) {

				if (i == xPosicion && j == yPosicion) {

					casillero = tableroPac[i][j];
				}

			}

		}

		return casillero;
	}

	private void llenarLibres() {

		for (int i = 0; i < this.tableroPac.length; i++) {
			for (int j = 0; j < this.tableroPac[i].length; j++) {

				tableroPac[i][j] = new Normal();

			}
		}

	}

	private void llenarMinas(int[][] mapaMinas) {

		for (int i = 0; i < mapaMinas.length; i++) {
			for (int j = 0; j < mapaMinas[i].length; j++) {
				if (mapaMinas[i][j] == 1) {

					tableroPac[i][j].agregarMina();

				}
			}
		}

	}

	private void llenarProvisiones(int[][] mapaProvisiones) {

		for (int i = 0; i < mapaProvisiones.length; i++) {
			for (int j = 0; j < mapaProvisiones[i].length; j++) {
				if (mapaProvisiones[i][j] != 0) {

					int numProvision = mapaProvisiones[i][j];
					agregarProvision(i, j, numProvision);
				}
			}
		}

	}

	private void agregarProvision(int i, int j, int numProvision) { // INCOMPLETO

		lector.leerProvisiones();
		TreeMap<Integer, ArrayList<Provision>> mapaProvisiones = lector.getMapaProvisiones();
		Iterator<Integer> itr = mapaProvisiones.keySet().iterator();

		while (itr.hasNext()) {

			int sig = itr.next();

			if (sig == numProvision) {

				for (Provision provision : mapaProvisiones.get(sig)) {
					tableroPac[i][j].agregarProvision(provision);
				}
			}

		}
	}

	private void llenarParedes(int[][] mapaPared) {

		for (int i = 0; i < mapaPared.length; i++) {
			for (int j = 0; j < mapaPared[i].length; j++) {
				if (mapaPared[i][j] == 1) {

					Pared pared = new Pared();

					this.tableroPac[i][j] = pared;
				}
			}
		}

	}

	public Casillero obtenerCasillero(int x, int y) {
		return tableroPac[x][y];
	}

	public void setPosJugador(int x, int y) {
		tableroPac[x][y] = new PosicionJugador();
	}

	public void setLibre(int x, int y) {
		tableroPac[x][y] = new Normal();
	}

	public int getAlto() {
		return this.alto;
	}

	public int getAncho() {
		return this.ancho;
	}

	public Casillero[][] obtenerTablero() {
		return this.tableroPac;
	}

	public static void resetTablero() {
		unTablero = null;
	}

	public void mostrarTableroTest() {

		Casillero[][] tableroCompleto = unTablero.obtenerTablero();

		for (int i = 0; i < tableroCompleto.length; i++) {

			System.out.print("|");

			for (int j = 0; j < tableroCompleto[i].length; j++) {
				System.out.print(tableroCompleto[i][j].mostrarCasillero());
			}

			System.out.print("|");
			System.out.println();
		}

	}

}
