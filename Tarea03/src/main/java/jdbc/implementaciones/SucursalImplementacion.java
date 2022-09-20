package jdbc.implementaciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidades.Sucursal;
import jdbc.DAO;

public class SucursalImplementacion implements DAO<Sucursal, String> {

	private PreparedStatement sentenciaPreparadaBusqueda;
	private PreparedStatement sentenciaPreparadaInsertar;
	private PreparedStatement sentenciaPreparadaEliminar;
	private PreparedStatement sentenciaPreparadaActualizar;
	private PreparedStatement sentenciaPreparadaListar;
	private Connection conexion;

	public SucursalImplementacion() {
		super();
	}

	public SucursalImplementacion(Connection conexion) {
		super();
		this.conexion = conexion;
	}

	@Override
	public Sucursal buscarPorClavePrimaria(String k) {
		String query = "Select nombre, ciudad from sucursales where nombre = ?";
		try {
			if (sentenciaPreparadaBusqueda == null) {
				sentenciaPreparadaBusqueda = conexion.prepareStatement(query);
			}
			sentenciaPreparadaBusqueda.setString(1, k);

			ResultSet resultado = sentenciaPreparadaBusqueda.executeQuery();

			if (resultado.next()) {
				String ciudad = resultado.getString("ciudad");

				Sucursal sucursal = new Sucursal(k, ciudad);
				return sucursal;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean insertar(Sucursal e) {
		if (e == null) {
			return false;
		}
		String query = "insert into sucursales (nombre,ciudad) values (?,?)";
		try {
			if (sentenciaPreparadaInsertar == null) {
				sentenciaPreparadaInsertar = conexion.prepareStatement(query);
			}

			sentenciaPreparadaInsertar.setString(1, e.getNombre());
			sentenciaPreparadaInsertar.setString(2, e.getCiudad());
			return sentenciaPreparadaInsertar.executeUpdate() == 1;
		} catch (SQLException e1) {
			e1.printStackTrace();

		}
		return false;
	}

	@Override
	public boolean actualizar(Sucursal e) {
		if (e == null) {
			return false;
		}
		String query = "update sucursales set ciudad = ? where nombre = ?";
		try {
			if (sentenciaPreparadaActualizar == null) {
				sentenciaPreparadaActualizar = conexion.prepareStatement(query);
			}

			sentenciaPreparadaActualizar.setString(1, e.getCiudad());
			sentenciaPreparadaActualizar.setString(2, e.getNombre());

			return sentenciaPreparadaActualizar.executeUpdate() == 1;

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return false;
	}

	public boolean eliminar(Sucursal e) {
		if (e == null) {
			return false;
		}
		String query = "delete from sucursales where nombre = ?";
		try {
			if (sentenciaPreparadaEliminar == null) {
				sentenciaPreparadaEliminar = conexion.prepareStatement(query);
			}
			sentenciaPreparadaEliminar.setString(1, e.getNombre());

			return sentenciaPreparadaEliminar.executeUpdate() == 1;

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return false;
	}

	public List<Sucursal> listar() {
		List<Sucursal> sucursales = new ArrayList<>();
		String query = "Select nombre , ciudad from sucursales";

		try {
			if (sentenciaPreparadaListar == null) {
				sentenciaPreparadaListar = conexion.prepareStatement(query);
			}
			ResultSet resultado = sentenciaPreparadaListar.executeQuery();

			while (resultado.next()) {
				Sucursal sucursal = new Sucursal();
				sucursal.setNombre(resultado.getString("nombre"));
				sucursal.setCiudad(resultado.getString("ciudad"));
				sucursales.add(sucursal);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sucursales;
	}

	public Connection getConexion() {
		return conexion;
	}

	public void setConexion(Connection conexion) {
		this.conexion = conexion;
	}

}
