package es.davante;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.QueryBuilder;
import com.toedter.calendar.JDateChooser;
import es.davante.models.Alquileres;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
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
    private Dao<Alquileres, String> alquileresDao;

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

    public AlquileresSmartOcupation(Dao<Alquileres, String> dao) {
        this.alquileresDao = dao;

        try {
            this.alquileres = alquileresDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        inicializarTabla();

        generarInforme.addActionListener(e -> {
            // TODO: Implementar lógica de generación de informe
        });

        consultar.addActionListener(e -> {
            if (jdcFecha.getDate() != null && jdcFecha2.getDate() != null) {
                Date fechaInicio = jdcFecha.getDate();
                Date fechaFin = jdcFecha2.getDate();

                try {
                    QueryBuilder<Alquileres, String> qb = alquileresDao.queryBuilder();
                    qb.where().ge("fecha_entrada", fechaInicio)
                            .and()
                            .le("fecha_entrada", fechaFin);
                    
                    this.alquileres = qb.query();
                    inicializarTabla(); // Refrescar la tabla con los nuevos datos
                    
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error al filtrar los datos: " + ex.getMessage());
                }

            } else {
                JOptionPane.showMessageDialog(null, "Por favor, seleccione ambas fechas.");
            }
        });
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
