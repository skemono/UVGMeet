package com.uvgmeetf.uvgmeetf;

import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.Pos;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ProfileScreen {
    private Pane pane;
    private User currentUser;

    public ProfileScreen(User user) {
        currentUser = user;


        pane = new Pane();
        pane.setPrefSize(800, 600);


        Label titleLabel = new Label("Mi Perfil");
        titleLabel.setLayoutX(350);
        titleLabel.setLayoutY(50);

        Label nameLabel = new Label("Nombre:");
        nameLabel.setLayoutX(250);
        nameLabel.setLayoutY(100);

        TextField nameField = new TextField(currentUser.getName());
        nameField.setLayoutX(400);
        nameField.setLayoutY(100);

        Label interestsLabel = new Label("Intereses Académicos:");
        interestsLabel.setLayoutX(250);
        interestsLabel.setLayoutY(150);

        TextArea interestsArea = new TextArea(currentUser.getInterests());
        interestsArea.setLayoutX(400);
        interestsArea.setLayoutY(150);
        interestsArea.setPrefWidth(300);
        interestsArea.setPrefHeight(100);

        Button saveButton = new Button("Guardar Cambios");
        saveButton.setLayoutX(350);
        saveButton.setLayoutY(300);


        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {


                currentUser.setName(nameField.getText());
                currentUser.setInterests(interestsArea.getText());


                showAlert("Éxito", "Cambios en el perfil guardados exitosamente.");
            }
        });


        pane.getChildren().addAll(titleLabel, nameLabel, nameField, interestsLabel, interestsArea, saveButton);
    }


    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    public Pane getPane() {
        return pane;
    }
}
