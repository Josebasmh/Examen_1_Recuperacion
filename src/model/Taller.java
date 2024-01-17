package model;

public class Taller {

	private String codigo,nombre;
	private Float precio;
	private boolean disponible;
	
	/**
	 * Constructor de clase Taller
	 * @param c codigo
	 * @param n nombre
	 * @param p precio
	 * @param d dispinible
	 */
	public Taller(String c, String n, Float p, boolean d) {
		codigo=c;
		nombre=n;
		precio = p;
		disponible=d;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Float getPrecio() {
		return precio;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
	
}
