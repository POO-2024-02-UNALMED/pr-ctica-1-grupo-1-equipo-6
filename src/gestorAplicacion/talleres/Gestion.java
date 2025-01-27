package gestorAplicacion.talleres;

public class Gestion {
    private int Documento;
    private int Seguro;
    private int Precio;
    private int descuento;
    private int presupuesto;

    public Gestion (int Documento, int Seguro, int Precio, int descuento, int presupuesto){
        this.Documento = Documento;
    }

    public int getDocumento() {
        return Documento;
    }

    public void setDocumento(int Documento) {
        this.Documento = Documento;
    }

    public int getSeguro() {
        return Seguro;
    }

    public void setSeguro(int Seguro) {
        this.Seguro = Seguro;
    }

    public int getPrecio() {
        return Precio;
    }

    public void setPrecio(int Precio) {
        this.Precio = Precio;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public int getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(int presupuesto) {
        this.presupuesto = presupuesto;
    }

}
