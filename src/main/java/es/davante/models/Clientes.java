package es.davante.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Clientes")
public class Clientes {

    @DatabaseField(columnName = "id_cliente", id = true)
    private String idCliente;

    @DatabaseField(columnName = "nombre", canBeNull = false)
    private String nombre;

    @DatabaseField(columnName = "datos_personales")
    private String datosPersonales;

    public Clientes() {
        // Constructor vacío requerido por ORMLite
    }

    public Clientes(String idCliente, String nombre, String datosPersonales) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.datosPersonales = datosPersonales;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDatosPersonales() {
        return datosPersonales;
    }

    public void setDatosPersonales(String datosPersonales) {
        this.datosPersonales = datosPersonales;
    }

    @Override
    public String toString() {
        return "Clientes{" +
                "idCliente='" + idCliente + '\'' +
                ", nombre='" + nombre + '\'' +
                ", datosPersonales='" + datosPersonales + '\'' +
                '}';
    }
}
