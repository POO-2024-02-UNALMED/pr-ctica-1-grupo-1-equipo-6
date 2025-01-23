//Codificado por Alejandro Pérez Barrera
//Este archivo reune todo lo referente a la reservación, y es el principal medio de interacción con el hotel y el destino de viaje. Aquí se realizan verificaciones importantes a los datos que llega a introducir el usuario, lo cual asegura que no haya información la cual pueda provocar comportamientos inesperados del sistema. Desde esta clase se verifica la validez de la fecha de viaje, la legalidad en lo referente a la cantidad de viajeros, y se le dan ciertas instrucciones al hotel y al destino, las cuales portan información específica de la reserva.
package gestorAplicacion.reservacionHotel;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import uiMain.uiReservaHotel;

public class Reserva implements Serializable{//Esta clase hace referencia a la informacion de un viajero que desea viajar a un destino, con cierta cantidad de personas, dentro de unas fechas, y que se va a quedar en una habitacion, de un hotel. Toda esa informacion se tiene guardada aqui, junto con cuanto le va a costar a la persona la estadia

    //Variables

    private static int idDestino;//iddestino es un valor que se utiliza principalmente para guardar el indice del destino al que pretende viajar el usuario dentro del arraay de los resultados de busqueda

    private static Reserva reservaActual=null;//Reservaactial puede que no se utilice, es para acceder a la ultima reserva que haya creado el usuario en esta instancia, idealmente para saber cosas como el precio de su reserva, o a donde se dirije, o por cuanto tiempo

    private Destino destinoViaje;//destinoViaje es el destino al cual desea viajar el usuario
    private Hotel hotelViaje; //hotelViaje es el hotel en el que el usuairio desea quedarse
    private byte lujoHotelViaje; //Lujohotelviaje es un número equivalente al lujo del cuarto de hotel donde desea quedarse el usuario(0=Habitación sencilla, 1=Habitación intermedia, 2=Habitación lujosa)
    private float precioTotal;//Preciototal es el preciototal de la reserva de hotel
    private int viajerosAdultos;//Viajeros adultos es el número de adultos que viajan
    private int viejerosMenores;//viajerosmenores es el numero de menores que viajan
    private int estadia;//la estadia es en numero de noches que desea quedarse el usuario
    private LocalDate fechaLlegar;//fechallegar es la fecha en la que el usuario desea comenzar su estadia
    private LocalDate fechaSalir;//fechasalir es la fecha en la que el usuario desea finalizar su estadia

    //constructor, solamente recibe el destino de viaje, lo único que hace es asignar el destino que se le introduce, como propiedad de la nueva reserva
    public Reserva(Destino destino){
        this.destinoViaje=destino;
    }

    //GETTERS Y SETTERS
    public static int getIdDestino() {//getiddestino obtiene el indice del destino al que quiere viajar el usuario dentro del array de resultados de busqueda, llamar a ewste metodo retorna un int, correspondiente al indice del destino al que desea viajar el usuario, esto dentro del array de resultados de busqueda
        return idDestino;
    }
    public static void setIdDestino(int idDestino) {//setiddestino define el indice del destino al que el usuario desea viajar, dentro del array de resultados de busqueda, llamar a estre metodo requere de un int, el cual va a ser definido como el nuevo iddestino, para cuando sea neceasrio 
        Reserva.idDestino = idDestino;
    }

    public static Reserva getReservaActual() { //getreserva actual retorna un objeto Reserva, correspondiente a la ultima reserva que se haya confirmado, si no se ha hecho una reserva, este metodo retorna un null, equivalente al valol predeterminado de reserva actual
        return reservaActual;
    }
    public static void setReservaActual(Reserva reservaActual) {//Serteservaactual toma como atribuoto a un objeto reserva,para convertirlo en la reserva actual, para que otrs puedan conocer su informacion
        Reserva.reservaActual = reservaActual;
    }

    public Destino getDestinoViaje() {//getdestinoviaje retorna un destino, equivalente al destino al que el usuario desee viajar, y que haya seleccionado pertienentemente
        return destinoViaje;
    }
    public void setDestinoViaje(Destino destinoViaje) {//setdestinoviaje recibe un objeto tipo destino, este metodo sirve para definir el destino al que, actualmente, se dirije el usuario
        this.destinoViaje = destinoViaje;
    }

    public int getViajerosAdultos() {//Ya me cansé de escribir lo mismo para los getters y setters
        return viajerosAdultos;
    }
    public void setViajerosAdultos(int viajerosAdultos) {
        this.viajerosAdultos = viajerosAdultos;
    }

    public int getViejerosMenores() {
        return viejerosMenores;
    }
    public void setViejerosMenores(int viejerosMenores) {
        this.viejerosMenores = viejerosMenores;
    }

    public int getEstadia() {
        return estadia;
    }
    public void setEstadia(int estadia) {
        this.estadia = estadia;
    }

    public Hotel getHotelViaje() {
        return hotelViaje;
    }
    public void setHotelViaje(Hotel hotelViaje) {
        this.hotelViaje = hotelViaje;
    }

    public void setLujoHotelViaje(byte lujoHotelViaje) {
        this.lujoHotelViaje = lujoHotelViaje;
    }
    public byte getLujoHotelViaje() {
        return lujoHotelViaje;
    }

    public LocalDate getFechaLlegar() {
        return fechaLlegar;
    }
    public void setFechaLlegar(LocalDate fechaLlegar) {
        this.fechaLlegar = fechaLlegar;
    }

