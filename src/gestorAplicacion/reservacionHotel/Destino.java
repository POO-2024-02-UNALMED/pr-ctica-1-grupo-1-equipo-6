//Codificado por Alejandro Pérez Barrera

//la clase destino contiene informacion acerca de un lugar a donde viaja el usuario, tiene su debida fama y temporada, junto con un metodo para buscar destinos

package gestorAplicacion.reservacionHotel;

import java.util.*;
import baseDatos.cargarObjetos;

import java.io.Serializable;
import java.lang.Math;

public class Destino implements Serializable{

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
    public static ArrayList<Destino> buscarDestino(String nombrePosibleDestino){//Este método busca dentro del listado de destinos, aquellos que el alguna parte, sea en su nombre, nombre alterno, pais o region; la palabra clave, o keyword, una vez es encontrado uno de estos destinos, se añade al array de resultados, cuando no queden más destinos por los que buscar, se termina este proceso, no sin antes haber añadido todos los destinos que hayan presentado una coincidencia
        ArrayList<Destino> aRetornar= new ArrayList<Destino>(); //Este array guarda las coincidencias

        for(Destino destino: getDestinos()){ //Este bucle for each itera por cada destino en el metodo getdestinos, un método estático que retorna los destinos cuando estos están en la memoria, o los carga si no lo estan, o los manda a crear, si ninguna de las anteriores, para por ultimo, devolverlos.
            if (destino.getNombre().equalsIgnoreCase(nombrePosibleDestino)){//Aqui se buscan casos de coincidencia, donde el nombre del destino coincide con la palabra que haya buscado el usuario, independiente de las mayusculas y minusculas
                aRetornar.add(destino); //Si su nombre coincide con el que buscó el usuario, se añade al array de resultados
            }

            else if (destino.getNombreAlterno().equalsIgnoreCase(nombrePosibleDestino)){//Aqui se buscan los casos de coincidencia, donde el nombre alterno del desrtino coincida con la palabra que haya buscado el usuario, independiente de si haya mayusculas o minusculas
                aRetornar.add(destino); //Si su nombre aleterno coincide con el que buscó el usuario, se añade al array de resultados
            }

            else if (destino.getPais().equalsIgnoreCase(nombrePosibleDestino)){
                aRetornar.add(destino); //Si su país coincide con lo que buscó el usuario, se añade al array de resultados
            }

            else if (destino.getRegion().equalsIgnoreCase(nombrePosibleDestino)){
                aRetornar.add(destino); //Si la región coincide con lo que buscó el usuario, se añade al arrays de resultados
            }

        }

        return aRetornar; //Se regresa el array de resultados
    }

