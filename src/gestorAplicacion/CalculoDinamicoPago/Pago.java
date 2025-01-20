import java.util.Scanner;
import java.util.UUID;

//Maneja el procesamiento de pagos y generación de recibos.
public class Pago {
    private static final String SEPARADOR_RECIBO = "--------------------------";
    private final Scanner scanner;

    public Pago() {
        this.scanner = new Scanner(System.in);
    }

    //Enum que representa diferentes métodos de pago.

    public enum MetodoPago {
        TARJETA("Tarjeta de crédito/débito"),
        TRANSFERENCIA("Transferencia bancaria"),
        E_WALLET("E-wallet");

        private final String nombreMostrado;

        MetodoPago(String nombreMostrado) {
            this.nombreMostrado = nombreMostrado;
        }

        public String getNombreMostrado() {
            return nombreMostrado;
        }
    }

    /**
     * Procesa un pago por el monto especificado.
     * @param monto El monto del pago (debe ser positivo)
     * @return true si el pago fue exitoso, false en caso contrario
     * @throws IllegalArgumentException si el monto es negativo o cero
     */
    public boolean procesarPago(float monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto del pago debe ser positivo");
        }

        System.out.printf("Procesando pago por $%.2f%n", monto);
        mostrarOpcionesPago();

        try {
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpia el buffer

            MetodoPago metodoSeleccionado = obtenerMetodoPago(opcion);
            if (metodoSeleccionado == null) {
                System.out.println("Método de pago no válido.");
                return false;
            }

            boolean exito = manejarMetodoPago(metodoSeleccionado);
            if (exito) {
                System.out.println(generarRecibo(monto, metodoSeleccionado.getNombreMostrado()));
            }
            return exito;

        } catch (Exception e) {
            System.out.println("Error procesando el pago: " + e.getMessage());
            return false;
        }
    }

    /**
     * Genera un recibo para el pago.
     * @param monto El monto del pago
     * @param metodoPago El método usado para el pago
     * @return Un string formateado del recibo
     */
    
    public String generarRecibo(float monto, String metodoPago) {
        return String.format("""
            %s
            ----- Recibo de Pago -----
            Monto: $%.2f
            Método: %s
            ID de Transacción: %s
            %s""",
            SEPARADOR_RECIBO,
            monto,
            metodoPago,
            UUID.randomUUID().toString(),
            SEPARADOR_RECIBO);
    }

    private void mostrarOpcionesPago() {
        System.out.println("Seleccione su método de pago:");
        System.out.println("1. Tarjeta de crédito/débito");
        System.out.println("2. Transferencia bancaria");
        System.out.println("3. E-wallet (PayPal, etc.)");
    }

    private MetodoPago obtenerMetodoPago(int opcion) {
        return switch (opcion) {
            case 1 -> MetodoPago.TARJETA;
            case 2 -> MetodoPago.TRANSFERENCIA;
            case 3 -> MetodoPago.E_WALLET;
            default -> null;
        };
    }

    private boolean manejarMetodoPago(MetodoPago metodo) {
        return switch (metodo) {
            case TARJETA -> {
                System.out.println("Ingrese los datos de su tarjeta (simulado).");
                yield true;
            }
            case TRANSFERENCIA -> {
                System.out.println("Realice una transferencia bancaria al número de cuenta XXX-XXX.");
                yield true;
            }
            case E_WALLET -> {
                System.out.println("Redirigiendo a PayPal (simulado)...");
                yield true;
            }
        };
    }
}
