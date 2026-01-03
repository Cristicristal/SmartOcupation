package es.davante;

public class Alquileres {
    private Vivienda vivienda;
    private Clientes cliente;
    private String numExpediente;
    private String fechaInicio;
    private String fechaFin;
    private double precioAlquiler;

    public Alquileres(Vivienda vivienda, Clientes cliente, String numExpediente, String fechaInicio, String fechaFin, double precioAlquiler) {
        this.vivienda = vivienda;
        this.cliente = cliente;
        this.numExpediente = numExpediente;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.precioAlquiler = precioAlquiler;
    }

    // Getters
    public Vivienda getVivienda() {
        return vivienda;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public double getPrecioAlquiler() {
        return precioAlquiler;
    }
    public String getNumExpediente() {
        return numExpediente;
    }


    // Setters
    public void setVivienda(Vivienda vivienda) {
        this.vivienda = vivienda;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setPrecioAlquiler(double precioAlquiler) {
        this.precioAlquiler = precioAlquiler;
    }
    public void setNumExpediente(String numExpediente) {
        this.numExpediente = numExpediente;
    }

    @Override
    public String toString() {
        return "Alquiler{" +
               "vivienda=" + vivienda +
               ", cliente=" + cliente +
                ", numExpediente='" + numExpediente + '\'' +
               ", fechaInicio='" + fechaInicio + '\'' +
               ", fechaFin='" + fechaFin + '\'' +
               ", precioAlquiler=" + precioAlquiler +
               '}';
    }

}
