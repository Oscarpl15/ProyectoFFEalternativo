package modelo;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.*;

public class SerieDAOTest {

    private SerieDAO dao;
    
    // Se ejecuta antes de cada prueba
    @BeforeEach
    void setUp() {
        dao = new SerieDAO();
    }
    
    //Se eliminan los datos utilizados al finalizar test
    @AfterEach
    void tearDown() {
        dao.eliminar(999); // Limpiar prueba 1
        dao.eliminar(888); // Limpiar prueba 2
        dao.eliminar(777); // Limpiar prueba 3
    }

    // Prueba: guardar una serie y comprobar que se guardó
    @Test
    void testGuardarYListar() {
        Serie serie = new Serie(999, "Prueba JUnit", "Drama", 1, 2024, "Netflix");
        dao.guardar(serie);

        List<Serie> lista = dao.listar();
        
        //Comprobamos que la lista no esté vacía.
        assertFalse(lista.isEmpty(), "La lista no debería estar vacía después de insertar una serie.");
    }

    // Prueba: buscar por título y eliminar
    @Test
    void testBuscarYEliminar() {
        String titulo = "Prueba Eliminación";
        Serie serie = new Serie(888, titulo, "Ciencia Ficción", 5, 2023, "HBO");
        dao.guardar(serie);

        List<Serie> resultado = dao.buscar(titulo, null, null, null);
        // Verificar que haya al menos una serie con ese título
        boolean encontrada = false;
        for (Serie s : resultado) {
            if (titulo.equals(s.getTitulo())) {
                encontrada = true;
                break;
            }
        }
        assertTrue(encontrada, "La serie debe aparecer en la búsqueda.");

        dao.eliminar(serie.getId());

        resultado = dao.buscar(titulo, null, null, null);
        //Verifica que ha eliminado la serie
        assertTrue(resultado.isEmpty(), "La serie debe haberse eliminado.");
    }

    // Prueba: editar una serie
    @Test
    void testEditar() {
        String tituloOriginal = "Serie Original";
        String nuevoTitulo = "Serie Modificada";

        Serie serie = new Serie(777, tituloOriginal, "Comedia", 2, 2020, "Disney");
        dao.guardar(serie);

     // Recuperar todas las series y encontrar la que acabamos de guardar
        List<Serie> lista = dao.listar();
        Serie serieEncontrada = null;

        for (Serie s : lista) {
            if (tituloOriginal.equals(s.getTitulo())) {
                serieEncontrada = s;
                break;
            }
        }

        // Asegurarnos de que se encontró
        assertNotNull(serieEncontrada, "La serie debe haber sido guardada");

        // Actualizar el título
        Serie serieEditada = new Serie(
            serieEncontrada.getId(),
            nuevoTitulo,
            serieEncontrada.getGenero(),
            serieEncontrada.getTemporadas(),
            serieEncontrada.getAnioLanzamiento(),
            serieEncontrada.getPlataforma()
        );
        dao.editar(serieEditada);

        // Buscar por el nuevo título
        List<Serie> resultado = dao.buscar(nuevoTitulo, null, null, null);

        // Verificar que haya resultados
        boolean encontradaModificada = false;
        for (Serie s : resultado) {
            if (nuevoTitulo.equals(s.getTitulo())) {
                encontradaModificada = true;
                break;
            }
        }

        assertTrue(encontradaModificada, "La serie debe haber sido modificada.");
    }
}
