package com.cinema.view;

import java.io.IOException;

import static com.cinema.app.AppContext.cinemaController;
import static com.cinema.app.AppContext.clienteController;
import com.cinema.components.SalaCard;
import com.cinema.model.Pessoa;
import com.cinema.model.Sala;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Controlador da interface de cadastro e seleção de cliente.
 * Permite ao usuário informar seus dados, escolher categoria e sala,
 * e iniciar o processo de compra de ingresso e seleção de assento.
 */
public class CadastroCliente {
    /** Botão para iniciar a compra do ingresso */
    @FXML private Button comprarIngresso;
    /** Campo de texto para o nome do usuário */
    @FXML private TextField nomeUser;
    /** Campo de texto para a idade do usuário */
    @FXML private TextField idadeUser;
    /** ComboBox para seleção da categoria do cliente */
    @FXML private ComboBox selecionarCategoria;
    /** ComboBox para seleção da sala */
    @FXML private ComboBox selecionarSala;
    /** Container visual para os cards das salas */
    @FXML private HBox salaContainer;
    /** Barra de menu da interface */
    @FXML private MenuBar menuBar;

    /**
     * Inicializa a tela de cadastro, atualizando e carregando as salas disponíveis.
     */
    @FXML
    public void initialize() {
        atualizarSalas();
        carregarSalasNoComboBox();
    }


    //@FXML
    //private HBox salaContainer;

    private VBox criarCard(Sala sala) {
        return new SalaCard(sala, () -> {
            handleAbrirAssentos(sala);
        });
    }

    /**
     * Carrega as salas disponíveis no ComboBox de seleção.
     */
    private void carregarSalasNoComboBox() {
        selecionarSala.getItems().clear();
        for (Sala sala : cinemaController.getSalas()) {
            selecionarSala.getItems().add(sala.getNumeroSala());
        }
    }

    /**
     * Manipula o evento de seleção de assento, validando os campos e abrindo a tela de assentos.
     */
    @FXML
    private void selecionarAssento() {
        try {
            // Validação dos campos obrigatórios
            if (nomeUser.getText().isEmpty() || idadeUser.getText().isEmpty()) {
                mostrarAlerta("Erro", "Preencha todos os campos obrigatórios");
                return;
            }

            // Conversão segura da idade
            int numIdade;
            try {
                numIdade = Integer.parseInt(idadeUser.getText());
                if (numIdade <= 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                mostrarAlerta("Erro", "Idade inválida. Digite um número positivo.");
                return;
            }

            // Validação das seleções
            if (selecionarSala.getValue() == null || selecionarCategoria.getValue() == null) {
                mostrarAlerta("Erro", "Selecione uma sala e uma categoria");
                return;
            }

            String categoria = selecionarCategoria.getValue().toString();

            // Conversão segura do número da sala
            int numSala;
            try {
                numSala = Integer.parseInt(selecionarSala.getValue().toString());
            } catch (NumberFormatException e) {
                mostrarAlerta("Erro", "Número de sala inválido");
                return;
            }

            // Criação do cliente e abertura da tela de assentos
            Pessoa cliente = clienteController.criarCliente(nomeUser.getText(), numIdade, categoria);

            System.out.printf("Reserva iniciada: %s, %d anos, %s, Sala %d%n",
                    cliente.getNome(), cliente.getIdade(),
                    cliente.getCategoria(), numSala);

            AbrirAssentos(numSala, cliente);

        } catch (Exception e) {
            System.err.println("Erro inesperado: " + e.getMessage());
            mostrarAlerta("Erro", "Ocorreu um erro inesperado. Tente novamente.");
        }
    }

    /**
     * Exibe um alerta de erro na tela.
     * @param titulo Título do alerta
     * @param mensagem Mensagem do alerta
     */
    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    /**
     * Abre a tela de seleção de assentos para o cliente e sala informados.
     * @param numeroSala Número da sala
     * @param cliente Cliente que irá reservar
     */
    @FXML
    private void AbrirAssentos(int numeroSala, Pessoa cliente) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/cinema/view/Assentos.fxml"));
            Parent root = loader.load();

            // Obtém o controlador e passa os parâmetros
            AssentosPage controller = loader.getController();
            controller.configurarPorNumeroSala(numeroSala, cliente);

            Stage stage = new Stage();
            stage.setTitle("Assentos - Sala " + numeroSala + " | Cliente: " + cliente.getNome());
            stage.setScene(new Scene(root, 800, 800));
            stage.setResizable(false);
            stage.show();

        } catch (IOException e) {
            System.err.println("Erro ao abrir tela de assentos:");
            e.printStackTrace();
            showErrorAlert("Erro", "Não foi possível abrir a tela de assentos");
        }
    }

    /**
     * Atualiza a exibição das salas cadastradas.
     */
    public void atualizarSalas() {
        salaContainer.getChildren().clear();
        for (Sala sala : cinemaController.getSalas()) {
            VBox card = criarCard(sala);
            salaContainer.getChildren().add(card);
        }
    }

    /**
     * Abre a visualização de assentos para uma sala específica.
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
            stage.setTitle("Visualização de Assentos");
            stage.setScene(new Scene(root, 800, 800));
            stage.show();

        } catch (IOException e) {
            System.err.println("Erro ao carregar a tela de assentos:");
            e.printStackTrace();
            showAlert("Erro", "Não foi possível abrir a tela de assentos");
        }
    }

    /**
     * Exibe um alerta de erro na tela.
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
     * Exibe um alerta de erro na tela.
     * @param title Título do alerta
     * @param message Mensagem do alerta
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

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
