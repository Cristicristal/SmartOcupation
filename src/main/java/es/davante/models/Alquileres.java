package es.davante.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.text.SimpleDateFormat;
import java.util.Date;

@DatabaseTable(tableName = "Alquileres")
public class Alquileres {

    @DatabaseField(columnName = "num_expediente", id = true)
    private String numExpediente;

    @DatabaseField(columnName = "fecha_entrada", canBeNull = false)
    private Date fechaEntrada;

    @DatabaseField(columnName = "tiempo_estimado")
    private Integer tiempoEstimado;

    @DatabaseField(columnName = "estado_cobro")
    private String estadoCobro;

    @DatabaseField(columnName = "id_cliente", foreign = true, foreignAutoRefresh = true)
    private Clientes cliente;

    @DatabaseField(columnName = "ref_vivienda", foreign = true, foreignAutoRefresh = true)
    private Vivienda vivienda;

    @DatabaseField(columnName = "datos_facturacion")
    private String datosFacturacion;

    public Alquileres() {

    }

    public Alquileres(String numExpediente, Date fechaEntrada, Integer tiempoEstimado, String estadoCobro, Clientes cliente, Vivienda vivienda, String datosFacturacion) {
        this.numExpediente = numExpediente;
        this.fechaEntrada = fechaEntrada;
        this.tiempoEstimado = tiempoEstimado;
        this.estadoCobro = estadoCobro;
        this.cliente = cliente;
        this.vivienda = vivienda;
        this.datosFacturacion = datosFacturacion;
    }

    public String getNumExpediente() {
        return numExpediente;
    }

    public void setNumExpediente(String numExpediente) {
        this.numExpediente = numExpediente;
    }

    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public String getFechaEntradaFormatted() {
        if (fechaEntrada != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            return sdf.format(fechaEntrada);
        }
        return "";
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public Integer getTiempoEstimado() {
        return tiempoEstimado;
    }

    public void setTiempoEstimado(Integer tiempoEstimado) {
        this.tiempoEstimado = tiempoEstimado;
    }

    public String getEstadoCobro() {
        return estadoCobro;
    }

    public void setEstadoCobro(String estadoCobro) {
        this.estadoCobro = estadoCobro;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public Vivienda getVivienda() {
        return vivienda;
    }

    public void setVivienda(Vivienda vivienda) {
        this.vivienda = vivienda;
    }

    public String getDatosFacturacion() {
        return datosFacturacion;
    }

    public void setDatosFacturacion(String datosFacturacion) {
        this.datosFacturacion = datosFacturacion;
    }

    @Override
    public String toString() {
        return "Alquileres{" +
                "numExpediente='" + numExpediente + '\'' +
                ", fechaEntrada=" + getFechaEntradaFormatted() +
                ", tiempoEstimado=" + tiempoEstimado +
                ", estadoCobro='" + estadoCobro + '\'' +
                ", cliente=" + cliente +
                ", vivienda=" + vivienda +
                ", datosFacturacion='" + datosFacturacion + '\'' +
                '}';
    }
}
