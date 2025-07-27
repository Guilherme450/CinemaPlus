package com.cinema.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.cinema.model.*;

/**
 * Representa um cinema, responsável por gerenciar salas e a venda de ingressos.
 * Atua como camada de modelo (Model) na arquitetura MVC.
 */
public class Cinema implements Serializable{
    private static final String ARQUIVO_DADOS = "cinema_data.dat";
    private List<Sala> salas = new ArrayList<>(5);

    /**
     * Adiciona uma nova sala ao cinema.
     *
     * @param sala Sala a ser adicionada.
     */
    public void adicionarSala(Sala sala) {
        salas.add(sala);
        salvarEstado();
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

    public void salvarEstado() {
        try (ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(ARQUIVO_DADOS))) {
            out.writeObject(this);
        } catch (IOException e) {
            System.err.println("Erro ao salvar dados do cinema: " + e.getMessage());
        }
    }

    /**
     * Remove uma sala pelo número
     * @param numeroSala Número da sala a ser removida
     * @return true se a sala foi removida, false caso contrário
     */
    public boolean removerSala(int numeroSala) {
        return salas.removeIf(sala -> sala.getNumeroSala() == numeroSala);
    }

    public static Cinema carregarEstado() {
        try (ObjectInputStream in = new ObjectInputStream(
                new FileInputStream(ARQUIVO_DADOS))) {
            return (Cinema) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Criando novo cinema...");
            return new Cinema();
        }
    }
    public List<Sala> getSalas() {
        return new ArrayList<>(salas); // Retorna uma cópia para evitar modificações externas
    }
}