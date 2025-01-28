package gestorAplicacion.transporte;

public class Avion extends Transporte{

    private static final float PRECIO_POR_KM=1463.19f;

    @Override
    float calculoPrecioTransporte(float distancia, float famaDestino, int temporadaDestino, int personas, int clase) {
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

        float total= (float) ((PRECIO_POR_KM/1000)*(((Math.pow(Math.log(distancia) / Math.log(5),2))*distancia)/((Math.sqrt(distancia))-(Math.log(distancia) / Math.log(5))))*(famaDestino/3)*t*clase*personas);
        
        return total;
    }

    float calculoPrecioTransporte(float distancia, float famaDestino, int temporadaDestino, int personas, int clase, boolean roundTrip) {
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


        float total= (float) ((PRECIO_POR_KM/1000)*(((Math.pow(Math.log(distancia) / Math.log(5),2))*distancia)/((Math.sqrt(distancia))-(Math.log(distancia) / Math.log(5))))*(famaDestino/3)*t*clase*personas*1.8);
        
        return total;
    }

    @Override
    float ETA(float distancia) {
        return ((distancia/720)+0.5f);
    }
    
}
