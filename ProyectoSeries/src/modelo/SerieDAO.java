// dao/SerieDAO.java
package modelo;

import java.sql.*;

import javax.swing.JOptionPane;

public class SerieDAO {

    // Método para obtener el siguiente ID usando la secuencia
    public int getSiguienteId() {
        String sql = "SELECT serie_seq.NEXTVAL FROM dual";
        int id = -1;

        try (Connection conn = ConexionBD.getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                id = rs.getInt(1);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener el ID: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        return id;
    }

    // Método para guardar una serie en la base de datos
    public void guardar(Serie serie) {
        String sql = "INSERT INTO series (id, titulo, genero, temporadas, anio_lanzamiento) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, serie.getId());
            pstmt.setString(2, serie.getTitulo());
            pstmt.setString(3, serie.getGenero());
            pstmt.setInt(4, serie.getTemporadas());
            pstmt.setInt(5, serie.getAnioLanzamiento());

            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "✅ Serie guardada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "❌ Error al guardar la serie: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}