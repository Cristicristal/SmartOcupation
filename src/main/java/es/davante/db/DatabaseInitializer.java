package es.davante.db;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

public class DatabaseInitializer {

    private final DatabaseConnection dbConnection;

    public DatabaseInitializer(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public void initializeDatabase() {
        List<String> sqlFiles = Arrays.asList(
                "data/000_query_insert.sql",
                "data/001_clientes_insert.sql",
                "data/002_vivienda_insert.sql",
                "data/003_facturas_insert.sql",
                "data/004_alquileres_insert.sql"
        );

        Connection connection = dbConnection.getConnection();

        if (connection == null) {
            System.err.println("No se pudo establecer conexión para inicializar la base de datos.");
            return;
        }

        for (String filePath : sqlFiles) {
            Path path = Paths.get(filePath);

            if (!Files.exists(path)) {
                System.err.println("El archivo SQL no existe: " + path.toAbsolutePath());
                continue;
            }

            try {
                String sql = Files.readString(path);
                // Separar sentencias por punto y coma
                String[] statements = sql.split(";");

                try (Statement statement = connection.createStatement()) {
                    for (String stmt : statements) {
                        if (!stmt.trim().isEmpty()) {
                            try {
                                statement.execute(stmt.trim());
                            } catch (SQLException e) {
                                // Ignorar errores de clave duplicada o tabla ya existente
                                if (e.getErrorCode() == 1062 || e.getErrorCode() == 1050) {
                                    System.out.println("Advertencia al ejecutar sentencia (posible duplicado o ya existente): " + e.getMessage());
                                } else {
                                    System.err.println("Error ejecutando sentencia SQL: " + stmt);
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                    System.out.println("Archivo procesado con éxito: " + filePath);
                }
            } catch (IOException | SQLException e) {
                System.err.println("Error procesando archivo: " + filePath);
                e.printStackTrace();
            }
        }
    }
}
