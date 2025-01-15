import java.util.Scanner;

class Pago {
    // Método para procesar el pago
    boolean procesarPago(float monto) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Procesando pago por $" + monto);
        System.out.println("Seleccione su método de pago: ");
        System.out.println("1. Tarjeta de crédito/débito");
        System.out.println("2. Transferencia bancaria");
        System.out.println("3. E-wallet (PayPal, etc.)");

        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpia el buffer
        switch (opcion) {
            case 1:
                System.out.println("Ingrese los datos de su tarjeta (simulado).");
                return true;
            case 2:
                System.out.println("Realice una transferencia bancaria al número de cuenta XXX-XXX.");
                return true;
            case 3:
                System.out.println("Redirigiendo a PayPal (simulado)...");
                return true;
            default:
                System.out.println("Método de pago no válido.");
                return false;
        }
    }

    // Método para generar un recibo
    String generarRecibo(float monto, String metodoPago) {
        String recibo = "----- Recibo de Pago -----\n" +
                        "Monto: $" + monto + "\n" +
                        "Método: " + metodoPago + "\n" +
                        "ID de Transacción: " + (int)(Math.random() * 1000000) + "\n" +
                        "--------------------------";
        return recibo;
    }
}
