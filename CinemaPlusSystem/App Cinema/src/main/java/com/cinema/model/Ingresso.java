package com.cinema.model;

import java.io.Serializable;

/**
 * Representa um ingresso de cinema, associando uma pessoa, sala, assento e preço.
 * Calcula o preço final com base na categoria do cliente.
 */
public class Ingresso implements Serializable {
    /** Pessoa que comprou o ingresso */
    private Pessoa pessoa;
    /** Sala do cinema onde será exibido o filme */
    private Sala sala;
    /** Assento reservado para o ingresso */
    private Assento assento;
    /** Preço base do ingresso */
    private double precoBase = 20;
    /** Preço final do ingresso após descontos */
    private double precoFinal;

    /**
     * Construtor do ingresso.
     * 
     * @param pessoa  Pessoa que comprou o ingresso
     * @param sala    Sala do cinema
     * @param assento Assento reservado
     */
    public Ingresso(Pessoa pessoa, Sala sala, Assento assento) {
        this.assento = assento;
        this.pessoa = pessoa;
        this.sala = sala;
        this.precoFinal = calcularDesconto();
    }

    /**
     * Calcula o preço final do ingresso de acordo com a categoria do cliente.
     * 
     * @return Preço final com desconto aplicado
     */
    private double calcularDesconto() {
        String categoria = pessoa.getCategoria();
        switch (categoria) {
            case "Estudante": return precoBase * 0.5;
            case "idoso": return precoBase * 0;
            case "Professor": return precoBase * 0.7;
            default: return precoBase;
        }
    }

    /**
     * Exibe as informações do ingresso no console.
     */
    public void exibirIngresso() {
        System.out.println("Ingresso:");
        System.out.println("Nome: " + pessoa.getNome());
        System.out.println("Filme: " + sala.getFilme().getTitulo());
        System.out.println("Sala: " + sala.getNumeroSala());
        System.out.println("Assento: " + assento.getIdentificador());
        System.out.println("Preço Final: R$ " + precoFinal);
    }

    /**
     * Retorna o assento do ingresso.
     * @return Assento reservado
     */
    public Assento getAssento() {
        return assento;
    }

    /**
     * Retorna o preço base do ingresso.
     * @return Preço base
     */
    public double getPrecoBase() {
        return precoBase;
    }

    /**
     * Retorna o preço final do ingresso (com desconto).
     * @return Preço final
     */
    public double getPrecoFinal() {
        return calcularDesconto();
    }

    /**
     * Retorna a pessoa associada ao ingresso.
     * @return Pessoa
     */
    public Pessoa getPessoa() {
        return pessoa;
    }

    /**
     * Retorna a sala do ingresso.
     * @return Sala
     */
    public Sala getSala() {
        return sala;
    }
}
