package modelo;

public class Serie {
    private int id;
    private String titulo;
    private String genero;
    private int temporadas;
    private int anioLanzamiento; 
    private String plataforma;

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
