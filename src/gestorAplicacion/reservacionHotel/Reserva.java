package gestorAplicacion.reservacionHotel;

import java.time.LocalDate;
import java.util.ArrayList;

import uiMain.uiReservaHotel;

public class Reserva{

    //Variables

    private static int idDestino;

    private Destino destinoViaje;
    private Hotel hotelViaje;
    private byte lujoHotelViaje;
    private float precioTotal;
    private int viajerosAdultos;
    private int viejerosMenores;
    private int estadia;
    private LocalDate fechaLlegar;
    private LocalDate fechaSalir;

    //constructor
    public Reserva(Destino destino){
        this.destinoViaje=destino;
    }

    //GETTERS Y SETTERS
    public static int getIdDestino() {
        return idDestino;
    }
    public static void setIdDestino(int idDestino) {
        Reserva.idDestino = idDestino;
    }

    //BUSCAR DESTINO
    //Esta funcion busca en destino lugares que coincidan con la búsqueda del usuairo
    public static ArrayList<Destino> buscarDestino(String keyword){
        ArrayList<Destino> resultados = Destino.buscarDestino(keyword); //Se va a buscar si el nombre que se introduce existe

        return resultados;
    }

    //Este método retorna cuantos resultados hay en una consulta por destinos, NO LOS RESULTADOS EN SÍ
    public static int cantidadResultadosEn123(ArrayList<Destino> resultadoBusqueda){
        
        switch (resultadoBusqueda.size()) { //Este switch retorna 0 si hay 0 resultados, 1 si hay 1 y 2 si hay 2 ó más
            case 0:
                return 0;
        
            case 1:
                return 1;

            default:
                return 2;

        }

    }

    //Colocar fechas
    public void setAmbasFechas(LocalDate fechaLlegada, LocalDate fechaSalida){

        if(fechaLlegada.isEqual(LocalDate.now())||fechaLlegada.isBefore(LocalDate.now())|| //Si la fecha de llegada es menor o igual a hoy,
                fechaSalida.isEqual(LocalDate.now())||fechaSalida.isBefore(LocalDate.now())||  //o si la fecha de salida es menor o igual a hoy,
                fechaSalida.isBefore(fechaLlegada)||fechaSalida.isEqual(fechaLlegada)){        //o si la fecha de salida es menor o igual a la de llegada:
                    
            uiReservaHotel.fechas(this, false); //No se aceptan las fechas
        
        }
        else{

            this.fechaLlegar=fechaLlegada;
            this.fechaSalir=fechaSalida;

            uiReservaHotel.viajeros(this, true,true); //Si son aceptadas las fechas, se pasa con los viajeros, asumiendo validez y legalidad

        }

    }

    //Numero de viajeros
    public void setAdultosMenores(int mayores, int menores){

        if(mayores>0&&menores>=0&&(mayores*2)>=menores){ //Este código revisa que haya al menos un adulto viajando, y que no hayan más de 2 niños por adulto responsable

            this.viajerosAdultos=mayores;
            this.viejerosMenores=menores;

        }
        else if(mayores>0&&menores>=0){ //Aquí se verifica que al menos los valores sean válidos

            uiReservaHotel.viajeros(this, true, false); //Si se cumple, se asume que son ilegales 

        }
        else{
            uiReservaHotel.viajeros(this, false, false); //En su defecto, se asume de inválido y de falso
        }

    }

}