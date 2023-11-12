package com.uvgmeetf.uvgmeetf;

import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InterestedScreen {
    private Pane pane;

    public InterestedScreen() {

        pane = new Pane();
        pane.setPrefSize(800, 600);


        Label titleLabel = new Label("Personas Interesadas");
        titleLabel.setLayoutX(350);
        titleLabel.setLayoutY(50);


        List<User> interestedUsers = generateRandomUsers(5);


        int yOffset = 100;
        for (User user : interestedUsers) {
            Label userLabel = new Label(user.getName());
            userLabel.setLayoutX(100);
            userLabel.setLayoutY(yOffset);
            yOffset += 50;


            pane.getChildren().add(userLabel);
        }
    }


    private List<User> generateRandomUsers(int count) {
        List<User> users = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < count; i++) {
            String[] randomNames = {"Alice", "Bob", "Charlie", "David", "Eve", "Frank", "Grace", "Helen"};
            String randomName = randomNames[random.nextInt(randomNames.length)];
            users.add(new User(randomName, "teta"));
        }

        return users;
    }


    public Pane getPane() {
        return pane;
    }
}
