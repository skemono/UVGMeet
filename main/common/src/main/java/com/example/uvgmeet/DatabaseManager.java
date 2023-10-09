package com.example.uvgmeet;

import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.db.Row;
import java.io.IOException;

public class DatabaseManager {

    private Database database;
    private static DatabaseManager instance;

    private DatabaseManager() {
        // El constructor privado evita la creación de múltiples instancias
    }

    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    public void openDatabase() {
        try {
            database = Database.openOrCreate("my_app_database.db");
            createTables(); // Llama a un método para crear tablas si es necesario
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeDatabase() {
        if (database != null) {
            try {
                database.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void createTables() {
        try {
            database.execute("CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY, username TEXT, age INTEGER)");
            database.execute("CREATE TABLE IF NOT EXISTS activities (id INTEGER PRIMARY KEY, name TEXT, description TEXT, date TEXT, location TEXT)");
            // Define otras tablas y esquemas según tus necesidades
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Métodos para realizar operaciones en la base de datos
    // Ejemplo: Insertar un usuario en la tabla de usuarios
    public void insertUser(String username, int age) {
        try {
            database.execute("INSERT INTO users (username, age) VALUES (?, ?)", username, age);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Ejemplo: Consultar todos los usuarios
    public Cursor getAllUsers() {
        try {
            return database.executeQuery("SELECT * FROM users");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Ejemplo: Cerrar un cursor
    public void closeCursor(Cursor cursor) {
        if (cursor != null) {
            cursor.close();
        }
    }

    // Ejemplo: Obtener un usuario por su ID
    public Row getUserById(int userId) {
        try {
            Cursor cursor = database.executeQuery("SELECT * FROM users WHERE id = ?", userId);
            if (cursor != null && cursor.next()) {
                return cursor.getRow();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Agrega más métodos según tus necesidades, como actualizar y eliminar registros

    // Debes manejar las excepciones y errores adecuadamente en tu aplicación
}
