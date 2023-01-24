package main.java.pacsim;

import java.io.IOException;
import java.util.*;

import main.java.excepciones.*;

public class LectorAdaptado {

	private TreeMap<Integer, ArrayList<Provision>> mapaProvisiones;

	private static Lector unLector;
	private int[][] tablero;

	private int filas;
	private int columnas;

	public LectorAdaptado(String archivoDeEntrada) throws IOException {
		unLector = new Lector();
		unLector.leerDatos(archivoDeEntrada);
		comprobarNull();
		mapaProvisiones = new TreeMap<Integer, ArrayList<Provision>>();
		this.columnas = leerFilasColumnas().get(0);
		this.filas = leerFilasColumnas().get(1);
		tablero = new int[filas][columnas];
	}

	public void comprobarNull() {
		try {
			if (unLector.getDatosTablero() == null
					|| unLector.getDatosMinas() == null
					|| unLector.getDatosProvisiones() == null
					|| unLector.getUbicacionProvisiones() == null) {

				throw new DatosNulosException();
			} else if (unLector.getDatosTablero().size() < 5) {
				throw new DatosNulosException();
			}
		} catch (DatosNulosException e) {
			System.out.println(e.getMessage());
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

	public ArrayList<Integer> leerFilasColumnas() {

		ArrayList<Integer> cantFilasCol = new ArrayList<Integer>();

		try {
			if (Integer.parseInt(unLector.getDatosTablero().get(0)) < 0
					|| Integer.parseInt(unLector.getDatosTablero().get(1)) < 0) {
				throw new ValoresNegativosException();
			} else if (Integer.parseInt(unLector.getDatosTablero().get(0)) == 0
					|| Integer.parseInt(unLector.getDatosTablero().get(1)) == 0) {
				throw new FilasColumnasIgualesACeroException();
			} else {
				cantFilasCol.add(Integer.parseInt(unLector.getDatosTablero()
						.get(0)));
				cantFilasCol.add(Integer.parseInt(unLector.getDatosTablero()
						.get(1)));
			}
		} catch (ValoresNegativosException e) {
			System.out.println(e.getMessage());
		} catch (FilasColumnasIgualesACeroException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return cantFilasCol;
	}

	public TreeMap<Integer, ArrayList<Provision>> getMapaProvisiones() {
		return mapaProvisiones;
	}

	public ArrayList<Integer> leerXYEntrada() {

		ArrayList<Integer> posXYEntrada = new ArrayList<Integer>();

		int entrada = Integer.parseInt(unLector.getDatosTablero().get(3));

		posXYEntrada = adaptarUnaPosicion(entrada);

		return posXYEntrada;
	}

	public ArrayList<Integer> leerXYSalida() {

		ArrayList<Integer> posXYSalida = new ArrayList<Integer>();

		int salida = Integer.parseInt(unLector.getDatosTablero().get(2));

		posXYSalida = adaptarUnaPosicion(salida);

		return posXYSalida;
	}

	public int[][] leerParedes() {
		String paredes = unLector.getDatosTablero().get(4);
		int[][] mapaParedes = new int[this.filas][this.columnas];
		int c = 0;

		while (c < (filas * columnas)) {
			char ch = paredes.charAt(c);
			if (ch == '1') {

				int fila = adaptarUnaPosicion(c).get(0);
				int columna = adaptarUnaPosicion(c).get(1);

				mapaParedes[fila][columna] = 1;

			}
			c++;
		}
		return mapaParedes;
	}

	public int[][] leerMinas() {
		String listaSinAdaptar = unLector.getDatosMinas();
		int[][] mapaMinas = new int[this.filas][this.columnas];

		String[] separadas;

		separadas = listaSinAdaptar.split(",");

		for (String unElemento : separadas) {
			int posicion = Integer.parseInt(unElemento);
			int fila = adaptarUnaPosicion(posicion).get(0);
			int columna = adaptarUnaPosicion(posicion).get(1);

			mapaMinas[fila][columna] = 1;
		}
		return mapaMinas;

	}

	public void leerProvisiones() {
		List<String> datosProv = new ArrayList<String>();

		datosProv = unLector.getDatosProvisiones();

		int i = 1;

		for (String unaProvision : datosProv) {
			ArrayList<Provision> listaProvisiones = new ArrayList<Provision>();
			if (!unaProvision.contains(",")) {
				if (unaProvision.equals("E") || unaProvision.equals("V")) {
					if (unaProvision.equals("E")) {

						listaProvisiones.add(new Escudo());
					} else if (unaProvision.equals("V")) {

						listaProvisiones.add(new Vitamina());
					}
					mapaProvisiones.put(i, listaProvisiones);
				} else {
					ProvisionCompuesta c1 = new ProvisionCompuesta();

					String p = unaProvision.substring(1);
					int pos = Integer.parseInt(p);

					if (mapaProvisiones.containsKey(pos)) {
						if (mapaProvisiones.get(pos).size() > 1) {
							for (Provision provision : mapaProvisiones.get(pos)) {
								c1.add(provision);
							}
							listaProvisiones.add(c1);
						} else {
							listaProvisiones.addAll(mapaProvisiones.get(pos));
						}
					}

					mapaProvisiones.put(i, listaProvisiones);
				}

			} else if (unaProvision.contains(",")) {

				String[] linea = unaProvision.split(",");

				ProvisionCompuesta provisionesSimples = new ProvisionCompuesta();
				ProvisionCompuesta provisionesCompuestas = new ProvisionCompuesta();

				for (String elemento : linea) {

					if (elemento.equals("E") || elemento.equals("V")) {
						if (elemento.equals("E")) {
							provisionesSimples.add(new Escudo());
						} else if (elemento.equals("V")) {
							provisionesSimples.add(new Vitamina());
						}
					} else {

						String p = elemento.substring(1);
						int pos = Integer.parseInt(p);

						if (mapaProvisiones.containsKey(pos)) {
							for (Provision provision : mapaProvisiones.get(pos)) {
								provisionesCompuestas.add(provision);
							}
						}
					}
				}

				if (provisionesCompuestas.getSize() > 0) {
					listaProvisiones.add(provisionesCompuestas);
				}
				if (provisionesSimples.getSize() > 0) {
					listaProvisiones.add(provisionesSimples);
				}

				mapaProvisiones.put(i, listaProvisiones);
			}
			i++;
		}
	}

	public int[][] leerUbicacionProvisiones() {

		List<String> provisionesLeidas = new ArrayList<String>();
		int[][] mapaProvisiones = new int[this.filas][this.columnas];

		provisionesLeidas = unLector.getUbicacionProvisiones();
		int i = 1;
		for (String unaUbicacion : provisionesLeidas) {

			if (!unaUbicacion.contains(",")) {

				int posicion = Integer.parseInt(unaUbicacion);
				int fila = adaptarUnaPosicion(posicion).get(0);
				int columna = adaptarUnaPosicion(posicion).get(1);

				mapaProvisiones[fila][columna] = i;

			} else if (unaUbicacion.contains(",")) {

				String[] linea = unaUbicacion.split(",");
				for (int j = 0; j < linea.length; j++) {

					int posicion = Integer.parseInt(linea[j]);
					int fila = adaptarUnaPosicion(posicion).get(0);
					int columna = adaptarUnaPosicion(posicion).get(1);

					mapaProvisiones[fila][columna] = i;

				}
			}
			i++;
		}
		return mapaProvisiones;

	}

	public ArrayList<Integer> adaptarUnaPosicion(int posicion) {
		ArrayList<Integer> posicionesXY = new ArrayList<Integer>();
		try {

			if (posicion > (this.filas * this.columnas)) {
				throw new ValorFueraDelTableroException();
			} else if (posicion < 0) {
				throw new ValoresNegativosException();
			} else {

				int contador = -1;

				for (int i = 0; i < tablero.length; i++) {
					for (int j = 0; j < tablero[i].length; j++) {
						contador++;
						if (contador == posicion) {
							posicionesXY.add(i);
							posicionesXY.add(j);
						}
					}
				}
			}
		} catch (ValorFueraDelTableroException e) {
			System.out.println(e.getMessage());
		} catch (ValoresNegativosException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return posicionesXY;
	}

	public void mostrar(int[][] listaAdaptada) {
		String unaFila = "";

		for (int i = 0; i < listaAdaptada.length; i++) {
			unaFila = "";
			for (int j = 0; j < listaAdaptada[i].length; j++) {
				if (listaAdaptada[i][j] != 0) {
					unaFila += listaAdaptada[i][j];
				} else {
					unaFila += "0";
				}
			}
			System.out.println(unaFila);
		}
	}
}