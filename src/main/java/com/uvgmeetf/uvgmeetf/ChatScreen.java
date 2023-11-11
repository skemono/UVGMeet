package com.example.uvgmeetdemo;

import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.Pos;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ChatScreen {
    private Pane pane;

    public ChatScreen() {

        pane = new Pane();
        pane.setPrefSize(800, 600);


        TextArea chatArea = new TextArea();
        chatArea.setLayoutX(50);
        chatArea.setLayoutY(50);
        chatArea.setPrefSize(700, 400);

        TextField messageField = new TextField();
        messageField.setLayoutX(50);
        messageField.setLayoutY(480);
        messageField.setPrefSize(600, 30);

        Button sendButton = new Button("Enviar");
        sendButton.setLayoutX(660);
        sendButton.setLayoutY(480);


        sendButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String message = messageField.getText();
                if (!message.isEmpty()) {


                    chatArea.appendText("Tú: " + message + "\n");


                    String response = "Respuesta del otro usuario.";
                    chatArea.appendText("Otro Usuario: " + response + "\n");


                    messageField.clear();
                }
            }
        });


        pane.getChildren().addAll(chatArea, messageField, sendButton);
    }


    public Pane getPane() {
        return pane;
    }
}
