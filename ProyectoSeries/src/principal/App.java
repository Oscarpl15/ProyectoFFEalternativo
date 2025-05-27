package principal;

import vista.VistaPrincipal;

import java.awt.Color;

import javax.swing.UIManager;

import controlador.ControladorApp;
import modelo.InicializadorBD;

public class App {
    public static void main(String[] args) {
    	try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    	
    	InicializadorBD.inicializar();
    	
        VistaPrincipal vista = new VistaPrincipal();
        vista.setVisible(true);

        ControladorApp controlador = new ControladorApp(vista);
        
    }
}
