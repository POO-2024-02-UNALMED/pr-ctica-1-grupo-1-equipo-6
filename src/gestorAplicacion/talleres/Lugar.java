package gestorAplicacion.talleres;
import java.util.List;
public class Lugar implements Ubicacion {
    public static int Puntuacion;
    int nro;
    int distancia;
    public List <Integer> sitios;
    public Lugar(List<Integer> sitios, int distancia, int nro){
        this.sitios = sitios;
        this.distancia = distancia;
        this.nro = nro;
    }
    public int getNro(){
        return nro;
    }

    public List<Integer> getSitios(){
        return sitios;
    }
}
