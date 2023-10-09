package com.example.uvgmeet;

import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.util.Resources;

public class Main {

    private Form current;
    private static Main instance;
    private Resources theme;

    public void init(Object context) {
        instance = this;
        theme = UIManager.initFirstTheme("/theme"); // Cargar el tema de la aplicación desde los recursos
    }

    public void start() {
        if (current != null) {
            current.show();
            return;
        }
        showLoginScreen(); // Mostrar la pantalla de inicio de sesión al iniciar la aplicación
    }

    public void showLoginScreen() {
        LoginForm loginForm = new LoginForm();
        loginForm.show();
    }

    public void showMatchingScreen() {
        MatchingScreen matchingScreen = new MatchingScreen();
        matchingScreen.show();
    }

    public void showActivitiesScreen() {
        ActivitiesScreen activitiesScreen = new ActivitiesScreen();
        activitiesScreen.show();
    }

    // Agregar métodos similares para las demás pantallas

    public void stop() {
        current = Display.getInstance().getCurrent();
    }

    public void destroy() {
    }

    // Método para mostrar la pantalla de detalles de actividad
    public void showActivityDetailScreen(int activityIndex) {
        // Implementa la lógica para mostrar los detalles de la actividad según el índice
        // Supongamos que tienes una clase ActivityDetailScreen implementada
        ActivityDetailScreen detailScreen = new ActivityDetailScreen(activityIndex);
        detailScreen.show();
    }

    // Método para mostrar la pantalla de creación de actividad
    public void showCreateActivityScreen() {
        // Implementa la lógica para mostrar la pantalla de creación de actividad
        // Supongamos que tienes una clase CreateActivityScreen implementada
        CreateActivityScreen createScreen = new CreateActivityScreen();
        createScreen.show();
    }

    // Método para mostrar la pantalla de chats
    public void showChatsScreen() {
        ChatScreen chatScreen = new ChatScreen();
        chatScreen.show();
    }

    // Método para mostrar la pantalla de composición de mensajes
    public void showMessageComposeScreen() {
        // Implementa la lógica para mostrar la pantalla de composición de mensajes
        // Supongamos que tienes una clase MessageComposeScreen implementada
        MessageComposeScreen composeScreen = new MessageComposeScreen();
        composeScreen.show();
    }

    // Método para mostrar la pantalla de detalles de chat
    public void showChatDetailScreen(int chatIndex) {
        // Implementa la lógica para mostrar los detalles del chat según el índice
        // Supongamos que tienes una clase ChatDetailScreen implementada
        ChatDetailScreen detailScreen = new ChatDetailScreen(chatIndex);
        detailScreen.show();
    }

    // Método para mostrar la pantalla de creación de perfil
    public void showProfileCreationScreen() {
        ProfileCreationScreen profileCreationScreen = new ProfileCreationScreen();
        profileCreationScreen.show();
    }

    public void showChatScreen() {
        ChatScreen chatScreen = new ChatScreen();
        chatScreen.show();
    }

    public static Main getInstance() {
        return instance;
    }

    public Resources getTheme() {
        return theme;
    }

    public static void main(String[] args) {
        new Main().init(null);
        Display.getInstance().scheduleBackgroundTask(() -> {
            // Realizar cualquier inicialización adicional aquí si es necesario
            Display.getInstance().callSerially(() -> {
                new Main().start();
            });
        });
    }
}
