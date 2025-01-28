package baseDatos;

import java.io.*;
import java.util.List;
import gestorAplicacion.talleres.Itinerario;

public class registro {

    public static void guardarRegistro(Itinerario registro, String nombreArchivo) {
        try {
            File archivo = new File(nombreArchivo);
            if (archivo.getParentFile() != null) {
                archivo.getParentFile().mkdirs(); 
            }

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/baseDatos/temp/registro.txt"))) {
                oos.writeObject(registro);
                System.out.println("La ruta se ha agendado exitosamente");
            }
        } catch (IOException e) {
            System.out.println("Error al guardar el objeto Registro: " + e.getMessage());
        }
    }

    public static Itinerario cargarRegistro(String nombreArchivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/baseDatos/temp/registro.txt"))) {
            return (Itinerario) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar el objeto Registro: " + e.getMessage());
        }
        return null;
    }

    static {
        String archivo = "temp/registro.txt";

        Itinerario registroEjemplo = new Itinerario(
            List.of(101139592, 1014466, 9491290),  
            List.of(100000, 200000, 300000),      
            10,                                   
            11,                                   
            List.of(1, 2, 3, 4, 5),               
            3,                                    
            5,                                    
            7,                                    
            4                                     
        );

        guardarRegistro(registroEjemplo, archivo);

        Itinerario registroCargado = cargarRegistro(archivo);
        if (registroCargado != null) {
            System.out.println("El objeto Registro se ha cargado correctamente desde " + archivo);
            System.out.println("Documentos: " + registroCargado.getDocumentos());
            System.out.println("Precios: " + registroCargado.getPrecios());
            System.out.println("Grupo1: " + registroCargado.getGrupo1());
            System.out.println("Grupo2: " + registroCargado.getGrupo2());
        }
    }
}
