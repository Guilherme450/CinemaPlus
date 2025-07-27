package com.cinema.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Representa uma sala de cinema, contendo informações sobre o filme exibido,
 * número da sala e o controle dos assentos disponíveis e ocupados.
 */
public class Sala implements Serializable {
    /** Número identificador da sala */
    private int numeroSala;
    /** Filme exibido na sala */
    private Filme filme;
    /** Quantidade de fileiras na sala */
    private static final int FILEIRAS = 20;
    /** Quantidade de assentos por fileira */
    private static final int ASSENTOS_POR_FILEIRA = 10;
    /** Matriz de controle de assentos ocupados */
    private boolean[][] assentosOcupados = new boolean[20][10];
    /** Mapa de assentos disponíveis na sala, indexados por código (ex: A1, B5) */
    private final Map<String, Assento> assentos;

    /**
     * Construtor da sala.
     * 
     * @param numeroSala Número identificador da sala
     * @param filme      Filme exibido na sala
     */
    public Sala(int numeroSala, Filme filme) {
        this.numeroSala = numeroSala;
        this.filme = filme;
        this.assentos = new HashMap<>();
        inicializarAssentos();
    }

    /**
     * Inicializa todos os assentos da sala, criando objetos Assento para cada posição.
     */
    private void inicializarAssentos() {
        if (assentos.isEmpty()) {
            for (int i = 0; i < FILEIRAS; i++) {
                char fileira = (char) ('A' + i);
                for (int j = 1; j <= ASSENTOS_POR_FILEIRA; j++) {
                    String codigo = fileira + String.valueOf(j);
                    assentos.put(codigo, new Assento(fileira, j));
                }
            }
        }
    }

    /**
     * Reserva um assento específico na sala.
     * 
     * @param fileira Letra da fileira (A-T)
     * @param numero  Número do assento (1-10)
     */
    public void reservarAssento(char fileira, int numero) {
        Assento assento = getAssento(fileira, numero);
        if (assento != null) {
            assento.reservar();
        }
    }

    /**
     * Verifica se um assento está ocupado.
     * 
     * @param fileira Letra da fileira (A-T)
     * @param numero  Número do assento (1-10)
     * @return true se o assento está ocupado, false caso contrário
     */
    public boolean isAssentoOcupado(char fileira, int numero) {
        Assento assento = getAssento(fileira, numero);
        return assento != null && assento.estarOcupado();
    }

    /**
     * Retorna o objeto Assento correspondente à fileira e número informados.
     * 
     * @param fileira Letra da fileira (A-T)
     * @param numero  Número do assento (1-10)
     * @return Assento correspondente ou null se não existir
     * @throws IllegalArgumentException se o assento for inválido
     */
    public Assento getAssento(char fileira, int numero) {
        if (fileira < 'A' || fileira > 'T' || numero < 1 || numero > 10) {
            throw new IllegalArgumentException("Assento inválido: " + fileira + numero);
        }
        String codigo = fileira + String.valueOf(numero);
        return assentos.get(codigo);
    }

    /**
     * Retorna o número identificador da sala.
     * @return Número da sala
     */
    public int getNumeroSala() {
        return numeroSala;
    }

    /**
     * Retorna o filme exibido na sala.
     * @return Filme exibido
     */
    public Filme getFilme() {
        return filme;
    }
}
