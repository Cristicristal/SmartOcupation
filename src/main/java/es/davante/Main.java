package es.davante;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import es.davante.db.DatabaseConnection;
import es.davante.db.DatabaseInitializer;
import es.davante.models.Alquileres;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {


        DatabaseConnection dbConnection = new DatabaseConnection();

        // Inicializar base de datos pasando la conexión existente
        DatabaseInitializer dbInitializer = new DatabaseInitializer(dbConnection);
        dbInitializer.initializeDatabase();


        ConnectionSource connectionSource = null;

        try {
            connectionSource = dbConnection.getConnectionSource();
            
            Dao<Alquileres, String> alquileresDao = DaoManager.createDao(connectionSource, Alquileres.class);

            AlquileresSmartOcupation f = new AlquileresSmartOcupation(alquileresDao);
            f.setContentPane(f.panel);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.pack();
            f.setVisible(true);

        } catch (SQLException e) {
            e.printStackTrace();
        } 

    }
}
