package com.example.uvgmeetdemo;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Pos;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class LoginScreen {
    private Pane pane;
    private TextField usernameField;
    private PasswordField passwordField;


    public LoginScreen() {

        pane = new Pane();
        pane.setPrefSize(800, 600);


        Label titleLabel = new Label("Inicio de Sesión");
        titleLabel.setLayoutX(350);
        titleLabel.setLayoutY(100);

        Label usernameLabel = new Label("Nombre de Usuario:");
        usernameLabel.setLayoutX(250);
        usernameLabel.setLayoutY(200);

        usernameField = new TextField();
        usernameField.setLayoutX(400);
        usernameField.setLayoutY(200);

        Label passwordLabel = new Label("Contraseña:");
        passwordLabel.setLayoutX(250);
        passwordLabel.setLayoutY(250);

        passwordField = new PasswordField();
        passwordField.setLayoutX(400);
        passwordField.setLayoutY(250);

        Button loginButton = new Button("Iniciar Sesión");
        loginButton.setLayoutX(350);
        loginButton.setLayoutY(300);


        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (isValidLogin(usernameField.getText(), passwordField.getText())) {

                    onLoginSuccess.run();
                } else {

                    showAlert("Error", "Credenciales incorrectas. Inténtelo de nuevo.");
                }
            }
        });


        pane.getChildren().addAll(titleLabel, usernameLabel, usernameField, passwordLabel, passwordField, loginButton);
    }


    private boolean isValidLogin(String username, String password) {



        return username.equals("usuario") && password.equals("contraseña");
    }


    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    private Runnable onLoginSuccess;

    public void setOnLoginSuccess(Runnable callback) {
        onLoginSuccess = callback;
    }


    public Pane getPane() {
        return pane;
    }
}
