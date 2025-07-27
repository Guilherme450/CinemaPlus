//package com.cinema.components;
//
//import com.cinema.view.AssentosPage;
//import javafx.application.Application;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.geometry.Insets;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.ScrollPane;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.VBox;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Rectangle;
//import javafx.stage.Stage;
//
//import com.cinema.model.*;
//import java.io.IOException;
//import static com.cinema.app.AppContext.cinemaController;
//
//public class CarrosselApp {
//
//    public static VBox criarCard(Sala sala) {
//        System.out.println("Salas: " + cinemaController.getSalas().size());
//        Label numeroSala = new Label("Sala " + sala.getNumeroSala());
//        Label titulo = new Label("Filme: " + sala.getFilme().getTitulo());
//        Button bntAssentos = new Button("Assentos");
//
//        bntAssentos.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");
//        bntAssentos.setOnAction(event -> handleAbrirAssentos(sala));
//
//        VBox card = new VBox(numeroSala, titulo, bntAssentos);
//        card.setSpacing(10);
//        card.setStyle("-fx-border-color: black; -fx-background-color: #f4f4f4; -fx-padding: 10; -fx-pref-width: 150;");
//
//        return card;
//    }
//
//    @FXML
//    private void handleAbrirAssentos(Sala sala) {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("Assentos.fxml"));
//            Parent root = loader.load();
//
//            // Obtém o controlador e passa a sala selecionada
//            AssentosPage assentosController = loader.getController();
//            if (assentosController == null) {
//                throw new RuntimeException("Controller não foi injetado corretamente no FXML");
//            }
//            assentosController.setSala(sala); // (Você precisará criar esse método em AssentosPage)
//
//            Stage stage = new Stage();
//            stage.setTitle("Seleção de Assentos");
//            stage.setScene(new Scene(root, 800, 1000));
//            stage.show();
//
//        } catch (IOException e) {
//            System.err.println("Erro ao carregar a tela de assentos:");
//            e.printStackTrace();
//            showAlert("Erro", "Não foi possível abrir a tela de assentos");
//        }
//    }
//    private void showAlert(String title, String message) {
//        Alert alert = new Alert(Alert.AlertType.ERROR);
//        alert.setTitle(title);
//        alert.setHeaderText(null);
//        alert.setContentText(message);
//        alert.showAndWait();
//    }
//
////    @Override
////    public void start(Stage stage) {
////        // Caixa que vai conter os cards
////        HBox hbox = new HBox(15); // Espaço entre os itens
////        hbox.setPadding(new Insets(10));
////
////        // Simula múltiplos cards
////        for (int i = 1; i <= 10; i++) {
////            VBox card = criarCard("Sala " + i, "Filme " + i);
////            hbox.getChildren().add(card);
////        }
////
////        // ScrollPane para permitir rolagem horizontal
////        ScrollPane scrollPane = new ScrollPane(hbox);
////        scrollPane.setFitToHeight(true); // ocupa altura toda
////        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS); // barra horizontal sempre
////        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);  // sem barra vertical
////        scrollPane.setPannable(true); // arrastar com mouse
////
////        Scene scene = new Scene(scrollPane, 800, 300);
////        stage.setScene(scene);
////        stage.setTitle("Carrossel de Salas");
////        stage.show();
////    }
////
////    public static VBox criarCard(String sala, String filme) {
////        VBox box = new VBox(10);
////        box.setPadding(new Insets(10));
////        box.setStyle("-fx-border-color: black; -fx-background-color: #eeeeee;");
////        box.setPrefSize(150, 220);
////
////        Label lblSala = new Label(sala);
////        Label lblFilme = new Label(filme);
////
////        Rectangle poster = new Rectangle(100, 120, Color.LIGHTGRAY);
////        poster.setArcWidth(10);
////        poster.setArcHeight(10);
////
////        box.getChildren().addAll(lblSala, poster, lblFilme);
////        return box;
////    }
//
//    //public static void main(String[] args) {
//    //    launch();
//    //}
//}
//
