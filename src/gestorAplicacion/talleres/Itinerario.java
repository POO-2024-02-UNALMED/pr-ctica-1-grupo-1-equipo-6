package gestorAplicacion.talleres;

import java.util.ArrayList;
import java.util.List;
public class Itinerario {
    private List<Integer> actividades;
    private List<Integer> Documentos;
    private List<Integer> precios;
    private int grupo1;
    private int grupo2;
    private List<Integer> fechas;
    private int lug1;
    private int lug2;
    private int lug3;
    private int lug4;
    List<Integer> refrigerios;
    private int transporte;
    private int fecha;
    private int grupo;
    public Itinerario(List<Integer> actividades, List<Integer> refrigerios, int fecha, int grupo, int transporte){
        this(actividades, refrigerios, fecha, grupo);
        this.transporte = transporte;
    }
    public Itinerario(List<Integer> actividades, List<Integer> refrigerios, int fecha, int grupo){
        this.actividades = actividades;
        this.refrigerios = refrigerios;
        this.fecha = fecha;
        this.grupo = grupo;
    }
    
    public Itinerario(List<Integer> Documentos, List<Integer> precios, int grupo1, int grupo2, List<Integer> fechas, int lug1, int lug2, int lug3, int lug4) {
        this.Documentos = Documentos;
        this.precios = precios;
        this.grupo1 = grupo1;
        this.grupo2 = grupo2;
        this.fechas = fechas;
        this.lug1 = lug1;
        this.lug2 = lug2;
        this.lug3 = lug3;
        this.lug4 = lug4;
    }

    public int getFecha(int fecha){
        return fecha;
    }
    
    public void setFecha(int fecha){
        this.fecha = fecha;
    }
    public void setActividades(int actividades){
        this.actividades.add(actividades);
    }

    public List<Integer> getActividades() {
        return actividades;
    }

    public void setActividades(List<Integer> actividades) {
        this.actividades = actividades;
    }

    public List<Integer> getDocumentos() {
        return Documentos;
    }

    public void setDocumentos(List<Integer> Documentos) {
        this.Documentos = Documentos;
    }

    public List<Integer> getPrecios() {
        return precios;
    }

    public void setPrecios(List<Integer> precios) {
        this.precios = precios;
    }

    public int getGrupo1() {
        return grupo1;
    }

    public void setGrupo1(int grupo1) {
        this.grupo1 = grupo1;
    }

    public int getGrupo2() {
        return grupo2;
    }

    public void setGrupo2(int grupo2) {
        this.grupo2 = grupo2;
    }

    public List<Integer> getFechas() {
        return fechas;
    }

    public void setFechas(List<Integer> fechas) {
        this.fechas = fechas;
    }

    public int getLug1() {
        return lug1;
    }

    public void setLug1(int lug1) {
        this.lug1 = lug1;
    }

    public int getLug2() {
        return lug2;
    }

    public void setLug2(int lug2) {
        this.lug2 = lug2;
    }

    public int getLug3() {
        return lug3;
    }

    public void setLug3(int lug3) {
        this.lug3 = lug3;
    }

    public int getLug4() {
        return lug4;
    }

    public void setLug4(int lug4) {
        this.lug4 = lug4;
    }

    public List<Integer> getRefrigerios() {
        return refrigerios;
    }

    public void setRefrigerios(List<Integer> refrigerios) {
        this.refrigerios = refrigerios;
    }

    public int getTransporte() {
        return transporte;
    }

    public void setTransporte(int transporte) {
        this.transporte = transporte;
    }

    public int getGrupo() {
        return grupo;
    }

    public void setGrupo(int grupo) {
        this.grupo = grupo;
    }
}

