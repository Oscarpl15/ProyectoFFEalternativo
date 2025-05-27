// dao/SerieDAO.java
package modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    
    //Método para listar las series.
    public List<Serie> listar() {
        String sql = "SELECT id, titulo, genero, temporadas, anio_lanzamiento FROM series";
        List<Serie> lista = new ArrayList<>();

        try (Connection conn = ConexionBD.getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Serie serie = new Serie(
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getString("genero"),
                    rs.getInt("temporadas"),
                    rs.getInt("anio_lanzamiento")
                );
                lista.add(serie);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar las series: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        return lista;
    }
}