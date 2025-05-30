
package modelo;

import java.sql.Connection;
import java.sql.Statement;

import javax.swing.JOptionPane;

import java.sql.SQLException;

public class InicializadorBD {

    public static void inicializar() {
    	//Usamos BEGIN ... EXCEPTION ... END; para capturar errores comunes:
    	//-955: La tabla ya existe.
    	//-2289: La secuencia ya existe.
    	//Así evitamos que el programa falle si la tabla ya está creada.
    	
        String creaTabla = "BEGIN "
                + "EXECUTE IMMEDIATE 'CREATE TABLE series (id NUMBER PRIMARY KEY, titulo VARCHAR2(100), genero VARCHAR2(50), temporadas NUMBER, anio_lanzamiento NUMBER)'; "
                + "EXECUTE IMMEDIATE 'CREATE SEQUENCE serie_seq START WITH 1 INCREMENT BY 1'; "
                + "EXCEPTION "
                + "WHEN OTHERS THEN "
                + "IF SQLCODE != -955 AND SQLCODE != -2289 THEN "
                + "RAISE; "
                + "END IF; "
                + "END;";

        try (Connection conn = ConexionBD.getConexion();
             Statement stmt = conn.createStatement()) {

            stmt.execute(creaTabla);
            JOptionPane.showMessageDialog(null, "✅ Tabla y secuencia creadas o ya existían.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
        	JOptionPane.showMessageDialog(null, "❌ Error al inicializar la base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}