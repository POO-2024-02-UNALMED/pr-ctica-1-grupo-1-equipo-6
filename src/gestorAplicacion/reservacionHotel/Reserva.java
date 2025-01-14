package gestorAplicacion.reservacionHotel;

import java.time.LocalDate;

import uiMain.uiReservaHotel;

public class Reserva{

    //Variables
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