package com.cinema.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa um cinema, responsável por gerenciar salas e a venda de ingressos.
 * Atua como camada de modelo (Model) na arquitetura MVC.
 */
public class Cinema {
    private List<Sala> salas = new ArrayList<>();

    /**
     * Adiciona uma nova sala ao cinema.
     *
     * @param sala Sala a ser adicionada.
     */
    public void adicionarSala(Sala sala) {
        salas.add(sala);
    }

    /**
     * Busca uma sala pelo seu número.
     *
     * @param numero Número identificador da sala.
     * @return Sala correspondente ao número informado, ou null se não encontrada.
     */
    public Sala getSala(int numero) {
        return salas.stream()
                .filter(s -> s.getNumeroSala() == numero)
                .findFirst()
                .orElse(null);
    }

    /**
     * Realiza a venda de um ingresso para uma pessoa em um assento específico de uma sala.
     *
     * @param pessoa  Pessoa que irá adquirir o ingresso.
     * @param salaNum Número da sala desejada.
     * @param fileira Letra da fileira do assento.
     * @param cadeira Número da cadeira do assento.
     * @return Ingresso gerado para a pessoa.
     * @throws IllegalArgumentException Se a sala não existir ou o assento estiver ocupado.
     */
    public Ingresso venderIngresso(Pessoa pessoa, int salaNum, char fileira, 
    int cadeira) {
        Sala sala = getSala(salaNum);
        if (sala == null) {
            throw new IllegalArgumentException("Sala inexistente.");
        }

        Assento assento = sala.getAssento(fileira, cadeira);
        if (assento.estarOcupado()) {
            throw new IllegalArgumentException("assento indisponível.");
        }

        assento.reservar();
        Ingresso ingresso = new Ingresso(pessoa, sala, assento);
        return ingresso;
    }
}