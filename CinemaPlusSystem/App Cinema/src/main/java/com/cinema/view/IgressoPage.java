package com.cinema.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import com.cinema.model.*;

public class IgressoPage {
    @FXML
    private Label lblFilme;
    @FXML private Label lblSala;
    @FXML private Label lblAssento;
    @FXML private Label lblCliente;
    @FXML private Label lblCategoria;
    @FXML private Label lblValor;

    public void setIngresso(Ingresso ingresso) {
        lblFilme.setText(ingresso.getSala().getFilme().getTitulo());
        lblSala.setText(String.valueOf(ingresso.getSala().getNumeroSala()));
        lblAssento.setText(ingresso.getAssento().getIdentificador());
        lblCliente.setText(ingresso.getPessoa().getNome());
        lblCategoria.setText(ingresso.getPessoa().getCategoria());
        lblValor.setText(String.format("R$ %.2f", ingresso.getPrecoFinal()));
    }
}
