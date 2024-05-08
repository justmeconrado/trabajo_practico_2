package ar.edu.unju.fi.ejercicio07.main;

import ar.edu.unju.fi.ejercicio07.model.Producto.Categoria;
import ar.edu.unju.fi.ejercicio07.model.Producto.OrigenDeFabricacion;
import ar.edu.unju.fi.ejercicio07.model.Producto;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		ArrayList<Producto> productos = new ArrayList<>();

		precargarProductos(productos);

		Scanner scanner = new Scanner(System.in);
		int opcion;

		do {
			System.out.println("Menú:");
			System.out.println("1 - Mostrar productos (solo con estado true)");
			System.out.println("2 - Mostrar productos faltantes (estado false)");
			System.out.println("3 - Incrementar precios de los productos en un 20%");
			System.out.println("4 - Mostrar productos de la categoría Electrohogar y disponibles");
			System.out.println("5 - Ordenar productos por precio de forma descendente");
			System.out.println("6 - Mostrar nombres de productos en mayúsculas");
			System.out.println("0 - Salir");
			System.out.print("Ingrese opción: ");
			opcion = scanner.nextInt();

			switch (opcion) {
			case 1:
				// Opción 1: Mostrar productos con estado true
				productos.stream().filter(Producto::isEstado).forEach(producto -> {
					System.out.println("Código: " + producto.getCodigo());
					System.out.println("Descripción: " + producto.getDescripcion());
					System.out.println("Precio Unitario: " + producto.getPrecioUnitario());
					System.out.println("Categoría: " + producto.getCategoria());
					System.out.println("Origen de Fabricación: " + producto.getOrigenDeFabricacion());
					System.out.println("Estado: " + producto.isEstado());
					System.out.println();
				});
				break;
			case 2:
				// Opción 2: Mostrar productos faltantes (estado false)
				productos.stream().filter(producto -> !producto.isEstado()).forEach(producto -> {
					System.out.println("Código: " + producto.getCodigo());
					System.out.println("Descripción: " + producto.getDescripcion());
					System.out.println("Precio Unitario: " + producto.getPrecioUnitario());
					System.out.println("Categoría: " + producto.getCategoria());
					System.out.println("Origen de Fabricación: " + producto.getOrigenDeFabricacion());
					System.out.println("Estado: " + producto.isEstado());
					System.out.println();
				});
				break;
			case 3:
				// Opción 3: Incrementar precios de los productos en un 20%
				ArrayList<Producto> productosIncrementados = new ArrayList<>();
				productos.stream().map(producto -> {
					double nuevoPrecio = producto.getPrecioUnitario() * 1.2;
					Producto p = new Producto(producto.getCodigo(), producto.getDescripcion(), nuevoPrecio,
							producto.getCategoria(), producto.getOrigenDeFabricacion(), producto.isEstado());
					productosIncrementados.add(p);
					return p;
				}).forEach(producto -> {
					System.out.println("Código: " + producto.getCodigo());
					System.out.println("Descripción: " + producto.getDescripcion());
					System.out.println("Precio con 20% de aumento: " + producto.getPrecioUnitario());
					System.out.println("Categoría: " + producto.getCategoria());
					System.out.println("Origen de Fabricación: " + producto.getOrigenDeFabricacion());
					System.out.println("Estado: " + producto.isEstado());
					System.out.println();
				});
				break;
			case 4:
				// Opción 4: Mostrar productos de la categoría Electrohogar y disponibles
				productos.stream().filter(
						producto -> producto.getCategoria() == Producto.Categoria.ELECTROHOGAR && producto.isEstado())
						.forEach(producto -> {
							System.out.println("Código: " + producto.getCodigo());
							System.out.println("Descripción: " + producto.getDescripcion());
							System.out.println("Precio Unitario: " + producto.getPrecioUnitario());
							System.out.println("Categoría: " + producto.getCategoria());
							System.out.println("Origen de Fabricación: " + producto.getOrigenDeFabricacion());
							System.out.println("Estado: " + producto.isEstado());
							System.out.println();
						});
				break;
			case 5:
				// Opción 5: Ordenar productos por precio de forma descendente
				productos.stream().sorted((p1, p2) -> Double.compare(p2.getPrecioUnitario(), p1.getPrecioUnitario()))
						.forEach(producto -> {
							System.out.println("Código: " + producto.getCodigo());
							System.out.println("Descripción: " + producto.getDescripcion());
							System.out.println("Precio Unitario: " + producto.getPrecioUnitario());
							System.out.println("Categoría: " + producto.getCategoria());
							System.out.println("Origen de Fabricación: " + producto.getOrigenDeFabricacion());
							System.out.println("Estado: " + producto.isEstado());
							System.out.println();
						});
				break;
			case 6:
				// Opción 6: Mostrar nombres de productos en mayúsculas
				productos.stream().map(producto -> {
					producto.setDescripcion(producto.getDescripcion().toUpperCase());
					return producto;
				}).forEach(producto -> {
					System.out.println("Código: " + producto.getCodigo());
					System.out.println("Descripción: " + producto.getDescripcion());
					System.out.println("Precio Unitario: " + producto.getPrecioUnitario());
					System.out.println("Categoría: " + producto.getCategoria());
					System.out.println("Origen de Fabricación: " + producto.getOrigenDeFabricacion());
					System.out.println("Estado: " + producto.isEstado());
					System.out.println();
				});
				break;
			case 0:
				// Salir
				System.out.println("Saliendo del programa...");
				break;
			default:
				System.out.println("Opción no válida. Por favor, ingrese una opción válida.");
				break;
			}
		} while (opcion != 0);

		scanner.close();
	}

	private static void precargarProductos(ArrayList<Producto> productos) {
		if (productos.isEmpty()) {
			productos.add(new Producto("1", "Heladera Philips", 1200000, Categoria.ELECTROHOGAR,
					OrigenDeFabricacion.ARGENTINA, true));
			productos.add(new Producto("2", "Smartwatch Samsung", 250000, Categoria.TELEFONIA,
					OrigenDeFabricacion.CHINA, true));
			productos.add(new Producto("3", "Tablet HP", 1800000, Categoria.INFORMATICA, OrigenDeFabricacion.ARGENTINA,
					true));
			productos.add(new Producto("4", "Taladro Black & Decker", 95000, Categoria.HERRAMIENTAS,
					OrigenDeFabricacion.CHINA, true));
			productos.add(
					new Producto("5", "TV LG", 1700000, Categoria.ELECTROHOGAR, OrigenDeFabricacion.URUGUAY, true));
			productos.add(new Producto("6", "Teclado Logitech", 45000, Categoria.INFORMATICA, OrigenDeFabricacion.CHINA,
					true));
			productos.add(new Producto("7", "Cafetera Oster", 80000, Categoria.ELECTROHOGAR, OrigenDeFabricacion.BRASIL,
					false));
			productos.add(new Producto("8", "Horno eléctrico", 140000, Categoria.ELECTROHOGAR,
					OrigenDeFabricacion.URUGUAY, true));
			productos.add(new Producto("9", "Impresora Epson", 110000, Categoria.INFORMATICA, OrigenDeFabricacion.CHINA,
					true));
			productos.add(new Producto("10", "Martillo Stanley", 60000, Categoria.HERRAMIENTAS,
					OrigenDeFabricacion.CHINA, true));
			productos.add(new Producto("11", "Refrigerador Mabe", 1300000, Categoria.ELECTROHOGAR,
					OrigenDeFabricacion.BRASIL, false));
			productos.add(new Producto("12", "Router TP-Link", 75000, Categoria.INFORMATICA, OrigenDeFabricacion.CHINA,
					true));
			productos.add(new Producto("13", "Aspiradora Electrolux", 95000, Categoria.ELECTROHOGAR,
					OrigenDeFabricacion.URUGUAY, false));
			productos.add(new Producto("14", "Destornillador Phillips", 25000, Categoria.HERRAMIENTAS,
					OrigenDeFabricacion.CHINA, false));
			productos.add(new Producto("15", "Licuadora Philips", 70000, Categoria.ELECTROHOGAR,
					OrigenDeFabricacion.ARGENTINA, false));
		}

	}
}
