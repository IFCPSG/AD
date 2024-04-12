package FicherosXMLEjercicio2;

public class Pelicula {
    private String titulo;
    private String fecha;
    private String genero;
    private String sinopsis;
    private String actoresPrincipales;

    public Pelicula(String titulo, String fecha, String genero, String sinopsis, String actoresPrincipales) {
        this.titulo = titulo;
        this.fecha = fecha;
        this.genero = genero;
        this.sinopsis = sinopsis;
        this.actoresPrincipales = actoresPrincipales;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getFecha() {
        return fecha;
    }

    public String getGenero() {
        return genero;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public String getActoresPrincipales() {
        return actoresPrincipales;
    }
}
