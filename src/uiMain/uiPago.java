package uiMain;

import gestorAplicacion.pago.*;
import java.util.Scanner;

public class uiPago {
    private static final Pago procesador = new Pago();
    private static final Notificacion notificador = new Notificacion();
    private static final Scanner scanner = new Scanner(System.in);

    public static void go() {
        System.out.println("\n=== PROCESO DE PAGO ===");
        
        // Paso 1: Solicitar monto
        double monto = solicitarMonto();
        if (monto < 0) return; // Si hubo error, salir
        
        // Paso 2: Seleccionar método de pago
        Pago.MetodoPago metodo = seleccionarMetodo();
        if (metodo == null) return;
        
        // Paso 3: Procesar pago
        procesarPago(monto, metodo);
    }

    private static double solicitarMonto() {
        while (true) {
            try {
                System.out.print("\nIngrese el monto a pagar: $");
                double monto = Double.parseDouble(scanner.nextLine());
                
                if (monto < Pago.CARGO_MINIMO) {
                    notificador.enviarError(String.format(
                        "Monto mínimo requerido: $%.2f", 
                        Pago.CARGO_MINIMO
                    ));
                    return -1;
                }
                return monto;
                
            } catch (NumberFormatException e) {
                notificador.enviarError("Debe ingresar un valor numérico válido.");
            }
        }
    }

    private static Pago.MetodoPago seleccionarMetodo() {
        System.out.println("\nSeleccione el método de pago:");
        Pago.MetodoPago[] metodos = Pago.MetodoPago.values();
        
        for (int i = 0; i < metodos.length; i++) {
            System.out.printf("%d. %s%n", i+1, metodos[i].getDescripcion());
        }
        
        try {
            System.out.print("\nOpción: ");
            int opcion = Integer.parseInt(scanner.nextLine());
            
            if (opcion < 1 || opcion > metodos.length) {
                notificador.enviarError("Opción inválida");
                return null;
            }
            return metodos[opcion-1];
            
        } catch (NumberFormatException e) {
            notificador.enviarError("Debe ingresar un número");
            return null;
        }
    }

    private static void procesarPago(double monto, Pago.MetodoPago metodo) {
        try {
            Factura factura = procesador.procesarPago(monto, metodo);
            notificador.enviar(factura);
            
            System.out.println("\nPago exitoso!\n");
            System.out.println(factura.generar());
            
        } catch (IllegalArgumentException e) {
            notificador.enviarError(e.getMessage());
        }
        
        System.out.println("\nPresione Enter para continuar...");
        scanner.nextLine();
    }
}
