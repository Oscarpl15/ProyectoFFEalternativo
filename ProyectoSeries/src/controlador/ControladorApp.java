package controlador;

import vista.VistaPrincipal;
import java.awt.event.*;

import javax.swing.JOptionPane;

public class ControladorApp {

    public ControladorApp(VistaPrincipal vista) {
        vista.getBtnRegistrar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vistaRegistrar(vista);
            }
        });
    }

    private void vistaRegistrar(VistaPrincipal vista) {
        vista.FormularioRegistro dialog = new vista.FormularioRegistro(vista);
        modelo.SerieDAO dao = new modelo.SerieDAO();

        dialog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                if (!dialog.getTitulo().isEmpty()
                    && dialog.getTemporadas() > 0
                    && dialog.getAnioLanzamiento() >= 1950
                    && dialog.getAnioLanzamiento() <= java.time.Year.now().getValue()) {

                    int id = dao.getSiguienteId();
                    modelo.Serie serie = new modelo.Serie(
                        id,
                        dialog.getTitulo(),
                        dialog.getGenero(),
                        dialog.getTemporadas(),
                        dialog.getAnioLanzamiento()
                    );

                    dao.guardar(serie);
                    dialog.cerrar();

                } else {
                    JOptionPane.showMessageDialog(
                        dialog,
                        "Por favor, completa todos los campos correctamente.\n" +
                        "El año debe estar entre 1950 y el actual.",
                        "Error",
                        JOptionPane.WARNING_MESSAGE
                    );
                }
            }
        });

        dialog.setVisible(true);
    }
}