    //Reservahecha calcula la fama y temporada del destino cuando se reserva un cuarto de hotel
    //Las reservas en cuartos u hoteles lujosos pueden subir la fama o la temporada
    public void reservaHecha(Hotel hotelReservado, byte lujoReserva, float deltaDemanda){

        float hotelPrestigio= hotelReservado.getPrestigio();
        
        if (deltaDemanda > 0.35 && hotelPrestigio > 8.65f) {  //Si la demanda sube lo sufuciente en un hotel prestigioso, sube la fama del destino
            this.fama += deltaDemanda * hotelPrestigio / 20; 
            this.fama = Math.min(this.fama, 5.0f);  //Si la fama se pasa de 5 se rompe la economía
        }
        
        if((lujoReserva>=2||deltaDemanda>0.45)&&this.temporada<2){//SI el cuarto de hotel es intemedio o lujoso y la delta demanda es grandecita se puede subir la temporada
            this.temporada++;//Pero si la temproada ya es 3, no se sube más para que no nos castigue Fontur
        }

    }
//Este método se ejecuta si el método que carga los destinos serializados no puede encontrar el archivo con los destinos (1ra vez que se ejecuta el programa), o si ese método no encuentra datos válidos
    public static ArrayList<Destino> generadorDeDatos(){ //Este metodo incluye los elementos prefabricados, si no los existen
        ArrayList<Destino> destinos = new ArrayList<Destino>();
        //de aqui para abajo son puros destinos predeterminados, lo unico que varia es el azar que se le añade al prestigio del hotel y el recargo que tiene este
        destinos.add(new Destino("París","paris", "Francia", "Île-de-France",azar(0,5), azar(0,2), 4, 
        List.of(new Hotel("Le Meurice",21,14,15,azar(7, 10),azar(80, 180)),
                new Hotel("Hotel Plaza Athénée",18,8,3,azar(7, 10),azar(80, 180)),
                new Hotel("The Peninsula Paris",20,9,19,azar(7, 10),azar(80, 180)))));

        destinos.add(new Destino("Nueva York", "new york", "Estados Unidos", "Nueva York", azar(0,5), azar(0,2),4, 
        List.of(new Hotel("The Plaza Hotel",5,13,9,azar(7, 10),azar(100, 200)),
                new Hotel("Mandarin Oriental New York",21,18,22,azar(7, 10),azar(100, 200)),
                new Hotel("The St. Regis New York",9,30,20,azar(7, 10),azar(100, 200)))));

        destinos.add(new Destino("Cartagena", "cartagena", "Colombia", "Bolivar", azar(0,5), azar(0,2),3, 
        List.of(new Hotel("Hotel Bocagrande",6,5,24,azar(7, 10),azar(30, 100)),
                new Hotel("Gyatt Residency Cartagena",16,22,10,azar(7, 10),azar(30, 100)),
                new Hotel("Hotel Cartagena Plaza",24,25,6,azar(7, 10),azar(30, 100)))));

        destinos.add(new Destino("Lima", "lima", "Peru", "Lima", azar(0, 5), azar(0, 2), 4, 
        List.of(new Hotel("Hotel Lima San Isidro", 25, 20, 15, azar(7, 10), azar(30, 100)),
                new Hotel("Inkari Suites Hotel", 22, 16, 12, azar(7, 10), azar(30, 100)),
                new Hotel("Radisson Hotel Decapolis Square Miraflores", 20, 15, 10, azar(7, 10), azar(30, 100)))));

        destinos.add(new Destino("Bogotá", "bogota", "Colombia", "Cundinamarca", azar(0, 5), azar(0, 2), 3, 
        List.of(new Hotel("Great Tower Bogotá", 25, 15, 10, azar(7, 10), azar(30, 100)),
                new Hotel("Hotel Mayasa Bogotá", 20, 12, 8, azar(7, 10), azar(30, 100)),
                new Hotel("Urbana Inn", 28, 20, 10, azar(7, 10), azar(30, 100)))));

        destinos.add(new Destino("Buenos Aires", "buenos aires", "Argentina", "CABA", azar(0, 5), azar(0, 2), 4, 
        List.of(new Hotel("All Seasons Hotel Buenos Aires", 20, 15, 10, azar(7, 10), azar(30, 100)),
                new Hotel("Buenos Aires Parrott", 22, 16, 12, azar(7, 10), azar(30, 100)),
                new Hotel("Milton Buenos Aires", 18, 14, 10, azar(7, 10), azar(30, 100)))));
    
        destinos.add(new Destino("Cali", "cali", "Colombia", "Valle del Cauca", azar(0, 5), azar(0, 2), 3, 
        List.of(new Hotel("Hotel Bisou Cali", 20, 15, 8, azar(7, 10), azar(30, 100)),
                new Hotel("Hotel Belén", 25, 18, 12, azar(7, 10), azar(30, 100)),
                new Hotel("Hotel Chipichape", 28, 20, 10, azar(7, 10), azar(30, 100)))));

        destinos.add(new Destino("Santa Marta", "santa marta", "Colombia", "Magdalena", azar(0, 5), azar(0, 2), 3, 
        List.of(new Hotel("Hotel El Rodadero", 20, 14, 10, azar(7, 10), azar(30, 100)),
                new Hotel("Hotel Tayrona", 24, 16, 12, azar(7, 10), azar(30, 100)),
                new Hotel("Milton Garden Inn Santa Marta", 26, 18, 10, azar(7, 10), azar(30, 100)))));

        destinos.add(new Destino("Río de Janeiro", "rio de janeiro", "Brasil", "Rio de Janeiro", azar(0, 5), azar(0, 2), 4, 
        List.of(new Hotel("Hotel Copacabana", 25, 20, 15, azar(7, 10), azar(55, 135)),
            new Hotel("Hotel Ipanema", 23, 18, 14, azar(7, 10), azar(60, 135)),
            new Hotel("Hotel Leblon", 20, 15, 12, azar(7, 10), azar(55, 135)))));

        destinos.add(new Destino("Leticia", "leticia", "Colombia", "Amazonas", azar(0, 5), azar(0, 2), 4, 
        List.of(new Hotel("Hotel Divino Niño", 15, 10, 5, azar(7, 10), azar(30, 100)),
                new Hotel("Waldo's Hotel Boutique", 18, 12, 6, azar(7, 10), azar(30, 100)))));

        destinos.add(new Destino("Santiago de Chile", "santiago", "Chile", "Metropolitana", azar(0, 5), azar(0, 2), 4, 
        List.of(new Hotel("Hotel Leonardo Da Vinci", 22, 17, 12, azar(7, 10), azar(50, 120)),
                new Hotel("DoubleTree by Milton Hotel Santiago", 24, 20, 14, azar(7, 10), azar(50, 120)),
                new Hotel("The Singular Santiago", 18, 14, 10, azar(7, 10), azar(50, 120)))));

        destinos.add(new Destino("Madrid", "madrid", "España", "Comunidad de Madrid", azar(0, 5), azar(0, 2), 4, 
        List.of(new Hotel("Hotel Rizz Madrid", 30, 20, 15, azar(7, 10), azar(60, 160)),
            new Hotel("\"Érase un hotel\"", 25, 18, 12, azar(7, 10), azar(55, 160)),
                new Hotel("Hotel Claridge", 28, 22, 14, azar(7, 10), azar(55, 160)))));

        destinos.add(new Destino("Barranquilla", "barranquilla", "Colombia", "Atlantico", azar(0, 5), azar(0, 2), 3, 
        List.of(new Hotel("Hotel Sides Barranquilla", 20, 15, 10, azar(7, 10), azar(30, 100)),
                new Hotel("GLS Hotel Grand Barranquilla", 22, 14, 12, azar(7, 10), azar(30, 100)),
                new Hotel("Hotel Costa Mar", 25, 18, 10, azar(7, 10), azar(30, 100)))));

        destinos.add(new Destino("San Andrés", "san andres", "Colombia", "San Andres", azar(0, 5), azar(0, 2), 4, 
        List.of(new Hotel("Decaroline Isleño", 18, 14, 10, azar(7, 10), azar(60, 120)),
                new Hotel("Hotel Calypse Beach", 20, 15, 10, azar(7, 10), azar(60, 120)),
                new Hotel("Hotel Casablanca", 22, 18, 12, azar(7, 10), azar(50, 120)))));

        destinos.add(new Destino("Pereira", "pereira", "Colombia", "Risaralda", azar(0, 5), azar(0, 2), 2, 
        List.of(new Hotel("Hotel Otún", 15, 10, 6, azar(7, 10), azar(30, 100)),
                new Hotel("Hotel Movich Pereira", 18, 12, 8, azar(7, 10), azar(30, 100)))));

        destinos.add(new Destino("Londres", "londres", "Reino Unido", "Inglaterra", azar(0, 5), azar(0, 2), 4, 
        List.of(new Hotel("Sea Containers London", 30, 20, 15, azar(7, 10), azar(86, 187)),
                new Hotel("Marlin Waterloo", 25, 18, 12, azar(7, 10), azar(96, 190)),
                new Hotel("The Grange Pub", 28, 22, 14, azar(7, 10), azar(100, 185)))));

        destinos.add(new Destino("Tokio", "tokyo", "Japon", "Kanto", azar(0, 5), azar(0, 2), 4, 
        List.of(new Hotel("Lucky Hotel", 30, 25, 20, azar(7, 10), azar(96, 178)),
                new Hotel("Grand Super Bodhisattva Hotel Square Plaza", 28, 22, 18, azar(7, 10), azar(103, 201)),
                new Hotel("The Shinjuku Resort", 26, 20, 15, azar(7, 10), azar(90, 185)))));

        destinos.add(new Destino("Sídney", "sidney", "Australia", "Nueva Gales del Sur", azar(0, 5), azar(0, 2), 4, 
        List.of(new Hotel("Park Gyatt Sydney", 28, 22, 16, azar(4, 10), azar(30, 100)),
                new Hotel("Rockdale Grand Hotel", 30, 25, 18, azar(4, 10), azar(30, 100)),
                new Hotel("Merlin Suites Sydney", 25, 20, 15, azar(4, 10), azar(30, 100)))));

        destinos.add(new Destino("Dubái", "dubai", "Emiratos Arabes Unidos", "Dubai", azar(0, 5), azar(0, 2), 4, 
        List.of(new Hotel("Al Habtoor Palace", 25, 20, 18, azar(9, 10), azar(348, 462)),
                new Hotel("Palace Downtown", 30, 25, 20, 10, azar(360, 450)),
                new Hotel("Mazaya Centre LEVA Hotel", 28, 22, 16, azar(8, 10), azar(300, 486)))));

        

        

        return destinos;
    }

    //El azar es solo para ponerle valores aleatorios a los destinos cuando se crean por 1ra vez
    private static int azar(int min, int max){ //Este metodo genera números al azar, de un mínimo a un máximo, solo se usa para generar objetos por primera vez
        return (int) (min+(Math.random()*(max-min)));
    }
    
}
