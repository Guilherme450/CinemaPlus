package com.cinema.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class HomePage {

    @FXML
    public void handleAdmniPage(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminPane.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage(); // nova janela
            stage.setTitle("Admin Page");
            stage.setScene(new Scene(root));
            stage.show();

            // Fecha a janela atual
            Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stageAtual.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleClientePage(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UserPane.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage(); // nova janela
            stage.setTitle("Pagina do Cliente");
            stage.setScene(new Scene(root));
            stage.show();

            // Fecha a janela atual
            Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stageAtual.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void trocarParaAdmin(ActionEvent event) {
        try {
            Parent adminRoot = FXMLLoader.load(getClass().getResource("/com/cinema/view/AdminPane.fxml"));
            Scene adminScene = new Scene(adminRoot);

            // Obtém o stage atual e troca a cena
            Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stageAtual.setScene(adminScene);
            stageAtual.setTitle("Administração");
            stageAtual.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void sair(ActionEvent event) {
        System.exit(0);
    }
}