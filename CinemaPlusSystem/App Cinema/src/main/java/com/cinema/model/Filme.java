package com.cinema.model;

import java.io.Serializable;

/**
 * Representa um filme exibido no cinema.
 * Contém informações como título, duração e gênero.
 */
public class Filme implements Serializable {
    /** Título do filme */
    private String titulo;
    /** Gênero do filme */
    private String genero;
    /** Duração do filme em minutos */
    private double duracao;

    /**
     * Construtor do filme.
     * 
     * @param titulo  Título do filme
     * @param duracao Duração do filme em minutos
     * @param genero  Gênero do filme
     */
    public Filme(String titulo, double duracao, String genero) {
        this.titulo = titulo;
        this.duracao = duracao;
        this.genero = genero;
    }

    // Sets

    /**
     * Define o título do filme.
     * @param titulo Novo título
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Define a duração do filme.
     * @param duracao Nova duração em minutos
     */
    public void setDuracao(double duracao) {
        this.duracao = duracao;
    }

    /**
     * Define o gênero do filme.
     * @param genero Novo gênero
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    // Gets

    /**
     * Retorna o título do filme.
     * @return Título
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Retorna o gênero do filme.
     * @return Gênero
     */
    public String getGenero() {
        return genero;
    }

    /**
     * Retorna a duração do filme em minutos.
     * @return Duração
     */
    public double getDuracao() {
        return duracao;
    }
}
