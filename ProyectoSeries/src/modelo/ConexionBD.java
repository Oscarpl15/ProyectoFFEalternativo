package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConexionBD {
	//Generamos la conexión, en primer lugar la iniciamos a null para poder iniciarla con los valores en caso de que la conexion no 
	//exista o esté cerrada.
    private static Connection conexion = null;

    public static Connection getConexion() {
        try {
            if (conexion == null || conexion.isClosed()) {
            	final String url = "jdbc:oracle:thin:@//172.17.0.2:1521/XEPDB1";
                final String usuario = "oscarpl";
                final String clave = "oscarpl";

                conexion = DriverManager.getConnection(url, usuario, clave);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
        return conexion;
    }
}