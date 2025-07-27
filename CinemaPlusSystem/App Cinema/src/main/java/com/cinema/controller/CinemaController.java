package com.cinema.controller;

import java.util.ArrayList;

import com.cinema.model.Cinema;
import com.cinema.model.Filme;
import com.cinema.model.Ingresso;
import com.cinema.model.Pessoa;
import com.cinema.model.Sala;

/**
 * Classe responsável pelo gerenciamento das operações do cinema,
 * como criação de salas, associação de filmes e venda de ingressos.
 * Atua como camada de controle entre a interface e o modelo Cinema.
 */
public class CinemaController {
//    /**
//     * Instância do cinema que armazena as salas e gerencia as operações.
//     */
    //private Cinema cinema = new Cinema();
    private final Cinema cinema;

    public CinemaController() {
        this.cinema = Cinema.carregarEstado();
    }

    public void salvarEstado() {
        cinema.salvarEstado();
    }
    /**
     * Lista interna que armazena todas as salas criadas.
     */
    //private ArrayList<Sala> salas = new ArrayList<>();

    /**
     * Cria uma nova sala de cinema, associa um filme a ela e adiciona ao cinema.
     *
     * @param numSala       Número identificador da sala.
     * @param tituloFilme   Título do filme a ser exibido.
     * @param duracaoFilme  Duração do filme em minutos.
     * @param generoFilme   Gênero do filme.
     * @return Sala recém-criada com o filme associado.
     */
    public Sala criarSala(int numSala, String tituloFilme,
                          double duracaoFilme, String generoFilme) {
        Filme novoFilme = new Filme(tituloFilme, duracaoFilme, generoFilme);
        Sala novaSala = new Sala(numSala, novoFilme);
        //salas.add(novaSala);
        cinema.adicionarSala(novaSala);
        return novaSala;
    }

    public Sala getSala(int numeroSala) {
        return cinema.getSalas().stream()
                .filter(s -> s.getNumeroSala() == numeroSala)
                .findFirst()
                .orElse(null);
    }

    /**
     * Realiza a venda de um ingresso para um cliente em uma sala específica.
     *
     * @param cliente  Cliente que irá comprar o ingresso.
     * @param salaNum  Número da sala onde será exibido o filme.
     * @param fileira  Fileira do assento escolhido.
     * @param cadeira  Número da cadeira escolhida.
     */
    public Ingresso gerarIngresso(Pessoa cliente, int salaNum,
                             char fileira, int cadeira) {
        try {
            //cinema.venderIngresso(cliente, salaNum, fileira, cadeira).exibirIngresso();
            Ingresso ingresso = cinema.venderIngresso(cliente, salaNum, fileira, cadeira);
            ingresso.exibirIngresso();
            return ingresso;
        } catch (Exception e) {
            System.out.println("Erro na emissão do ingresso: " + e.getMessage());
        }
        return null;
    }

    /**
     * Remove uma sala do cinema
     * @param numeroSala Número da sala a ser removida
     * @return true se a sala foi removida com sucesso
     */
    public boolean removerSala(int numeroSala) {
        boolean removido = cinema.removerSala(numeroSala);
        if (removido) {
            cinema.salvarEstado(); // Atualiza o arquivo de persistência
        }
        return removido;
    }

    /**
     * Retorna uma cópia da lista contendo as salas
     * 
     * @return lista de salas
     */
    public ArrayList<Sala> getSalas(){
        return new ArrayList<>(cinema.getSalas());
    }
}
