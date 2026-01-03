package es.davante;


import es.davante.db.DatabaseInitializer;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        // Inicializar base de datos
        DatabaseInitializer dbInitializer = new DatabaseInitializer();
        dbInitializer.initializeDatabase();

        AlquileresSmartOcupation f = new AlquileresSmartOcupation();
        f.setContentPane(f.panel);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);
    }
}
