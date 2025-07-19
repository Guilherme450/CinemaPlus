package com.cinema.model;

public class Pessoa {
    private String nome, categoria;
    private int idade;

    public Pessoa(String nome, int idade, String categoria) {
        this.nome = nome;
        this.idade = idade;
        this.categoria = categoria.toLowerCase();
    }

    // Sets

    public void setNome(String nome) {this.nome = nome;}
    public void setIdade(int idade) {this.idade = idade;}
    public void setCategoria(String categoria) {this.categoria = categoria;}

    // Gets

    public String getNome() {return nome;}
    public int getIdade() {
        return idade;
    }
    public String getCategoria() {
        if (idade >= 60) return "idoso";
        return categoria;
    }
}