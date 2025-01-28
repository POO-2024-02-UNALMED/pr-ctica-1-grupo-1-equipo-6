package gestorAplicacion.pago;

import java.util.UUID;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Pago {
    private static final double CARGO_MINIMO = 1000.0;
    private final List<Factura> historialFacturas;
    
    public Pago() {
        this.historialFacturas = new ArrayList<>();
    }

    // Enum para métodos de pago
    public enum MetodoPago {
        TARJETA("Tarjeta de Crédito/Débito"),
        TRANSFERENCIA("Transferencia Bancaria"),
        E_WALLET("Billetera Electrónica");

        private final String descripcion;

        MetodoPago(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getDescripcion() {
            return descripcion;
        }
    }

    // Enum para estado del pago
    public enum EstadoPago {
        COMPLETADO // Simplificado para la simulación
    }

    // Sobrecarga de métodos (Sobreconstructor)
    public Factura procesarPago(double monto) {
        return procesarPago(monto, MetodoPago.TARJETA);
    }

    public Factura procesarPago(double monto, MetodoPago metodo) {
        validarMonto(monto);
        Factura factura = new Factura(monto, metodo, UUID.randomUUID().toString(), LocalDateTime.now());
        historialFacturas.add(factura);
        return factura;
    }

    private void validarMonto(double monto) {
        if (monto < CARGO_MINIMO) {
            throw new IllegalArgumentException(
                String.format("El monto mínimo de pago es $%.2f", CARGO_MINIMO));
        }
    }

    public List<Factura> getHistorialFacturas() {
        return new ArrayList<>(historialFacturas);
    }

    public double getTotalFacturado() {
        return historialFacturas.stream()
                .mapToDouble(Factura::getMonto)
                .sum();
    }
}
