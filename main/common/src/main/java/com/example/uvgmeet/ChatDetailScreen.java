package com.example.uvgmeet;

import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

public class ChatDetailScreen extends Form {

    public ChatDetailScreen(int chatIndex) {
        super("Detalle de Chat", BoxLayout.y());

        // Obtener detalles del chat según el índice (debes implementar la lógica)
        Chat chat = getChatDetails(chatIndex);

        if (chat != null) {
            // Mostrar detalles del chat
            Label senderLabel = new Label("Remitente: " + chat.getSender());
            Label messageLabel = new Label("Mensaje: " + chat.getMessage());

            addAll(senderLabel, messageLabel);
        } else {
            // Manejar el caso en el que no se encuentren detalles del chat
            Label noDetailsLabel = new Label("No se encontraron detalles del chat.");
            add(noDetailsLabel);
        }
    }

    // Método para obtener los detalles del chat desde la base de datos (debes implementar esto)
    private Chat getChatDetails(int chatIndex) {
        // Implementa aquí la lógica para obtener los detalles del chat según el índice
        // Puedes consultar tu base de datos o fuente de datos aquí.
        // Devuelve una instancia de la clase Chat con los detalles del chat.
        // Si no se encuentra el chat, devuelve null.
        return null; // Reemplaza esto con la lógica real
    }
}
