//Codificado por Alejandro Pérez Barrera

package gestorAplicacion.reservacionHotel;

import java.util.*;
import baseDatos.cargarObjetos;
import java.lang.Math;

public class Destino {

    private static ArrayList<Destino> listadoDestinos= new ArrayList<Destino>();
    private static boolean datosYaCargados=false; //Cuando se crea destino se asume que no hay destinos en el array listadoDestinos

    private String nombre;
    private String nombreAlterno;
    private String region;
    private String pais;
    private float fama;
    private int temporada;
    private int llegar;

    private List<Hotel> hotelesDestino;

    //Constructor
    public Destino(String nombre, String nombreAlt, String pais, String region, float fama, int temporada, int llegar, List<Hotel> hotelesDestino){
        this. nombre= nombre;
        this.nombreAlterno=nombreAlt;
        this.region=region;
        this.pais= pais;
        this.fama=fama;
        this.temporada=temporada;
        this.llegar=llegar;

        this.hotelesDestino= hotelesDestino;
    }

    //getters setters
    public static ArrayList<Destino> getDestinos(){ //Este es el getter más entretenido, porque hace cargar los destinos
        if (datosYaCargados){ //Si ya se cargaron los destinos entonces se retornan
            return listadoDestinos;
        }
        else{ //Si todavia no se han cargado los destinos, se ejecuta el metodo que los carga
            setListadoDestinos(cargarObjetos.destinos()); //El metodo que carga los destinos los guarda en el array listadoDestinos mediante el setter
            datosYaCargados=true; //Como ya hay datos en el array, este valor = true
            return listadoDestinos;
        }
    }
    public static void setListadoDestinos(ArrayList<Destino> destinos){
        listadoDestinos=destinos;
    }

    //SETTERS Y GETTERS ABURRIDOS
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreAlterno() {
        return nombreAlterno;
    }
    public void setNombresAlternos(String nombreAlterno) {
        this.nombreAlterno = nombreAlterno;
    }

    public String getRegion() {
        return region;
    }
    public void setRegion(String region) {
        this.region = region;
    }

    public String getPais() {
        return pais;
    }
    public void setPais(String pais) {
        this.pais = pais;
    }

    public float getFama() {
        return fama;
    }
    public void setFama(float fama) {
        this.fama = fama;
    }

    public int getLlegar() {
        return llegar;
    }
    public void setLlegar(int llegar) {
        this.llegar = llegar;
    }

    public int getTemporada() {
        return temporada;
    }
    public void setTemporada(int temporada) {
        this.temporada = temporada;
    }

    public List<Hotel> getHotelesDestino() {
        return hotelesDestino;
    }
    public void setHotelesDestino(List<Hotel> hotelesDestino) {
        this.hotelesDestino = hotelesDestino;
    }
    //TERMINAN SETTERS Y GETTERS

    //Buscar un destino con la información del usuario
    public static ArrayList<Destino> buscarDestino(String nombrePosibleDestino){
        ArrayList<Destino> aRetornar= new ArrayList<Destino>(); //Este array guarda las coincidencias

        for(Destino destino: getDestinos()){ //Se pasa cada destino
            if (destino.getNombre().equalsIgnoreCase(nombrePosibleDestino)){
                aRetornar.add(destino); //Si su nombre coincide con el que buscó el usuario, se añade al array temporal
            }

            else if (destino.getNombreAlterno().equalsIgnoreCase(nombrePosibleDestino)){
                aRetornar.add(destino); //Si su nombre aleterno coincide con el que buscó el usuario, se añade al array temporal
            }

            else if (destino.getPais().equalsIgnoreCase(nombrePosibleDestino)){
                aRetornar.add(destino); //Si su país coincide con lo que buscó el usuario, se añade al array temporal
            }

            else if (destino.getRegion().equalsIgnoreCase(nombrePosibleDestino)){
                aRetornar.add(destino); //Si la región coincide con lo que buscó el usuario, se añade al array temporal
            }

        }

        return aRetornar; //Se regresa el array temporal
    }

    public void reservaHecha(Hotel hotelReservado, byte lujoReserva, float deltaDemanda){

        float hotelPrestigio= hotelReservado.getPrestigio();
        
        if (deltaDemanda > 0.35 && hotelPrestigio > 8.65f) {  //Si la demanda sube lo sufuciente en un hotel prestigioso, sube la fama del destino
            this.fama += deltaDemanda * hotelPrestigio / 20; 
            fama = Math.min(this.fama, 5.0f);  //Si la fama se pasa de 5 se rompe la economía
        }
        
        if((lujoReserva==3||deltaDemanda>0.45)&&this.temporada<2){
            this.temporada++;
        }

    }

    public static ArrayList<Destino> generadorDeDatos(){ //Este metodo incluye los elementos prefabricados, si no los existen
        ArrayList<Destino> destinos = new ArrayList<Destino>();

        destinos.add(new Destino("París","paris", "Francia", "Île-de-France",azar(0,5),
        azar(0,2), 4, 
        List.of(new Hotel("A",1,2,1,azar(4, 10),azar(30, 100)),
         new Hotel("B",18,8,3,azar(4, 10),azar(30, 100)),
          new Hotel("C",20,9,19,azar(4, 10),azar(30, 100)))));

        destinos.add(new Destino("Nueva York", "new york", "Estados Unidos", "Nueva York",
        azar(0,5), azar(0,2),4, 
        List.of(new Hotel("A",5,13,9,azar(4, 10),azar(30, 100)),
        new Hotel("B",21,18,22,azar(4, 10),azar(30, 100)),
         new Hotel("C",9,30,20,azar(4, 10),azar(30, 100)))));

        destinos.add(new Destino("Cartagena", "paris", "Colombia", "Caribe", //TODO: cambiar nombre alterno
        azar(0,5), azar(0,2),4, 
        List.of(new Hotel("A",6,5,24,azar(4, 10),azar(30, 100)),
        new Hotel("B",16,22,10,azar(4, 10),azar(30, 100)),
         new Hotel("C",24,25,6,azar(4, 10),azar(30, 100)))));

        //TODO: Agregar más destinos

        return destinos;
    }

    private static int azar(int min, int max){ //Este metodo genera números al azar, de un mínimo a un máximo, solo se usa para generar objetos por primera vez
        return (int) (min+(Math.random()*(max-min)));
    }
    
}
