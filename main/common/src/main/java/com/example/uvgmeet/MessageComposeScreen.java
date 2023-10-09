package com.example.uvgmeet;

import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.Button;
import com.codename1.ui.layouts.BoxLayout;

public class MessageComposeScreen extends Form {

    public MessageComposeScreen() {
        super("Nuevo Mensaje", BoxLayout.y());

        // Campo de entrada para el mensaje
        TextField messageField = new TextField("", "Escribe tu mensaje aquí...");

        Button sendButton = new Button("Enviar");
        sendButton.addActionListener(e -> {
            // Obtener el mensaje ingresado por el usuario
            String messageText = messageField.getText();

            // Enviar el mensaje (debes implementar la lógica)
            sendMessage(messageText);

            // Cerrar la pantalla de composición de mensajes después de enviar
            this.close();
        });

        addAll(messageField, sendButton);
    }

    // Método para enviar un mensaje (debes implementar esto)
    private void sendMessage(String messageText) {
        // Implementa aquí la lógica para enviar el mensaje, como enviarlo a través de una red o guardar en la base de datos.
    }
}
