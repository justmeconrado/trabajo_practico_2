package ar.edu.unju.fi.ejercicio07.model;


//Clase Producto
public class Producto {
	private String codigo;
	private String descripcion;
	private double precioUnitario;
	private Categoria categoria;
	private OrigenDeFabricacion origenDeFabricacion;
	private boolean estado;

//Constructores
	public Producto() {
	}

	public Producto(String codigo, String descripcion, double precioUnitario, Categoria categoria,
			OrigenDeFabricacion origenDeFabricacion, boolean estado) {
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.precioUnitario = precioUnitario;
		this.categoria = categoria;
		this.origenDeFabricacion = origenDeFabricacion;
		this.estado = estado;
	}

//Enumerados
	public enum Categoria {
		TELEFONIA, INFORMATICA, ELECTROHOGAR, HERRAMIENTAS;
	}

	public enum OrigenDeFabricacion {
		ARGENTINA, CHINA, BRASIL, URUGUAY;
	}

//Getters y Setters

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public OrigenDeFabricacion getOrigenDeFabricacion() {
		return origenDeFabricacion;
	}

	public void setOrigenDeFabricacion(OrigenDeFabricacion origenDeFabricacion) {
		this.origenDeFabricacion = origenDeFabricacion;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
}