package com.cinema.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.cinema.model.Pessoa;

/**
 * Controlador para gerenciamento de clientes.
 * 
 * Responsável por criar, armazenar, buscar, atualizar e remover clientes
 * de forma thread-safe, além de validar dados e gerar IDs únicos.
 */
public class ClienteController {
    /** Mapa thread-safe de clientes, indexados por ID. */
    private final ConcurrentHashMap<Integer, Pessoa> usuarios = new ConcurrentHashMap<>();
    /** Gerador atômico de IDs únicos para clientes. */
    private final AtomicInteger ultimoId = new AtomicInteger(0);

    /** Lista de categorias válidas para clientes. */
    private static final List<String> CATEGORIAS_VALIDAS =
            List.of("Normal", "Estudante", "Professor", "Idoso", "Deficiente");

    /**
     * Cria e armazena um novo cliente após validação dos dados.
     *
     * @param nome      Nome do cliente
     * @param idade     Idade do cliente
     * @param categoria Categoria do cliente (deve ser válida)
     * @return Pessoa   Cliente criado e armazenado
     * @throws IllegalArgumentException se algum dado for inválido
     */
    public Pessoa criarCliente(String nome, int idade, String categoria) {
        validarDados(nome, idade, categoria);

        int novoId = ultimoId.incrementAndGet();
        Pessoa cliente = new Pessoa(novoId, nome, idade, categoria);
        usuarios.put(novoId, cliente);
        
        return cliente;
    }

    /**
     * Valida os dados do cliente.
     *
     * @param nome      Nome do cliente
     * @param idade     Idade do cliente
     * @param categoria Categoria do cliente
     * @throws IllegalArgumentException se algum dado for inválido
     */
    private void validarDados(String nome, int idade, String categoria) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome inválido");
        }

        if (idade < 0 || idade > 120) {
            throw new IllegalArgumentException("Idade deve ser entre 0 e 120 anos");
        }

        if (!CATEGORIAS_VALIDAS.contains(categoria)) {
            throw new IllegalArgumentException("Categoria inválida. Use: " + CATEGORIAS_VALIDAS);
        }
    }

    /**
     * Busca um cliente pelo ID.
     *
     * @param id ID do cliente
     * @return Pessoa correspondente ao ID, ou null se não encontrado
     */
    public Pessoa getCliente(int id) {
        return usuarios.get(id);
    }

    /**
     * Retorna uma lista imutável de todos os clientes cadastrados.
     *
     * @return Lista imutável de clientes
     */
    public List<Pessoa> getTodosClientes() {
        return Collections.unmodifiableList(new ArrayList<>(usuarios.values()));
    }

    /**
     * Remove um cliente pelo ID.
     *
     * @param id ID do cliente a ser removido
     * @return true se o cliente foi removido, false se não encontrado
     */
    public boolean removerCliente(int id) {
        return usuarios.remove(id) != null;
    }

    /**
     * Atualiza os dados de um cliente existente.
     *
     * @param id            ID do cliente a ser atualizado
     * @param novoNome      Novo nome
     * @param novaIdade     Nova idade
     * @param novaCategoria Nova categoria (deve ser válida)
     * @return Pessoa atualizada, ou null se o cliente não existir
     * @throws IllegalArgumentException se algum dado for inválido
     */
    public Pessoa atualizarCliente(int id, String novoNome, int novaIdade, String novaCategoria) {
        validarDados(novoNome, novaIdade, novaCategoria);

        return usuarios.computeIfPresent(id, (k, cliente) -> {
            cliente.setNome(novoNome);
            cliente.setIdade(novaIdade);
            cliente.setCategoria(novaCategoria);
            return cliente;
        });
    }
}