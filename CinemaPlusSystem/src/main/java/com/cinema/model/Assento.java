package com.cinema.model;

/**
 * Representa um assento em uma sala de cinema.
 * Cada assento possui uma fileira, um número e um estado de ocupação.
 */
public class Assento {
    /** Fileira do assento (ex: 'A', 'B', 'C'). */
    private final char fileira;
    /** Número do assento na fileira. */
    private final int numero;
    /** Indica se o assento está ocupado. */
    private boolean ocupado;

    /**
     * Cria um novo assento com a fileira e número especificados.
     * O assento é criado como disponível (não ocupado).
     *
     * @param fileira a fileira do assento
     * @param numero o número do assento
     */
    public Assento(char fileira, int numero) {
        this.fileira = fileira;
        this.numero = numero;
        this.ocupado = false;
    }

    /**
     * Retorna a fileira do assento.
     *
     * @return a fileira do assento
     */
    public char getFileira() {
        return fileira;
    }

    /**
     * Retorna o número do assento.
     *
     * @return o número do assento
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Verifica se o assento está ocupado.
     *
     * @return {@code true} se o assento estiver ocupado, {@code false} caso contrário
     */
    public boolean estarOcupado() {
        return ocupado;
    }

    /**
     * Reserva o assento, marcando-o como ocupado.
     */
    public void reservar() {
        this.ocupado = true;
    }

    /**
     * Retorna o identificador único do assento, composto pela fileira e número.
     *
     * @return identificador do assento (ex: "A10")
     */
    public String getIdentificador() {
        return "" + fileira + numero;
    }
}
