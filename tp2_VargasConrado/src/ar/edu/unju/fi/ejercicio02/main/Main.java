package ar.edu.unju.fi.ejercicio02.main;

import ar.edu.unju.fi.ejercicio02.model.Efemeride;
import ar.edu.unju.fi.ejercicio02.constante.Mes;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<Efemeride> efemerides = new ArrayList<>();

		while (true) {
			mostrarMenu();

			try {
				int opcion = sc.nextInt();
				sc.nextLine();

				switch (opcion) {
				case 1:
					crearEfemeride(efemerides, sc);
					break;
				case 2:
					mostrarEfemerides(efemerides);
					break;
				case 3:
					eliminarEfemeride(efemerides, sc);
					break;
				case 4:
					modificarEfemeride(efemerides, sc);
					break;
				case 5:
					System.out.println("Adios");
					return;
				default:
					System.out.println("Opción no válida. Intente nuevamente.");
				}
			} catch (InputMismatchException e) {
				System.out.println("Error: Por favor ingrese un valor numérico.");
				sc.nextLine();
			}
		}
	}

	private static void mostrarMenu() {
		System.out.println("\n\nMenú de Efemérides");
		System.out.println("-----------------");
		System.out.println("1. Crear efeméride");
		System.out.println("2. Mostrar efemérides");
		System.out.println("3. Eliminar efeméride");
		System.out.println("4. Modificar efeméride");
		System.out.println("5. Salir");
		System.out.print("Ingrese su opción: ");
	}

	private static void crearEfemeride(ArrayList<Efemeride> efemerides, Scanner sc) {
		try {
			System.out.print("Ingrese el código de la efeméride: ");
			int codigo = sc.nextInt();
			sc.nextLine(); // Consume newline character

			System.out.print("Ingrese el mes (1-12): ");
			int numeroMes = sc.nextInt();
			sc.nextLine(); // Consume newline character

			if (numeroMes < 1 || numeroMes > 12) {
				throw new IllegalArgumentException("Mes inválido. Ingrese un número entre 1 y 12.");
			}

			Mes mes = Mes.values()[numeroMes - 1];

			System.out.print("Ingrese el día del mes: ");
			int dia = sc.nextInt();
			sc.nextLine();

			System.out.print("Ingrese detalle de la efeméride: ");
			String detalle = sc.nextLine();

			Efemeride efemeride = new Efemeride(codigo, mes, dia, detalle);
			efemerides.add(efemeride);

			System.out.println("Efeméride creada exitosamente.");
		} catch (InputMismatchException e) {
			System.out.println("Error: Por favor ingrese un valor numérico válido.");
			sc.nextLine();
		} catch (IllegalArgumentException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	private static void mostrarEfemerides(ArrayList<Efemeride> efemerides) {
		System.out.println("\nLista de Efemérides:");
		for (Efemeride e : efemerides) {
			System.out.println("Código: " + e.getCodigo());
			System.out.println("Mes: " + e.getMes());
			System.out.println("Día: " + e.getDia());
			System.out.println("Detalle: " + e.getDetalle());
			System.out.println("--------------------");
		}
	}

	private static void eliminarEfemeride(ArrayList<Efemeride> efemerides, Scanner sc) {
		if (efemerides.isEmpty()) {
			System.out.println("No hay efemérides para eliminar.");
			return;
		}

		try {
			System.out.print("Ingrese el código de la efeméride a eliminar: ");
			int codigo = sc.nextInt();
			sc.nextLine();

			boolean removed = efemerides.removeIf(efemeride -> efemeride.getCodigo() == codigo);

			if (removed) {
				System.out.println("Efeméride eliminada exitosamente.");
			} else {
				System.out.println("No se encontró ninguna efeméride con ese código.");
			}
		} catch (InputMismatchException e) {
			System.out.println("Error: Por favor ingrese un valor numérico válido.");
			sc.nextLine();
		}
	}

	private static void modificarEfemeride(ArrayList<Efemeride> efemerides, Scanner sc) {
		if (efemerides.isEmpty()) {
			System.out.println("No hay efemérides para modificar.");
			return;
		}

		try {
			System.out.print("Ingrese el código de la efeméride a modificar: ");
			int codigo = sc.nextInt();
			sc.nextLine();

			Efemeride efemerideModificar = null;
			for (Efemeride e : efemerides) {
				if (e.getCodigo() == codigo) {
					efemerideModificar = e;
					break;
				}
			}

			if (efemerideModificar == null) {
				System.out.println("No se encontró ninguna efeméride con ese código.");
				return;
			}

			System.out.println("Ingrese los nuevos datos:");

			System.out.print("Nuevo código: ");
			int nuevoCodigo = sc.nextInt();
			sc.nextLine();
			efemerideModificar.setCodigo(nuevoCodigo);

			System.out.print("Nuevo mes (1-12): ");
			int nuevoMesNumber = sc.nextInt();
			sc.nextLine();
			if (nuevoMesNumber < 1 || nuevoMesNumber > 12) {
				throw new IllegalArgumentException("Mes inválido. Ingrese un número entre 1 y 12.");
			}
			Mes nuevoMes = Mes.values()[nuevoMesNumber - 1];
			efemerideModificar.setMes(nuevoMes);

			System.out.print("Nuevo día del mes: ");
			int nuevoDia = sc.nextInt();
			sc.nextLine();
			efemerideModificar.setDia(nuevoDia);

			System.out.println("Efeméride modificada exitosamente.");
		} catch (InputMismatchException e) {
			System.out.println("Error: Por favor ingrese un valor numérico válido.");
			sc.nextLine();
		} catch (IllegalArgumentException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}
