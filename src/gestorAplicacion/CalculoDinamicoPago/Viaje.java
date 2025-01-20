//Representa una reserva de viaje con cálculos de costo.

public class Viaje {
    private static final double COSTO_BASE_TRANSPORTE = 10000.0;
    private static final double COSTO_EXTENSION_RUTA = 20000.0;
    
    public enum CategoriaTransporte {
        ECONOMICO(1),
        EJECUTIVO(2),
        PRIMERA_CLASE(3);

        private final int categoria;

        CategoriaTransporte(int categoria) {
            this.categoria = categoria;
        }

        public int getCategoria() {
            return categoria;
        }

        public static CategoriaTransporte deCategoria(int categoria) {
            return switch (categoria) {
                case 1 -> ECONOMICO;
                case 2 -> EJECUTIVO;
                case 3 -> PRIMERA_CLASE;
                default -> throw new IllegalArgumentException("Categoría de transporte inválida: " + categoria);
            };
        }
    }

    private final int numViajeros;
    private final CategoriaTransporte transporte;
    private final boolean extenderRuta;
    private double costoTotal;

    /**
     * Crea una nueva instancia de Viaje.
     * @param numViajeros Número de viajeros (debe ser positivo)
     * @param transporte Categoría de transporte (1-3)
     * @param extenderRuta Si la ruta se extiende
     * @throws IllegalArgumentException si los parámetros son inválidos
     */
    
    public Viaje(int numViajeros, int transporte, boolean extenderRuta) {
        if (numViajeros <= 0) {
            throw new IllegalArgumentException("El número de viajeros debe ser positivo");
        }

        this.numViajeros = numViajeros;
        this.transporte = CategoriaTransporte.deCategoria(transporte);
        this.extenderRuta = extenderRuta;
        this.costoTotal = calcTotal();
    }

    /**
     * Calcula el costo total del viaje.
     * @return El costo total
     */
    
    public double calcTotal() {
        double costoTransporte = transporte.getCategoria() * COSTO_BASE_TRANSPORTE;
        double costoExtension = extenderRuta ? COSTO_EXTENSION_RUTA : 0;
        costoTotal = (costoTransporte + costoExtension) * numViajeros;
        return costoTotal;
    }

    //Muestra un desglose detallado de los costos del viaje.
     
    public void mostrarDesglose() {
        System.out.println("----- Desglose del Costo -----");
        System.out.printf("Número de viajeros: %d%n", numViajeros);
        System.out.printf("Categoría de transporte: %s%n", transporte.name());
        System.out.printf("Costo de transporte por persona: $%.2f%n", 
            transporte.getCategoria() * COSTO_BASE_TRANSPORTE);
        System.out.printf("Costo por extender la ruta por persona: $%.2f%n", 
            extenderRuta ? COSTO_EXTENSION_RUTA : 0);
        System.out.printf("Costo total: $%.2f%n", costoTotal);
        System.out.println("------------------------");
    }

    // Getters
    public int getNumViajeros() {
        return numViajeros;
    }

    public CategoriaTransporte getTransporte() {
        return transporte;
    }

    public boolean isExtenderRuta() {
        return extenderRuta;
    }

    public double getCostoTotal() {
        return costoTotal;
    }
}
