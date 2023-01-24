package main.java.pacsim;

import java.io.IOException;
import java.util.Scanner;

public class InterfazJugador {

	private static final int INCORRECTO = 10;

	public static Juego unJuego;

	public static Pac unPac;
	public static Tablero tablero;

	public static void main(String[] args) throws IOException {

		iniciarJuego();

	}

	private static void iniciarJuego() throws IOException {
		

		unJuego = Juego.getJuego("src/main/resources/Configuracion2.txt");

		Scanner sc = new Scanner(System.in);

		unPac = unJuego.unPac;

		tablero = unJuego.tablero;

		unJuego.mostrarTablero();

		menuMovimientos(sc);

		unJuego.mostrarPuntuacion();

		resetPartida(sc);
		sc.close();
	}

	public static void menuMovimientos(Scanner sc) {

		String leido;
		int opc = 0;

		do {
			bordeSuperiorMenu();
			System.out.println("Elija un movimiento:");
			System.out.println("\r\n" + "1. Arriba ");
			System.out.println("2. Abajo");
			System.out.println("3. Izquierda");
			System.out.println("4. Derecha");
			System.out.print("Opcion ->");

			try {
				leido = sc.nextLine();
				opc = Integer.parseInt(leido);
			} catch (Exception e) {
				opc = INCORRECTO;
			}

			switch (opc) {

			case 1:

				Casillero casillero = unJuego.casilleroAMover("Arriba");
				if (unJuego.posicionValida) {
					if (!casillero.esSalida()) {
						unJuego.mover(casillero);

					} else {
						unJuego.gano();
					}
				}
				break;
			case 2:

				Casillero casillero2 = unJuego.casilleroAMover("Abajo");
				if (unJuego.posicionValida) {
					if (!casillero2.esSalida()) {
						unJuego.mover(casillero2);

					} else {
						unJuego.gano();
					}
				}
				break;
			case 3:
				Casillero casillero3 = unJuego.casilleroAMover("Izquierda");
				if (unJuego.posicionValida) {
					if (!casillero3.esSalida()) {
						unJuego.mover(casillero3);

					} else {
						unJuego.gano();
					}
				}
				break;

			case 4:
				Casillero casillero4 = unJuego.casilleroAMover("Derecha");
				if (unJuego.posicionValida) {
					if (!casillero4.esSalida()) {
						unJuego.mover(casillero4);

					} else {
						unJuego.gano();
					}
				}
				break;
			default:
				System.out.println("Debes introducir SOLAMENTE numeros del 1 al 4");
				break;
			}
		} while (opc != -1 && !unJuego.termino());

	}

	private static void resetPartida(Scanner sc) throws IOException {

		System.out.println("�Jugar otra vez? (si/no)");

		String decision;
		decision = sc.next();

		while (!decision.equalsIgnoreCase("si") && !decision.equalsIgnoreCase("no")) {
			System.out.println("Comando invalido, debe ingresarse si o no");
			decision = sc.nextLine();
		}
		if (decision.equalsIgnoreCase("si")) {
			resetearInterfaz();
		}

	}

	private static void resetearInterfaz() throws IOException {
		unJuego.resetearJuego();
		iniciarJuego();
	}

	private static void bordeSuperiorMenu() {
		System.out.println("\r\n" + "=========== MENU ============");
	}

}
