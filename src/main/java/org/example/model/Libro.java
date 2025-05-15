package org.example.model;

public class Libro {
    private int libroId;
    private String titulo;
    private String genero;
    private int autorId;

    public Libro(int libroId, String titulo, String genero, int autorId) {
        this.libroId = libroId;
        this.titulo = titulo;
        this.genero = genero;
        this.autorId = autorId;
    }

    public int getLibroId() {
        return libroId;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getGenero() {
        return genero;
    }

    public int getAutorId() {
        return autorId;
    }

    public void setLibroId(int libroId) {
        this.libroId = libroId;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setAutorId(int autorId) {
        this.autorId = autorId;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "libroId=" + libroId +
                ", titulo='" + titulo + '\'' +
                ", genero='" + genero + '\'' +
                ", autorId=" + autorId +
                '}';
    }
}
