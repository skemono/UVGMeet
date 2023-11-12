package com.uvgmeetf.uvgmeetf;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;


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

    private UVGMeetDB baseDatos;

    public RegisterController() throws IOException {
        this.baseDatos = new UVGMeetDB();
    }

    @FXML
    void Register(ActionEvent event) throws ExecutionException, InterruptedException {
        System.out.println("boton registro nashe");

        String correo = email.getText();
        String usuario = user.getText();
        String contra = passwd.getText();

        Map<String, Object> nuevoUsuario = new HashMap<>();
        nuevoUsuario.put("nombre", usuario);
        nuevoUsuario.put("correo", correo);
        nuevoUsuario.put("passwd", contra);

        if (correo.contains("@uvg.edu.gt")){
            this.baseDatos.agregar("usuario", "usuario" + this.baseDatos.nextNumberOfDoc("usuario"), nuevoUsuario);
        } else{

        }
    }

}
