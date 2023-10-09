package com.example.uvgmeet;

import com.codename1.ui.*;
import com.codename1.ui.layouts.*;
import com.codename1.ui.util.Resources;

public class InterestedScreen extends Form {

    public InterestedScreen() {
        super("Personas Interesadas", BoxLayout.y());

        // Crea una lista de personas interesadas (simulado)
        Container interestedContainer = createInterestedContainer();

        // Agregar un botón para volver a la pantalla anterior
        Button backButton = new Button("Volver");
        backButton.addActionListener(e -> {
            // Puedes volver a la pantalla anterior (por ejemplo, la pantalla de emparejamiento)
            Main.getInstance().showMatchingScreen();
        });

        // Agregar componentes a la pantalla
        add(interestedContainer);
        add(backButton);
    }

    // Método para crear la lista de personas interesadas (simulado)
    private Container createInterestedContainer() {
        // Aquí puedes cargar datos de personas interesadas desde tu base de datos
        // y crear elementos visuales para cada persona interesada.
        // Por ahora, crearemos elementos de ejemplo.

        Container interestedContainer = new Container(BoxLayout.y());

        for (int i = 1; i <= 5; i++) {
            Container interestedItem = createInterestedItem(i);
            interestedContainer.add(interestedItem);
        }

        return interestedContainer;
    }

    // Método para crear elementos de persona interesada (simulado)
    private Container createInterestedItem(int interestedIndex) {
        Container interestedItem = new Container(new BorderLayout());

        Label nameLabel = new Label("Usuario " + interestedIndex); // Nombre de la persona interesada
        Label descriptionLabel = new Label("Descripción corta del interés " + interestedIndex);

        interestedItem.add(BorderLayout.NORTH, nameLabel);
        interestedItem.add(BorderLayout.SOUTH, descriptionLabel);

        // Agregar una imagen de ejemplo (puedes cargar imágenes reales)
        Image image = Resources.getGlobalResources().getImage("profile_image.png");
        Label imageLabel = new Label(image);
        interestedItem.add(BorderLayout.CENTER, imageLabel);

        return interestedItem;
    }
}
