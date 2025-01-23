//Inicialmente codificado por Alejandro Pérez Barrera
//Este archivo tiene una clase que sirve para serializar objetos y guardarlos en la memoria al final de la ejecucion, para que el programa tenga memoria de lo que sucede
package baseDatos;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import gestorAplicacion.reservacionHotel.Destino;

public class guardarObjetos{//Esta clase se encarga de serializar los objetos y guardarlos en la memoria
    
    private static final String DIRECCION_BBDD="src/baseDatos/temp/"; //En esta ruta es donde se almacenan los archivos

    public static boolean guardar(ArrayList<Destino> destinos){//Este código de este método se encarga de guardar los datos
        //Solo es sobrecargarlo con el tipo de datos necesario, y cambiar la ruta donde se guardan las cosas
        //El retorno en forma de boolean es para verificar que si se hayan guardado las cosas
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DIRECCION_BBDD+"destinos.txt"))){ 
            oos.writeObject(destinos);
            return true;
        }
        catch(IOException e){
            return false;
        }
    }

}
