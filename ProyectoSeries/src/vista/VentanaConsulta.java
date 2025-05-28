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
    private JFrame padre;
    private JButton btnEditar;
    private JButton btnEliminar;

    public VentanaConsulta(JFrame padre) {
        super(padre, "Consulta de Series", true);
        this.padre = padre;
        setSize(1000, 500);
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
        
        // Panel de acciones (botones Editar / Eliminar / Cerrar)
        JPanel panelAcciones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelAcciones.setBackground(new Color(45, 45, 45));

        // Botón Editar
        btnEditar = new JButton("✏️ Editar");
        btnEditar.setBackground(new Color(46, 139, 87));
        btnEditar.setForeground(Color.WHITE);
        btnEditar.setFocusPainted(false);
        btnEditar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnEditar.addActionListener(e -> editarSerieSeleccionada());

        // Botón Eliminar
        btnEliminar = new JButton("🗑️ Eliminar");
        btnEliminar.setBackground(new Color(178, 34, 34));
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFocusPainted(false);
        btnEliminar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnEliminar.addActionListener(e -> eliminarSerieSeleccionada());

        // Botón Cerrar
        JButton btnCerrar = new JButton("❌ Cerrar");
        btnCerrar.setBackground(new Color(100, 100, 100));
        btnCerrar.setForeground(Color.WHITE);
        btnCerrar.setFocusPainted(false);
        btnCerrar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnCerrar.addActionListener(e -> dispose());

        // Añadir botones al panel
        panelAcciones.add(btnEditar);
        panelAcciones.add(btnEliminar);
        panelAcciones.add(btnCerrar);

        // Agregar el panel a la ventana
        add(panelAcciones, BorderLayout.SOUTH);
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
    
    // Obtener fila seleccionada
    private int getFilaSeleccionada() {
        return tabla.getSelectedRow();
    }

    // Método para editar la serie seleccionada
    private void editarSerieSeleccionada() {
        int fila = getFilaSeleccionada();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona una serie.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Obtener datos de la fila
        int id = (int) modeloTabla.getValueAt(fila, 0);
        String titulo = (String) modeloTabla.getValueAt(fila, 1);
        String genero = (String) modeloTabla.getValueAt(fila, 2);
        int temporadas ;
        int anio ;
        String plataforma = (String) modeloTabla.getValueAt(fila, 5);
        
        try {
            Object tempObj = modeloTabla.getValueAt(fila, 3);
            Object anioObj = modeloTabla.getValueAt(fila, 4);

            // Convertir a enteros de forma segura
            temporadas = Integer.parseInt(tempObj.toString());
            anio = Integer.parseInt(anioObj.toString());

        } catch (NumberFormatException | NullPointerException ex) {
            JOptionPane.showMessageDialog(this, "Error al leer los datos de la serie.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Abrir formulario con datos precargados
        FormularioRegistro dialog = new FormularioRegistro(padre);
        dialog.setDatos(titulo, genero, String.valueOf(temporadas), String.valueOf(anio), plataforma);
        dialog.setTitle("Editar Serie");

        dialog.addActionListener(ev -> {
            if (!dialog.getTitulo().isEmpty()
                && dialog.getTemporadas() > 0
                && dialog.getAnioLanzamiento() >= 1800
                && dialog.getAnioLanzamiento() <= java.time.Year.now().getValue()) {

                Serie serieEditada = new Serie(
                    id,
                    dialog.getTitulo(),
                    dialog.getGenero(),
                    dialog.getTemporadas(),
                    dialog.getAnioLanzamiento(),
                    dialog.getPlataforma()
                );

                dao.editar(serieEditada);
                cargarDatos(); // Recargar tabla
                dialog.cerrar();

            } else {
                JOptionPane.showMessageDialog(dialog, "Por favor, completa todos los campos correctamente.", "Error", JOptionPane.WARNING_MESSAGE);
            }
        });

        dialog.setVisible(true);
    }

    // Método para eliminar la serie seleccionada
    private void eliminarSerieSeleccionada() {
        int fila = getFilaSeleccionada();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona una serie.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirmado = JOptionPane.showConfirmDialog(this, "¿Estás seguro de eliminar esta serie?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirmado == JOptionPane.YES_OPTION) {
            int id = (int) modeloTabla.getValueAt(fila, 0);
            dao.eliminar(id);
            modeloTabla.removeRow(fila);
        }
    }
}