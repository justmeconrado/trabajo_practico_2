package ar.edu.unju.fi.ejercicio05.model;

import java.time.LocalDate;

import ar.edu.unju.fi.ejercicio05.interfaces.IPago;

public class PagoTarjeta implements IPago {
	private String numeroTarjeta;
	private LocalDate fechaDePago;
	private double montoPagado;

	public PagoTarjeta(String numeroTarjeta, LocalDate fechaDePago, double montoPagado) {
		super();
		this.numeroTarjeta = numeroTarjeta;
		this.fechaDePago = fechaDePago;
		this.montoPagado = montoPagado;
	}

//Getters y Setters
	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public LocalDate getFechaDePago() {
		return fechaDePago;
	}

	public void setFechaDePago(LocalDate fechaDePago) {
		this.fechaDePago = fechaDePago;
	}

	public double getMontoPagado() {
		return montoPagado;
	}

	public void setMontoPagado(double montoPagado) {
		this.montoPagado = montoPagado;
	}

	// Metodos de la interfaz IPago
	@Override
	public void realizarPago(double monto) {
		double recargo = monto * 0.15; // Calculamos el recargo del 15%
		setMontoPagado(monto + recargo); // Sumamos el recargo al monto total
	}

	@Override
	public void imprimirRecibo() {
		double montoSinRecargo = Math.floor(montoPagado / 1.15);

		System.out.println("\n===================================");
		System.out.println("\n>>>> RECIBO DE PAGO CON TARJETA <<<<");
		System.out.println("\nNúmero de tarjeta: " + numeroTarjeta);
		System.out.println("Fecha de pago: " + fechaDePago);
		System.out.println("Monto total sin recargo: $" + montoSinRecargo);
		System.out.println("Monto pagado con recargo del 15%: $" + montoPagado);
		System.out.println("\n===================================");
	}

	@Override
	public String toString() {
		return "PagoTarjeta {" + "Fecha de pago: " + fechaDePago + ", Monto pagado: $" + montoPagado
				+ ", Número de tarjeta: " + numeroTarjeta + '}';
	}

}
