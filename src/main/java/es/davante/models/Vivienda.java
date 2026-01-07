package es.davante.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Viviendas")
public class Vivienda {

    @DatabaseField(columnName = "cod_referencia", id = true)
    private String codReferencia;

    @DatabaseField(columnName = "ubicacion", canBeNull = false)
    private String ubicacion;

    @DatabaseField(columnName = "metros")
    private Integer metros;

    @DatabaseField(columnName = "num_habitaciones")
    private Integer numHabitaciones;

    @DatabaseField(columnName = "num_banos")
    private Integer numBanos;

    @DatabaseField(columnName = "precio_mensual")
    private Double precioMensual;

    public Vivienda() {

    }

    public Vivienda(String codReferencia, String ubicacion, Integer metros, Integer numHabitaciones, Integer numBanos, Double precioMensual) {
        this.codReferencia = codReferencia;
        this.ubicacion = ubicacion;
        this.metros = metros;
        this.numHabitaciones = numHabitaciones;
        this.numBanos = numBanos;
        this.precioMensual = precioMensual;
    }

    public String getCodReferencia() {
        return codReferencia;
    }

    public void setCodReferencia(String codReferencia) {
        this.codReferencia = codReferencia;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Integer getMetros() {
        return metros;
    }

    public void setMetros(Integer metros) {
        this.metros = metros;
    }

    public Integer getNumHabitaciones() {
        return numHabitaciones;
    }

    public void setNumHabitaciones(Integer numHabitaciones) {
        this.numHabitaciones = numHabitaciones;
    }

    public Integer getNumBanos() {
        return numBanos;
    }

    public void setNumBanos(Integer numBanos) {
        this.numBanos = numBanos;
    }

    public Double getPrecioMensual() {
        return precioMensual;
    }

    public void setPrecioMensual(Double precioMensual) {
        this.precioMensual = precioMensual;
    }

    @Override
    public String toString() {
        return "Vivienda{" +
                "codReferencia='" + codReferencia + '\'' +
                ", ubicacion='" + ubicacion + '\'' +
                ", metros=" + metros +
                ", numHabitaciones=" + numHabitaciones +
                ", numBanos=" + numBanos +
                ", precioMensual=" + precioMensual +
                '}';
    }
}
