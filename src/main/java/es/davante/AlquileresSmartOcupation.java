package es.davante;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.toedter.calendar.JDateChooser;
import es.davante.models.Alquileres;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
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
    private JScrollPane scrollPane;
    private JLabel hastaLb;
    private JLabel desdeLb;


    private List<Alquileres> alquileres;
    private Dao<Alquileres, String> alquileresDao;

    private void aplicarEstilos() {

        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        UIManager.put("Button.arc", 15);
        UIManager.put("Component.arc", 10);
        UIManager.put("ProgressBar.arc", 10);
        UIManager.put("TextComponent.arc", 10);


        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setForeground(new Color(45, 52, 54)); // Un gris oscuro elegante

        //Estilo para los botones principales
        consultar.putClientProperty("JButton.buttonType", "accent"); // Color destacado
        generarInforme.putClientProperty("JButton.buttonType", "roundRect");
    }

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
        aplicarEstilos();
        try {
            this.alquileres = alquileresDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        inicializarTabla();

        generarInforme.addActionListener(e -> {
            if (alquileres == null || alquileres.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No hay datos para exportar.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try (PDDocument document = new PDDocument()) {
                PDPage page = new PDPage();
                document.addPage(page);

                try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                    // 1. Configurar Título del Documento
                    contentStream.beginText();
                    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);
                    contentStream.newLineAtOffset(50, 750);
                    contentStream.showText("Informe de Alquileres - SmartOcupation");
                    contentStream.endText();

                    // 2. Definir Coordenadas X fijas para columnas
                    int colExpediente = 50;
                    int colCliente = 150;
                    int colVivienda = 250;
                    int colPrecio = 375;
                    int colEstadoCobro= 475;
                    int yPosition = 710;

                    // 3. Dibujar Encabezados (Cada uno en su coordenada X)
                    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 11);

                    contentStream.beginText();
                    contentStream.newLineAtOffset(colExpediente, yPosition);
                    contentStream.showText("Expediente");
                    contentStream.endText();

                    contentStream.beginText();
                    contentStream.newLineAtOffset(colCliente, yPosition);
                    contentStream.showText("Cliente");
                    contentStream.endText();

                    contentStream.beginText();
                    contentStream.newLineAtOffset(colVivienda, yPosition);
                    contentStream.showText("Vivienda");
                    contentStream.endText();

                    contentStream.beginText();
                    contentStream.newLineAtOffset(colPrecio, yPosition);
                    contentStream.showText("Precio");
                    contentStream.endText();

                    contentStream.beginText();
                    contentStream.newLineAtOffset(colEstadoCobro, yPosition);
                    contentStream.showText("Estado Cobro");
                    contentStream.endText();

                    // Dibujar una línea decorativa debajo de la cabecera
                    contentStream.setLineWidth(1f);
                    contentStream.moveTo(50, yPosition - 5);
                    contentStream.lineTo(550, yPosition - 5);
                    contentStream.stroke();

                    yPosition -= 25; // Espacio antes de la primera fila

                    // 4. Recorrer y alinear datos
                    contentStream.setFont(PDType1Font.HELVETICA, 10);
                    for (Alquileres a : alquileres) {
                        if (yPosition < 50) break; // Control de fin de página simple

                        // Datos Expediente
                        contentStream.beginText();
                        contentStream.newLineAtOffset(colExpediente, yPosition);
                        contentStream.showText(a.getNumExpediente());
                        contentStream.endText();

                        // Datos Cliente (con control de nulos)
                        contentStream.beginText();
                        contentStream.newLineAtOffset(colCliente, yPosition);
                        String nombreCliente = (a.getCliente() != null) ? a.getCliente().getNombre() : "N/A";
                        contentStream.showText(nombreCliente);
                        contentStream.endText();

                        // Datos Vivienda
                        contentStream.beginText();
                        contentStream.newLineAtOffset(colVivienda, yPosition);
                        String refVivienda = (a.getVivienda() != null) ? a.getVivienda().getCodReferencia() : "N/A";
                        contentStream.showText(refVivienda);
                        contentStream.endText();

                        // Datos Precio
                        contentStream.beginText();
                        contentStream.newLineAtOffset(colPrecio, yPosition);
                        double precio = (a.getVivienda() != null) ? a.getVivienda().getPrecioMensual() : 0.0;
                        contentStream.showText(String.format("%.2f €", precio));
                        contentStream.endText();

                        // Datos Cobro
                        contentStream.beginText();
                        contentStream.newLineAtOffset(colEstadoCobro, yPosition);
                        String estadoCobro = (a.getEstadoCobro() != null) ? a.getEstadoCobro().toUpperCase() : "N/A";
                        contentStream.showText(estadoCobro);
                        contentStream.endText();

                        yPosition -= 20; // Salto de línea para la siguiente fila
                    }
                }

                document.save("Informe_SmartOcupation.pdf");
                JOptionPane.showMessageDialog(this, "PDF generado con éxito y alineado.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
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
        // Ajustes visuales para mejorar la usabilidad
        tablaAlquileres.setRowHeight(30);
        tablaAlquileres.setShowVerticalLines(false);
        tablaAlquileres.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // Alternar colores de fila
        tablaAlquileres.putClientProperty("Table.alternateRowColor", true);
    }
}
