// VistaPrincipal.java
package vista;

import javax.swing.*;
import java.awt.*;

public class VistaPrincipal extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnRegistrar, btnConsultar, btnModificar, btnEliminar;

    public VistaPrincipal() {
        setTitle("Gestión de Series");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar ventana
        setLayout(new GridLayout(4, 1, 10, 10));
        getContentPane().setBackground(new Color(0, 150, 255));

        btnRegistrar = new JButton("Registrar Serie");
        btnConsultar = new JButton("Consultar Series");
        btnModificar = new JButton("Modificar Serie");
        btnEliminar = new JButton("Eliminar Serie");
        
        estiloBoton(btnRegistrar);
        estiloBoton(btnConsultar);
        estiloBoton(btnModificar);
        estiloBoton(btnEliminar);
        
        add(btnRegistrar);
        add(btnConsultar);
        add(btnModificar);
        add(btnEliminar);
    }

    private void estiloBoton(JButton boton) {
        boton.setBackground(new Color(70, 130, 180));
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        boton.setFocusPainted(false);
    }
    
    public JButton getBtnRegistrar() { return btnRegistrar; }
    public JButton getBtnConsultar() { return btnConsultar; }
    public JButton getBtnModificar() { return btnModificar; }
    public JButton getBtnEliminar() { return btnEliminar; }
}