package gestorAplicacion.transporte;

public abstract class Transporte {
    abstract float calculoPrecioTransporte(float distancia, float famaDestino, int temporadaDestino, int personas, int clase);

    abstract float ETA(float distancia);

    protected float distanciaKM(String pais, String region){
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
