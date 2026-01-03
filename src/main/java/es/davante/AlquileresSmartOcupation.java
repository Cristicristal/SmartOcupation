package es.davante;

import com.toedter.calendar.JDateChooser;
import es.davante.models.Alquileres;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Date;
import java.util.List;

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

    private List<Alquileres> alquileres;

    String[] alquilereColumnNames = {
            "Num. Exp.",
            "Fecha Entrada",
            "Tiempo Est.",
            "Estado Cobro",
            "Cliente",
            "Vivienda (Ref)",
            "Ubicación",
            "Metros",
            "Habitaciones",
            "Baños",
            "Precio Mensual",
            "Facturación"
    };

    public AlquileresSmartOcupation(List<Alquileres> alquileresList) {
        this.alquileres = alquileresList;
        inicializarTabla();

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

    public AlquileresSmartOcupation() {
        // Constructor vacío para inicialización sin datos o pruebas
    }

    private void inicializarTabla() {
        DefaultTableModel dtm = new DefaultTableModel(alquilereColumnNames, 0);

        if (alquileres != null) {
            for (Alquileres a : alquileres) {
                Object[] rowData = {
                        a.getNumExpediente(),
                        a.getFechaEntradaFormatted(),
                        a.getTiempoEstimado(),
                        a.getEstadoCobro(),
                        (a.getCliente() != null) ? a.getCliente().getNombre() : "N/A",
                        (a.getVivienda() != null) ? a.getVivienda().getCodReferencia() : "N/A",
                        (a.getVivienda() != null) ? a.getVivienda().getUbicacion() : "N/A",
                        (a.getVivienda() != null) ? a.getVivienda().getMetros() : 0,
                        (a.getVivienda() != null) ? a.getVivienda().getNumHabitaciones() : 0,
                        (a.getVivienda() != null) ? a.getVivienda().getNumBanos() : 0,
                        (a.getVivienda() != null) ? a.getVivienda().getPrecioMensual() : 0.0,
                        a.getDatosFacturacion()
                };
                dtm.addRow(rowData);
            }
        }

        if (tablaAlquileres != null) {
            tablaAlquileres.setModel(dtm);
        }

    }

    private void createUIComponents() {
        jdcFecha = new JDateChooser();
        jdcFecha.setDateFormatString("dd/MM/yyyy");

        jdcFecha2 = new JDateChooser();
        jdcFecha2.setDateFormatString("dd/MM/yyyy");

        DefaultTableModel dtm = new DefaultTableModel(alquilereColumnNames, 0);

        tablaAlquileres = new JTable(dtm);
        tablaAlquileres.setVisible(true);
    }
}
