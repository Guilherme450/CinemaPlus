package com.cinema.model;

public class Assento {
    private char fileira;
    private int numero;
    private boolean ocupado;

    public Assento(char fileira, int numero) {
        this.fileira = fileira;
        this.numero = numero;
        this.ocupado = false;
    }

    public boolean estarOcupado() {return ocupado;}
    public void reservar() {this.ocupado = true;}
    public String getIdentificador() {return "" + fileira + numero;}
}
