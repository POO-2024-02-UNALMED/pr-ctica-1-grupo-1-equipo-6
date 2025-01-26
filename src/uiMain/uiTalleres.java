package uiMain;

import java.util.ArrayList; 
import java.util.List; 
import java.util.Scanner;

import gestorAplicacion.talleres.Lugar;
import gestorAplicacion.talleres.Gestion;
import gestorAplicacion.talleres.Itinerario;


public class uiTalleres {
        static int doc;
        static int dias;
        static int dia;
        static List<Integer> actividades;
        public static void empezar(){
                while (true){
                        Scanner entrada = new Scanner(System.in);
                        System.out.println("Digite número de documento");
                        doc = entrada.nextInt();
                        if (doc > 5 && doc < 11){
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
                                String transport;
                                Scanner entrace = new Scanner(System.in);
                                System.out.println("¿Desea incluir transporte al recorrido?: Digite si o no");
                                transport = entrace.next();
                                if (transport == "Si" || transport == "si" || transport == "SI"){
                                        Scanner entra = new Scanner(System.in);
                                        System.out.println("Elija un número de transporte: 1.Moto  2.Carro express  3.Carro  4.Bus turístico");
                                        int entero;
                                        entero = entra.nextInt();
                                        Itinerario Ruta = new Itinerario(new ArrayList<Integer>(), new ArrayList<Integer>(), doc, 0, entero);
                                        break;
                                }else{
                                        Itinerario Ruta = new Itinerario(new ArrayList<Integer>(), new ArrayList<Integer>(), doc, 0);
                                        break;
                                }
                                break;
                        }else{
                                System.out.println("Digite un dia del mes entre el 1 y el 20");
                        }


 
                }
                for (int i = 0; i < dias; i++){
                        Scanner entracion = new Scanner(System.in);
                        System.out.println("¿Qué taller desea agendar para el dia " + i);
                        int taller;
                        taller =  entracion.nextInt();  
                        actividades.add(taller);

                }
                }

        }
}

