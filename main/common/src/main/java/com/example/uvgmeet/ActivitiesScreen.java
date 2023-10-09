package com.example.uvgmeet;

import com.codename1.ui.*;
import com.codename1.ui.layouts.*;
import com.codename1.ui.util.Resources;

public class ActivitiesScreen extends Form {

    public ActivitiesScreen() {
        super("Actividades Académicas", BoxLayout.y());

        // Crea una lista de actividades (simulado)
        Container activitiesContainer = createActivitiesContainer();

        // Botón para crear una nueva actividad
        Button createActivityButton = new Button("Crear Nueva Actividad");
        createActivityButton.addActionListener(e -> {
            // Puedes abrir una pantalla para crear una nueva actividad aquí
            Main.getInstance().showCreateActivityScreen();
        });

        // Agregar componentes a la pantalla
        add(activitiesContainer);
        add(createActivityButton);
    }

    // Método para crear la lista de actividades (simulado)
    private Container createActivitiesContainer() {
        // Aquí puedes cargar datos reales de actividades desde tu base de datos
        // y crear elementos visuales para cada actividad.
        // Por ahora, crearemos elementos de ejemplo.

        Container activitiesContainer = new Container(BoxLayout.y());

        for (int i = 1; i <= 5; i++) {
            Container activityItem = createActivityItem(i);
            activitiesContainer.add(activityItem);
        }

        return activitiesContainer;
    }

    // Método para crear elementos de actividad (simulado)
    private Container createActivityItem(int activityIndex) {
        Container activityItem = new Container(new BorderLayout());

        Label nameLabel = new Label("Actividad " + activityIndex); // Nombre de la actividad
        Label dateLabel = new Label("Fecha: 2023-10-15"); // Fecha de la actividad
        Label descriptionLabel = new Label("Descripción de la actividad " + activityIndex);

        activityItem.add(BorderLayout.NORTH, nameLabel);
        activityItem.add(BorderLayout.CENTER, descriptionLabel);
        activityItem.add(BorderLayout.SOUTH, dateLabel);

        // Agregar un botón para ver detalles de la actividad
        Button viewDetailsButton = new Button("Ver Detalles");
        viewDetailsButton.addActionListener(e -> {
            // Puedes abrir una pantalla de detalles de actividad aquí
            Main.getInstance().showActivityDetailScreen(activityIndex);
        });
        activityItem.add(BorderLayout.EAST, viewDetailsButton);

        return activityItem;
    }
}
