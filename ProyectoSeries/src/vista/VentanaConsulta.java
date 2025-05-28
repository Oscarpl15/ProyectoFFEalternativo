// vista/VentanaConsulta.java

package vista;

import modelo.Serie;
import modelo.SerieDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class VentanaConsulta extends JDialog {

    private static final long serialVersionUID = 1L;
	private JTextField txtTitulo, txtGenero, txtAnio;
	private JComboBox<String> cboPlataformaFiltro;
    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private SerieDAO dao;

    public VentanaConsulta(JFrame padre) {
        super(padre, "Consulta de Series", true);
        setSize(900, 500);
        setLocationRelativeTo(padre);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(30, 30, 30));

        dao = new SerieDAO();

        // Panel de filtros
        JPanel panelFiltros = new JPanel(new GridLayout(1, 8, 10, 10));
        panelFiltros.setBackground(new Color(45, 45, 45));
        panelFiltros.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        txtTitulo = new JTextField();
        txtGenero = new JTextField();
        txtAnio = new JTextField();

        estilizarCampo(txtTitulo);
        estilizarCampo(txtGenero);
        estilizarCampo(txtAnio);

        panelFiltros.add(new JLabel("Título:", SwingConstants.RIGHT));
        panelFiltros.add(txtTitulo);
        panelFiltros.add(new JLabel("Género:", SwingConstants.RIGHT));
        panelFiltros.add(txtGenero);
        panelFiltros.add(new JLabel("Año:", SwingConstants.RIGHT));
        panelFiltros.add(txtAnio);
        
        // ComboBox para filtrar por plataforma
        cboPlataformaFiltro = new JComboBox<>();
        cboPlataformaFiltro.addItem("Todas");
        cboPlataformaFiltro.addItem("Movistar");
        cboPlataformaFiltro.addItem("Netflix");
        cboPlataformaFiltro.addItem("HBO");
        cboPlataformaFiltro.addItem("Amazon");
        cboPlataformaFiltro.addItem("Disney");

        cboPlataformaFiltro.setBackground(new Color(60, 60, 60));
        cboPlataformaFiltro.setForeground(Color.LIGHT_GRAY);
        cboPlataformaFiltro.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        panelFiltros.add(new JLabel("Plataforma:", SwingConstants.RIGHT));
        panelFiltros.add(cboPlataformaFiltro);
        
        //Boton filtros
        JButton btnBuscar = new JButton("🔍 Filtrar");
        btnBuscar.setBackground(new Color(70, 130, 180));
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.setFocusPainted(false);
        btnBuscar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnBuscar.addActionListener(this::aplicarFiltro);

        panelFiltros.add(btnBuscar);

        add(panelFiltros, BorderLayout.NORTH);

        // Tabla
        tabla = new JTable();
        modeloTabla = new DefaultTableModel();
        tabla.setModel(modeloTabla);
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Título");
        modeloTabla.addColumn("Género");
        modeloTabla.addColumn("Temporadas");
        modeloTabla.addColumn("Año");
        modeloTabla.addColumn("Plataforma");

        cargarDatos();

        JScrollPane scrollPane = new JScrollPane(tabla);
        add(scrollPane, BorderLayout.CENTER);
        
     // Botón cerrar
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dispose());
        add(btnCerrar, BorderLayout.SOUTH);
    }

    private void estilizarCampo(JTextField campo) {
        campo.setBackground(new Color(60, 60, 60));
        campo.setForeground(Color.LIGHT_GRAY);
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    }

    private void cargarDatos() {
        modeloTabla.setRowCount(0); // Limpiar tabla

        String titulo = txtTitulo.getText().trim();
        String genero = txtGenero.getText().trim();
        Integer anio = parseInteger(txtAnio.getText().trim());
        String plataforma = (String) cboPlataformaFiltro.getSelectedItem();

        if ("Todas".equals(plataforma)) {
            plataforma = null; // No filtrar por plataforma
        }

        List<Serie> lista = dao.buscar(titulo, genero, anio, plataforma);

        for (Serie serie : lista) {
            Object[] fila = {
                serie.getId(),
                serie.getTitulo(),
                serie.getGenero(),
                serie.getTemporadas(),
                serie.getAnioLanzamiento(),
                serie.getPlataforma()
            };
            modeloTabla.addRow(fila);
        }
    }

    private Integer parseInteger(String texto) {
        try {
            return Integer.parseInt(texto);
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    private void aplicarFiltro(ActionEvent e) {
        cargarDatos();
    }
}