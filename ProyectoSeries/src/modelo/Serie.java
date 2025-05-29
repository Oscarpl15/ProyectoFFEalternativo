package modelo;

public class Serie {
    private final int id;
    private final String titulo;
    private final String genero;
    private final int temporadas;
    private final int anioLanzamiento; 
    private final String plataforma;

    public Serie(int id, String titulo, String genero, int temporadas, int anioLanzamiento, String plataforma) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.temporadas = temporadas;
        this.anioLanzamiento = anioLanzamiento;
        this.plataforma = plataforma;
        
    }

    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getGenero() { return genero; }
    public int getTemporadas() { return temporadas; }
    public int getAnioLanzamiento() { return anioLanzamiento; }
    public String getPlataforma() { return plataforma; }
}
