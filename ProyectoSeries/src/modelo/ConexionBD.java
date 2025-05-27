// modelo/ConexionBD.java
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConexionBD {

    private static Connection conexion = null;

    public static Connection getConexion() {
        try {
            if (conexion == null || conexion.isClosed()) {
            	String url = "jdbc:oracle:thin:@//172.17.0.2:1521/XEPDB1";
                String usuario = "oscarpl";
                String clave = "oscarpl";

                conexion = DriverManager.getConnection(url, usuario, clave);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
        return conexion;
    }
}