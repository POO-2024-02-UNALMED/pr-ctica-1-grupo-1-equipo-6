package gestorAplicacion.talleres;

import java.util.ArrayList;
import java.util.List;
public class Itinerario {
    public List<Integer> actividades;
    List<Integer> refrigerios;
    int transporte;
    int fecha;
    int grupo;
    public Itinerario(List actividades, List refrigerios, int fecha, int grupo, int transporte){
        this(actividades, refrigerios, fecha, grupo);
        this.transporte = transporte;
    }
    public Itinerario(List actividades, List refrigerios, int fecha, int grupo){
        this.actividades = actividades;
        this.refrigerios = refrigerios;
        this.fecha = fecha;
        this.grupo = grupo;
    }

    public void setActividades(int actividades){
        this.actividades.add(actividades);
    }
}
