package entidades;

import java.sql.Array;
import java.time.LocalDateTime;

public class Empleado {
	private String id_empleado;
	private String nombre;
	private String telefono;
	private LocalDateTime fecha_incorporacion;
	private String id_empleado_sup;
	private Array col_nombre_sub;
	
	
	public Empleado() {
		super();
	}


	public Empleado(String id_empleado, String nombre, String telefono, LocalDateTime fecha_incorporacion,
			String id_empleado_sup, Array col_nombre_sub) {
		super();
		this.id_empleado = id_empleado;
		this.nombre = nombre;
		this.telefono = telefono;
		this.fecha_incorporacion = fecha_incorporacion;
		this.id_empleado_sup = id_empleado_sup;
		this.col_nombre_sub = col_nombre_sub;
	}


	public String getId_empleado() {
		return id_empleado;
	}


	public void setId_empleado(String id_empleado) {
		this.id_empleado = id_empleado;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public LocalDateTime getFecha_incorporacion() {
		return fecha_incorporacion;
	}


	public void setFecha_incorporacion(LocalDateTime fecha_incorporacion) {
		this.fecha_incorporacion = fecha_incorporacion;
	}


	public String getId_empleado_sup() {
		return id_empleado_sup;
	}


	public void setId_empleado_sup(String id_empleado_sup) {
		this.id_empleado_sup = id_empleado_sup;
	}


	public Array getCol_nombre_sub() {
		return col_nombre_sub;
	}


	public void setCol_nombre_sub(Array col_nombre_sub) {
		this.col_nombre_sub = col_nombre_sub;
	}


	@Override
	public String toString() {
		return "Empleado [id_empleado=" + id_empleado + ", nombre=" + nombre + ", telefono=" + telefono
				+ ", fecha_incorporacion=" + fecha_incorporacion + ", id_empleado_sup=" + id_empleado_sup
				+ ", col_nombre_sub=" + col_nombre_sub + "]";
	}
	
	
	
	
}
