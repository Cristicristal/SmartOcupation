package es.davante;

public class Vivienda {
    private String ubicacion;
    private double metrosVivienda;
    private int numHabitaciones;
    private int numBanos;
    private double precio;

    public Vivienda(double metrosVivienda, int numBanos, int numHabitaciones, double precio, String ubicacion) {
        this.metrosVivienda = metrosVivienda;
        this.numBanos = numBanos;
        this.numHabitaciones = numHabitaciones;
        this.precio = precio;
        this.ubicacion = ubicacion;
    }
    // Getters
    public String getUbicacion() {
        return ubicacion;
    }

    public double getMetrosVivienda() {
        return metrosVivienda;
    }

    public int getNumHabitaciones() {
        return numHabitaciones;
    }
    public int getNumBanos() {
        return numBanos;
    }
    public double getPrecio() {
        return precio;
    }

    // Setters
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    public void setMetrosVivienda(double metrosVivienda) {
        this.metrosVivienda = metrosVivienda;
    }
    public void setNumHabitaciones(int numHabitaciones) {
        this.numHabitaciones = numHabitaciones;
    }
    public void setNumBanos(int numBanos) {
        this.numBanos = numBanos;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Vivienda{" +
                "metrosVivienda=" + metrosVivienda +
                ", ubicacion='" + ubicacion + '\'' +
                ", numHabitaciones=" + numHabitaciones +
                ", numBaños=" + numBanos +
                ", precio=" + precio +
                '}';
    }
}
