package com.example.uvgmeet;

import com.codename1.ui.*;
import com.codename1.ui.layouts.*;
import com.codename1.ui.util.Resources;

public class LoginScreen extends Form {

    public LoginScreen() {
        super("Iniciar Sesión", BoxLayout.y());

        // Crear campos de entrada de texto para el usuario y la contraseña
        TextField usernameField = new TextField("", "Nombre de usuario", 20, TextField.EMAILADDR);
        TextField passwordField = new TextField("", "Contraseña", 20, TextField.PASSWORD);

        // Botón para iniciar sesión
        Button loginButton = new Button("Iniciar Sesión");
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            // Aquí puedes realizar la lógica de autenticación con los datos ingresados

            // Por ejemplo, puedes validar los datos en una base de datos
            // Si la autenticación es exitosa, puedes navegar a la pantalla de Matching
            if (authenticate(username, password)) {
                Main.getInstance().showMatchingScreen();
            } else {
                Dialog.show("Error de autenticación", "Nombre de usuario o contraseña incorrectos", "Aceptar", null);
            }
        });

        // Agregar componentes a la pantalla
        add(usernameField);
        add(passwordField);
        add(loginButton);

        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_ARROW_FORWARD, e -> {
            // Botón para ir a la pantalla de creación de perfil
            Main.getInstance().showProfileCreationScreen();
        });
    }

    // Método para realizar la autenticación (simulado)
    private boolean authenticate(String username, String password) {
        // Aquí puedes implementar la lógica real de autenticación,
        // como verificar los datos en una base de datos, LDAP, etc.
        // En este ejemplo, asumimos que la autenticación siempre es exitosa.
        return true;
    }
}
