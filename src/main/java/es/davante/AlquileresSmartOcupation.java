package es.davante;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.util.Date;

public class AlquileresSmartOcupation extends JFrame {
    JPanel panel;
    private JDateChooser jdcFecha;
    private JDateChooser jdcFecha2;
    private JButton consultar;
    private JLabel titulo;
    private JTable tablaAlquileres;
    private JButton generarInforme;
    private JTextField textField1;
    private JScrollPane scrollPane;

    public AlquileresSmartOcupation() {
        generarInforme.addActionListener(e -> {
            // TODO: Implementar lógica de generación de informe
        });

        consultar.addActionListener(e -> {
            if (jdcFecha.getDate() != null && jdcFecha2.getDate() != null) {
                Date miFecha = jdcFecha.getDate();
                long fecha = miFecha.getTime();
                java.sql.Date fecha_sql = new java.sql.Date(fecha);

                Date miFecha2 = jdcFecha2.getDate();
                long fecha2 = miFecha2.getTime();
                java.sql.Date fecha_sql2 = new java.sql.Date(fecha2);

            } else {
                JOptionPane.showMessageDialog(null, "Por favor, seleccione ambas fechas.");
            }
        });
    }

    private void createUIComponents() {
        jdcFecha = new JDateChooser();
        jdcFecha.setDateFormatString("dd/MM/yyyy");

        jdcFecha2 = new JDateChooser();
        jdcFecha2.setDateFormatString("dd/MM/yyyy");

        tablaAlquileres = new JTable();
    }
}
