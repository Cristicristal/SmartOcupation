package es.davante.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    static final String url = "jdbc:mysql://localhost:3306/smartocupation";
    static final String user = "root";
    static final String pwd = "my_password";

    Connection conn = null;

    public DatabaseConnection() {
        try {
            conn = DriverManager.getConnection(url, user, pwd);
            System.out.println("Conexión establecida con SmartOcupation.");
        } catch (Exception e) {
            System.err.println("Error al conectar a la base de datos.");
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return conn;
    }
}
