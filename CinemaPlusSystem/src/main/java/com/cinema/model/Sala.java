package com.cinema.model;

public class Sala {
    private int numeroSala;
    private Assento[][] assentos = new Assento[20][10];
    private Filme filme;

    public Sala(int numeroSala, Filme filme) {
        this.numeroSala = numeroSala;
        this.filme = filme;
        for (int i = 0; i < 10; i++) {
            char fileira = (char) ('A' + i);
            for (int j = 0; j < 10; j++) {
                assentos[i][j] = new Assento(fileira, j + 1);
            }
        }
    }

    // Gets
    public Assento getAssento(char fileira, int numero) {
        int i = fileira - 'A';
        int j = numero - 1;
        return assentos[i][j];
    }

    public int getNumeroSala() {return numeroSala;}
    public Filme getFilme() {return filme;}

}
