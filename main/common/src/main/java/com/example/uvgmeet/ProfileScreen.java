package com.example.uvgmeet;

import com.codename1.ui.*;
import com.codename1.ui.layouts.*;
import com.codename1.ui.util.Resources;

public class ProfileScreen extends Form {

    public ProfileScreen() {
        super("Perfil", BoxLayout.y());

        // Crea campos de texto editables para el perfil del usuario (simulado)
        TextField nameField = new TextField("Nombre de usuario", "Nombre completo", 20, TextField.ANY);
        TextField ageField = new TextField("25", "Edad", 2, TextField.NUMERIC);
        TextField interestsField = new TextField("Deporte, Música, Ciencia", "Intereses", 50, TextField.ANY);

        // Botón para guardar los cambios en el perfil
        Button saveButton = new Button("Guardar Cambios");
        saveButton.addActionListener(e -> {
            String name = nameField.getText();
            String ageText = ageField.getText();
            String interests = interestsField.getText();

            // Valida los datos ingresados (puedes agregar más validaciones según tus necesidades)
            if (name.isEmpty() || ageText.isEmpty()) {
                Dialog.show("Error", "Por favor, complete todos los campos obligatorios.", "Aceptar", null);
            } else {
                try {
                    int age = Integer.parseInt(ageText);

                    // Actualiza los datos del perfil en la base de datos (simulado)
                    // También puedes utilizar una API de actualización de perfil aquí

                    Dialog.show("Éxito", "Perfil actualizado con éxito.", "Aceptar", null);
                } catch (NumberFormatException ex) {
                    Dialog.show("Error", "La edad debe ser un número válido.", "Aceptar", null);
                }
            }
        });

        // Agregar componentes a la pantalla
        add(nameField);
        add(ageField);
        add(interestsField);
        add(saveButton);
    }
}
