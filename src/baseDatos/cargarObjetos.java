//Inicialmente codificado por Alejandro Pérez Barrera
//La clase cargar objetos se utiliza para cargar los objetos que se encuentren serializados
package baseDatos;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import gestorAplicacion.reservacionHotel.Destino;

public class cargarObjetos{

    private static final String DIRECCION_BBDD="src/baseDatos/temp/"; //En esta ruta es donde se almacenan los archivos

    @SuppressWarnings("unchecked")//Se suprime el unchecked porque los objetos se van a cargar desde un archivo en en cual se supone que únicamente hay objetos de tipo destino
    public static ArrayList<Destino> destinos(){ //Este método carga los objetos de tipo destino, que se encuentren guardados en la memoria

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DIRECCION_BBDD+"destinos.txt"))) {//Se va a intentar leer desde "src/baseDatos/temp/destinos.txt"
            return (ArrayList<Destino>) ois.readObject();//Lo que se pueda leer se va a regresar como un arraylist
        }
            
        catch(IOException | ClassNotFoundException e){
            System.out.println("Un momento, por favor.");//Si no se pueden cargar los archivos se pasa a generar destinos con ajustes predeterminados
            return Destino.generadorDeDatos(); //Se va a llamar el método que genera los destinos predeterminados, y lo que retorne este método que genera destinos predeterminados, lo va a retornar este método que debe de cargarlos desde la memoria.
        }

    }
    
}
