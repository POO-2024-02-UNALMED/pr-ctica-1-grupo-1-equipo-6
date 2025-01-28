//Codificado por Alejandro Pérez Barrera
//Esta clase es la interfaz para reservas el transporte

package uiMain;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import gestorAplicacion.reservacionHotel.Destino;
import gestorAplicacion.reservacionHotel.Reserva;
import gestorAplicacion.transporte.Autobus;
import gestorAplicacion.transporte.Avion;
import gestorAplicacion.transporte.Transporte;
import gestorAplicacion.transporte.Tren;

public class uiTransporte extends uiMain{

    private static int idDestino;
    private static ArrayList<Destino> resultados;
    private static int personas;
    
    public static void go(){
        System.out.println("Precaución: La implementación de esta funcionalidad puede contener errores"+'\n'+"Última revisión: AlPerBara, 2025-01-28-12-36"+'\n');

        System.out.println("Por favor introduce el nombre de tu destino, de nuestro listado de destinos disponibles:");

        for(Destino destino : Destino.getDestinos()){ //Se muestra una lista con los destinos
            System.out.println("- "+destino.getNombre()+", "+destino.getPais()+".");
        }

        paso1();
    }

    public static void paso1(){//ESTE CODIGO ES RECICLADO DE uiReservaHotel

        System.out.println("Por favor introduce el nombre de tu destino.:");
        String posibleDestino = scannerPrompt.nextLine();

        resultados = Reserva.buscarDestino(posibleDestino);

        switch(Reserva.cantidadResultadosEn123(resultados)){
            case 0: //Si no hay resultados entonces toca buscar otra vez😢
                System.out.println("Lo lamentamos, pero no pudimos encontrar tu destino, por favor asegúrate de que el nombre esté bien escrito."+'\n');
                go();
                break;
        
            case 1: //Este else if y el else de abajo es por si se encuentran uno o varios resultados, para aplicar el plural
                System.out.println("Este fue el resultado que encontramos: ");

                System.out.println("- "+resultados.get(0).getNombre()+", "+resultados.get(0).getPais()+".");

                System.out.println("¿Es este el resultado correcto? (S/N)");
                String eleccion = scannerPrompt.nextLine(); //Este if es para verificar si el destino encontrado es el correcto

                if (eleccion.equalsIgnoreCase("s")||eleccion.equalsIgnoreCase("si")){

                    //Se actualiza el indice 
                    idDestino=0;

                }
                else if(eleccion.equalsIgnoreCase("n")||eleccion.equalsIgnoreCase("no")){
                    System.out.println("Lamentamos que ese no sea tu destino, por favor asegúrate de que el nombre esté bien escrito."+'\n');
                    go(); //Si no es el destino vuelve al inicio
                }
                else{
                    System.out.println("Por favor introduce una opción válida."+'\n');
                    paso1(); //Si se introduce lo que no es, vuelve a buscarcontext1(String posibleDestino)
                }
                break;

        
            case 2://Cuando más de un destino comparten palabras clave:
                System.out.println("Estos fueron los resultados que encontramos: ");

                int accion=0;//Este es el número del destino que va a escojer el usuario
                while(true){

                    int i=1; //Se listan las opciones
                    for(Destino resultado: resultados){
                        System.out.println(i+". "+resultado.getNombre()+", "+resultado.getPais()+".");
                        i++;
                    }

                    System.out.println("Por favor introduce el número asociado a tu destino.");

                    try{

                        accion = Integer.parseInt(scannerPrompt.nextLine()); //Espacio para introducir el número del destino, según aparecen listados
                        if(accion<=resultados.size()&&accion>0){

                            System.out.println("¿Este es el destino correcto? (S/N)"+'\n'+"- "+resultados.get(accion-1).getNombre()+", "+resultados.get(accion-1).getPais()+".");
                            eleccion= scannerPrompt.nextLine();

                            if(eleccion.equalsIgnoreCase("s")||eleccion.equalsIgnoreCase("si")){
                                break;
                            }
                            else{ 
                                continue;
                            }
                            
                        }
                        else{
                            System.out.println("Por favor selecciona una opción válida.");
                            continue;
                        }
                    
                    }
                    //Si se introduce algo que no sea un int, se atrapa la excepción y sale el mensaje de que se deben introducir números
                    catch(NumberFormatException e){
    
                        System.out.println("Por favor introduce un valor válido."+'\n'+'\n'+
                        "==========");
                        //Se regresa al inicio del bucle
                        continue;
    
                    }

                }

                idDestino=(accion-1); //Se actualiza el indice, se resta 1 porque los índices del array comienzan en 0
                
            }

            paso2();

    }

