package gestorAplicacion.evento;

import java.time.LocalDate;

public class EventoEspecifico extends Evento {
    private String tipoEvento;

    public EventoEspecifico(String nombre, LocalDate fecha, double precio, String tipoEvento) {
        super(nombre, fecha, precio); 
        this.tipoEvento = tipoEvento;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    @Override
    public String detalles() {
        return "Tipo de Evento: " + tipoEvento + ", " + super.detalles();
    }

    public boolean esTipoEventoValido() {
        return tipoEvento != null && !tipoEvento.trim().isEmpty();
    }
}
