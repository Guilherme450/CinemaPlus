package com.cinema.controller;

import com.cinema.model.Pessoa;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Classe responsável pelo gerenciamento dos clientes do sistema CinemaPlus.
 * Permite criar novos clientes e armazená-los em uma lista interna.
 * Atua como camada de controle entre a interface e o modelo Pessoa.
 */
public class ClienteController {
    /**
     * Lista estática que armazena todos os usuários (clientes) cadastrados.
     */
    private final List<Pessoa> usuarios = new ArrayList<>();

    /**
     * Cria um novo cliente e o adiciona à lista de usuários.
     *
     * @param nomeCliente      Nome do cliente.
     * @param idadeCliente     Idade do cliente.
     * @param categoriaCliente Categoria do cliente (ex: estudante, idoso, etc).
     * @return Objeto Pessoa recém-criado.
     */
    public Pessoa criarCliente(String nomeCliente, int idadeCliente, 
    String categoriaCliente)
    {
        // camada de validação dos dados
        if (nomeCliente == null || nomeCliente.trim().isEmpty())
        {
            throw new IllegalArgumentException("Nome de usuário não pode ser vazio");
        }
        else if (idadeCliente < 0)
        {
            throw new IllegalArgumentException("Idade negativa proibida");
        }
        else if (categoriaCliente == null || categoriaCliente.trim().isEmpty())
        {
            throw new IllegalArgumentException("Categoria de cliente não pode ser vazia");
        }

        Pessoa novoCliente = new Pessoa(nomeCliente, idadeCliente, categoriaCliente);
        usuarios.add(novoCliente);
        return novoCliente;
    }

   public List<Pessoa> getUsuarios()
   {
        return Collections.unmodifiableList(usuarios);
   }
}
