package com.cinema.model;

import java.util.ArrayList;
import java.util.List;

public class Cinema {
    private List<Sala> salas = new ArrayList<>();

    public void adicionarSala(Sala sala) {
        salas.add(sala);
    }

    public Sala getSala(int numero) {
        return salas.stream()
                .filter(s -> s.getNumeroSala() == numero)
                .findFirst()
                .orElse(null);
    }

    public Ingresso venderIngresso(Pessoa pessoa, int salaNum, char fileira, int cadeira) {
        Sala sala = getSala(salaNum);
        if (sala == null) {
            System.out.println("Sala não encontrada!");
            return null;
        }

        Assento assento = sala.getAssento(fileira, cadeira);
        if (assento.estarOcupado()) {
            System.out.println("Assento ocupado!");
            return null;
        }

        assento.reservar();
        Ingresso ingresso = new Ingresso(pessoa, sala, assento);
        ingresso.exibirIngresso();
        return ingresso;
    }
}