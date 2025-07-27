package com.cinema.view;

import java.io.IOException;
import java.util.Optional;

import static com.cinema.app.AppContext.cinemaController;
import com.cinema.model.Sala;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Controlador da interface de administração do sistema de cinema.
 * Permite cadastrar filmes, remover salas, visualizar e gerenciar salas e assentos.
 */
public class AdminPage {

    /**
     * Abre a tela de cadastro de filme em uma nova janela.
     */
    public void handleCadastrarFilme() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/cinema/view/CadastrarFilme.fxml"));
            Parent root = loader.load();

            // Obtém o controlador e define a AdminPage atual
            CadastrarFilme cadastrarFilmeController = loader.getController();
            cadastrarFilmeController.setAdminPage(this); // Passa a instância atual

            Stage stage = new Stage(); // nova janela
            stage.setTitle("Cadastrar Filme");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Manipula a remoção de uma sala a partir do número informado pelo usuário.
     * Exibe alertas de sucesso ou erro conforme o resultado.
     * @param event Evento de ação do botão
     */
    @FXML
    private void handleRemoverSala(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Remover Sala");
        dialog.setHeaderText("Digite o número da sala a ser removida:");
        dialog.setContentText("Número:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(numeroStr -> {
            try {
                int numero = Integer.parseInt(numeroStr);//casting string -> int
                if (cinemaController.removerSala(numero)) {
                    atualizarSalas(); // Atualiza a exibição
                    showSucessAlert("Sala " + numero + " removida com sucesso!");

                } else {
                    showErrorAlert("Erro", "Sala não encontrada!");
                }
            } catch (NumberFormatException e) {
                showErrorAlert("Erro", "Número inválido!");
            }
        });
    }

    /**
     * Cria um card visual (VBox) para exibir informações de uma sala.
     * @param sala Sala a ser exibida
     * @return VBox com informações da sala
     */
    private VBox criarCard(Sala sala) {
        System.out.println("Salas: " + cinemaController.getSalas().size());
        Label numeroSala = new Label("Sala " + sala.getNumeroSala());
        Label titulo = new Label("Filme: " + sala.getFilme().getTitulo());
        Button bntAssentos = new Button("Assentos");

        bntAssentos.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");
        bntAssentos.setOnAction(event -> handleAbrirAssentos(sala));

        // Alinha o VBox no centro
        salaContainer.setAlignment(Pos.CENTER);

        VBox card = new VBox(numeroSala, titulo, bntAssentos);
        card.setSpacing(10);
        card.setStyle("-fx-border-color: black; -fx-background-color: #f4f4f4; -fx-padding: 10; -fx-pref-width: 150;");

        return card;
    }

    /** Container visual para os cards das salas */
    @FXML
    private HBox salaContainer;

    /**
     * Inicializa a página de administração, atualizando a lista de salas.
     */
    @FXML
    public void initialize() {
        atualizarSalas();
    }

    /**
     * Atualiza a exibição das salas cadastradas no sistema.
     */
    public void atualizarSalas() {
        salaContainer.getChildren().clear();

        System.out.println("Salas: " + cinemaController.getSalas().size());
        for (Sala sala : cinemaController.getSalas()) {
            VBox card = criarCard(sala);
            salaContainer.getChildren().add(card);
        }
    }

    /**
     * Abre a tela de seleção de assentos para a sala informada.
     * @param sala Sala selecionada
     */
    @FXML
    private void handleAbrirAssentos(Sala sala) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Assentos.fxml"));
            Parent root = loader.load();

            // Pega o controlador e passa a sala selecionada
            AssentosPage assentosController = loader.getController();
            if (assentosController == null) {
                throw new RuntimeException("Controller não foi injetado corretamente no FXML");
            }
            assentosController.setSala(sala);
            assentosController.esconderBotaoExcluir();

            Stage stage = new Stage();
            stage.setTitle("Seleção de Assentos");
            stage.setScene(new Scene(root, 800, 800));
            stage.show();

        } catch (IOException e) {
            System.err.println("Erro ao carregar a tela de assentos:");
            e.printStackTrace();
            showErrorAlert("Erro", "Não foi possível abrir a tela de assentos");
        }
    }

    /**
     * Exibe um alerta de erro com título e mensagem.
     * @param title Título do alerta
     * @param message Mensagem do alerta
     */
    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Exibe um alerta de sucesso com mensagem.
     * @param message Mensagem do alerta
     */
    private void showSucessAlert(String message)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sucesso");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /** Barra de menu da interface */
    @FXML
    private MenuBar menuBar;

    /**
     * Manipula o retorno para a tela inicial do sistema.
     * @param event Evento de ação do botão
     */
    @FXML
    public void handleHome(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePane.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage(); // nova janela
            stage.setTitle("Sistema de Cinema");
            stage.setScene(new Scene(root));
            stage.show();

            // Fecha a janela atual
            Stage stageAtual = (Stage) menuBar.getScene().getWindow();
            stageAtual.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Encerra o sistema.
     * @param event Evento de ação do botão
     */
    @FXML
    public void sair(ActionEvent event) {
        System.exit(0);
    }
}
