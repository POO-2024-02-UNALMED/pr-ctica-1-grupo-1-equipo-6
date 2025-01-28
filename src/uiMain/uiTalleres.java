//Esta es la clase desde la que se llevan todas las interacciones entre el usuario y la funcionalidad de reservar planes complementarios
//Inicialmente codificado por Andres Felipe Muñoz Ortiz
//Primera depuración realizada por Alejandro Pérez Barrera.

package uiMain;

import java.util.ArrayList; 
import java.util.List; 
//import java.util.Scanner; El scanner se hereda de uiMain

import gestorAplicacion.talleres.Lugar;
import gestorAplicacion.talleres.Gestion;
import gestorAplicacion.talleres.Itinerario;
import baseDatos.registro;


public class uiTalleres extends uiMain{//La pongo a heredar de uiMain para usar el scanner
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
                List<Integer> actividades = new ArrayList<>();
                List<Integer> refrigerios = new ArrayList<>();
                List<Integer> sitio = new ArrayList<>();
                while (true){
                        try{//Estamos reciclando el scanner que ya tiene la clase uiMain
                                //El try catch impide introducir cosas que no corresponden
                                System.out.println("Digite número de documento con 9 digitos o menos");
                                doc = Integer.parseInt(scannerPrompt.nextLine());    
                        }
                        catch(NumberFormatException e){
                                System.out.println("Por favor introduzca un número válido"+'\n');
                                continue;
                        }
                        if (doc > 0 && doc < 999999999){
                                break;
                        }else{
                                System.out.println("Digite un número de documento valido "+'\n');
                        }
                }
                Gestion Manejo = new Gestion(doc, 0, 0, 0, 0);

                while (true){
                        try{//Removí un scanner a favor de utilizar el de uiMain
                                System.out.println("¿Cuántos dias vas a realizar actividades?");
                                dias = Integer.parseInt(scannerPrompt.nextLine());
                        }
                        catch(NumberFormatException e){
                                System.out.println("Por favor introduzca un número válido"+'\n');
                                continue;
                        }
                        
                        if (dias > 0 && dias < 8){
                                break;
                        }else{
                                System.out.println("Solo puede reservar actividades entre 1 y 7 dias en total"+'\n');
                        }


                }
                while (true){
                        try{//Removí un scanner a favor de utilizar el de uiMain
                                System.out.println("¿Qué dia del mes vas a empezar con las actividades? Debe ser antes del 21, pues después deben estar listas todas la agendas");
                                int dia;
                                dia = Integer.parseInt(scannerPrompt.nextLine());
                                if (dia > 0 && dia < 21){
                                        break;
                                }else{
                                        System.out.println("Por favor digite un dia del mes entre el 1 y el 20"+'\n');
                                }
                        }
                        catch(NumberFormatException e){
                                System.out.println("Por favor introduzca un número válido"+'\n');
                                continue;
                        }
                }
                int taller;//Inicializar este valor desde fuera del bucle
                int locate;//Este valor igual
                int refrigerio;//Lo mismo este

