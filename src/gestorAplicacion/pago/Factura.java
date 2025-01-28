package gestorAplicacion.pago;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Factura {
    private final double monto;
    private final Pago.MetodoPago metodo;
    private final String id;
    private final LocalDateTime fechaEmision;
    private Pago.EstadoPago estado;
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public Factura(double monto, Pago.MetodoPago metodo, String id, LocalDateTime fechaEmision) {
        this.monto = monto;
        this.metodo = metodo;
        this.id = id;
        this.fechaEmision = fechaEmision;
        this.estado = Pago.EstadoPago.COMPLETADO;
    }

    // Getters
    public double getMonto() { return monto; }
    public Pago.MetodoPago getMetodo() { return metodo; }
    public String getId() { return id; }
    public LocalDateTime getFechaEmision() { return fechaEmision; }
    public Pago.EstadoPago getEstado() { return estado; }

    // Ligadura dinámica: método generar() polimórfico
    public String generar() {
        return String.format("""
            =============================
            FACTURA DE PAGO
            =============================
            ID Transacción: %s
            Fecha: %s
            Método de Pago: %s
            Estado: %s
            -----------------------------
            Total: $%.2f
            =============================
            """, 
            id,
            fechaEmision.format(FORMATO_FECHA),
            metodo.getDescripcion(),
            estado,
            monto
        );
    }

    @Override
    public String toString() {
        return String.format("Factura[id=%s, monto=%.2f, método=%s]", 
            id, monto, metodo.getDescripcion());
    }
}
