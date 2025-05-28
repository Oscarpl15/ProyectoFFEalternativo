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
        String sql = "INSERT INTO series (id, titulo, genero, temporadas, anio_lanzamiento, plataforma) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, serie.getId());
            pstmt.setString(2, serie.getTitulo());
            pstmt.setString(3, serie.getGenero());
            pstmt.setInt(4, serie.getTemporadas());
            pstmt.setInt(5, serie.getAnioLanzamiento());
            pstmt.setString(6, serie.getPlataforma());

            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "✅ Serie guardada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "❌ Error al guardar la serie: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    //Método para listar las series.
    public List<Serie> listar() {
        String sql = "SELECT id, titulo, genero, temporadas, anio_lanzamiento, plataforma FROM series";
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
                    rs.getInt("anio_lanzamiento"),
                    rs.getString("plataforma")
                );
                lista.add(serie);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar las series: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        return lista;
    }
    
    //Método para buscar series.

    public List<Serie> buscar(String titulo, String genero, Integer anio, String plataforma) {
        List<Serie> lista = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT id, titulo, genero, temporadas, anio_lanzamiento, plataforma FROM series WHERE 1=1");

        if (titulo != null && !titulo.isEmpty()) {
            sql.append(" AND UPPER(titulo) LIKE UPPER('").append("%").append(titulo).append("%')");
        }
        if (genero != null && !genero.isEmpty()) {
            sql.append(" AND UPPER(genero) LIKE UPPER('").append("%").append(genero).append("%')");
        }
        if (anio != null && anio > 0) {
            sql.append(" AND anio_lanzamiento = ").append(anio);
        }
        if (plataforma != null && !plataforma.isEmpty()) {
            sql.append(" AND UPPER(plataforma) = UPPER('").append(plataforma).append("')");
        }

        try (Connection conn = ConexionBD.getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql.toString())) {

            while (rs.next()) {
                Serie serie = new Serie(
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getString("genero"),
                    rs.getInt("temporadas"),
                    rs.getInt("anio_lanzamiento"),
                    rs.getString("plataforma")
                );
                lista.add(serie);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar series: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        return lista;
    }
}