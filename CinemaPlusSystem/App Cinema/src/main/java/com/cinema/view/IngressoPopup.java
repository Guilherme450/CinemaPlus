package com.cinema.view;

import com.cinema.model.*;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
//import javafx.stage.Stage;

public class IngressoPopup {

    public static void mostrar(Ingresso ingresso) {
        // Cria o diálogo
        Dialog<Void> dialog = new Dialog<>();
        dialog.setTitle("Ingresso Reservado");

        // Configura o modal
        dialog.initModality(Modality.APPLICATION_MODAL);

        // Adiciona botão OK
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);

        // Cria o conteúdo
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 20, 10, 10));

        // Adiciona informações do ingresso
        grid.add(new Label("Filme:"), 0, 0);
        grid.add(new Label(ingresso.getSala().getFilme().getTitulo()), 1, 0);

        grid.add(new Label("Sala:"), 0, 1);
        grid.add(new Label(String.valueOf(ingresso.getSala().getNumeroSala())), 1, 1);

        grid.add(new Label("Assento:"), 0, 2);
        grid.add(new Label(ingresso.getAssento().getIdentificador()), 1, 2);

        grid.add(new Label("Cliente:"), 0, 3);
        grid.add(new Label(ingresso.getPessoa().getNome()), 1, 3);

        grid.add(new Label("Categoria:"), 0, 4);
        grid.add(new Label(ingresso.getPessoa().getCategoria()), 1, 4);

        grid.add(new Label("Valor:"), 0, 5);
        grid.add(new Label(String.format("R$ %.2f", ingresso.getPrecoFinal())), 1, 5);


        grid.setStyle("-fx-font-size: 14px;");

        // Define o conteúdo do diálogo
        dialog.getDialogPane().setContent(grid);

        // Mostra o diálogo
        dialog.showAndWait();
    }
}