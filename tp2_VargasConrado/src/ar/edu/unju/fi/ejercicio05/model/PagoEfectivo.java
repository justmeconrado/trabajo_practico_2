package ar.edu.unju.fi.ejercicio05.model;

import java.time.LocalDate;
import ar.edu.unju.fi.ejercicio05.interfaces.IPago;

public class PagoEfectivo implements IPago {
	private double montoPagado;
	private LocalDate fechaDePago;

	// Getters y Setters
	public PagoEfectivo(double montoPagado, LocalDate fechaDePago) {
		super();
		this.montoPagado = montoPagado;
		this.fechaDePago = fechaDePago;
	}

	public double getMontoPagado() {
		return montoPagado;
	}

	public void setMontoPagado(double montoPagado) {
		this.montoPagado = montoPagado;
	}

	public LocalDate getFechaDePago() {
		return fechaDePago;
	}

	public void setFechaDePago(LocalDate fechaDePago) {
		this.fechaDePago = fechaDePago;
	}

	// Metodos de la interfaz IPago
	@Override
	public void realizarPago(double monto) {
		double descuento = monto * 0.10;
		setMontoPagado(monto - descuento); // restamos el descuento al monto total
	}

	@Override
	public void imprimirRecibo() {
		double montoSinDescuento = Math.floor(montoPagado / 0.90); // 1 - 0.10 = 0.90 (porcentaje sin descuento)
		System.out.println("\n===================================");
		System.out.println("\n>>>> RECIBO DE PAGO EN EFECTIVO <<<<");
		System.out.println("\nFecha de pago: " + fechaDePago);
		System.out.println("Monto sin descuento: $" + montoSinDescuento);
		System.out.println("Monto pagado con un descuento del 10%: $" + montoPagado);
		System.out.println("\n===================================");
	}

	@Override
	public String toString() {
		return "PagoEfectivo {" + "Fecha de pago: " + fechaDePago + ", Monto pagado: $" + montoPagado + '}';
	}

}
