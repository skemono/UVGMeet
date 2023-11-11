package com.uvgmeetf.uvgmeetf;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


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
    void Register(ActionEvent event) {
        System.out.println("boton registro nashe");
    }

}
