package com.cinema.view;

import java.util.ArrayList;

import static com.cinema.app.AppContext.cinemaController;
import com.cinema.model.Sala;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controlador da interface de cadastro de filmes.
 * Permite cadastrar um novo filme e associá-lo a uma sala disponível.
 */
public class CadastrarFilme {
    /** Referência para a página de administração, para atualização após cadastro */
    private AdminPage adminPage;

    /**
     * Define a página de administração associada.
     * @param adminPage Instância da AdminPage
     */
    public void setAdminPage(AdminPage adminPage) {
        this.adminPage = adminPage;
    }

    /** Campo de texto para o título do filme */
    @FXML private TextField tituloField;
    /** Campo de texto para o gênero do filme */
    @FXML private TextField generoField;
    /** Campo de texto para a duração do filme */
    @FXML private TextField duracaoField;
    /** Campo de texto para o número da sala (não utilizado, pois usa ComboBox) */
    @FXML private TextField salaField;
    /** ComboBox para seleção da sala disponível */
    @FXML private ComboBox selecionarSala;

    /**
     * Inicializa a tela de cadastro, carregando as salas disponíveis no ComboBox.
     */
    @FXML
    public void initialize() {
        carregarSalasNoComboBox();
    }

    /**
     * Manipula o evento de salvar um novo filme.
     * Valida os campos, cria a sala e associa o filme.
     */
    @FXML
    private void handleSalvar() {
        String titulo = tituloField.getText();
        String genero = generoField.getText();
        String duracaoStr = duracaoField.getText();
        String salaStr = selecionarSala.getValue().toString();

        try {
            if (titulo.isEmpty() || genero.isEmpty() || duracaoStr.isEmpty() || salaStr.isEmpty()) {
                System.out.println("Preencha todos os campos!");
                return;
            }

            if (cinemaController.getSalas().size() >= 5) {
                System.out.println("Limite de 5 salas atingido.");
                return;
            }

            double duracao = Double.parseDouble(duracaoStr);
            int numSala = Integer.parseInt(salaStr);

            cinemaController.criarSala(numSala, titulo, duracao, genero);

            if (adminPage != null) {
                adminPage.atualizarSalas();
            }

            System.out.println("Filme cadastrado com sucesso!");
            //handleLimpar();

        } catch (NumberFormatException e) {
            System.out.println("Duração inválida: " + duracaoStr);
        }
    }

    /**
     * Carrega as salas disponíveis (de 1 a 5) no ComboBox, removendo as já cadastradas.
     */
    private void carregarSalasNoComboBox() {
        selecionarSala.getItems().clear();

        // Lista com todas as salas de 1 a 5
        ArrayList<Integer> todasSalas = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            todasSalas.add(i);
        }

        // Remove as salas que já foram cadastradas
        for (Sala sala : cinemaController.getSalas()) {
            todasSalas.remove(Integer.valueOf(sala.getNumeroSala()));
        }

        // Adiciona ao ComboBox apenas as que sobraram
        selecionarSala.getItems().addAll(todasSalas);

        // Seleciona a primeira disponível, se existir
        if (!todasSalas.isEmpty()) {
            selecionarSala.getSelectionModel().selectFirst();
        }
    }

    /**
     * Limpa todos os campos do formulário.
     */
    @FXML
    private void handleLimpar() {
        tituloField.clear();
        generoField.clear();
        duracaoField.clear();
        salaField.clear();
    }

    /**
     * Encerra o sistema.
     * @param event Evento de ação do botão
     */
    @FXML
    private void handleSair(ActionEvent event) {
        System.exit(0);
    }

    /**
     * Fecha a janela de cadastro de filme.
     * @param event Evento de ação do botão
     */
    @FXML
    private void handleCancelar(ActionEvent event) {
        // Obtém o Stage atual a partir do botão que disparou o evento
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }


}





