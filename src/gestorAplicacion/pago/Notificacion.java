package gestorAplicacion.pago;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Notificacion {
    private final List<String> historialNotificaciones;
    
    public Notificacion() {
        this.historialNotificaciones = new ArrayList<>();
    }

    // Ligadura dinámica: Sobrecarga de métodos enviar()
    public void enviar(String mensaje) {
        String notificacion = String.format("[%s] %s", 
            LocalDateTime.now(), mensaje);
        historialNotificaciones.add(notificacion);
        System.out.println("NOTIFICACIÓN: " + notificacion);
    }

    public void enviar(Factura factura) {
        String mensaje = String.format("""
            Pago procesado exitosamente
            ID: %s
            Método: %s
            Monto: $%.2f""",
            factura.getId(),
            factura.getMetodo().getDescripcion(),
            factura.getMonto()
        );
        enviar(mensaje);
    }

    public void enviarError(String error) {
        enviar("ERROR: " + error);
    }

    public List<String> getHistorialNotificaciones() {
        return new ArrayList<>(historialNotificaciones);
    }
}
