package uiMain;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import gestorAplicacion.reservacionHotel.Destino;
import gestorAplicacion.reservacionHotel.Reserva;

public class uiReservaHotel extends uiMain{
    
    public static void go(){

        System.out.println("¿A dónde deseas viajar? Escribe el nombre de tu destino, o escribe 0 (cero) para ver todos nuestros destinos.");
        System.out.println("Escribe 9 para regresar al menú principal.");
        
        
        String posibleDestino = scannerPrompt.nextLine();

        if(posibleDestino.equals("0") || posibleDestino.equalsIgnoreCase("cero")){ //Si el usuario digita cero, se ejecuta este código

            System.out.println("Mostrando nuestros destinos: ");
            int i=1; //Este valor enumera los destinos
            for(Destino destino : Destino.getDestinos()){ //Se muestra una lista con los destinos
                System.out.println(i+". "+destino.getNombre()+", "+destino.getPais()+".");
                i++;
            }

            System.out.println("Escribe el nombre de tu destino.");
            posibleDestino = scannerPrompt.nextLine();
            buscarContext1(posibleDestino);

        }
        else if (posibleDestino.equals("9")){
            System.out.println("Regresando al menú principal..."+'\n');
            //Esta condición se queda vacía para devolverse inmediatamente al bucle principal
        }
        else{
            buscarContext1(posibleDestino);
        }
    }
    
    public static void buscarContext1(String posibleDestino){ //Este es el método para buscar entre las coincidencias

        ArrayList<Destino> resultados = Reserva.buscarDestino(posibleDestino); //Se va a buscar si el nombre que se introduce existe

        switch(Reserva.cantidadResultadosEn123(resultados)){
            case 0: //Si no hay resultados entonces toca buscar otra vez😢
                System.out.println("Lo lamentamos, pero no pudimos encontrar tu destino, por favor asegúrate de que el nombre esté bien escrito."+'\n');
                go();
                break;
        

            //A partir de aquí se puede crear la reserva
            case 1: //Este else if y el else de abajo es por si se encuentran uno o varios resultados, para aplicar el plural
                System.out.println("Este fue el resultado que encontramos: ");

                System.out.println("- "+resultados.get(0).getNombre()+", "+resultados.get(0).getPais()+".");

                System.out.println("¿Es este el resultado correcto? (S/N)");
                String eleccion = scannerPrompt.nextLine(); //Este if es para verificar si el destino encontrado es el correcto

                if (eleccion.equalsIgnoreCase("s")||eleccion.equalsIgnoreCase("si")){

                    //Se actualiza el indice de la reserva
                    Reserva.setIdDestino(0);

                }
                else if(eleccion.equalsIgnoreCase("n")||eleccion.equalsIgnoreCase("no")){
                    System.out.println("Lamentamos que ese no sea tu destino, por favor asegúrate de que el nombre esté bien escrito."+'\n');
                    go(); //Si no es el destino vuelve al inicio
                }
                else{
                    System.out.println("Por favor introduce una opción válida."+'\n');
                    buscarContext1(posibleDestino); //Si se introduce lo que no es, vuelve a buscarcontext1(String posibleDestino)
                }
                break;

        
            case 2:
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
                            break;
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

                Reserva.setIdDestino(accion-1); //Se actualiza el indice de la reserva, se resta 1 porque los índices del array comienzan en 0
            
            }

            Reserva reservaUsuario = new Reserva(resultados.get(Reserva.getIdDestino()));
            fechas(reservaUsuario, true); //Se pasa a las fechas, se pone true porque no hay inconvenientes
    
        }


    public static void fechas(Reserva reservaUsuario, boolean condicion){ //True significa que todo está bien, false que toca mostrar mensaje de error
        //Estas son las fechas de la estadía
        LocalDate fechaLlegada;
        LocalDate fechaSalida;

        if(condicion==false){ //Esto solo se desata cuando las fechas están mal y reservaUsuario se da cuenta
            System.out.println("Por favor introduce fechas válidas."+'\n');

        }

        while(true){
        
            try {

                //Aquí se asignan las fechas de llegada y salida
                System.out.println("Por favor introduce la fecha de llegada (En formato AAAA-MM-DD): ");
                fechaLlegada= LocalDate.parse(scannerPrompt.nextLine());

                System.out.println("Por favor introduce la fecha de salida (En formato AAAA-MM-DD): ");
                fechaSalida= LocalDate.parse(scannerPrompt.nextLine());
                break;

            } catch (DateTimeParseException e) { //Si el usuario mete algo que no es una fecha hay excepción
            
                System.out.println("Por favor introduce una fecha válida."+'\n'+'\n'+
                "==========");
                //Se regresa al inicio del bucle
                continue;

            }

        }

        reservaUsuario.setAmbasFechas(fechaLlegada, fechaSalida);
        
    }

    public static void viajeros(Reserva reservaUsuario, boolean esValido, boolean elLegal){

        int adultos;
        int menores;

        if(esValido==false){ //Esto solo se desata cuando los números de adultos y de niños están mal y reservaUsuario se da cuenta
            System.out.println("Por favor introduce valores válidos."+'\n');

        }else if(elLegal==false){
            System.out.println("Lo lamentamos, pero por motivos legales y de seguridad, nungún adulto puede estar a cargo de más de 2 menores de edad."+'\n');
        }

        //Cuantas personas viajan
        while(true){
    
            try{
    
                System.out.println("¿Cuántos adultos viajan?");
                adultos = Integer.parseInt(scannerPrompt.nextLine()); //Espacio para introducir el número de adultos

                System.out.println("¿Cuántos menores viajan?");
                menores = Integer.parseInt(scannerPrompt.nextLine()); //Espacio para introducir el número de menores

                break;
                
            }
            //Si se introduce algo que no sea un int, se atrapa la excepción y sale el mensaje de que se deben introducir números
            catch(NumberFormatException e){
    
                System.out.println("Por favor introduce un número válido."+'\n'+'\n'+
                "==========");
                //Se regresa al inicio del bucle
                continue;
    
            }
    
        }

        reservaUsuario.setAdultosMenores(adultos, menores);

    }
}
