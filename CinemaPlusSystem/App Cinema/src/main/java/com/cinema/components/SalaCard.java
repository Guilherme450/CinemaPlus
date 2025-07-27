package com.cinema.components;

import com.cinema.model.Sala;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class SalaCard extends VBox {
    private final Sala sala;
    private final Runnable onAssentosClick;

    public SalaCard(Sala sala, Runnable onAssentosClick) {
        this.sala = sala;
        this.onAssentosClick = onAssentosClick;
        initialize();
    }

    private void initialize() {
        // Estilo base do card
        this.setSpacing(10);
        this.setStyle("-fx-border-color: #ddd; -fx-border-radius: 5; -fx-padding: 15;");
        this.setPrefWidth(180);
        double duracao = sala.getFilme().getDuracao();
        String strDuracao = Double.toString(duracao);

        // Conteúdo do card
        Label lblSala = new Label("Sala " + sala.getNumeroSala());
        lblSala.setStyle("-fx-font-weight: bold; -fx-font-size: 16;");

        Label lblFilme = new Label(sala.getFilme().getTitulo());
        lblFilme.setStyle("-fx-text-fill: #555; -fx-font-size: 14;");

        Label lblGenero = new Label(sala.getFilme().getGenero());
        lblGenero.setStyle("-fx-text-fill: #555; -fx-font-size: 14;");

        Label lblDuracao = new Label(strDuracao + " min");
        lblDuracao.setStyle("-fx-text-fill: #555; -fx-font-size: 14;");

        Button btnAssentos = new Button("Ver Assentos");
        btnAssentos.setStyle("-fx-background-color: #4285f4; -fx-text-fill: white;");
        btnAssentos.setOnAction(e -> onAssentosClick.run());

        // Adiciona elementos ao card
        this.getChildren().addAll(lblSala, lblFilme, lblGenero, lblDuracao, btnAssentos);
    }
}