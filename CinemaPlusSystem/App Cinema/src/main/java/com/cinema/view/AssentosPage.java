package com.cinema.view;

import static com.cinema.app.AppContext.cinemaController;
import static com.cinema.app.AppContext.clienteController;
import com.cinema.model.Ingresso;
import com.cinema.model.Pessoa;
import com.cinema.model.Sala;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Controlador da interface de seleção de assentos.
 * Permite visualizar, selecionar e reservar assentos em uma sala de cinema.
 */
public class AssentosPage {
    /** Grid para organizar os assentos visualmente */
    @FXML private GridPane gridAssentos;
    /** Label para mensagens e informações da sala */
    @FXML private Label lblInfo;
    /** VBox para exibir a legenda de cores dos assentos */
    @FXML private VBox legendaBox;
    /** Botão para voltar à tela anterior */
    @FXML private Button btnVoltar;
    /** Botão para excluir (pode ser ocultado) */
    @FXML private Button botaoExcluir;

    /** Sala atualmente exibida */
    private Sala sala;
    /** Cliente que está reservando o assento */
    private Pessoa cliente;
    /** Referência ao botão do assento selecionado */
    private Button assentoSelecionado;

    /**
     * Inicializa a interface de assentos, configurando a legenda e ação do botão voltar.
     */
    @FXML
    public void initialize() {
        //criarAssentos();
        configurarLegenda();
        btnVoltar.setOnAction(e -> voltarParaAdmin());
    }

    /**
     * Define a sala a ser exibida e carrega os assentos.
     * @param sala Sala selecionada
     */
    public void setSala(Sala sala) {
        this.sala = sala;
        // Atualiza a interface conforme a sala
        if (sala != null) {
            lblInfo.setText("Sala " + sala.getNumeroSala() + " - " + sala.getFilme().getTitulo());
            carregarAssentos();
        }
    }

    /**
     * Carrega e exibe os botões dos assentos da sala.
     */
    private void carregarAssentos() {
        gridAssentos.getChildren().clear();

        // Verifica se a sala existe
        if (sala == null) {
            System.out.println("Aviso: Nenhuma sala definida");
            return;
        }

        // 20 fileiras (A-T)
        for (char fileira = 'A'; fileira <= 'T'; fileira++) {
            // 10 assentos por fileira
            for (int numero = 1; numero <= 10; numero++) {
                Button btn = criarBotaoAssento(fileira, numero);
                gridAssentos.add(btn, numero-1, fileira-'A');
            }
        }
    }

    /**
     * Cria um botão visual para um assento, configurando cor e ação.
     * @param fileira Letra da fileira
     * @param numero Número do assento
     * @return Botão configurado
     */
    private Button criarBotaoAssento(char fileira, int numero) {
        Button btn = new Button(fileira + "" + numero);
        btn.setPrefSize(40, 20);

        // Verifica status do assento
        if (sala.isAssentoOcupado(fileira, numero)) {
            btn.setStyle("-fx-background-color: #FFC107; -fx-text-fill: black;"); // Amarelo (reservado)
            btn.setDisable(true);
        } else {
            btn.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;"); // Verde (livre)
            btn.setOnAction(e -> selecionarAssento(btn, fileira, numero));
        }

        return btn;
    }

    /**
     * Seleciona visualmente um assento e atualiza o estado do botão.
     * @param btn Botão do assento selecionado
     * @param fileira Letra da fileira
     * @param numero Número do assento
     */
    private void selecionarAssento(Button btn, char fileira, int numero) {
        // Desseleciona o anterior
        if (assentoSelecionado != null) {
            assentoSelecionado.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        }

        // Seleciona o novo
        btn.setStyle("-fx-background-color: #F44336; -fx-text-fill: white;"); // Vermelho
        assentoSelecionado = btn;

        System.out.println("Assento selecionado: " + fileira + numero);
    }

    /**
     * Configura a legenda de cores dos assentos (livre, reservado, selecionado).
     */
    private void configurarLegenda() {
        legendaBox.setSpacing(10);
        legendaBox.setPadding(new Insets(15));
        legendaBox.setStyle("-fx-border-color: #ccc; -fx-border-radius: 5;");

        Label titulo = new Label("Legenda:");
        titulo.setFont(Font.font(14));

        HBox livre = criarItemLegenda("#4CAF50", "Livre");
        HBox reservado = criarItemLegenda("#FFC107", "Reservado");
        HBox selecionado = criarItemLegenda("#F44336", "Selecionado");

        legendaBox.getChildren().addAll(titulo, livre, reservado, selecionado);
    }

    /**
     * Cria um item visual para a legenda de assentos.
     * @param cor Cor do retângulo
     * @param texto Texto descritivo
     * @return HBox com legenda
     */
    private HBox criarItemLegenda(String cor, String texto) {
        HBox box = new HBox(10);
        box.setAlignment(Pos.CENTER_LEFT);

        Rectangle rect = new Rectangle(20, 20);
        rect.setFill(Color.web(cor));

        Label label = new Label(texto);

        box.getChildren().addAll(rect, label);
        return box;
    }

    /**
     * Fecha a janela atual e retorna para a tela de administração.
     */
    @FXML
    private void voltarParaAdmin() {
        Stage stage = (Stage) btnVoltar.getScene().getWindow();
        stage.close();
    }

    /**
     * Confirma a reserva do assento selecionado, gera o ingresso e exibe popup.
     */
    @FXML
    private void confirmarReserva() {
        if (assentoSelecionado != null) {
            String texto = assentoSelecionado.getText();
            char fileira = texto.charAt(0);
            int numero = Integer.parseInt(texto.substring(1));

            try {
                clienteController.criarCliente("João Silva", 25, "Estudante");
                Ingresso ingresso = cinemaController.gerarIngresso(cliente, sala.getNumeroSala(), fileira, numero);

                // Atualiza visualmente
                assentoSelecionado.setStyle("-fx-background-color: #FFC107;");
                assentoSelecionado.setDisable(true);
                assentoSelecionado = null;

                // Mostra o popup do ingresso
                IngressoPopup.mostrar(ingresso);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro na reserva");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        }
    }

    /**
     * Configura a interface para uma sala específica e cliente.
     * @param numeroSala Número da sala
     * @param cliente Cliente que irá reservar
     */
    public void configurarPorNumeroSala(int numeroSala, Pessoa cliente) {
        this.cliente = cliente;
        this.sala = cinemaController.getSala(numeroSala);

        if (sala != null) {
            Platform.runLater(() -> {
                carregarAssentos();
                atualizarTitulo();
            });
        } else {
            System.err.println("Sala não encontrada: " + numeroSala);
            ((Stage) gridAssentos.getScene().getWindow()).close();
        }
    }

    /**
     * Atualiza o título da janela com informações da sala e filme.
     */
    private void atualizarTitulo() {
        Stage stage = (Stage) gridAssentos.getScene().getWindow();
        stage.setTitle("Assentos - Sala " + sala.getNumeroSala()
                + " - " + sala.getFilme().getTitulo());
    }

    /**
     * Esconde o botão de exclusão de assentos (caso necessário).
     */
    public void esconderBotaoExcluir() {
        botaoExcluir.setVisible(false);
    }
}