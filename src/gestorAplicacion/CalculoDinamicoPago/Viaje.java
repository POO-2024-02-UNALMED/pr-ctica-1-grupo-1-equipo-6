class Viaje {
    int numViajeros;
    int transporte; // Categorías: 1, 2 o 3.
    boolean extenderRuta; // true = sí, false = no.
    float costoTotal;

    // Método para calcular el costo total del viaje
    float calcTotal() {
        float costoTransporte = transporte * 10000; // Costo base por categoría.
        float costoExtension = extenderRuta ? 20000 : 0; // Costo fijo por extender.
        costoTotal = (costoTransporte + costoExtension) * numViajeros;
        return costoTotal;
    }

    // Método para mostrar el desglose del costo
    void mostrarDesglose() {
        System.out.println("----- Desglose del Costo -----");
        System.out.println("Número de viajeros: " + numViajeros);
        System.out.println("Costo de transporte: $" + (transporte * 10000));
        System.out.println("Costo por extender la ruta: $" + (extenderRuta ? 20000 : 0));
        System.out.println("Costo total: $" + costoTotal);
        System.out.println("--------------------------------");
    }
}
