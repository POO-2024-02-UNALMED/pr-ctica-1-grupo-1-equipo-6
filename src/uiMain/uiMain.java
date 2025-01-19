//Inicialmente codificado por Alejandro Pérez Barrera

package uiMain;

import gestorAplicacion.*;
import baseDatos.*;
import java.util.Scanner;

public class uiMain {

    //scannerPrompt lee las input por consola
    public static Scanner scannerPrompt = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Bienvenido a la Agencia de Viajes Rumbo Aventura");

        //Este es el bucle desde el cual se acceden a todas las funciones del programa, se sigue repitiendo hasta presionar 0    
        while (true){

            //Menú de opciones
            System.out.println("Por favor selecciona que deseas hacer:"+'\n'+
            " 1. Reservar una habitación de hotel."+'\n'+
            " 2. Reservar un viaje."+'\n'+
            " 3. Agendar planes complementarios."+'\n'+
            " 4. Realizar pagos."+'\n'+
            " 5. Cancelaciones."+'\n'+
            " "+'\n'+
            " 0. Salir");

            //accion es el valor que se va a introducir, para ejecutar una de las 6 funciones
            int accion=0;
            
            //try intenta convertir la input del scanner en un int
            try{

                accion = Integer.parseInt(scannerPrompt.nextLine());
                
            }
            //Si se introduce algo que no sea un int, se atrapa la excepción y sale el mensajito de que se deben introducir números
            catch(NumberFormatException e){

                System.out.println("Por favor introduce un valor válido."+'\n'+'\n'+
                "==========");
                //Se regresa al inicio del bucle
                continue;

            }

            //Verificar que "accion" no equivalga a 0
            if (accion!=0){

                //SWITCH OPCIÓN FUNCIÓN

                switch (accion) {
                    case 1:
                        System.out.println('\n'+"Has seleccionado reservar una habitación de hotel."+'\n');
                        uiReservaHotel.go(false, null); //El boolean indica que esto es una reserva nueva, no una modificación
                        break;

                    case 2:
                        System.out.println("Has seleccionado reservar un viaje."+'\n');
                        reservarViaje();//TODO: Al correspondiente, comenzar a ejecutar su funcionalidad desde aquí.
                        break;

                    case 3:
                        System.out.println("Has seleccionado agendar planes complementarios."+'\n');
                        reservarPlanes();//TODO: Al correspondiente, comenzar a ejecutar su funcionalidad desde aquí.
                        break;

                    case 4:
                        System.out.println("Has seleccionado realizar pagos."+'\n');
                        hacerPago();//TODO: Al correspondiente, comenzar a ejecutar su funcionalidad desde aquí.
                        break;

                    case 5:
                        System.out.println("Has ingresado al servicio de cancelaciones."+'\n');
                        cancelaciones();//TODO: Al correspondiente, comenzar a ejecutar su funcionalidad desde aquí.
                        break;
            
                    default:
                    System.out.println("Por favor introduce una opción válida."+'\n'+'\n'+
                    "==========");
                        break;
                }

            }
            
            //CERRAR EL PROGRAMA

            //Si accion equivale a 0, se procede a verificar si en verdad de quiere cerrar el programa
            else{


                //El string "Si no" es la opción del usuario "si" o "no"
                String siNo=null;

                System.out.println("¿Estás seguro? (S/N)");

                //siNo toma el valor de la consola
                siNo=scannerPrompt.nextLine();

                //Si la variable equivale a "s", se cierra el programa
                if(siNo.equalsIgnoreCase("s")||siNo.equalsIgnoreCase("si")){
                    System.out.println("Muchas gracias por utilizar nuestros servicios.");

                    //AQUÍ COMIENZA EN GUARDAR LOS DATOS
                    if(!baseDatos.guardarObjetos.guardar(gestorAplicacion.reservacionHotel.Destino.getDestinos())){//Aquí se verifica si se guardaron lo datos de los destinos y hoteles tras reservar
                        System.out.println("Ha ocurrido un error al guardar destinos.");
                        continue;
                    }
                    else{ break;}
                    //TODO: elaborar un sistema para guardar todo antes de salir, justo aquí.
                    
                }

                //Si equivale a "n", se regresa al bucle principal
                else if(siNo.equalsIgnoreCase("n")||siNo.equalsIgnoreCase("no")){
                    System.out.println('\n'+"=========="+'\n');
                    continue;
                }

                //Si se introduce algo diferente, se expresa un mensaje de error y se retorna al bucle inicial por conveniencia
                else{
                    System.out.println("Por favor introduce una opción válida."+'\n'+'\n'+
                                    "==========");
                }

            }

        }
            
    }

    //TODO: Placeholder para funcionalidad de reservar un viaje.
    public static void reservarViaje(){
        System.out.println("Viaje");
    }

    //TODO: Placeholder para funcionalidad de planes.
    public static void reservarPlanes(){
        System.out.println("Planes");
    }

    //TODO: Placeholder para funcionalidad de pago
    public static void hacerPago(){
        System.out.println("Pagar");
    }

    //TODO: Placeholder para funcionalidad de cancelar
    public static void cancelaciones(){
        System.out.println("Cancelar");
    }

}
