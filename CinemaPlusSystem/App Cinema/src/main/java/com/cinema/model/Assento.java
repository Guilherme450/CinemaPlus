package com.cinema.model;

import java.io.Serializable;

import static com.cinema.app.AppContext.cinemaController;

/**
 * Representa um assento em uma sala de cinema.
 * Cada assento possui uma fileira, um número e um estado de ocupação.
 */
public class Assento implements Serializable {
    /** Letra da fileira do assento. */
    private final char fileira;
    /** Número do assento na fileira. */
    private final int numero;
    /** Indica se o assento está ocupado. */
    private boolean ocupado;

    /**
     * Construtor da classe Assento.
     *
     * @param fileira Letra da fileira do assento.
     * @param numero  Número do assento na fileira.
     */
    public Assento(char fileira, int numero) {
        this.fileira = fileira;
        this.numero = numero;
        this.ocupado = false;
    }

    /**
     * Verifica se o assento está ocupado.
     *
     * @return true se o assento estiver ocupado, false caso contrário.
     */
    public boolean estarOcupado() { return ocupado; }

    /**
     * Reserva o assento, marcando-o como ocupado e salvando o estado do sistema.
     */
    public void reservar() {
        this.ocupado = true;
        cinemaController.salvarEstado();
    }

    /**
     * Retorna o identificador do assento, composto pela fileira e número.
     *
     * @return Identificador do assento (ex: "A10").
     */
    public String getIdentificador() { return "" + fileira + numero;}
}
