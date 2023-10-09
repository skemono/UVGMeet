package com.example.uvgmeet;

import com.codename1.notifications.LocalNotification;
import com.codename1.notifications.LocalNotificationCallback;
import com.codename1.notifications.LocalNotificationManager;

public class NotificationManager {

    public static void sendLocalNotification(String title, String message, int delayInSeconds) {
        LocalNotification notification = new LocalNotification();
        notification.setId(String.valueOf(System.currentTimeMillis()));
        notification.setAlertTitle(title);
        notification.setAlertBody(message);
        notification.setAlertSound("/notification_sound.mp3"); // Ruta al archivo de sonido de notificación (opcional)

        // Configura la hora de inicio de la notificación (opcional)
        long currentTime = System.currentTimeMillis();
        long fireTime = currentTime + (delayInSeconds * 1000L); // Convierte segundos en milisegundos
        notification.setStartTime(fireTime);

        // Mostrar la notificación incluso si la aplicación está en primer plano (opcional)
        notification.setBadgeNumber(1);

        // Definir un callback para manejar acciones cuando el usuario toque la notificación (opcional)
        notification.setCallback(new LocalNotificationCallback() {
            @Override
            public void actionPerformed(String notificationId) {
                // Implementa acciones específicas cuando se toque la notificación
                System.out.println("Notificación tocada: " + notificationId);
            }
        });

        LocalNotificationManager.getInstance().scheduleNotification(notification);
    }

    // Otros métodos relacionados con notificaciones aquí, como cancelar notificaciones, etc.
}
