package com.example.uvgmeet;

import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

public class ActivityDetailScreen extends Form {

    public ActivityDetailScreen(int activityIndex) {
        super("Detalles de Actividad", BoxLayout.y());

        // Obtener los detalles de la actividad según el índice (debes implementar la lógica)
        Activity activity = getActivityDetails(activityIndex);

        if (activity != null) {
            // Mostrar los detalles de la actividad
            Label nameLabel = new Label("Nombre: " + activity.getName());
            Label dateLabel = new Label("Fecha: " + activity.getDate());
            Label descriptionLabel = new Label("Descripción: " + activity.getDescription());
            Label locationLabel = new Label("Ubicación: " + activity.getLocation());

            addAll(nameLabel, dateLabel, descriptionLabel, locationLabel);
        } else {
            // Manejar el caso en el que no se encuentren detalles de la actividad
            Label noDetailsLabel = new Label("No se encontraron detalles de la actividad.");
            add(noDetailsLabel);
        }
    }

    // Método para obtener los detalles de la actividad desde la base de datos (debes implementar esto)
    private Activity getActivityDetails(int activityIndex) {
        // Implementa aquí la lógica para obtener los detalles de la actividad según el índice
        // Puedes consultar tu base de datos o fuente de datos aquí.
        // Devuelve una instancia de la clase Activity con los detalles de la actividad.
        // Si no se encuentra la actividad, devuelve null.
        return null; // Reemplaza esto con la lógica real
    }
}
