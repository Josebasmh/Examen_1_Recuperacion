package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexion.ConexionBD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Taller;

public class TallerDao {
	
	private ConexionBD conexion;

	/**
	 * Carga los registros de la BBDD y los añade a un ObservableList que devuelve. 
	 * @return lista de talleres.
	 */
	public ObservableList<Taller> cargarDatos() {
		ObservableList<Taller>listaTaller= FXCollections.observableArrayList();
		String consulta = "SELECT * FROM inventario;";
		PreparedStatement pstmt;
		try {
			conexion = new ConexionBD();
			pstmt = conexion.getConexion().prepareStatement(consulta);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String codigo = rs.getString("cod");
				String nombre = rs.getString("nombre_inventario");
				Float precio = rs.getFloat("precio_inventario");
				boolean disponible = rs.getBoolean("disponibilidad_inventario");
				
				listaTaller.add(new Taller(codigo, nombre, precio, disponible));
			}			
			rs.close();
			pstmt.close();
			conexion.CloseConexion();
		} catch (SQLException e) {			
			e.printStackTrace();			
		} 		
		return listaTaller;	
	}

	/**
	 * Añade el taller pasado como parámetro para insertar en la BBDD.
	 * @param t Taller
	 */
	public void aniadirTaller(Taller t) {
		try {
			conexion = new ConexionBD();
			String consulta = "INSERT INTO inventario(cod,nombre_inventario,precio_inventario,disponibilidad_inventario) values('" +
			t.getCodigo()+"','"+t.getNombre()+"',"+t.getPrecio()+","+t.isDisponible()+");";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	/**
	 * Modifica el taller pasado como parámetro.
	 * @param t Taller
	 * @return true=éxito,false=error.
	 */
	public boolean actualizarTaller(Taller t) {
		try {
			conexion = new ConexionBD();
			String consulta = "UPDATE inventario SET nombre_inventario='"+t.getNombre()+
					"',precio_inventario="+t.getPrecio()+
					",disponibilidad_inventario="+t.isDisponible()+
					" WHERE cod='"+t.getCodigo()+"';";
			return ejecutarConsulta(consulta);
		} catch (SQLException e) {return false;}
		
	
	}
	
	/**
	 * Ejecuta la consulta (menos SELECT) que se le pasa como parámetro
	 * @param consulta
	 * @return true=éxito,false=error.
	 */
	private boolean ejecutarConsulta(String consulta) {
		try {			
			conexion = new ConexionBD();
			PreparedStatement ps = conexion.getConexion().prepareStatement(consulta);
			int i = ps.executeUpdate(consulta);
			conexion.CloseConexion();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
