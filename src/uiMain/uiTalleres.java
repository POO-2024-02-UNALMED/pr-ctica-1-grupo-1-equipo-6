package uiMain;

import java.util.ArrayList; 
import java.util.List; 
import java.util.Scanner;

import gestorAplicacion.talleres.Lugar;
import gestorAplicacion.talleres.Gestion;
import gestorAplicacion.talleres.Itinerario;


public class uiTalleres {
        static List<Integer> Documentos = new ArrayList<>();
        static int transportacion = 0;
        static float estimado = 0;
        static int loop = 0;
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
                                break;
                        }else{
                                System.out.println("Digite un número de documento valido ");
                        }
                }
                Gestion Manejo = new Gestion(doc, 0, 0, 0, 0);

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
               
                Scanner entrace = new Scanner(System.in);
                int transport;
                System.out.println("¿Desea incluir transporte al recorrido?: 1. Si o 2. No");
                transport = entrace.nextInt();
                if (transport == 1){
                        Scanner entra = new Scanner(System.in);
                        System.out.println("Elija un número de transporte: 1.Moto  2.Carro express  3.Carro  4.Bus turístico");
                        transportacion = entra.nextInt();
                }else{
                        
                }
                Itinerario Ruta1 = new Itinerario(actividades, refrigerios, dia, 0, transportacion);
                Itinerario Ruta2 = new Itinerario(actividades, refrigerios, dia, 0);
                
                for (int i=0; i < Destinos.getNro(); i++){
                        int x = 0;
                        x = (Destinos.sitios.get(i) + Ruta1.getActividades().get(0))/2;
                        loop=loop+x;
                } 
                loop = loop / Destinos.getNro();
                System.out.println(loop);
                if (Registro.getGrupo1() > 15 && loop < 4){
                        Registro.setGrupo1(Registro.getGrupo1() +1);
                        System.out.println("Quedaste asignado al grupo 1");

                }else{
                        Registro.setGrupo2(Registro.getGrupo2() +1);
                        System.out.println("Quedaste asignado al grupo 2");


                }
                loop = 0;
                for (int i=0; i < Destinos.getSitios().size(); i++){
                        int x;
                        x = Destinos.sitios.get(i);
                        loop = loop + Ruta1.getActividades().get(i) + Ruta1.getRefrigerios().get(i);
                    }
                loop+=Registro.getGrupo()+Destinos.getNro();
                Manejo.setDescuento(loop);

                while(true){
                        Scanner dist = new Scanner(System.in);
                        System.out.println("¿Qué valor seguro deseas contratar?: 1. Póliza todo riesgo $90000 2. Póliza express $60000 o 3. Póliza parcial $50000");
                        int xo;
                        xo = dist.nextInt();
                        if (xo == 50000 || xo == 60000 || xo == 90000){
                                Manejo.setSeguro(xo); 
                                break;
                        }else{
                                System.out.println("Digite una cantidad valida");
                        }
                }
                int x = 0;
                for (int i=0; i < Destinos.getNro(); i++){
                        x += (Destinos.sitios.get(i) + Ruta1.getActividades().get(i) + Ruta1.getRefrigerios().get(i)) * Lugar.lug1 ; 
                    }
                x+=Manejo.getSeguro();

                if (transportacion == 1){
                        x+=5000;
                } else if(transportacion == 2){
                        x+=7000;
                }else if(transportacion == 3){
                        x+=9000;
                }else if(transportacion == 4){
                        x+=10000;
                }

                while(true){
                        Scanner dist = new Scanner(System.in);
                        System.out.println("Digite un presupuesto estimado para este recorrido que sea mayor o igual a: "+ x);
                        int xo;
                        xo = dist.nextInt();
                        if (xo >= x){
                                Manejo.setPresupuesto(xo);
                                break;
                        }else{
                                System.out.println("Digite una cantidad valida");
                        }
                }
                x+=Manejo.getPresupuesto();
                float estimacion = (float) x;
                if (Ruta1.getGrupo() == 1){
                        estimacion = estimacion * 95/100;
                }else{
                        estimacion = estimacion * 115/100;

                }
                
                estimacion = estimacion - (estimacion*(Manejo.getDescuento())/100);
                estimacion += (Manejo.getPresupuesto())/2;
                x = (int) estimacion;
                Manejo.setPresupuesto(x);

                System.out.println("El valor de la ruta de actividades y talleres es de: "+ Manejo.getPresupuesto());
                Scanner dist = new Scanner(System.in);
                System.out.println("Por favor elija 1 para confirmar o 2 para cancelar");
                int xo;
                xo = dist.nextInt();
                if (xo == 1){
                        Registro.getDocumentos().add(Manejo.getDocumento());
                        Registro.getPrecios().add(Manejo.getPresupuesto());
                        if (Ruta1.getGrupo() == 1){
                                Registro.setGrupo1(Registro.getGrupo1()+1);
                        }else{
                                Registro.setGrupo2(Registro.getGrupo2()+1);
                        }
                        System.out.println("Ruta agregada exitosamente, disfruta de tu agendamiento y no olvides llevar abrigo siempre :)");
                }else{
                        System.out.println("Ruta cancelada exitosamente");
                }


        }
}

