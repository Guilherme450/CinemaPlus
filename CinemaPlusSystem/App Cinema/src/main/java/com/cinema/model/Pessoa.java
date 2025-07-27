package com.cinema.model;

/**
 * Representa uma pessoa no sistema do CinemaPlus.
 * Contém informações como id, nome, idade e categoria.
 */
public class Pessoa {
    /** Identificador único da pessoa. */
    private final int id;
    /** Nome da pessoa. */
    private String nome;
    /** Idade da pessoa. */
    private int idade;
    /** Categoria da pessoa (ex: estudante, adulto, etc). */
    private String categoria;

    /**
     * Construtor da classe Pessoa.
     *
     * @param id        Identificador único da pessoa.
     * @param nome      Nome da pessoa.
     * @param idade     Idade da pessoa.
     * @param categoria Categoria da pessoa.
     */
    public Pessoa(int id, String nome, int idade, String categoria) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.categoria = categoria;
    }

    /**
     * Altera o nome da pessoa.
     * @param nome Novo nome.
     */
    public void setNome(String nome) {this.nome = nome;}

    /**
     * Altera a idade da pessoa.
     * @param idade Nova idade.
     */
    public void setIdade(int idade) {this.idade = idade;}

    /**
     * Altera a categoria da pessoa.
     * @param categoria Nova categoria.
     */
    public void setCategoria(String categoria) {this.categoria = categoria;}

    /**
     * Retorna o identificador da pessoa.
     * @return id da pessoa.
     */
    public int getId() { return id; }

    /**
     * Retorna o nome da pessoa.
     * @return nome da pessoa.
     */
    public String getNome() {return nome;}

    /**
     * Retorna a idade da pessoa.
     * @return idade da pessoa.
     */
    public int getIdade() {
        return idade;
    }

    /**
     * Retorna a categoria da pessoa.
     * Se a idade for maior ou igual a 60, retorna "idoso".
     * @return categoria da pessoa.
     */
    public String getCategoria() {
        if (idade >= 60) return "idoso";
        return categoria;
    }
}