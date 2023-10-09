package com.example.uvgmeet;

import com.codename1.ui.*;
import com.codename1.ui.layouts.*;
import com.codename1.ui.util.Resources;

public class MatchingScreen extends Form {

    public MatchingScreen() {
        super("Emparejamiento", BoxLayout.y());

        // Crea una lista de elementos que se pueden deslizar (puedes personalizar esto según tus necesidades)
        SwipeableContainer[] swipeableContainers = createSwipeableContainers();

        for (SwipeableContainer container : swipeableContainers) {
            add(container);
        }

        // Botón para ir a la pantalla de chats
        Button chatButton = new Button("Chats");
        chatButton.addActionListener(e -> {
            Main.getInstance().showChatScreen();
        });

        // Agregar componentes a la pantalla
        add(chatButton);
    }

    // Método para crear elementos SwipeableContainer (simulado)
    private SwipeableContainer[] createSwipeableContainers() {
        // Aquí puedes cargar datos de personas o grupos de interés desde tu base de datos
        // y crear SwipeableContainers para cada uno de ellos.
        // Por ahora, crearemos elementos de ejemplo.

        SwipeableContainer[] containers = new SwipeableContainer[5]; // Ejemplo: 5 elementos

        for (int i = 0; i < containers.length; i++) {
            Container content = new Container(new BorderLayout());
            Label nameLabel = new Label("Nombre de la persona o grupo " + (i + 1));
            Label descriptionLabel = new Label("Descripción corta del interés " + (i + 1));
            content.add(BorderLayout.NORTH, nameLabel);
            content.add(BorderLayout.SOUTH, descriptionLabel);

            // Agregar una imagen de ejemplo (puedes cargar imágenes reales)
            Image image = Resources.getGlobalResources().getImage("profile_image.png");
            Label imageLabel = new Label(image);
            content.add(BorderLayout.CENTER, imageLabel);

            containers[i] = new SwipeableContainer(
                    content,
                    createSwipeButtons(i)
            );
        }

        return containers;
    }

    // Método para crear botones de deslizamiento
    private Component[] createSwipeButtons(int index) {
        Button likeButton = new Button("Me gusta");
        likeButton.addActionListener(e -> {
            // Maneja la acción de "Me gusta" aquí
            // Puedes registrar el interés del usuario en la persona o grupo
        });

        Button passButton = new Button("Pasar");
        passButton.addActionListener(e -> {
            // Maneja la acción de "Pasar" aquí
            // Puedes pasar al siguiente elemento sin registrar interés
        });

        return new Component[]{likeButton, passButton};
    }
}
