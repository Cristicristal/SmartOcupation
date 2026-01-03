package es.davante.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
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

    @DatabaseField(columnName = "id_cliente")
    private String idCliente;

    @DatabaseField(columnName = "ref_vivienda")
    private String refVivienda;

    @DatabaseField(columnName = "datos_facturacion")
    private String datosFacturacion;

    public Alquileres() {
    }

    public Alquileres(String numExpediente, Date fechaEntrada, Integer tiempoEstimado, String estadoCobro, String idCliente, String refVivienda, String datosFacturacion) {
        this.numExpediente = numExpediente;
        this.fechaEntrada = fechaEntrada;
        this.tiempoEstimado = tiempoEstimado;
        this.estadoCobro = estadoCobro;
        this.idCliente = idCliente;
        this.refVivienda = refVivienda;
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

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getRefVivienda() {
        return refVivienda;
    }

    public void setRefVivienda(String refVivienda) {
        this.refVivienda = refVivienda;
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
                ", fechaEntrada=" + fechaEntrada +
                ", tiempoEstimado=" + tiempoEstimado +
                ", estadoCobro='" + estadoCobro + '\'' +
                ", idCliente='" + idCliente + '\'' +
                ", refVivienda='" + refVivienda + '\'' +
                ", datosFacturacion='" + datosFacturacion + '\'' +
                '}';
    }
}
