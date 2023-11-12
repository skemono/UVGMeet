package com.uvgmeetf.uvgmeetf;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CreacionPerfilController {

    @FXML
    private TextArea bio;

    @FXML
    private TextField carrera;

    @FXML
    private Button crearPerfilBton;

    @FXML
    private TextField edad;

    @FXML
    private TextArea gusto;

    @FXML
    private Label registrarError;

    @FXML
    private Button subirImageBton;

    private UVGMeetDB baseDatos = Main.getMeetDB();

    @FXML
    void crearPerfil(ActionEvent event) {

    }

}
