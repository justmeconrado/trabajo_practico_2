package ar.edu.unju.fi.ejercicio04.main;

import ar.edu.unju.fi.ejercicio04.constante.Posicion;
import ar.edu.unju.fi.ejercicio04.model.Jugador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		ArrayList<Jugador> jugadores = new ArrayList<>();
		Scanner scanner = new Scanner(System.in);

		int opcion;
		do {
			mostrarMenu();

			try {
				opcion = scanner.nextInt();
				scanner.nextLine();

				switch (opcion) {
				case 1:
					altaJugador(jugadores, scanner);
					break;
				case 2:
					mostrarJugadores(jugadores);
					break;
				case 3:
					modificarPosicion(jugadores, scanner);
					break;
				case 4:
					eliminarJugador(jugadores, scanner);
					break;
				case 5:
					System.out.println("\nGracias, vuelva pronto.\n");
					break;
				default:
					System.out.println("\nOpción inválida. Intente nuevamente.\n");
				}
			} catch (InputMismatchException e) {
				System.out.println("\nError: Por favor ingrese un número entero.\n");
				scanner.nextLine();
				opcion = 0;
			}

		} while (opcion != 5);

		scanner.close();
	}

	private static void mostrarMenu() {
		
		System.out.println("\n\nMenú de opciones:");
		System.out.println("------------------------------");
		System.out.println("1 - Alta de jugador");
		System.out.println("2 - Mostrar todos los jugadores");
		System.out.println("3 - Modificar la posición de un jugador");
		System.out.println("4 - Eliminar un jugador");
		System.out.println("5 - Salir");
		System.out.println("------------------------------");
		System.out.print("\nIngrese su opción: ");
	}

	private static void altaJugador(ArrayList<Jugador> jugadores, Scanner scanner) {
		try {
			System.out.println("\nIngrese los datos del jugador:");
			System.out.println("------------------------------");

			System.out.println("Nombre:");
			String nombre = scanner.nextLine();

			System.out.println("Apellido:");
			String apellido = scanner.nextLine();

			System.out.println("Fecha de nacimiento (DD/MM/AAAA):");
			String fechaNacimientoString = scanner.nextLine();
			LocalDate fechaNacimiento = LocalDate.of(Integer.parseInt(fechaNacimientoString.substring(6)),
					Integer.parseInt(fechaNacimientoString.substring(3, 5)),
					Integer.parseInt(fechaNacimientoString.substring(0, 2)));

			if (fechaNacimiento.isAfter(LocalDate.now())) {
				throw new IllegalArgumentException("La fecha de nacimiento no puede ser en el futuro.");
			}

			System.out.println("Nacionalidad:");
			String nacionalidad = scanner.nextLine();

			System.out.println("Estatura (en metros, por ejemplo 1,80):");
			double estatura = scanner.nextDouble();
			if (estatura <= 0) {
				throw new IllegalArgumentException("La estatura debe ser un valor positivo.");
			}

			System.out.println("Peso (en kilogramos, por ejemplo 70,5):");
			double peso = scanner.nextDouble();
			if (peso <= 0) {
				throw new IllegalArgumentException("El peso debe ser un valor positivo.");
			}
			scanner.nextLine();

			System.out.println("Posición:");
			System.out.println("-------------");
			System.out.println("1 - Delantero");
			System.out.println("2 - Medio");
			System.out.println("3 - Defensa");
			System.out.println("4 - Arquero");
			System.out.println("-------------");
			int posicionIndex = scanner.nextInt();
			Posicion posicion = Posicion.values()[posicionIndex - 1];

			Jugador jugador = new Jugador(nombre, apellido, fechaNacimiento, nacionalidad, estatura, peso, posicion);
			jugadores.add(jugador);

			System.out.println("\nJugador agregado correctamente.\n");
		} catch (NumberFormatException e) {
			System.out.println(
					"\nError: Formato de fecha incorrecto. Por favor ingrese la fecha en el formato DD/MM/AAAA.\n");
		} catch (InputMismatchException e) {
			System.out.println("\nError: Por favor ingrese un número válido para la estatura y el peso.\n");
			scanner.nextLine();
		} catch (IllegalArgumentException e) {
			System.out.println("\nError: " + e.getMessage() + "\n");
		}
	}

	private static void mostrarJugadores(ArrayList<Jugador> jugadores) {
		if (jugadores.isEmpty()) {
			System.out.println("\nNo hay jugadores registrados.\n");
		} else {
			System.out.println("\nLista de jugadores:\n");
			for (Jugador jugador : jugadores) {
				System.out.println("Nombre: " + jugador.getNombre() + "\nApellido: " + jugador.getApellido()
						+ "\nFecha de Nacimiento: " + jugador.getFechaNacimiento().getDayOfMonth() + "/"
						+ jugador.getFechaNacimiento().getMonthValue() + "/" + jugador.getFechaNacimiento().getYear()
						+ "\nNacionalidad: " + jugador.getNacionalidad() + "\nEstatura: "
						+ String.format("%.2f", jugador.getEstatura()).replace(".", ",") + " m" + "\nPeso: "
						+ String.format("%.2f", jugador.getPeso()).replace(".", ",") + " kg" + "\nPosición: "
						+ jugador.getPosicion() + "\n------------------------------");
			}
		}
	}

	private static void modificarPosicion(ArrayList<Jugador> jugadores, Scanner scanner) {
		System.out.println("\nIngrese el nombre del jugador:");
		String nombre = scanner.nextLine();

		System.out.println("Ingrese el apellido del jugador:");
		String apellido = scanner.nextLine();

		boolean encontrado = false;
		for (Jugador jugador : jugadores) {
			if (jugador.getNombre().equalsIgnoreCase(nombre) && jugador.getApellido().equalsIgnoreCase(apellido)) {
				System.out.println("Ingrese la nueva posición:");
				System.out.println("-------------");
				System.out.println("1 - Delantero");
				System.out.println("2 - Medio");
				System.out.println("3 - Defensa");
				System.out.println("4 - Arquero");
				System.out.println("-------------");
				int posicionIndex = scanner.nextInt();
				Posicion nuevaPosicion = Posicion.values()[posicionIndex - 1];
				jugador.setPosicion(nuevaPosicion);
				encontrado = true;
				System.out.println("\nPosición modificada correctamente.\n");
				break;
			}
		}

		if (!encontrado) {
			System.out.println("\nNo se encontró al jugador con ese nombre y apellido.\n");
		}
	}

	private static void eliminarJugador(ArrayList<Jugador> jugadores, Scanner scanner) {
		System.out.println("\nIngrese el nombre del jugador:");
		String nombre = scanner.nextLine();

		System.out.println("Ingrese el apellido del jugador:");
		String apellido = scanner.nextLine();

		boolean encontrado = false;
		for (int i = 0; i < jugadores.size(); i++) {
			Jugador jugador = jugadores.get(i);
			if (jugador.getNombre().equalsIgnoreCase(nombre) && jugador.getApellido().equalsIgnoreCase(apellido)) {
				jugadores.remove(i);
				encontrado = true;
				System.out.println("\nJugador eliminado correctamente.\n");
				break;
			}
		}

		if (!encontrado) {
			System.out.println("\nNo se encontró al jugador con ese nombre y apellido.\n");
		}
	}
}
