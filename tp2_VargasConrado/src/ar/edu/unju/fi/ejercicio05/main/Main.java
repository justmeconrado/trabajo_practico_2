package ar.edu.unju.fi.ejercicio05.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import ar.edu.unju.fi.ejercicio05.model.PagoEfectivo;
import ar.edu.unju.fi.ejercicio05.model.PagoTarjeta;
import ar.edu.unju.fi.ejercicio05.model.Producto;
import ar.edu.unju.fi.ejercicio05.model.Producto.Categoria;
import ar.edu.unju.fi.ejercicio05.model.Producto.OrigenDeFabricacion;

public class Main {

	public static void main(String[] args) {
		ArrayList<Producto> productos = new ArrayList<>();
		Scanner sc = new Scanner(System.in);

		int opcion = 0;
		precargarProductos(productos);

		try {
			do {
				System.out.println("\nMENÚ DE OPCIONES");
				System.out.println("1 - Mostrar productos");
				System.out.println("2 - Realizar compra");
				System.out.println("3 - Salir");
				System.out.print("Seleccione una opción: ");

				try {
					opcion = sc.nextInt();
					sc.nextLine(); // Limpiar el buffer del scanner

					switch (opcion) {
					case 1:
						System.out.println("\nMostrando productos disponibles:");
						mostrarProductos(productos, sc);
						break;
					case 2:
						ArrayList<Producto> carrito = new ArrayList<>();
						System.out.println("\nIniciando proceso de compra:");
						realizarCompra(productos, carrito, sc);
						break;
					case 3:
						System.out.println("\nSaliendo de la tienda. ¡Gracias por su visita!");
						break;
					default:
						System.out.println("\n¡Opción no válida! Por favor, intenta de nuevo.");
					}
				} catch (InputMismatchException e) {
					System.out.println("\nError: por favor ingresa un número entero válido.");
					sc.nextLine(); // Limpiar el buffer del scanner
				}
			} while (opcion != 3);
		} finally {
			sc.close(); // Cerrar el scanner al finalizar
		}
	}

	public static void realizarCompra(ArrayList<Producto> productos, ArrayList<Producto> carrito, Scanner scanner) {
		boolean continuarCompra = true;

		while (continuarCompra) {
			boolean agregarProducto = true;

			while (agregarProducto) {
				try {
					System.out.println("¿Desea agregar algún producto al carrito? (Ingrese 'S' para sí, 'N' para no):");
					String respuesta = scanner.next().toUpperCase();
					scanner.nextLine(); // Limpiar el buffer del scanner

					if (respuesta.equals("N")) {
						continuarCompra = false;
						break;
					}

					System.out.println(
							"Por favor, ingrese el número correspondiente al producto que desea agregar al carrito:");
					int indiceProducto = scanner.nextInt();
					scanner.nextLine(); // Limpiar el buffer del scanner

					if (indiceProducto < 1 || indiceProducto > productos.size()) {
						System.out.println("¡Lo siento! El número de producto ingresado es inválido.");
						continue;
					}

					Producto productoSeleccionado = productos.get(indiceProducto - 1);
					if (!productoSeleccionado.isEstado()) {
						System.out.println(
								"¡Lo siento! El producto seleccionado está fuera de stock y no puede ser agregado al carrito.");
						continue;
					}

					carrito.add(productoSeleccionado);
					System.out.println(productoSeleccionado.getDescripcion() + " $"
							+ productoSeleccionado.getPrecioUnitario() + " ha sido agregado al carrito.");
				} catch (InputMismatchException e) {
					System.out.println("\nError: por favor ingresa un número entero válido.");
					scanner.nextLine(); // Limpiar el buffer del scanner
				}
			}

			double totalCompra = calcularTotal(carrito);

			if (totalCompra == 0) {
				System.out.println(
						"No seleccionó ningún producto o no hay stock de el/los producto/s seleccionado/s. ¿Desea comprar algo?");
				System.out.println("1 - Sí");
				System.out.println("2 - No, volver al menú anterior");

				try {
					int opcion = scanner.nextInt();
					if (opcion == 2) {
						System.out.println("Volviendo al menú anterior...");
						return;
					}
				} catch (InputMismatchException e) {
					System.out.println("\nError: por favor ingresa un número entero válido.");
					scanner.nextLine(); // Limpiar el buffer del scanner
				}
			} else {
				try {
					System.out.println("\nProductos seleccionados:");
					for (Producto producto : carrito) {
						System.out.println("- " + producto.getDescripcion() + ": $" + producto.getPrecioUnitario());
					}
					System.out.println("\nEl total de la compra es: $" + totalCompra);
					System.out.println("\nPor favor, seleccione el método de pago:");
					System.out.println("1 - Pago en efectivo");
					System.out.println("2 - Pago con tarjeta");
					System.out.print("Seleccione una opción de pago: ");

					int opcionPago = scanner.nextInt();

					switch (opcionPago) {
					case 1:
						procesarPagoEfectivo(totalCompra, scanner);
						continuarCompra = false;
						break;
					case 2:
						procesarPagoTarjeta(totalCompra, scanner);
						continuarCompra = false;
						break;
					default:
						System.out.println("¡La opción de pago seleccionada no es válida!");
					}
				} catch (InputMismatchException e) {
					System.out.println("\nError: por favor ingresa un número entero válido.");
					scanner.nextLine(); // Limpiar el buffer del scanner
				}
			}
		}
	}

