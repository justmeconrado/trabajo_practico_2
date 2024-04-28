package ar.edu.unju.fi.ejercicio01.model;

// Clase Producto
public class Producto {
	private String codigo;
	private String descripcion;
	private double precioUnitario;
	private Categoria categoria;
	private OrigenDeFabricacion origenDeFabricacion;

// Constructores
	public Producto() {
	}

	public Producto(String codigo, String descripcion, double precioUnitario, Categoria categoria,
			OrigenDeFabricacion origenDeFabricacion) {
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.precioUnitario = precioUnitario;
		this.categoria = categoria;
		this.origenDeFabricacion = origenDeFabricacion;
	}

// Enumerados
	public enum Categoria {
		TELEFONIA, INFORMATICA, ELECTROHOGAR, HERRAMIENTAS;
	}

	public enum OrigenDeFabricacion {
		ARGENTINA, CHINA, BRASIL, URUGUAY;
	}

// Getters y Setters
	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecioUnitario() {
		return this.precioUnitario;
	}

	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public OrigenDeFabricacion getOrigenDeFabricacion() {
		return this.origenDeFabricacion;
	}

	public void setOrigenDeFabricacion(OrigenDeFabricacion origenDeFabricacion) {
		this.origenDeFabricacion = origenDeFabricacion;
	}

	@Override

	public String toString() {
		return "Producto [categoria=" + categoria + ", codigo=" + codigo + ", descripcion=" + descripcion
				+ ", origenDeFabricacion=" + origenDeFabricacion + ", precioUnitario=" + precioUnitario + "]";
	}
}