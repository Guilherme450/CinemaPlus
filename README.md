# CinemaPlus
# 🎬 Sistema de Gerenciamento de Cinema

Projeto desenvolvido como atividade final da disciplina de **Programação Orientada a Objetos (POO)** no Instituto Federal de Educação, Ciência e Tecnologia do Maranhão - IFMA (Campus Caxias), sob orientação do professor **Dr. Wilker Luz**.
# Desenvolvedores

<table> <tr> <td align="center"> <a href="https://github.com/Guilherme450"> <img src="https://github.com/Guilherme450.png" width="100px;" alt="Foto de Guilherme no GitHub"/><br /> <sub><b>Guilherme Lima</b></sub> </a> </td> <td align="center"> <a href="https://github.com/Kelvinl14"> <img src="https://media.licdn.com/dms/image/v2/D4D03AQG-jEXhl8_L7g/profile-displayphoto-shrink_400_400/profile-displayphoto-shrink_400_400/0/1715601864655?e=1756339200&v=beta&t=ou2t6AKj0t4ff2Pz_8YBMRzwGBaTH74jdzQblJVyMTk" width="100px;" alt="Foto do Segundo Autor"/><br /> <sub><b>Kelvyn Leôncio</b></sub> </a> </td> </tr> </table>

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
- Framework: **JavaFX**
- Paradigma: **Programação Orientada a Objetos**
- IDE sugerida: IntelliJ IDEA ou VS code

## 🛠️ Como Executar o Projeto

1. Clone este repositório:

   ```bash
   git clone https://github.com/Guilherme450/CinemaPlus
   cd CinemaPlus

2. Crie uma nova branch para sua feature ou correção
    ```bash
   git checkout -b minha-nova-feature

3. Implemente sua alteração

4. Commit e push das alterações
    ```bash
   git add .
   git commit -m "Adiciona nova funcionalidade: X"
   git push origin minha-nova-feature
