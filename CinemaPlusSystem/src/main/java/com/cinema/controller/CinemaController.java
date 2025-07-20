package com.cinema.controller;

import com.cinema.model.Cinema;
import com.cinema.model.Filme;
import com.cinema.model.Pessoa;
import com.cinema.model.Sala;

/**
 * Classe responsável pelo gerenciamento das operações do cinema,
 * como criação de salas, associação de filmes e venda de ingressos.
 * Atua como camada de controle entre a interface e o modelo Cinema.
 */
public class CinemaController {
    /**
     * Instância do cinema que armazena as salas e gerencia as operações.
     */
    private Cinema cinema = new Cinema();

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

    /**
     * Realiza a venda de um ingresso para um cliente em uma sala específica.
     *
     * @param cliente  Cliente que irá comprar o ingresso.
     * @param salaNum  Número da sala onde será exibido o filme.
     * @param fileira  Fileira do assento escolhido.
     * @param cadeira  Número da cadeira escolhida.
     */
    public void gerarIngresso(Pessoa cliente, int salaNum,
                             char fileira, int cadeira) {
        cinema.venderIngresso(cliente, salaNum, fileira, cadeira);
     }

}
