package principal;

import vista.VistaPrincipal;

import javax.swing.UIManager;

import controlador.ControladorApp;
import modelo.InicializadorBD;

//Inicializamos todo lo necesario desde el metodo main
public class App {
    public static void main(String[] args) {
    	
    	//Esto sirve para cambiar el diseño de la interfaz.
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
