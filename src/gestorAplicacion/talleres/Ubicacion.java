package gestorAplicacion.talleres;
import java.util.List;
public interface Ubicacion {
    int lug1 = 2000;
    int lug2 = 3680;
    int lug3 = 4000;
    int lug4 = 4500;
    default int Puntuacion(List<Integer> numero, int numero1, int numero2, int numero3, int numero4){
        int x = 0;
        int uno = 0;
        int dos = 0;
        int tres = 0;
        int cuatro = 0;
        int y = numero.size();
        for (int i=0; i < y; i++){
            x+=numero.get(i);
        }
        int suma = uno + dos + tres + cuatro;
        suma = suma/4;
        suma+=x;
        suma = suma/5;
        return suma;
    }
}