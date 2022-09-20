package jdbc.implementaciones;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entidades.Cuenta;
import jdbc.DAO;

public class CuentaImplementacion implements DAO<Cuenta, String> {

	private PreparedStatement sentenciaPreparadaBusqueda;
	private PreparedStatement sentenciaPreparadaInsertar;
	private PreparedStatement sentenciaPreparadaEliminar;
	private PreparedStatement sentenciaPreparadaActualizar;
	private PreparedStatement sentenciaPreparadaListar;
	private Connection conexion;

	public CuentaImplementacion() {
		super();
	}

	public CuentaImplementacion(Connection conexion) {
		super();
		this.conexion = conexion;
	}

	public Cuenta buscarPorClavePrimaria(String id_cuenta) {
		String query = "Select id_cuenta, tipo, col_cliente from cuentas where id_cuenta = ?";
		try {
			if (sentenciaPreparadaBusqueda == null) {
				sentenciaPreparadaBusqueda = conexion.prepareStatement(query);
			}
			sentenciaPreparadaBusqueda.setString(1, id_cuenta);

			ResultSet resultado = sentenciaPreparadaBusqueda.executeQuery();

			if (resultado.next()) {
				String tipo = resultado.getString("tipo");
				Array col_cliente = (resultado.getArray("col_cliente"));

				Cuenta cuenta = new Cuenta(id_cuenta, tipo, col_cliente);
				return cuenta;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean insertar(Cuenta e) {
		if (e == null) {
			return false;
		}
		String query = "insert into cuentas (id_cuenta,tipo,col_cliente) values (?,?,?)";
		try {
			if (sentenciaPreparadaInsertar == null) {
				sentenciaPreparadaInsertar = conexion.prepareStatement(query);
			}

			sentenciaPreparadaInsertar.setString(1, e.getId_cuenta());
			sentenciaPreparadaInsertar.setString(2, e.getTipo());
			sentenciaPreparadaInsertar.setArray(3, e.getCol_cliente());
			return sentenciaPreparadaInsertar.executeUpdate() == 1;
		} catch (SQLException e1) {
			e1.printStackTrace();

		}
		return false;
	}

	@Override
	public boolean actualizar(Cuenta e) {
		if (e == null) {
			return false;
		}
		String query = "update cuentas set tipo = ?, col_cliente = ? where id_cuenta = ?";
		try {
			if (sentenciaPreparadaActualizar == null) {
				sentenciaPreparadaActualizar = conexion.prepareStatement(query);
			}

			sentenciaPreparadaActualizar.setString(1, e.getTipo());
			sentenciaPreparadaActualizar.setArray(2, e.getCol_cliente());
			sentenciaPreparadaActualizar.setString(3, e.getId_cuenta());

			return sentenciaPreparadaActualizar.executeUpdate() == 1;

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean eliminar(Cuenta e) {
		if (e == null) {
			return false;
		}
		String query = "delete from cuentas where id_cuenta = ?";
		try {
			if (sentenciaPreparadaEliminar == null) {
				sentenciaPreparadaEliminar = conexion.prepareStatement(query);
			}
			sentenciaPreparadaEliminar.setString(1, e.getId_cuenta());

			return sentenciaPreparadaEliminar.executeUpdate() == 1;

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Cuenta> listar() {
		List<Cuenta> cuentas = new ArrayList<>();
		String query = "Select id_cuenta, tipo , col_cliente from cuentas";

		try {
			if (sentenciaPreparadaListar == null) {
				sentenciaPreparadaListar = conexion.prepareStatement(query);
			}
			ResultSet resultado = sentenciaPreparadaListar.executeQuery();

			while (resultado.next()) {
				Cuenta cuenta = new Cuenta();
				cuenta.setId_cuenta(resultado.getString("id_cuenta"));
				cuenta.setTipo(resultado.getString("tipo"));
				cuenta.setCol_cliente(resultado.getArray("col_cliente"));
				cuentas.add(cuenta);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cuentas;
	}

}