    public static void paso2(){

        int choice;
        while(true){
            System.out.println("¿Con cuántas personas viajas?"+'\n');
            try{
            
                choice = Integer.parseInt(scannerPrompt.nextLine()); //Espacio para introducir el número de personas
                if(choice>0){
                
                    System.out.println("Haz indicado que viajas con "+choice+" persona(s) ¿Es esta la cantidad correcta? (S/N)"+'\n');
                    String eleccion= scannerPrompt.nextLine();
                
                    if(eleccion.equalsIgnoreCase("s")||eleccion.equalsIgnoreCase("si")){
                        personas=choice;
                        break;
                    }
                    else{ 
                        continue;
                    }
                    
                }
                else{
                    System.out.println("Por favor introduce un valor válido.");
                    continue;
                }
            
            }
            //Si se introduce algo que no sea un int, se atrapa la excepción y sale el mensaje de que se deben introducir números
            catch(NumberFormatException e){

                System.out.println("Por favor introduce un valor válido."+'\n'+'\n'+
                "==========");
                //Se regresa al inicio del bucle
                continue;

            }
        }

        paso3();

    }

    public static void paso3(){
        Transporte transporte;
        System.out.println("Por favor selecciona cómo deseas llegar a tu destino: "+'\n');
        while(true){
            if(resultados.get(idDestino).getLlegar()==1){
                //System.out.println("Esta fue la opción de transporte que encontramos para llegar a "+resultados.get(idDestino).getNombre());
                System.out.println("1. Autobús.");
            }
            else if(resultados.get(idDestino).getLlegar()==2){
                //System.out.println("Esta fue la opción de transporte que encontramos para llegar a "+resultados.get(idDestino).getNombre());
                System.out.println("1. Autobús.");
                System.out.println("2. Tren.");
            }
            else if(resultados.get(idDestino).getLlegar()==3){
                //System.out.println("Esta fue la opción de transporte que encontramos para llegar a "+resultados.get(idDestino).getNombre());
                System.out.println("1. Autobús.");
                System.out.println("2. Tren.");
                System.out.println("3. Avión.");
            }
            else{
                System.out.println("4. Avión.");
            }
            try{
                System.out.println("Por favor escribe el número asociado al medio de transporte que deseas tomar:"+'\n');
                int accion=Integer.parseInt(scannerPrompt.nextLine());

                if((accion==4&&resultados.get(idDestino).getLlegar()==4)||(accion==3&&resultados.get(idDestino).getLlegar()==3)){
                    System.out.println("Has seleccionado avión.");
                    transporte =new Avion(resultados.get(idDestino), personas);
                    break;
                }
                else if(accion==2&&(resultados.get(idDestino).getLlegar()>0||resultados.get(idDestino).getLlegar()<4)){
                    System.out.println("Has seleccionado tren.");
                    transporte =new Tren(resultados.get(idDestino), personas);
                    break;
                }
                else if(accion==1&&(resultados.get(idDestino).getLlegar()==3||resultados.get(idDestino).getLlegar()==2)){
                    System.out.println("Has seleccionado Autobús.");
                    transporte =new Autobus(resultados.get(idDestino), personas);
                    break;
                }
                else{
                    System.out.println("Por favor introduce una opción válida.");
                    continue;
                }

            }
            catch(NumberFormatException e){

                System.out.println("Por favor introduce un valor válido."+'\n'+'\n'+
                "==========");
                //Se regresa al inicio del bucle
                continue;

            }
        }
        fechas(transporte, true);   
    }

