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
}
