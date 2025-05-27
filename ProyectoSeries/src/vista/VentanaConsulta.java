// vista/VentanaConsulta.java

package vista;

import modelo.Serie;
import modelo.SerieDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VentanaConsulta extends JDialog {

    private static final long serialVersionUID = 1L;

	public VentanaConsulta(JFrame padre) {
        super(padre, "Consulta de Series", true);
        setSize(800, 400);
        setLocationRelativeTo(padre);
        setLayout(new BorderLayout());

        // Tabla
        JTable tabla = new JTable();
        DefaultTableModel modelo = new DefaultTableModel();
        tabla.setModel(modelo);

        // Configurar columnas
        modelo.addColumn("ID");
        modelo.addColumn("Título");
        modelo.addColumn("Género");
        modelo.addColumn("Temporadas");
        modelo.addColumn("Año");

        // Cargar datos
        SerieDAO dao = new SerieDAO();
        for (Serie serie : dao.listar()) {
            Object[] fila = {
                serie.getId(),
                serie.getTitulo(),
                serie.getGenero(),
                serie.getTemporadas(),
                serie.getAnioLanzamiento()
            };
            modelo.addRow(fila);
        }

        // Scroll pane
        JScrollPane scrollPane = new JScrollPane(tabla);
        add(scrollPane, BorderLayout.CENTER);

        // Botón cerrar
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dispose());
        add(btnCerrar, BorderLayout.SOUTH);
    }
}