                for (int i = 0; i < dias; i++){
                        int u=i+1;

                        boolean repetir=true;//Este boolean es para no utilizar el comando break;, porque ese directamente lo saca a uno del bucle for
                        //en cambio, usar un boolean permite salir del bucle while, simplemente pasando el valor a false. Como una especie de bucle while true, pero con más control, y sin necesidad del comando break;
                        while(repetir){//Removí un scanner a favor de utilizar el de uiMain
                                try{
                                        System.out.println("¿Qué taller desea agendar para el dia " + u +"?"+'\n'+"1.Plantaton "+'\n'+"2.Avevisor "+'\n'+"3.casaCultura "+'\n'+"4. casaMusica "+'\n'+"5.TurcoParque "+'\n'+"6.Tejedores "+'\n'+"7.Toboganes");
                                        
                                        taller =  Integer.parseInt(scannerPrompt.nextLine());  
                                        //Este condicional es para revisar que el usuario no ponga valores fuera del rango
                                        if(taller<8&&taller>0){
                                                actividades.add(taller);
                                                repetir=false;
                                        }
                                        else{
                                                System.out.println("Por favor introduzca una opción válida"+'\n');
                                                continue;
                                        }
                                }
                                catch(NumberFormatException e){
                                        System.out.println("Por favor introduzca un número válido"+'\n');
                                        continue;
                                }

                        }

                        repetir=true;
                        while(repetir){//Estos while repetir los puse yo para evitar poner valores fuera del rango de las opciones
                                try{
                                        System.out.println("¿En que sitio deseas realizar la actividad? "+'\n'+"1.Parque Berrío "+'\n'+"2.San Antonio "+'\n'+"3. San Ignacio"+'\n'+"4.Prado");
                        
                                        locate =  Integer.parseInt(scannerPrompt.nextLine());  

                                        //Este condicional es para revisar que el usuario no ponga valores fuera del rango
                                        if(locate<5&&locate>0){
                                                sitio.add(locate);
                                                repetir=false;
                                        }
                                        else{
                                                System.out.println("Por favor introduzca una opción válida"+'\n');
                                                continue;
                                        }
                                }

                                catch(NumberFormatException e){
                                        System.out.println("Por favor introduzca un número válido"+'\n');
                                        continue;
                                }

                        }

                        repetir=true;
                        while(repetir){
                                try{
                                        System.out.println("¿Qué refrigerio desea para este dia? "+'\n'+"1.Sandwich "+'\n'+"2. Hamburguesa "+'\n'+"3. Pizza");
                                        
                                        refrigerio =  Integer.parseInt(scannerPrompt.nextLine()); 

                                        //Este condicional es para revisar que el usuario no ponga valores fuera del rango
                                        if (refrigerio<4&&refrigerio>0){
                                                refrigerios.add(refrigerio);
                                                repetir=false;
                                        }
                                        else{
                                                System.out.println("Por favor introduzca una opción válida"+'\n');
                                                continue;
                                        }
                                }
                                catch(NumberFormatException e){
                                        System.out.println("Por favor introduzca un número válido"+'\n');
                                        continue;
                                }

                        }
                        

                }
                Lugar Destinos = new Lugar(sitio, 0, dias);
                Lugar.Puntuacion = Destinos.Puntuacion(Destinos.getSitios(), Registro.getLug1(), Registro.getLug2(), Registro.getLug3(), Registro.getLug4());
                //Aquí antes había un scanner, pero lo quité a favor de usar el de uiMain
                int transport;

                while(true){
                        try{//El try catch lo puse para evitar excepciones
                                System.out.println("¿Desea incluir transporte al recorrido?: 1. Si o 2. No");
                                transport = Integer.parseInt(scannerPrompt.nextLine());
                                break;
                        }
                        catch(NumberFormatException e){
                                System.out.println("Por favor introduzca un número válido"+'\n');
                                continue;
                        }
                }

                if (transport == 1){

                        while(true){//Estos while true y try catch los puse para que el transporte pueda ser una opción válida
                                try{
                                        System.out.println("Elija un número de transporte:"+'\n'+"1.Moto"+'\n'+"2.Carro express"+'\n'+"3.Carro"+'\n'+"4.Bus turístico");
                                        transportacion = Integer.parseInt(scannerPrompt.nextLine());

                                        //Este condicional es para revisar que el usuario ponga valores fuera del rango
                                        if (transportacion<5&&transportacion>0){
                                                break;
                                        }
                                        else{
                                                System.out.println("Por favor introduzca una opción válida"+'\n');
                                                continue;
                                        }
                                        
                                }
                                catch(NumberFormatException e){
                                        System.out.println("Por favor introduzca una opción válida"+'\n');
                                        continue;
                                }
                        }
                        
                }
                else{}//Nada
                
                Itinerario Ruta1 = new Itinerario(actividades, refrigerios, dia, 0, transportacion);
                Itinerario Ruta2 = new Itinerario(actividades, refrigerios, dia, 0);
                
                for (int i=0; i < Destinos.getNro(); i++){
                        int x = 0;
                        x = (Destinos.sitios.get(i) + Ruta1.getActividades().get(0))/2;
                        loop=loop+x;
                } 
                loop = loop / Destinos.getNro();
                System.out.println(loop);//TODO: REVISAR: ¿Por qué solo imprimes un número (Según lo que veo del código)
                if (Registro.getGrupo1() > 15 && loop < 4){
                        Registro.setGrupo1(Registro.getGrupo1() +1);
                        System.out.println("Quedaste asignado al grupo 1"+'\n');

                }else{
                        Registro.setGrupo2(Registro.getGrupo2() +1);
                        System.out.println("Quedaste asignado al grupo 2"+'\n');


                }
                loop = 0;
                for (int i=0; i < Destinos.getSitios().size(); i++){
                        int x = Destinos.sitios.get(i); //Declarar e instanciar variable x ahora se hace en una sola línea
                        loop = loop + Ruta1.getActividades().get(i) + Ruta1.getRefrigerios().get(i);
                    }
                loop+=Registro.getGrupo()+Destinos.getNro();
                Manejo.setDescuento(loop);

