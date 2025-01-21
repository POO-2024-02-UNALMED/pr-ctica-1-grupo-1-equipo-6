//Maneja el envío de notificaciones en el sistema.

public class Notificacion {
    private static final String PREFIJO_NOTIFICACION = "Notificación: ";
    
    /**
     * Envía una notificación con el mensaje especificado.
     * @param mensaje El mensaje a enviar (no puede ser null)
     * @throws IllegalArgumentException si el mensaje es null
     */
    public void enviarNotificacion(String mensaje) {
        if (mensaje == null) {
            throw new IllegalArgumentException("El mensaje no puede ser null");
        }
        
        System.out.println(PREFIJO_NOTIFICACION + mensaje);
    }
}
