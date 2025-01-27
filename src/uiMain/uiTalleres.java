package uiMain;

import java.util.ArrayList; 
import java.util.List; 
import java.util.Scanner;

import gestorAplicacion.talleres.Lugar;
import gestorAplicacion.talleres.Gestion;
import gestorAplicacion.talleres.Itinerario;


public class uiTalleres {
        static List<Integer> Documentos = new ArrayList<>();
        static List<Integer> precios = new ArrayList<>();
        static List<Integer> fechas = new ArrayList<>();
        static{Documentos.add(101139592);
        Documentos.add(1014466);
        Documentos.add(9491290);
        precios.add(100000);
        precios.add(200000);
        precios.add(300000);
        for (int i = 0; i < 30; i++) {
                fechas.add(i);
            }
}
        static int lug1 = 3;
        static int lug2 = 5;
        static int lug3 = 7;
        static int lug4 = 4;
        static int grupo1 = 10;
        static int grupo2 = 11;
        static int doc;
        static int dias;
        static int dia;
        public static void empezar(){
                Itinerario Registro = new Itinerario(Documentos, precios, grupo1, grupo2, fechas, lug1, lug2, lug3, lug4);
                List actividades = new ArrayList<>();
                List refrigerios = new ArrayList<>();
                List sitio = new ArrayList<>();
                while (true){
                        Scanner entrada = new Scanner(System.in);
                        System.out.println("Digite número de documento con 9 digitos o menos");
                        doc = entrada.nextInt();
                        if (doc > 0 && doc < 999999999){
                                {Gestion Manejo = new Gestion(doc, 0, 0, 0, 0);}
                                break;
                        }else{
                                System.out.println("Digite un número de documento valido ");
                        }
                }

                while (true){
                        Scanner entrada = new Scanner(System.in);
                        System.out.println("¿Cuántos dias vas a realizar actividades?");
                        dias = entrada.nextInt();
                        if (dias > 0 && dias < 8){
                                break;
                        }else{
                                System.out.println("Solo puede reservar actividades entre 1 y 7 dias en total");
                        }


                }
                while (true){
                        Scanner entrada = new Scanner(System.in);
                        System.out.println("¿Qué dia del mes vas a empezar con las actividades? Debe ser antes del 21, pues después deben estar listas todas la agendas");
                        int dia;
                        dia = entrada.nextInt();
                        if (dia > 0 && dia < 21){
                                break;
                        }else{
                                System.out.println("Digite un dia del mes entre el 1 y el 20");
                        }
                }
                for (int i = 0; i < dias; i++){
                        int u=i+1;
                        Scanner entracion = new Scanner(System.in);
                        System.out.println("¿Qué taller desea agendar para el dia " + u +": 1.Plantaton  2.Avevisor  3.casaCultura  4. casaMusica  5.TurcoParque  6.Tejedores o 7.Toboganes");
                        int taller;
                        taller =  entracion.nextInt();  
                        actividades.add(taller);

                        Scanner tup = new Scanner(System.in);
                        System.out.println("¿En que sitio deseas realizar la actividad? " + ": 1.Parque Berrio 2.San Antonio 3. San Ignacio o 4.Prado");
                        int locate;
                        locate =  tup.nextInt();  
                        sitio.add(locate);

                        Scanner cat = new Scanner(System.in);
                        System.out.println("¿Qué refrigerio desea para este dia: 1.Sandwich  2. Hamburguesa  3. Pizza");
                        int refrigerio;
                        refrigerio =  cat.nextInt();  
                        refrigerios.add(refrigerio);

                }
                Lugar Destinos = new Lugar(sitio, 0, dias);
                
                Lugar.Puntuacion = Destinos.Puntuacion(Destinos.getSitios(), Registro.getLug1(), Registro.getLug2(), Registro.getLug3(), Registro.getLug4());
                System.out.println(Lugar.Puntuacion);
                Scanner entrace = new Scanner(System.in);
                String transport;
                System.out.println("¿Desea incluir transporte al recorrido?: Digite si o no");
                transport = entrace.next();
                if (transport == "Si" || transport == "si" || transport == "SI"){
                        Scanner entra = new Scanner(System.in);
                        System.out.println("Elija un número de transporte: 1.Moto  2.Carro express  3.Carro  4.Bus turístico");
                        int entero;
                        entero = entra.nextInt();
                        Itinerario Ruta = new Itinerario(actividades, new ArrayList<Integer>(), dia, 0, entero);
                }else{
                        Itinerario Ruta = new Itinerario(actividades, new ArrayList<Integer>(), dia, 0);
                        
                }

        }
}

