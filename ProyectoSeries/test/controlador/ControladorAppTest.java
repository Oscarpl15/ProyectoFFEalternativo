package controlador;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import vista.VistaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorAppTest {

    private VistaPrincipal vista;
    private ControladorApp controlador;

    @BeforeEach
    void setUp() {
        // Simulamos la ventana principal
        vista = new VistaPrincipal();
        controlador = new ControladorApp(vista);
    }

    @Test
    void testBotonRegistrarAbreFormulario() {
        // Contador para saber si se ejecutó la acción
        final boolean[] formularioAbierto = {false};

        // Reemplazamos el comportamiento del botón Registrar
        vista.getBtnRegistrar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formularioAbierto[0] = true;
            }
        });

        // Simulamos un clic en el botón "Registrar"
        vista.getBtnRegistrar().doClick();

        // Verificamos que se haya abierto el formulario
        assertTrue(formularioAbierto[0], "Al hacer clic en 'Registrar', debería abrirse el formulario.");
    }

    @Test
    void testBotonConsultarAbreVentanaConsulta() {
        // Contador para saber si se ejecutó la acción
        final boolean[] ventanaConsultaAbierta = {false};

        // Reemplazamos el comportamiento del botón Consultar
        vista.getBtnConsultar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaConsultaAbierta[0] = true;
            }
        });

        // Simulamos un clic en el botón "Consultar"
        vista.getBtnConsultar().doClick();

        // Verificamos que se haya abierto la ventana de consulta
        assertTrue(ventanaConsultaAbierta[0], "Al hacer clic en 'Consultar', debería abrirse la ventana de consulta.");
    }
}
