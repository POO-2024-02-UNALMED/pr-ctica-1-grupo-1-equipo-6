package gestorAplicacion.evento;

public class Reserva {
    private EventoEspecifico evento;
    private int numPersonas;
    private double costoTotal;

    public Reserva(EventoEspecifico evento, int numPersonas) {
        this.evento = evento;
        this.numPersonas = numPersonas;
        this.costoTotal = calcularCostoTotal();
    }

    public double calcularCostoTotal() {
        return evento.getPrecio() * numPersonas;
    }

    public EventoEspecifico getEvento() {
        return evento;
    }

    public void setEvento(EventoEspecifico evento) {
        this.evento = evento;
    }

    public int getNumPersonas() {
        return numPersonas;
    }

    public void setNumPersonas(int numPersonas) {
        this.numPersonas = numPersonas;
        this.costoTotal = calcularCostoTotal(); 

    public double getCostoTotal() {
        return costoTotal;
    }

    public String detallesReserva() {
        return "Reserva para el evento: " + evento.getNombre() + ", Fecha: " + evento.getFecha() +
                ", Tipo de Evento: " + evento.getTipoEvento() + ", Personas: " + numPersonas + 
                ", Costo Total: $" + costoTotal;
    }

    public static boolean realizarReserva(EventoEspecifico evento, int numPersonas) {
        if (evento == null || numPersonas <= 0) {
            System.out.println("No se puede realizar la reserva. Datos incorrectos.");
            return false;
        }
        Reserva reserva = new Reserva(evento, numPersonas);

        System.out.println("Reserva realizada con éxito:");
        System.out.println(reserva.detallesReserva());
        return true;
    }
}