    public void setFechaSalir(LocalDate fechaSalir) {
        this.fechaSalir = fechaSalir;
    }
    public LocalDate getFechaSalir() {
        return fechaSalir;
    }

    public void setPrecioTotal(float precioTotal) {
        this.precioTotal = precioTotal;
    }
    public float getPrecioTotal() {
        return precioTotal;
    }

    //BUSCAR DESTINO
    //Esta funcion busca en destino lugares que coincidan con la búsqueda del usuairo
    public static ArrayList<Destino> buscarDestino(String keyword){//Esta funcion retorna un array con destinos, los cuales poseen sea en su nombre, nombre alterno, region o pais, la palabra (keyword) que insertó el usuario
        ArrayList<Destino> resultados = Destino.buscarDestino(keyword); //Se va a buscar si el nombre que se introduce existe

        return resultados;
    }

    //Este método retorna cuantos resultados hay en una consulta por destinos, NO LOS RESULTADOS EN SÍ
    public static int cantidadResultadosEn123(ArrayList<Destino> resultadoBusqueda){
        
        switch (resultadoBusqueda.size()) { //Este switch retorna 0 si hay 0 resultados, 1 si hay 1 y 2 si hay 2 ó más
            case 0://Este switch va a retornar el numero 0 si no hay ningun resultado
                return 0;
        
            case 1://Este switch va a retornar 1 si hay solo 1 resultado
                return 1;

            default://en cualquier otro caso va a retornar 2, se asume que solo van a ser numeros postivos mayores a 1, porque size por lo general no retorna valores negativos
                return 2;

        }

    }

    //Colocar fechas, asegurando que no den error
    //Este método recibe un boolean modificar, que le permite saber si esto es una modiciacion a la reserva, para en ese caso no continuar con la siguiente parte del proceso de reserva, recibe un iten que es la fecha de llegfada y uno que es la fechaa de salida
    public void setAmbasFechas(boolean modificar, LocalDate fechaLlegada, LocalDate fechaSalida){

        if(fechaLlegada.isEqual(LocalDate.now())||fechaLlegada.isBefore(LocalDate.now())|| //Si la fecha de llegada es menor o igual a hoy,
                fechaSalida.isEqual(LocalDate.now())||fechaSalida.isBefore(LocalDate.now())||  //o si la fecha de salida es menor o igual a hoy,
                fechaSalida.isBefore(fechaLlegada)||fechaSalida.isEqual(fechaLlegada)){        //o si la fecha de salida es menor o igual a la de llegada:
                    
            uiReservaHotel.fechas(modificar, this, false); //No se aceptan las fechas
        
        }
        else{

            this.fechaLlegar=fechaLlegada;
            this.fechaSalir=fechaSalida;

            this.setEstadia((int)fechaLlegada.until(fechaSalida, ChronoUnit.DAYS)); //Se define la estadía en días

            if(!modificar){//Esta parte solo se va a ejecutrar si no se esta modificando la reserva, para contiuar con el proceso de reserva, pero si se esta haciendo una modificacion, se termina el proceso y se regresa al menu para revisar la reserva
                uiReservaHotel.viajeros(modificar, this, true,true); //Si son aceptadas las fechas, se pasa con los viajeros, asumiendo validez y legalidad
            }

        }

    }

    //Numero de viajeros, se verifica la validez de la info
    //Este metodo recibe un boolean modificar para saber si es que esta parte de la reserva se está modificando, para en ese caso no continuar con la parte de elegir el hotel, tambien recibe un int que son los mayores de eedad y uno que son los menores de edad que viajan
    public void setAdultosMenores(boolean modificar, int mayores, int menores){

        if(mayores>0&&menores>=0&&(mayores*2)>=menores){ //Este código revisa que haya al menos un adulto viajando, y que no hayan más de 2 niños por adulto responsable

            this.viajerosAdultos=mayores;
            this.viejerosMenores=menores;

            if(!modificar){  //si no se esta modificando la reserva, se ejecuta el siguiente codigo  
                //Arraylist con los hoteles del destino, solamente si no se están haciendo modificaciones al número de viajero
                ArrayList<Hotel> ocpionHoteles = new ArrayList<>(this.destinoViaje.getHotelesDestino());//En el array opcionhoteles se guardan los hoteles del destino que se selecciono para la reserva
                uiReservaHotel.listarHoteles(modificar, this, ocpionHoteles);//Se dirige a la interfaz de usuario para que liste los destinos
            
            }

        }
        else if(mayores>0&&menores>=0){ //Aquí se verifica que al menos los valores sean válidos

            uiReservaHotel.viajeros(modificar, this, true, false); //Si se cumple, se asume que son ilegales 

        }
        else{
            uiReservaHotel.viajeros(modificar, this, false, false); //En su defecto, se asume de inválido y de falso
        }

    }

    //El calculo se hace por parte del hotel, el precio total se guarda en la reserva
    public float calculoEstadiaTotal(){//Este metodo procura el calculo del precio de la estadia
        
        this.precioTotal= this.hotelViaje.calcularPrecioTotal(this.lujoHotelViaje,this.estadia);
        return this.precioTotal;
    }

    //Este método sirve para definir la reserva actual, la cual puede ser de utilidad en otros lugares, y para indicarle al hotel que debe descontar un cuarto
    public void confirmarHotel(){
        this.hotelViaje.cuartoReservado(this.estadia, this.lujoHotelViaje, (this.viajerosAdultos+this.viejerosMenores), this.destinoViaje);
        reservaActual=this;

    }

}