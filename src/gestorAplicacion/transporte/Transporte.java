//Codificado por Alejandro Pérez Barrera
//Esta clase contiene atributos y metodos de los transportes

package gestorAplicacion.transporte;

import java.time.LocalDate;

import gestorAplicacion.reservacionHotel.Destino;
import uiMain.uiTransporte;

public abstract class Transporte {

    protected Destino destino;
    protected int personas;
    protected LocalDate fechaLlegar;
    protected LocalDate fechaSalir;
    private boolean roundTrip=false;
    protected float distancia;

    public Transporte(Destino destino, int personas){
        this.destino=destino;
        this.personas=personas;
        this.distancia=distanciaKM(destino.getPais(), destino.getRegion());
    }

    public Destino getDestino() {
        return destino;
    }
    public void setDestino(Destino destino) {
        this.destino = destino;
    }

    public int getPersonas() {
        return personas;
    }
    public void setPersonas(int personas) {
        this.personas = personas;
    }

    public boolean isRoundTrip() {
        return roundTrip;
    }
    public void setRoundTrip(boolean roundTrip) {
        this.roundTrip = roundTrip;
    }
    public float getDistancia() {
        return distancia;
    }
    public void setDistancia(float distancia) {
        this.distancia = distancia;
    }

    public void fechas(LocalDate fechaLlegada){//Fechas con solo un valor verifica la validez para transporte de ida
        if(fechaLlegada.isEqual(LocalDate.now())||fechaLlegada.isBefore(LocalDate.now())){//Si la fecha de llegada es menor o igual a hoy
            uiTransporte.fechas(this, false); //No se aceptan las fechas
        }
        else{
            this.fechaLlegar=fechaLlegada;
            this.fechaSalir=null;
            uiTransporte.precios(this);
        }
        
    }
//Fechas con dos valores verifica reservas para transporte de ida y vuelta
    public void fechas(LocalDate fechaLlegada, LocalDate fechaSalida){
        if(fechaLlegada.isEqual(LocalDate.now())||fechaLlegada.isBefore(LocalDate.now())|| //Si la fecha de llegada es menor o igual a hoy,
                fechaSalida.isEqual(LocalDate.now())||fechaSalida.isBefore(LocalDate.now())||  //o si la fecha de salida es menor o igual a hoy,
                fechaSalida.isBefore(fechaLlegada)||fechaSalida.isEqual(fechaLlegada)){        //o si la fecha de salida es menor o igual a la de llegada:
                    
            uiTransporte.fechas(this, false); //No se aceptan las fechas
        
        }
        else{
            this.fechaLlegar=fechaLlegada;
            this.fechaSalir=fechaSalida;
            this.roundTrip=true;
            uiTransporte.precios(this);
        }
    }

    //Estos son los metodos de calculo de precio, cada transporte calcula precios diferentes, por lo que tienen implementaciones distintas
    public abstract float calculoPrecioTransporte(float famaDestino, int temporadaDestino, int personas, int clase);
    public abstract float calculoRoundTrip(float fama, int temporada, int personas, int i);


    public abstract float ETA(float distancia);//Eta es el tiempo que se demora llegar al destino

    protected float distanciaKM(String pais, String region){//Distancia es la distancia hasta el destino
        if (pais.equalsIgnoreCase("Colombia")){
            if(region.equalsIgnoreCase("Bolívar")){
                return 460.88f;
            }
            else if(region.equalsIgnoreCase("Cundinamarca")){
                return 278.71f;
            }
            else if(region.equalsIgnoreCase("Valle del Cauca")){
                return 329.49f;
            }
            else if(region.equalsIgnoreCase("Magdalena")){
                return 573.62f;
            }
            else if(region.equalsIgnoreCase("Amazonas")){
                return 1322.06f;
            }
            else if(region.equalsIgnoreCase("Atlántico")){
                return 532.71f;
            }
            else if(region.equalsIgnoreCase("San Andrés")){
                return 972.14f;
            }
            else if(region.equalsIgnoreCase("Risaralda")){
                return 158.02f;
            }
            else{
                return 350.45f;
            }
        }else{
            if (pais.equalsIgnoreCase("Francia")){
                return 8602.81f;
            }
            else if (pais.equalsIgnoreCase("Estados Unidos")){
                return 3836.29f;
            }
            else if (pais.equalsIgnoreCase("Perú")){
                return 2037.48f;
            }
            else if (pais.equalsIgnoreCase("Argentina")){
                return 4898.86f;
            }
            else if (pais.equalsIgnoreCase("Brasil")){
                return 4781.5f;
            }
            else if (pais.equalsIgnoreCase("Chile")){
                return 4441.34f;
            }
            else if (pais.equalsIgnoreCase("España")){
                return 8026.88f;
            }
            else if (pais.equalsIgnoreCase("Reino Unido")){
                return 8461.90f;
            }
            else if (pais.equalsIgnoreCase("Japón")){
                return 14048.71f;
            }
            else if (pais.equalsIgnoreCase("Australia")){
                return 14324.28f;
            }
            else if (pais.equalsIgnoreCase("Emiratos Árabes Unidos")){
                return 13658.18f;
            }
            else{
                return 5654.45f;
            }
        }
    }

    
}
