import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LugTuristico lugTuristico = new LugTuristico();
        ruta ruta = new ruta();
        Viaje viaje = new Viaje();

        System.out.println("Digite 1 para empezar a agendar planes complementarios");
        int opcion = scanner.nextInt();

        if (opcion == 1) {
            int puntaje = lugTuristico.buscar();
            ruta.itinerario(puntaje);
            viaje.calcTotal(ruta.precio);
        } else {
            System.out.println("Opción no válida.");
        }
    }
}
