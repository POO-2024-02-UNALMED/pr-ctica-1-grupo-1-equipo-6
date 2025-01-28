package uiMain;

import java.util.ArrayList;

import gestorAplicacion.reservacionHotel.Destino;
import gestorAplicacion.reservacionHotel.Reserva;

public class uiTransporte extends uiMain{

    private static int idDestino;
    
    public static void go(){
        System.out.println("Precaución: La implementación de esta funcionalidad puede contener errores"+'\n'+"Última revisión: AlPerBara, 2025-01-28-08-25"+'\n');

        System.out.println("Por favor introduce el nombre de tu destino, de nuestro listado de destinos disponibles:");

        for(Destino destino : Destino.getDestinos()){ //Se muestra una lista con los destinos
            System.out.println("- "+destino.getNombre()+", "+destino.getPais()+".");
        }

        paso1();
    }

    public static void paso1(){//ESTE CODIGO ES RECICLADO DE uiReservaHotel

        System.out.println("Por favor introduce el nombre de tu destino.:");
        String posibleDestino = scannerPrompt.nextLine();

        ArrayList<Destino> resultados = Reserva.buscarDestino(posibleDestino);

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

            

    }

}
