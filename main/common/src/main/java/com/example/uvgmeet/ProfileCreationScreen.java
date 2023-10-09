package com.example.uvgmeet;

import com.codename1.ui.*;
import com.codename1.ui.layouts.*;
import com.codename1.ui.util.Resources;

public class ProfileCreationScreen extends Form {

    public ProfileCreationScreen() {
        super("Creación de Perfil", BoxLayout.y());

        // Campos de entrada de texto para la creación de perfil
        TextField nameField = new TextField("", "Nombre completo", 20, TextField.ANY);
        TextField ageField = new TextField("", "Edad", 2, TextField.NUMERIC);
        TextField interestsField = new TextField("", "Intereses (separados por comas)", 50, TextField.ANY);

        // Botón para guardar el perfil
        Button saveButton = new Button("Guardar Perfil");
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

                    // Aquí puedes guardar los datos del perfil en la base de datos
                    // y asociarlos al usuario actual

                    // Después de guardar el perfil, puedes navegar a otras pantallas
                    Main.getInstance().showMatchingScreen();
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
