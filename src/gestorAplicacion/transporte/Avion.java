package gestorAplicacion.transporte;

import gestorAplicacion.reservacionHotel.Destino;

public class Avion extends Transporte{

    private static final float PRECIO_POR_KM=1463.19f;

    public Avion(Destino destino, int personas){
        super(destino, personas);
    }

    @Override
    public float calculoPrecioTransporte(float famaDestino, int temporadaDestino, int personas, int clase) {

        float distancia=this.distanciaKM(this.destino.getPais(), this.destino.getRegion());
        float t;
        
        switch(temporadaDestino){
            case 0:
                t=1;
                break;
            case 1:
                t=1.2f;
                break;
            case 3:
                t=1.5f;
                break;
            default:
                t=1.2f;
        }
        float c;
        switch(clase){
            case 0:
                c=1;
                break;
            case 1:
                c=1.5f;
                break;
            case 3:
                c=2;
                break;
            default:
                c=1.5f;
        }

        float total= (float) ((PRECIO_POR_KM/1000)*(((Math.pow(Math.log(distancia) / Math.log(5),2))*distancia)/((Math.sqrt(distancia))-(Math.log(distancia) / Math.log(5))))*(famaDestino/3)*t*c*personas);
        
        return total*100;
    }

    @Override
    public float calculoRoundTrip(float famaDestino, int temporadaDestino, int personas, int clase) {

        float distancia=this.distanciaKM(this.destino.getPais(), this.destino.getRegion());
        float t;
        
        switch(temporadaDestino){
            case 0:
                t=1;
                break;
            case 1:
                t=1.2f;
                break;
            case 3:
                t=1.5f;
                break;
            default:
                t=1.2f;
        }
        float c;
        switch(clase){
            case 0:
                c=1;
                break;
            case 1:
                c=1.5f;
                break;
            case 3:
                c=2;
                break;
            default:
                c=1.5f;
        }


        float total= (float) ((PRECIO_POR_KM/1000)*(((Math.pow(Math.log(distancia) / Math.log(5),2))*distancia)/((Math.sqrt(distancia))-(Math.log(distancia) / Math.log(5))))*(famaDestino/3)*t*c*personas*1.8);
        
        return total*100;
    }

    @Override
    public float ETA(float distancia) {
        return ((distancia/720)+0.5f);
    }
    
}
