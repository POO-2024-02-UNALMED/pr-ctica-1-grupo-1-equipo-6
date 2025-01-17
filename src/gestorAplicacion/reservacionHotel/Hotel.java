//Codificado por Alejandro Pérez Barrera

package gestorAplicacion.reservacionHotel;

import java.util.ArrayList;

public class Hotel {

    private String nombre;
    private int cuartosSimples;
    private int cuartosIntermedios;
    private int cuartosLujosos;
    private float prestigio;
    private float demanda;
    private float recargo;

    private float precEspNoc;

    //constructor
    public Hotel(String nombre, int cuartosSimples, int cuartosIntermedios, int cuartosLujosos, int prestigio, int recargo){

        this.nombre=nombre;
        this.cuartosSimples=cuartosSimples; //a
        this.cuartosIntermedios=cuartosIntermedios; //b
        this.cuartosLujosos=cuartosLujosos; //c
        this.prestigio=prestigio;
        this.recargo=recargo;

        //Se va al comando de calcular demanda
        this.demanda=calcularDemanda(this.cuartosSimples,this.cuartosIntermedios,this.cuartosLujosos, this.prestigio);

    }

    //GETTERS Y SETTERS
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public int getCuartosSimples() {return cuartosSimples;}
    public void setCuartosSimples(int cuartosSimples) {this.cuartosSimples = cuartosSimples;}

    public int getCuartosIntermedios() {return cuartosIntermedios;}
    public void setCuartosIntermedios(int cuartosIntermedios) {this.cuartosIntermedios = cuartosIntermedios;}

    public int getCuartosLujosos() {return cuartosLujosos;}
    public void setCuartosLujosos(int cuartosLujosos) {this.cuartosLujosos = cuartosLujosos;}

    public float getPrestigio() {return prestigio;}
    public void setPrestigio(float prestigio) {this.prestigio = prestigio;}

    public float getDemanda() {return demanda;}
    public void setDemanda(float demanda) {this.demanda = demanda;}

    public float getRecargo() {return recargo;}
    public void setRecargo(float recargo) {this.recargo = recargo;}
    //TERMINAN GETTERS Y SETTERS

    private static float calcularDemanda(int simples, int medios, int altos, float prestigio){
        
        //Si alguno de estos valores es menor a 1 se predetermina a 1. Si es mayor a 30 se predetermina a 30
        if(simples<1){simples=1;}else if(simples>30){simples=30;}
        if(medios<1){medios=1;}else if(medios>30){medios=30;}
        if(altos<1){altos=1;}else if(altos>30){altos=30;}

        //Esta es la fórmula para la demanda
        float calculuDemanda=(float) ((float)(((1.2*altos*prestigio)+(0.79*medios*prestigio)+(0.05*simples*prestigio))/(((0.93*altos)*simples*medios)-1))+1.2);

        //Si la demanda se escapa del rango [0,10], se regresa a 10
        if(calculuDemanda>10||calculuDemanda<0){calculuDemanda=10;}
        return calculuDemanda;

    }

    public float calcularPrecioEsperadoNoche(float famaDestino, int temporadaDestino, int adultosReserva, int menoresReserva, float prestigioThis, float demandaThis){

        float viajeros=(float) (1+((adultosReserva+(1.2*menoresReserva))/(adultosReserva+menoresReserva+5)));//Esta fórmula permite obtener un factor, dependiendo del número de viajeros adultos y niños
        //Los niños hacen que el precio suba más (multiplicar por 1.2), y abajo se suma 5, para hacer que a los grupos más grandes les sea más económico viajar

        int sesgo=600; //El sesgo permite ajustar los precios de manera directa, el valor 600 es para utilizar pesos colombianos como moneda, pero puede ser cualquier valor

        //Dependiendo de la temporada, se va a multiplicar un recargo distinto (0.85, 1, 1.3)
        switch (temporadaDestino) {//Esta fórmula como tal determina el precio con el recargo del hotel, la fama del destino, prestigio y demanda del hotel, viajeros y el sesgo
            case 0:
            this.precEspNoc = (float) (recargo*(1+(famaDestino/10))*0.85*(1+(prestigioThis/20))*demandaThis*viajeros*sesgo);
                return this.precEspNoc;
        
            case 1:
            this.precEspNoc = (float) (recargo*(1+(famaDestino/10))*1*(1+(prestigioThis/20))*demandaThis*viajeros*sesgo);
                return this.precEspNoc;

            case 2:
                this.precEspNoc = (float) (recargo*(1+(famaDestino/10))*1.3*(1+(prestigioThis/20))*demandaThis*viajeros*sesgo);
                return this.precEspNoc;

            default:
            this.precEspNoc=853694.68f;//Si ocurre algún inesperado, este precio se asigna como default, para que no se caiga el programa
                return this.precEspNoc;

        }

    }

    public float calcularPrecioTotal(byte lujo, int estadia){

        float total=0f;

        if(lujo==0){
            total= (float)((this.precEspNoc*0.85)*estadia);
            
        }
        else if(lujo==1){
            total= (float)((this.precEspNoc*1.3)*estadia);
            
        }
        else{
            total= (float)((this.precEspNoc*1.6)*estadia);
           
        }

        return total;

    }

    public ArrayList<Float> listarPrecios(){

        ArrayList<Float> precios= new ArrayList<Float>();
        
        //Si hay cuartos disponibles, se multiplican por su lujo respectivo y el resultado se mete en un array, en una posición en particular
        //Si no hay cuartos, esa misma posición se asigna como null, y el programa asume que no hay habitaciones de ese tipo.
        //System.out.println("cuartos"+this.cuartosSimples+" "+this.cuartosIntermedios+" "+this.cuartosLujosos);

        if(this.cuartosSimples>=1){ precios.add(0, (float)(this.precEspNoc*0.85));} else{precios.add(0, null);}
        
        if(this.cuartosIntermedios>=1){ precios.add(1, (float)(this.precEspNoc*1.3));} else{precios.add(1, null);}

        if(this.cuartosLujosos>=1){ precios.add(2, (float)(this.precEspNoc*1.6));} else{precios.add(2, null);}

        return precios;
        
    }

    public void cuartoReservado(int noches, byte lujo, int personas, Destino destino){

        if(lujo==0&&this.cuartosSimples>0){
            this.cuartosSimples--;
        }
        else if(lujo==1&&this.cuartosIntermedios>0){
            this.cuartosIntermedios--;
        }
        else if(lujo==2&&this.cuartosLujosos>0){
            this.cuartosLujosos--;
        }
        else{}

        float demandaPrevia=this.demanda;
        this.demanda=calcularDemanda(this.cuartosSimples, this.cuartosIntermedios, this.cuartosLujosos, this.prestigio);

        destino.reservaHecha(this, lujo, deltaDemanda(demandaPrevia));

    }

    private float deltaDemanda(float demandaPrevia){
        return this.demanda-demandaPrevia;
    }

}
