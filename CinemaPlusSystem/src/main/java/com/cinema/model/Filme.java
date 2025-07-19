package com.cinema.model;

public class Filme {
    private String titulo, genero;
    private double duracao;

    public Filme(String titulo, double duracao, String genero) {
        this.titulo = titulo;
        this.duracao = duracao;
        this.genero = genero;
    }

    // Sets

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public void setDuracao(double duracao) {
        this.duracao = duracao;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }

    // Gets

    public String getTitulo() {
        return titulo;
    }
    public String getGenero() {
        return genero;
    }
    public double getDuracao() {
        return duracao;
    }
}
