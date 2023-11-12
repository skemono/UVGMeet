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

    private boolean imageUploaded = false;

    void showError(String txt){
        this.registrarError.setStyle("-fx-text-fill: red");
        this.registrarError.setText(txt);
        this.registrarError.requestLayout();

    }

    void showMessage(String txt){
        this.registrarError.setStyle("-fx-text-fill: black");
        this.registrarError.setText(txt);
        this.registrarError.requestLayout();
    }
    @FXML
    void crearPerfil(ActionEvent event) {
        String biografia = this.bio.getText();
        String career = this.carrera.getText();
        String age = this.edad.getText();
        String likes = this.gusto.getText();

        if (biografia.isEmpty() || career.isEmpty() || age.isEmpty() || likes.isEmpty() || !imageUploaded){
            showError("Debe llenar todos los campos.");
            return;
        }
    }

    @FXML
    void subirImagen(ActionEvent event) {

    }

}
