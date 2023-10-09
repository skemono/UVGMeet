package com.example.uvgmeet;

import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.Button;
import com.codename1.ui.layouts.BoxLayout;

public class CreateActivityScreen extends Form {

    public CreateActivityScreen() {
        super("Crear Nueva Actividad", BoxLayout.y());

        // Campos de entrada para crear una nueva actividad
        TextField nameField = new TextField("", "Nombre de la actividad");
        TextField dateField = new TextField("", "Fecha de la actividad");
        TextField descriptionField = new TextField("", "Descripción de la actividad");
        TextField locationField = new TextField("", "Ubicación de la actividad");

        Button createButton = new Button("Crear Actividad");
        createButton.addActionListener(e -> {
            // Obtener los valores ingresados por el usuario
            String name = nameField.getText();
            String date = dateField.getText();
            String description = descriptionField.getText();
            String location = locationField.getText();

            // Crear una nueva instancia de la clase Activity con los datos ingresados
            Activity newActivity = new Activity(name, description, date, location);

            // Guardar la nueva actividad en la base de datos (debes implementar esto)
            saveActivityToDatabase(newActivity);

            // Cerrar la pantalla de creación de actividad después de guardar
            this.close();
        });

        addAll(nameField, dateField, descriptionField, locationField, createButton);
    }

    // Método para guardar la nueva actividad en la base de datos (debes implementar esto)
    private void saveActivityToDatabase(Activity activity) {
        // Implementa aquí la lógica para guardar la actividad en tu base de datos.
    }
}
