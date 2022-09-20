package entidades;

import java.sql.Array;

public class Cuenta {
	private String id_cuenta;
	private String tipo;
	private Array col_cliente;
	
	
	public Cuenta() {
		super();
	}


	public Cuenta(String id_cuenta, String tipo, Array col_cliente) {
		super();
		this.id_cuenta = id_cuenta;
		this.tipo = tipo;
		this.col_cliente = col_cliente;
	}


	public String getId_cuenta() {
		return id_cuenta;
	}


	public void setId_cuenta(String id_cuenta) {
		this.id_cuenta = id_cuenta;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public Array getCol_cliente() {
		return col_cliente;
	}


	public void setCol_cliente(Array col_cliente) {
		this.col_cliente = col_cliente;
	}


	@Override
	public String toString() {
		return "Cuenta [id_cuenta=" + id_cuenta + ", tipo=" + tipo + ", col_cliente=" + col_cliente + "]";
	}
	
	
	
	
}
