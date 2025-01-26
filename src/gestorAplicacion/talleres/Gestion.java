package gestorAplicacion.talleres;

public class Gestion {
    private int Documento;

    public Gestion (int Documento, int Seguro, int Precio, int descuento, int presupuesto){
        this.Documento = Documento;
    }

    public int getDocumento(){
        return Documento;
    }

    public void setDocumento(int Documento){
        this.Documento = Documento;
    }

}
