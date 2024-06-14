package com.edwingonzalez.turismm.Clases;

import android.graphics.drawable.Drawable;
public class Noticia {
    private String titulo;
    private String autor;
    private String contenido;
    private int likes;
    private Drawable imagen;
    private Drawable imagenAutor;

    public Noticia() {
    }

    public Noticia(String titulo, String autor, String contenido, int likes, Drawable imagen, Drawable imagenAutor) {
        this.titulo = titulo;
        this.autor = autor;
        this.contenido = contenido;
        this.likes = likes;
        this.imagen = imagen;
        this.imagenAutor = imagenAutor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public Drawable getImagen() {
        return imagen;
    }

    public void setImagen(Drawable imagen) {
        this.imagen = imagen;
    }

    public Drawable getImagenAutor() {
        return imagenAutor;
    }

    public void setImagenAutor(Drawable imagenAutor) {
        this.imagenAutor = imagenAutor;
    }
}
