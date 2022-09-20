package jdbc.implementaciones;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import entidades.Empleado;
import jdbc.DAO;

public class EmpleadoImplementacion implements DAO<Empleado, String> {

	private PreparedStatement sentenciaPreparadaBusqueda;
	private PreparedStatement sentenciaPreparadaInsertar;
	private PreparedStatement sentenciaPreparadaEliminar;
	private PreparedStatement sentenciaPreparadaActualizar;
	private PreparedStatement sentenciaPreparadaListar;
	private Connection conexion;
	
	
	public EmpleadoImplementacion() {
		super();
	}

	public EmpleadoImplementacion(Connection conexion) {
		super();
		this.conexion = conexion;
	}

	@Override
	public Empleado buscarPorClavePrimaria(String k) {
		String query = "Select id_empleado, nombre, telefono, fecha_incorporacion, id_empleado_sup, col_nombre_sub from empleados where id_empleado = ?";
		try {
			if (sentenciaPreparadaBusqueda == null) {
				sentenciaPreparadaBusqueda = conexion.prepareStatement(query);
			}
			sentenciaPreparadaBusqueda.setString(1, k);

			ResultSet resultado = sentenciaPreparadaBusqueda.executeQuery();

			if (resultado.next()) {
				String nombre = resultado.getString("nombre");
				String telefono = resultado.getString("telefono");
				LocalDateTime fecha = LocalDateTime.parse(resultado.getString("fecha_incorporacion"));
				String id_empleado_sup = resultado.getString("id_empleado_sup");
				Array col_nombre_sub = (resultado.getArray("col_nombre_sub"));

				Empleado empleado = new Empleado(k, nombre, telefono, fecha, id_empleado_sup, col_nombre_sub);

				return empleado;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean insertar(Empleado e) {
		if (e == null) {
			return false;
		}
		String query = "insert into empleados (id_empleado,nombre,telefono,fecha_incorporacion,id_empleado_sup, col_nombre_sub) values (?,?,?,NOW(),?,?)";
		try {
			if (sentenciaPreparadaInsertar == null) {
				sentenciaPreparadaInsertar = conexion.prepareStatement(query);
			}

			sentenciaPreparadaInsertar.setString(1, e.getId_empleado());
			sentenciaPreparadaInsertar.setString(2, e.getNombre());
			sentenciaPreparadaInsertar.setString(3, e.getTelefono());
			sentenciaPreparadaInsertar.setString(4, e.getId_empleado_sup());
			sentenciaPreparadaInsertar.setArray(5, e.getCol_nombre_sub());
			return sentenciaPreparadaInsertar.executeUpdate() == 1;
		} catch (SQLException e1) {
			e1.printStackTrace();

		}
		return false;
	}

	public boolean actualizar(Empleado e) {
		if (e == null) {
			return false;
		}
		String query = "update empleados set telefono = ?, col_nombre_sub = ? where id_empleado = ?";
		try {
			if (sentenciaPreparadaActualizar == null) {
				sentenciaPreparadaActualizar = conexion.prepareStatement(query);
			}

			sentenciaPreparadaActualizar.setString(1, e.getTelefono());
			sentenciaPreparadaActualizar.setArray(2, e.getCol_nombre_sub());
			sentenciaPreparadaActualizar.setString(3, e.getId_empleado());

			return sentenciaPreparadaActualizar.executeUpdate() == 1;

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return false;
	}

	public boolean eliminar(Empleado e) {
		if (e == null) {
			return false;
		}
		String query = "delete from empleados where id_empleado = ?";
		try {
			if (sentenciaPreparadaEliminar == null) {
				sentenciaPreparadaEliminar = conexion.prepareStatement(query);
			}
			sentenciaPreparadaEliminar.setString(1, e.getId_empleado());

			return sentenciaPreparadaEliminar.executeUpdate() == 1;

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Empleado> listar() {
		List<Empleado> empleados = new ArrayList<>();
		String query = "Select id_empleado, nombre, telefono, fecha_incorporacion, id_empleado_sup, col_nombre_sub from empleados";

		try {
			if (sentenciaPreparadaListar == null) {
				sentenciaPreparadaListar = conexion.prepareStatement(query);
			}
			ResultSet resultado = sentenciaPreparadaListar.executeQuery();

			while (resultado.next()) {
				Empleado empleado = new Empleado();
				empleado.setId_empleado(resultado.getString("id_empleado"));
				empleado.setNombre(resultado.getString("nombre"));
				empleado.setTelefono(resultado.getString("telefono"));
				empleado.setFecha_incorporacion(LocalDateTime.parse(resultado.getString("fecha_incorporacion")));
				empleado.setId_empleado_sup(resultado.getString("id_empleado_sup"));
				empleado.setCol_nombre_sub(resultado.getArray("col_nombre_sub"));
				empleados.add(empleado);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return empleados;
	}

	public Connection getConexion() {
		return conexion;
	}

	public void setConexion(Connection conexion) {
		this.conexion = conexion;
	}
	
	

}