                int xo;//xo se declara desde fuera del bucle
                while(true){//Por aquí había otro de esos scanners revoltosos 😒
                        try{
                                System.out.println("¿Qué póliza de seguros deseas contratar?:"+'\n'+"1. Póliza todo riesgo, valor: $90000"+'\n'+"2. Póliza express, valor: $60000"+'\n'+"3. Póliza parcial, valor: $50000");
                                xo = Integer.parseInt(scannerPrompt.nextLine());// La variable xo se declara e instancia en una sola línea
                        }
                        catch(NumberFormatException e){
                                System.out.println("Por favor introduzca un número válido"+'\n');
                                continue;
                        }

                        //TODO: no estoy seguro de que representan los valores, así que solo puse los del código antiguo, asociadas a las opciones que uno esperaría que introduzca el usuario (Diseño humano):
                        //*****CÓDIGO ANTIGUO*****/
                        //int xo;
                        //xo = dist.nextInt();
                        //if (xo == 50000 || xo == 60000 || xo == 90000){
                        //        Manejo.setSeguro(xo); 
                        //*****FIN CÓDIGO ANTIGUO*****/


                        if(xo==1){//Así las acciones del código corresponden a las instrucciones del usuario
                                Manejo.setSeguro(50000);
                                break;
                        }
                        else if(xo==2){
                                Manejo.setSeguro(60000);
                                break;
                        }
                        else if (xo==3){
                                Manejo.setSeguro(90000);
                                break;
                        }
                        else{
                                System.out.println("Digite una cantidad valida"+'\n');
                                continue;
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

                while(true){// Ni lo digo, otro scanner 💢💢
                        System.out.println("Digite un presupuesto estimado para este recorrido que sea mayor o igual a: "+ x);
                        //int xo; Ya esta variable la han instanciado varias veces
                        try{
                                xo = Integer.parseInt(scannerPrompt.nextLine());
                        }
                        catch(NumberFormatException e){
                                System.out.println("Por favor introduzca una opción válida"+'\n');
                                continue;
                        }
                        
                        if (xo >= x){
                                Manejo.setPresupuesto(xo);
                                break;
                        }else{
                                System.out.println("Digite una cantidad valida"+'\n');
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
                Manejo.setPresupuesto(x/2);

                System.out.println("El valor de la ruta de actividades y talleres es de: "+ Manejo.getPresupuesto());
                //¿¿¿¿¿????? Aquí había OTRO SCANNER ¡¡RAHHH!!🦅🦅
                System.out.println("Por favor elija 1 para confirmar o 2 para cancelar");
                //int xo;
                while(true){
                        try {//Este try catch lo puse para que el programa no se caiga
                                xo = Integer.parseInt(scannerPrompt.nextLine());
                                break;
                        } catch (NumberFormatException e) {
                                System.out.println("Por favor introduzca una opción válida"+'\n');
                                continue;
                        }
                }
                if (xo == 1){
                        Registro.getDocumentos().add(Manejo.getDocumento());
                        Registro.getPrecios().add(Manejo.getPresupuesto());
                        if (Ruta1.getGrupo() == 1){
                                Registro.setGrupo1(Registro.getGrupo1()+1);
                        }else{
                                Registro.setGrupo2(Registro.getGrupo2()+1);
                        }
                        registro.guardarRegistro(Registro, "registro.txt");
                        System.out.println("Ruta agregada exitosamente, disfruta de tu agendamiento y no olvides llevar abrigo siempre :)"+'\n');
                }else{
                        System.out.println("Ruta cancelada exitosamente"+'\n');
                }

                //Que curioso que antes de yo agarrar el código este tenía como 200 líneas, mientras que ahora, tiene 360 y es un tanto más robusto
                //-AlPerBara

        }
}