	private static void procesarPagoEfectivo(double totalCompra, Scanner scanner) {
		try {
			PagoEfectivo pagoEfectivo = new PagoEfectivo(totalCompra, LocalDate.now());
			pagoEfectivo.realizarPago(totalCompra);
			pagoEfectivo.imprimirRecibo();
			scanner.nextLine(); // Limpiar el buffer del scanner
		} catch (Exception e) {
			System.out.println("\n¡Error en el pago en efectivo!");
			System.out.println(
					"\nHa ocurrido un error al procesar el pago en efectivo. Por favor, intente nuevamente o seleccione otro método de pago.");
		}
	}

	private static void procesarPagoTarjeta(double totalCompra, Scanner scanner) {
		System.out.println("\nPor favor, ingrese el número de tarjeta:");
		String numeroTarjeta = scanner.next();

		try {
			PagoTarjeta pagoTarjeta = new PagoTarjeta(numeroTarjeta, LocalDate.now(), totalCompra);
			pagoTarjeta.realizarPago(totalCompra);
			pagoTarjeta.imprimirRecibo();
			scanner.nextLine(); // Limpiar el buffer del scanner
		} catch (Exception e) {
			System.out.println("\n¡Error en el pago con tarjeta!");
			System.out.println(
					"\nHa ocurrido un error al procesar el pago con tarjeta. Por favor, asegúrese de ingresar el número de tarjeta correcto y vuelva a intentarlo.");
		}
	}

	public static double calcularTotal(ArrayList<Producto> carrito) {
		double total = 0;
		for (Producto producto : carrito) {
			total += producto.getPrecioUnitario() * cantidadDelProductoEnElCarrito(producto, carrito);
		}
		return total;
	}

	public static int cantidadDelProductoEnElCarrito(Producto producto, ArrayList<Producto> carrito) {
		int cantidad = 0;
		for (Producto p : carrito) {
			if (p.equals(producto)) {
				cantidad++;
			}
		}
		return cantidad;
	}

	public static void precargarProductos(ArrayList<Producto> productos) {
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

	public static void mostrarProductos(ArrayList<Producto> productos, Scanner sc) {
		System.out.println("LISTA DE PRODUCTOS");
		for (Producto p : productos) {
			System.out.println("===================================");
			System.out.println("Código: " + p.getCodigo());
			System.out.println("Descripción: " + p.getDescripcion());
			System.out.println("Precio: $" + p.getPrecioUnitario());
			System.out.println("Estado: " + (p.isEstado() ? "Disponible" : "No disponible"));
			System.out.println("Categoría: " + p.getCategoria());
			System.out.println("Origen: " + p.getOrigenDeFabricacion());
			System.out.println("===================================");
		}
	}

}
