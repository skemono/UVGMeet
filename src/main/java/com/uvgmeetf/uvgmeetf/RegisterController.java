package com.uvgmeetf.uvgmeetf;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import java.util.Random;



/*
Map<String, Object> updates = new HashMap<>();
        updates.put("nombre", "Juan");
        updates.put("edad", 15);
*/

public class RegisterController {

    @FXML
    private TextField email;

    @FXML
    private PasswordField passwd;

    @FXML
    private Button regBton;

    @FXML
    private TextField user;

    @FXML
    private Label registrarError;

    private UVGMeetDB baseDatos;

    public RegisterController() throws IOException {
        this.baseDatos = Main.getMeetDB();
    }

    @FXML
    void Register(ActionEvent event) throws ExecutionException, InterruptedException {
        System.out.println("boton registro nashe");
        Random random = new Random();

        String correo = email.getText();
        String usuario = user.getText();
        String contra = passwd.getText();
        int randomNumber = random.nextInt(90000) + 10000;
        while (this.baseDatos.verificarExistencia("usuario", "id", randomNumber)){
            randomNumber = random.nextInt(90000) + 10000;
        }

        Map<String, Object> nuevoUsuario = new HashMap<>();
        nuevoUsuario.put("nombre", usuario);
        nuevoUsuario.put("correo", correo);
        nuevoUsuario.put("passwd", contra);
        nuevoUsuario.put("id", randomNumber);

        if (correo.contains("@uvg.edu.gt")){
            if (!this.baseDatos.verificarExistencia("usuario", "correo", correo)){
                this.baseDatos.agregar("usuario", "usuario" + this.baseDatos.nextNumberOfDoc("usuario"), nuevoUsuario);
                registrarError.setStyle("-fx-text-fill: black");
                registrarError.setText("Registrado con éxito.");
            } else {
                registrarError.setStyle("-fx-text-fill: red");
                registrarError.setText("El correo ingresado ya esta en uso.");
            }
        } else{
            registrarError.setStyle("-fx-text-fill: red");
            registrarError.setText("Tiene que ingresar un correo de la UVG.");
        }
        registrarError.requestLayout();

    }

}
