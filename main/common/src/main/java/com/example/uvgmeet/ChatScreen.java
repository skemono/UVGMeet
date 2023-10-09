package com.example.uvgmeet;

import com.codename1.ui.*;
import com.codename1.ui.layouts.*;
import com.codename1.ui.util.Resources;

public class ChatScreen extends Form {

    public ChatScreen() {
        super("Chats", BoxLayout.y());

        // Crea una lista de chats (puedes cargar chats reales desde tu base de datos)
        Container chatsContainer = createChatsContainer();

        // Botón para ir a la pantalla de creación de mensajes
        Button composeButton = new Button("Nuevo Mensaje");
        composeButton.addActionListener(e -> {
            Main.getInstance().showMessageComposeScreen();
        });

        // Agregar componentes a la pantalla
        add(chatsContainer);
        add(composeButton);
    }

    // Método para crear la lista de chats (simulado)
    private Container createChatsContainer() {
        // Aquí puedes cargar chats reales desde tu base de datos
        // y crear elementos visuales para cada chat.
        // Por ahora, crearemos elementos de ejemplo.

        Container chatsContainer = new Container(BoxLayout.y());

        for (int i = 1; i <= 5; i++) {
            Container chatItem = createChatItem(i);
            chatsContainer.add(chatItem);
        }

        return chatsContainer;
    }

    // Método para crear elementos de chat (simulado)
    private Container createChatItem(int chatIndex) {
        Container chatItem = new Container(new BorderLayout());

        Label senderLabel = new Label("Usuario " + chatIndex); // Nombre del remitente
        Label messageLabel = new Label("Mensaje " + chatIndex); // Último mensaje

        chatItem.add(BorderLayout.NORTH, senderLabel);
        chatItem.add(BorderLayout.CENTER, messageLabel);

        // Agregar un evento de clic para abrir el chat completo
        chatItem.setLeadComponent(messageLabel);

        chatItem.addPointerReleasedListener(e -> {
            Main.getInstance().showChatDetailScreen(chatIndex);
        });

        return chatItem;
    }
}
