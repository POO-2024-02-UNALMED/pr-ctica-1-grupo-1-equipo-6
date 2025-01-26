package gestorAplicacion.talleres;
import java.util.List;
public class Lugar implements Ubicacion {
    int nro;
    int distancia;
    List <Integer> sitios;
    public Lugar(List<Integer> sitios, int distancia, int nro){
        this.sitios = sitios;
        this.distancia = distancia;
        this.nro = nro;
    }
}
