package vista;

import javax.swing.*;


import java.awt.*;
import java.awt.event.ActionListener;

public class FormularioRegistro extends JDialog {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtTitulo, txtGenero, txtTemporadas, txtAnio;
	private JComboBox<String> cboPlataforma;
    private JButton btnGuardar;
    private boolean guardado = false;
    

    public FormularioRegistro(JFrame padre) {
        super(padre, "Registrar Serie", true);
        setSize(400, 350);
        setLocationRelativeTo(padre);
        setLayout(new GridLayout(6, 2, 10, 10));
        getContentPane().setBackground(new Color(30, 30, 30));

        // Estilos
        Font fuente = new Font("Segoe UI", Font.PLAIN, 14);
        Color colorTexto = Color.LIGHT_GRAY;
        Color colorFondoCampo = new Color(50, 50, 50);

        // Campos
        txtTitulo = new JTextField();
        txtGenero = new JTextField();
        txtTemporadas = new JTextField();
        txtAnio = new JTextField();
        
        // ComboBox para plataforma
        String[] plataformas = {"Movistar", "Netflix", "HBO", "Amazon", "Disney"};
        cboPlataforma = new JComboBox<>(plataformas);
        cboPlataforma.setBackground(colorFondoCampo);
        cboPlataforma.setForeground(colorTexto);
        cboPlataforma.setFont(fuente);

        // Estilizado
        estilizarCampo(txtTitulo, colorFondoCampo, colorTexto, fuente);
        estilizarCampo(txtGenero, colorFondoCampo, colorTexto, fuente);
        estilizarCampo(txtTemporadas, colorFondoCampo, colorTexto, fuente);
        estilizarCampo(txtAnio, colorFondoCampo, colorTexto, fuente);

        // Etiquetas
        addEtiqueta("Título:");
        add(this.txtTitulo);
        addEtiqueta("Género:");
        add(this.txtGenero);
        addEtiqueta("Temporadas:");
        add(this.txtTemporadas);
        addEtiqueta("Año de lanzamiento:");
        add(txtAnio);
        addEtiqueta("Plataforma:");
        add(cboPlataforma);

        // Botón Guardar
        btnGuardar = new JButton("Guardar");
        btnGuardar.setBackground(new Color(70, 130, 180));
        btnGuardar.setForeground(Color.BLACK);
        btnGuardar.setFont(fuente.deriveFont(Font.BOLD));
        btnGuardar.setFocusPainted(false);
        add(new JLabel());
        add(btnGuardar);
    }

    private void addEtiqueta(String texto) {
        JLabel etiqueta = new JLabel(texto);
        etiqueta.setForeground(Color.LIGHT_GRAY);
        etiqueta.setFont(new Font("Segoe UI", Font.BOLD, 14));
        add(etiqueta);
    }

    private void estilizarCampo(JTextField campo, Color fondo, Color texto, Font fuente) {
        campo.setBackground(fondo);
        campo.setForeground(texto);
        campo.setFont(fuente);
       // campo.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));//?
    }

    public void addActionListener(ActionListener listener) {
        btnGuardar.addActionListener(listener);
    }

    public String getTitulo() {
        return txtTitulo.getText().trim();
    }

    public String getGenero() {
        return txtGenero.getText().trim();
    }

    public int getTemporadas() {
        try {
            return Integer.parseInt(txtTemporadas.getText().trim());
        } catch (NumberFormatException ex) {
            return -1;
        }
    }
    
    public int getAnioLanzamiento() {
        try { return Integer.parseInt(txtAnio.getText().trim()); }
        catch (NumberFormatException ex) { 
        	return -1;
        }
    }
    
    public String getPlataforma() {
        return (String) cboPlataforma.getSelectedItem();
    }
    
    public boolean isGuardado() {
        return guardado;
    }

    public void cerrar() {
        guardado = true;
        dispose();
    }
}
