package gestorAplicacion.pago;

public class Main {
    public static void main(String[] args) {
        Pago procesador = new Pago();
        Notificacion notificador = new Notificacion();

        try {
            // Interacción 1: Pago con tarjeta (Sobreconstructor)
            System.out.println("\n=== Procesando pago con tarjeta ===");
            Factura factura1 = procesador.procesarPago(150000.0);
            notificador.enviar(factura1); // Ligadura dinámica
            System.out.println(factura1.generar());

            // Interacción 2: Pago con transferencia
            System.out.println("\n=== Procesando pago con transferencia ===");
            Factura factura2 = procesador.procesarPago(200000.0, Pago.MetodoPago.TRANSFERENCIA);
            notificador.enviar("Confirmación rápida: Pago aceptado");
            System.out.println(factura2.generar());

            // Interacción 3: Pago con E-Wallet
            System.out.println("\n=== Procesando pago con E-Wallet ===");
            Factura factura3 = procesador.procesarPago(300000.0, Pago.MetodoPago.E_WALLET);
            notificador.enviar(factura3);
            System.out.println(factura3.generar());

            // Estadísticas
            System.out.println("\n=== Estadísticas de Pagos ===");
            System.out.printf("Total facturado: $%.2f%n", procesador.getTotalFacturado());
            System.out.println("Número de transacciones: " + procesador.getHistorialFacturas().size());

        } catch (IllegalArgumentException e) {
            notificador.enviarError(e.getMessage());
        }

        // Historial de notificaciones
        System.out.println("\n=== Historial de Notificaciones ===");
        notificador.getHistorialNotificaciones().forEach(System.out::println);
    }
}
