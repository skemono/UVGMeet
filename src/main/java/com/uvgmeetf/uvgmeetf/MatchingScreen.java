package com.example.uvgmeetdemo;

import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.Pos;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.util.ArrayList;
import java.util.List;

public class MatchingScreen {
    private Pane pane;
    private List<User> userProfiles;
    private int currentProfileIndex;

    public MatchingScreen() {

        pane = new Pane();
        pane.setPrefSize(800, 600);


        userProfiles = new ArrayList<>();
        userProfiles.add(new User("Usuario 1", ""));
        userProfiles.add(new User("Usuario 2", ""));
        userProfiles.add(new User("Usuario 3", ""));


        currentProfileIndex = 0;


        Label profileNameLabel = new Label(userProfiles.get(currentProfileIndex).getName());
        profileNameLabel.setLayoutX(350);
        profileNameLabel.setLayoutY(100);

        Button likeButton = new Button("Me gusta");
        likeButton.setLayoutX(300);
        likeButton.setLayoutY(250);

        Button dislikeButton = new Button("No me gusta");
        dislikeButton.setLayoutX(450);
        dislikeButton.setLayoutY(250);


        likeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {



                showNextProfile();
            }
        });


        dislikeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {


                showNextProfile();
            }
        });


        pane.getChildren().addAll(profileNameLabel, likeButton, dislikeButton);
    }


    private void showNextProfile() {
        currentProfileIndex++;
        if (currentProfileIndex < userProfiles.size()) {
            Label profileNameLabel = new Label(userProfiles.get(currentProfileIndex).getName());
            profileNameLabel.setLayoutX(350);
            profileNameLabel.setLayoutY(100);
            pane.getChildren().set(0, profileNameLabel);
        } else {


            currentProfileIndex = 0;
            showNextProfile();
        }
    }


    public Pane getPane() {
        return pane;
    }
}

