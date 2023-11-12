package com.uvgmeetf.uvgmeetf;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.Pos;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.time.LocalDate;

public class ActivitiesScreen {
    private Pane pane;

    public ActivitiesScreen() {

        pane = new Pane();
        pane.setPrefSize(800, 600);


        Label titleLabel = new Label("Actividades Académicas");
        titleLabel.setLayoutX(350);
        titleLabel.setLayoutY(50);


        ListView<Activity> activitiesListView = new ListView<>();
        activitiesListView.setLayoutX(100);
        activitiesListView.setLayoutY(100);
        activitiesListView.setPrefSize(600, 350);


        Button createActivityButton = new Button("Crear Actividad");
        createActivityButton.setLayoutX(100);
        createActivityButton.setLayoutY(470);

        Button viewDetailsButton = new Button("Ver Detalles");
        viewDetailsButton.setLayoutX(220);
        viewDetailsButton.setLayoutY(470);


        createActivityButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {



                Activity newActivity = new Activity("Nueva Actividad", "Descripción de la actividad", LocalDate.now(), "Ubicación");
                activitiesListView.getItems().add(newActivity);
            }
        });


        viewDetailsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Activity selectedActivity = activitiesListView.getSelectionModel().getSelectedItem();
                if (selectedActivity != null) {
                    showAlert("Detalles de la Actividad", selectedActivity.toString());
                } else {
                    showAlert("Error", "Selecciona una actividad para ver detalles.");
                }
            }
        });


        pane.getChildren().addAll(titleLabel, activitiesListView, createActivityButton, viewDetailsButton);
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
