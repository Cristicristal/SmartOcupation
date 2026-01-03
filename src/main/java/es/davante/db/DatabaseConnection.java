package es.davante.db;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    static final String URL = "jdbc:mysql://localhost:3306/smartocupation";
    static final String USER = "root";
    static final String PWD = "my_password";

    private Connection conn = null;

    public DatabaseConnection() {
    }

    // Método para obtener conexión JDBC clásica (usado por DatabaseInitializer)
    public Connection getConnection() {
        try {
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(URL, USER, PWD);
                System.out.println("Conexión JDBC establecida con SmartOcupation.");
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos.");
            e.printStackTrace();
        }
        return conn;
    }

    // Método para obtener ConnectionSource de ORMLite
    public ConnectionSource getConnectionSource() throws SQLException {
        return new JdbcConnectionSource(URL, USER, PWD);
    }
}
