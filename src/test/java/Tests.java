package es.davante.db;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
public class Tests {

        @Mock
        private DatabaseConnection mockDbConnection;

        @Mock
        private Connection mockConnection;

        @Mock
        private Statement mockStatement;

        private DatabaseInitializer databaseInitializer;

        @BeforeEach
        void setUp() throws SQLException {
            // Inicializar  mocks
            MockitoAnnotations.openMocks(this);

            // Configurar comportamiento de los mocks
            when(mockDbConnection.getConnection()).thenReturn(mockConnection);
            when(mockConnection.createStatement()).thenReturn(mockStatement);

            // Instanciar la clase a probar
            databaseInitializer = new DatabaseInitializer(mockDbConnection);
        }

        @Test
        void testInitializeDatabase_Success() throws SQLException {

            databaseInitializer.initializeDatabase();
            verify(mockConnection, atLeastOnce()).createStatement();

        }

        @Test
        void testInitializeDatabase_NullConnection() {
            // Simular que la conexión falla
            when(mockDbConnection.getConnection()).thenReturn(null);

            databaseInitializer.initializeDatabase();

            // Verificar que no se intentó crear un statement si la conexión era nula
            try {
                verify(mockConnection, never()).createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
