package com.cinema.model;

import com.cinema.model.Pessoa;
import com.cinema.model.Sala;

public class Ingresso {
    private Pessoa pessoa;
    private Sala sala;
    private Assento assento;
    private double precoBase = 20;
    private double precoFinal;

    public Ingresso(Pessoa pessoa, Sala sala, Assento assento) {
        this.assento = assento;
        this.pessoa = pessoa;
        this.sala = sala;
        this.precoFinal = calcularDesconto();
    }

    private double calcularDesconto() {
        String categoria = pessoa.getCategoria();
        switch (categoria) {
            case "estudante": return precoBase * 0.5;
            case "idoso": return precoBase * 0;
            case "professor": return precoBase * 0.7;
            default: return precoBase;
        }
    }

    public void exibirIngresso() {
        System.out.println("Ingresso:");
        System.out.println("Nome: " + pessoa.getNome());
        System.out.println("Filme: " + sala.getFilme().getTitulo());
        System.out.println("Sala: " + sala.getNumeroSala());
        System.out.println("Assento: " + assento.getIdentificador());
        System.out.println("Preço Final: R$ " + precoFinal);
    }
}
