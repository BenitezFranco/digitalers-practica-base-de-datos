package entidades;

import java.sql.Array;

public class Cliente {
	private String id_cliente;
	private String nombre;
	private String calle;
	private String id_empleado;
	private boolean prestamo;
	private Array col_cuenta;
	
	
	
	public Cliente() {
		super();
	}
	
	public Cliente(String id_cliente, String nombre, String calle, String id_empleado, boolean prestamo,
			Array col_cuenta) {
		super();
		this.id_cliente = id_cliente;
		this.nombre = nombre;
		this.calle = calle;
		this.id_empleado = id_empleado;
		this.prestamo = prestamo;
		this.col_cuenta = col_cuenta;
	}
	
	
	public String getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(String id_cliente) {
		this.id_cliente = id_cliente;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public String getId_empleado() {
		return id_empleado;
	}
	public void setId_empleado(String id_empleado) {
		this.id_empleado = id_empleado;
	}
	public boolean isPrestamo() {
		return prestamo;
	}
	public void setPrestamo(boolean prestamo) {
		this.prestamo = prestamo;
	}
	public Array getCol_cuenta() {
		return col_cuenta;
	}
	public void setCol_cuenta(Array col_cuenta) {
		this.col_cuenta = col_cuenta;
	}

	@Override
	public String toString() {
		return "Cliente [id_cliente=" + id_cliente + ", nombre=" + nombre + ", calle=" + calle + ", id_empleado="
				+ id_empleado + ", prestamo=" + prestamo + ", col_cuenta=" + col_cuenta + "]";
	}
	
	
}