    public static void fechas(Transporte transporte, boolean estaBien){
        LocalDate fechaLlegada;
        LocalDate fechaSalida=null;
        String salir;

        if(estaBien==false){ //Esto solo se desata cuando las fechas están mal y reservaUsuario se da cuenta
            System.out.println("Por favor introduce fechas válidas."+'\n');

        }

        while(true){
        
            try {

                //Aquí se asignan las fechas de llegada y salida
                System.out.println("Por favor introduce la fecha de salida (En formato AAAA-MM-DD): ");
                fechaLlegada= LocalDate.parse(scannerPrompt.nextLine());

                System.out.println("Por favor introduce la fecha de retorno (En formato AAAA-MM-DD), o introduce 0 (cero) si es un viaje de solo ida: ");
                salir=scannerPrompt.nextLine();
                if(!salir.equals("0")){
                    fechaSalida= LocalDate.parse(salir);
                }

                if(fechaSalida!=null){
                    System.out.println("¿Las fechas son correctas? (S/N)"+'\n'+"Fecha de salida: "+fechaLlegada.toString()+
                    '\n'+"Fecha de llegada: "+fechaSalida.toString());
                }
                else{
                    System.out.println("¿La fecha es correcta? (S/N)"+'\n'+"Fecha de llegada: "+fechaLlegada.toString()+
                    '\n');
                }

                String eleccion = scannerPrompt.nextLine();

                if (eleccion.equalsIgnoreCase("si")||eleccion.equalsIgnoreCase("s")){
                    break;
                }
                else {
                    continue;
                }
                

            } catch (DateTimeParseException e) { //Si el usuario mete algo que no es una fecha hay excepción
            
                System.out.println("Por favor introduce una fecha válida."+'\n'+'\n'+
                "==========");
                //Se regresa al inicio del bucle
                continue;

            }

        }

        if(fechaSalida!=null){
            transporte.fechas(fechaLlegada, fechaSalida);
        }
        else{
            transporte.fechas(fechaLlegada);
        }
        

    }

    public static void precios(Transporte transporte){

        System.out.println("Estos son los precios que ofrece tu opción de transporte:");

        if(transporte.isRoundTrip()){
            System.out.println(" 1. Clase Turista: $"+(String.format("%,.2f",(transporte.calculoRoundTrip(transporte.getDestino().getFama(), transporte.getDestino().getTemporada(), transporte.getPersonas(), 1)))));
            System.out.println(" 2. Clase Negocios: $"+(String.format("%,.2f",(transporte.calculoRoundTrip(transporte.getDestino().getFama(), transporte.getDestino().getTemporada(), transporte.getPersonas(), 2)))));
            System.out.println(" 3. Primera Clase: $"+(String.format("%,.2f",(transporte.calculoRoundTrip(transporte.getDestino().getFama(), transporte.getDestino().getTemporada(), transporte.getPersonas(), 3)))));
        }
        else{
            System.out.println(" 1. Clase Turista: $"+(String.format("%,.2f",(transporte.calculoPrecioTransporte(transporte.getDestino().getFama(), transporte.getDestino().getTemporada(), transporte.getPersonas(), 1)))));
            System.out.println(" 2. Clase Negocios: $"+(String.format("%,.2f",(transporte.calculoPrecioTransporte(transporte.getDestino().getFama(), transporte.getDestino().getTemporada(), transporte.getPersonas(), 2)))));
            System.out.println(" 3. Primera Clase: $"+(String.format("%,.2f",(transporte.calculoPrecioTransporte(transporte.getDestino().getFama(), transporte.getDestino().getTemporada(), transporte.getPersonas(), 3)))));
        }

        int eleccion;
        while (true) {
            System.out.println("Por favor selecciona el número asociado a la opción que deseas."+'\n');
            try{
                eleccion=Integer.parseInt(scannerPrompt.nextLine());

                if(eleccion==1){
                    System.out.println("Has seleccionado viajar en clase turista.");
                    break;
                }
                else if(eleccion==2){
                    System.out.println("Has seleccionado viajar en clase de negocios.");
                    break;
                }
                else if(eleccion==3){
                    System.out.println("Has seleccionado viajar en primera clase.");
                    break;
                }
                else{
                    System.out.println("Por favor selecciona una opción válida.");
                    continue;
                }
            }
            catch(NumberFormatException e){
                System.out.println("Por favor introduce un valor válido.");
                continue;
            }
        }

        System.out.println("El precio total de tu reserva es de: $"+(String.format("%,.2f",(transporte.calculoRoundTrip(transporte.getDestino().getFama(), transporte.getDestino().getTemporada(), transporte.getPersonas(), eleccion)))));
        System.out.println("El tiempo total de tu viaje será de: "+transporte.ETA(transporte.getDistancia())+" horas.");
    }



}
