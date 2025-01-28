package gestorAplicacion.evento;

import java.time.LocalDate;

public class Evento {
    private String nombre;
    private LocalDate fecha;
    private double precio;

    public Evento(String nombre, LocalDate fecha, double precio) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String detalles() {
        return "Evento: " + nombre + ", Fecha: " + fecha + ", Precio: $" + precio;
    }

    public boolean esPrecioValido() {
        return precio > 0;
    }
}
