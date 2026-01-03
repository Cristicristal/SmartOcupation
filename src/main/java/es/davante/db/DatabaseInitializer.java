package es.davante.db;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {

    public void initializeDatabase() {
        Path path = Paths.get("data/000_query_insert.sql");

        if (!Files.exists(path)) {
            System.err.println("El archivo SQL no existe: " + path.toAbsolutePath());
            return;
        }

        try {
            String sql = Files.readString(path);
            // Separar sentencias por punto y coma
            String[] statements = sql.split(";");

            DatabaseConnection dbConnection = new DatabaseConnection();
            Connection connection = dbConnection.getConnection();

            if (connection != null) {
                try (Statement statement = connection.createStatement()) {
                    for (String stmt : statements) {
                        if (!stmt.trim().isEmpty()) {
                            statement.execute(stmt.trim());
                        }
                    }
                    System.out.println("Base de datos inicializada con éxito.");
                }
            } else {
                System.err.println("No se pudo establecer conexión para inicializar la base de datos.");
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
