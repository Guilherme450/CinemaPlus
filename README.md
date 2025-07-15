# CinemaPlus
# 🎬 Sistema de Gerenciamento de Cinema

Projeto desenvolvido como atividade final da disciplina de **Programação Orientada a Objetos (POO)** no Instituto Federal de Educação, Ciência e Tecnologia do Maranhão - IFMA (Campus Caxias), sob orientação do professor **Dr. Wilker Luz**.

## 📌 Descrição

Este projeto consiste em um sistema orientado a objetos em **Java**, desenvolvido com o objetivo de gerenciar sessões de cinema. O sistema simula um cinema com múltiplas salas, controle de assentos e aplicação de descontos na compra de ingressos, utilizando os principais pilares da Programação Orientada a Objetos:

- Encapsulamento
- Herança
- Polimorfismo
- Composição

## 🎯 Funcionalidades

- Cadastro de filmes e alocação em salas;
- Visualização do mapa de assentos disponíveis e ocupados;
- Compra de ingressos com escolha personalizada de assento;
- Aplicação de descontos por categoria do cliente;
- Cálculo do valor final do ingresso com base nas regras definidas.

## 🧾 Categorias e Descontos

| Categoria  | Desconto Aplicado |
|------------|-------------------|
| Normal     | 0%                |
| Estudante  | 50%               |
| Idoso (60+)| 100%              |
| Professor  | 30%               |

## 🏛️ Estrutura do Cinema

- O cinema possui **5 salas**, cada uma com **200 lugares**.
- As salas são organizadas em **20 fileiras (A a T)**, com **10 cadeiras por fileira**.
- Cada sala exibe **um único filme por vez**.

## 🧩 Estrutura de Classes

- `Pessoa`: nome, idade, categoria (normal, estudante, idoso, professor).
- `Filme`: título, duração (em minutos), gênero.
- `Assento`: fileira (letra), número (1–10), status (ocupado ou não).
- `Sala`: número da sala, matriz de assentos, filme em exibição.
- `Ingresso`: pessoa, sala, assento, preço final com desconto.
- `Cinema`: lista de salas, gerenciamento da venda de ingressos.

## 🧪 Tecnologias Utilizadas

- Linguagem: **Java**
- Paradigma: **Programação Orientada a Objetos**
- IDE sugerida: IntelliJ IDEA ou VS code

## 🛠️ Como Executar o Projeto

1. Clone este repositório:

   ```bash
   git clone https://github.com/seu-usuario/sistema-cinema.git
   cd sistema-cinema

2. Crie uma nova branch para sua feature ou correção
    ```bash
   git checkout -b minha-nova-feature

3. Implemente sua alteração

4. Commit e push das alterações
    ```bash
   git add .
   git commit -m "Adiciona nova funcionalidade: X"
   git push origin minha-nova-feature
