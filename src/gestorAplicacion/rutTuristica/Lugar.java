import java.util.ArrayList;
import java.util.Scanner;

public class Lugar {
    String nombre;
    String descripcion;
    int precio;

    Lugar(String nombre, String descripcion, int precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    void show() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Descripción: " + descripcion);
        System.out.println("Precio: " + precio);
    }
}