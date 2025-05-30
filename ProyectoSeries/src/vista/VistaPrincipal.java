
package vista;

import javax.swing.*;
import java.awt.*;

//Vista principal, de aqui se derivara a una de las otras dos interfaces, no tiene mas función que esa.
public final class VistaPrincipal extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnRegistrar, btnConsultar;

    public VistaPrincipal() {
        setTitle("Gestión de Series");
        setSize(200, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar ventana
        setLayout(new GridLayout(2, 1, 10, 10));
        getContentPane().setBackground(new Color(0, 150, 255));

        btnRegistrar = new JButton("Registrar Serie");
        btnConsultar = new JButton("Consultar Series");
        
        
        estiloBoton(btnRegistrar);
        estiloBoton(btnConsultar);
        
        
        add(btnRegistrar);
        add(btnConsultar);
        
    }

    private void estiloBoton(JButton boton) {
        boton.setBackground(new Color(70, 130, 180));
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        boton.setFocusPainted(false);
    }
    
    public JButton getBtnRegistrar() { return btnRegistrar; }
    public JButton getBtnConsultar() { return btnConsultar; }
}