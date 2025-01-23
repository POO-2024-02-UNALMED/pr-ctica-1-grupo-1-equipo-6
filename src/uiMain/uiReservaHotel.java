//Codificado por Alejandro Pérez Barrera

//Esta clase es la interfaz de usuario para reservar hoteles, incluye todo lo necesario en terminos de interacciones y mensajes de print

package uiMain;

import java.util.ArrayList;
//import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import gestorAplicacion.reservacionHotel.Destino;
import gestorAplicacion.reservacionHotel.Hotel;
import gestorAplicacion.reservacionHotel.Reserva;

public class uiReservaHotel extends uiMain{
    
    public static void go(boolean modificar, Reserva reservaModificada){ //modificar y reservaModificada solo importan al cambiar el destino y hotel

        if(!modificar){//Esto se imprime si no se va a modificar el destino
            System.out.println("¿A dónde deseas viajar? Escribe el nombre de tu destino, o escribe 0 (cero) para ver todos nuestros destinos.");
            System.out.println("Escribe 9 para regresar al menú principal.");
        }
        else{//Esto solo sale si es para cambiar el destino
            System.out.println("Introduce el nombre de tu nuevo destino, o escribe 0 (cero) para ver todos nuestros destinos.");
        }
        
        String posibleDestino = scannerPrompt.nextLine();

        if(posibleDestino.equals("0") || posibleDestino.equalsIgnoreCase("cero")){ //Si el usuario digita cero, se ejecuta este código

            //Mostrar todos los destinos en un listado
            System.out.println("Mostrando nuestros destinos: ");

            for(Destino destino : Destino.getDestinos()){ //Se muestra una lista con los destinos
                System.out.println("- "+destino.getNombre()+", "+destino.getPais()+".");
            }

            System.out.println("Escribe el nombre de tu destino.");
            posibleDestino = scannerPrompt.nextLine();
            //Si no es una modificación, no se pasa ninguna reserva
            if(!modificar){
                buscarContext0(modificar, posibleDestino);
            }
            else{
                buscarContext0(modificar, reservaModificada, posibleDestino);
            }

        }
        else if (posibleDestino.equals("9")&&!modificar){
            System.out.println("Regresando al menú principal..."+'\n');
            //Esta condición se queda vacía para devolverse inmediatamente al bucle principal
        }
        else if(!modificar){ //Si no es una modificación, no se pasa ninguna reserva
            buscarContext0(modificar, posibleDestino);
        }
        else{
            buscarContext0(modificar, reservaModificada, posibleDestino);
        }
    }
    
    //el metodo buscarcontext0 se sobrecarga para definir a reserva como null, de ser esta la primera vez que se reserva
    public static void buscarContext0(boolean modificar, Reserva reservaUsuario, String posibleDestino){
        buscarContext1(modificar, reservaUsuario, posibleDestino);
    }
    public static void buscarContext0(boolean modificar, String posibleDestino){
        buscarContext1(modificar, null, posibleDestino);
    }

    public static void buscarContext1(boolean modificar, Reserva reservaModificada, String posibleDestino){ //Este es el método para buscar entre las coincidencias

        ArrayList<Destino> resultados = Reserva.buscarDestino(posibleDestino); //Se va a buscar si el nombre que se introduce existe

        switch(Reserva.cantidadResultadosEn123(resultados)){
            case 0: //Si no hay resultados entonces toca buscar otra vez😢
                System.out.println("Lo lamentamos, pero no pudimos encontrar tu destino, por favor asegúrate de que el nombre esté bien escrito."+'\n');
                go(modificar, reservaModificada);
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
                    go(modificar,reservaModificada); //Si no es el destino vuelve al inicio
                }
                else{
                    System.out.println("Por favor introduce una opción válida."+'\n');
                    buscarContext0(modificar, posibleDestino); //Si se introduce lo que no es, vuelve a buscarcontext1(String posibleDestino)
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

                Reserva.setIdDestino(accion-1); //Se actualiza el indice de la reserva, se resta 1 porque los índices del array comienzan en 0
            
            }

            //Si se está modificando una reserva, solo se le cambia el hotel, de lo contrario, se crea una nueva y se pasa a fechas
            if(!modificar){
                Reserva reservaUsuario = new Reserva(resultados.get(Reserva.getIdDestino()));
                fechas(modificar, reservaUsuario, true); //Se pasa a las fechas, se pone true porque no hay inconvenientes
            }
            else{
                reservaModificada.setDestinoViaje(resultados.get(Reserva.getIdDestino()));
            }
    
        }


    public static void fechas(boolean modificar, Reserva reservaUsuario, boolean condicion){ //True significa que todo está bien, false que toca mostrar mensaje de error
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

                System.out.println("¿Las fechas son correctas? (S/N)"+'\n'+"Fecha de llegada: "+fechaLlegada.toString()+
                '\n'+"Fecha de salida: "+fechaSalida.toString());

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

        reservaUsuario.setAmbasFechas(modificar, fechaLlegada, fechaSalida);
        
        
    }

    public static void viajeros(boolean modificar, Reserva reservaUsuario, boolean esValido, boolean elLegal){

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

                System.out.println("¿Los datos son correctos? (S/N)"+'\n'+"Adultos: "+adultos+
                '\n'+"Menores: "+menores);

                String eleccion = scannerPrompt.nextLine();

                if (eleccion.equalsIgnoreCase("si")||eleccion.equalsIgnoreCase("s")){
                    break;
                }
                else {
                    continue;
                }

            }
            //Si se introduce algo que no sea un int, se atrapa la excepción y sale el mensaje de que se deben introducir números
            catch(NumberFormatException e){
    
                System.out.println("Por favor introduce un número válido."+'\n'+'\n'+
                "==========");
                //Se regresa al inicio del bucle
                continue;
    
            }
    
        }

        //Este método en reserva define el número de viajeros
        reservaUsuario.setAdultosMenores(modificar, adultos, menores);

    }

    public static void listarHoteles(boolean modificar, Reserva reservaUsuario, ArrayList<Hotel> opcionHoteles){
        
        //Elegir hotel
        int accion=0;
        Hotel hotelSelecto=null;
        while(true){
    
            try{

                System.out.println("Estos son los hoteles que encontramos:"+'\n');
                int i=1;
                for(Hotel hotel: opcionHoteles){//Esta opción lista los precios por noche
                    System.out.print(i+". "+hotel.getNombre()+". ("+(hotel.getPrestigio()/2)+" estrellas). Precio esperado por noche: $");
                    System.out.println(String.format("%,.2f",hotel.calcularPrecioEsperadoNoche(reservaUsuario.getDestinoViaje().getFama(), reservaUsuario.getDestinoViaje().getTemporada(), reservaUsuario.getViajerosAdultos(), reservaUsuario.getViejerosMenores(),hotel.getPrestigio(),hotel.getDemanda())));
                    i++;
                }
                System.out.println('\n'+"*Todos los precios están en pesos colombianos (COP)."+'\n'+"**Los precios están sujetos a cambio, dependiendo del tipo de estadía seleccionada."+'\n');
                System.out.println("Por favor digita en el número asociado a un hotel para conocer más información.");

                accion=Integer.parseInt(scannerPrompt.nextLine());

                if(accion<=opcionHoteles.size()&&accion>0){//Cuando se selecciona un hotel válido, se pasan los precios de ese hotel a un Arraylist

                    //Se define de manera provisoria un hotel seleccionado por el viajero, todavía no se asigna en la reserva
                    hotelSelecto=reservaUsuario.getDestinoViaje().getHotelesDestino().get(accion-1);
                    //Este Array guarda los precios en orden
                    ArrayList<Float> preciosNocheListados = reservaUsuario.getDestinoViaje().getHotelesDestino().get(accion-1).listarPrecios();
                    
                    System.out.println("Habitaciones disponibles:");

                    for(int j=0; j<preciosNocheListados.size();j++){//Se va a pasar por cada elemento del array
                        if(preciosNocheListados.get(j)!=null){//Esto se ejecuta solo si una posición del array no es null, por cada posición

                            //System.out.println("Switch");
                            switch (j) {//Este switch solo cambia el tipo de cuarto en el print
                                case 0:
                                    System.out.print((j+1)+". Cuarto simple: $");
                                    break;

                                case 2:
                                    System.out.print((j+1)+". Suite Premium: $");
                                    break;
                            
                                default:
                                    System.out.print((j+1)+". Habitación ejecutiva: $");
                                    break;
                            }
                            System.out.println(String.format("%,.2f",preciosNocheListados.get(j)));

                        }
                    }

                    System.out.println('\n'+"*Todos los precios están en pesos colombianos (COP)."+'\n');
                    System.out.println("Por favor digita en el número asociado a un tipo de habitación para reservarla.");

                    //Esta parte es para elegir la habitación, que el número sea válido y hayan habitaciones de este tipo
                    accion=Integer.parseInt(scannerPrompt.nextLine());
                    if(accion<=preciosNocheListados.size()&&accion>0&&preciosNocheListados.get(accion-1)!=null){

                        System.out.println("¿Es esta la habitación correcta? (S/N)");
                        
                        switch (accion) {//Switch solo es para imprimir el tipo de cuarto
                            case 1:
                                System.out.print("- Cuarto simple: $");
                                break;
                        
                            case 2:
                                System.out.print("- Habitación ejecutiva: $");
                                break;

                            case 3:
                                System.out.print("- Suite Premium: $");
                                break;
                        }
                        System.out.println(String.format("%,.2f",preciosNocheListados.get(accion-1)));

                        String eleccion= scannerPrompt.nextLine();

                        //Al verificar el usuario el tipo de cuarto:
                        if(eleccion.equalsIgnoreCase("s")||eleccion.equalsIgnoreCase("si")){

                            reservaUsuario.setLujoHotelViaje((byte)(accion-1)); //Se le asgina a reserva el lujo del hotel
                            reservaUsuario.setHotelViaje(hotelSelecto); //Se le asigna a reserva el hotel
                            reservaUsuario.setPrecioTotal(reservaUsuario.calculoEstadiaTotal()); //Se le asigna a reserva el precio

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
                else{
                    System.out.println("Por favor selecciona una opción válida.");
                    continue;
                }                
                
            }
            //Si se introduce algo que no sea un int, se atrapa la excepción y sale el mensaje de que se deben introducir números
            catch(NumberFormatException e){
    
                System.out.println("Por favor introduce un número válido."+'\n'+'\n'+
                "==========");
                //Se regresa al inicio del bucle
                continue;
    
            }
    
        }

        revision(modificar, reservaUsuario);

    }

    public static void revision(boolean modificar, Reserva reservaUsuario){ //Este método le muestra al usuario un resumen de toda la reserva

        if(!modificar){//Solo se muestra cuando no se va a modificar, para no mostrarlo 2 veces
            //Impreso, un resumen de todo
            System.out.println('\n'+"Este es el resumen de tu reserva:");
            System.out.println("    Destino: "+reservaUsuario.getDestinoViaje().getNombre()+", "+reservaUsuario.getDestinoViaje().getPais()+".");
            System.out.println("    Fecha de llegada: "+reservaUsuario.getFechaLlegar().toString()+".");
            System.out.println("    Fecha de salida: "+reservaUsuario.getFechaSalir().toString()+".");
            System.out.println("    Estadía: "+reservaUsuario.getEstadia()+" noches.");
            System.out.println("    Viajeros adultos: "+reservaUsuario.getViajerosAdultos()+".");
            System.out.println("    Viajeros menores de edad: "+reservaUsuario.getViejerosMenores()+".");
            System.out.println("    Hotel: "+reservaUsuario.getHotelViaje().getNombre()+".");
            System.out.print("  Tipo de habitación: ");

            switch (reservaUsuario.getLujoHotelViaje()) {
                case 0:
                    System.out.println("Cuarto simple.");
                    break;
        
                case 1:
                    System.out.println("Habitación ejecutiva.");
                    break;

                case 2:
                    System.out.println("Suite Premium.");
                    break;
            }

            System.out.println('\n'+"   El total a pagar es: $"+(String.format("%,.2f", reservaUsuario.getPrecioTotal()))+'\n');

            System.out.println("¿Estás conforme con tu reserva? ¿Deseas cambiar algo?"+'\n'+"Presiona 1 para confirmar tu reserva."+'\n'+"Presiona 2 para modificarla."+'\n'+"Presiona 0 para cancelar la reserva.");

            while(true){//Switch para las opctiones
                String eleccion =scannerPrompt.nextLine();

                if(eleccion.equals("1")||eleccion.equalsIgnoreCase("uno")){
                    confirmacion(reservaUsuario);//Método para confirmar
                    break;
                }
                else if(eleccion.equals("2")||eleccion.equalsIgnoreCase("dos")){
                    modificar(reservaUsuario); //Método para cambiar algo
                    break;
                }
                else if(eleccion.equals("0")||eleccion.equalsIgnoreCase("cero")){
                    reservaUsuario=null;
                    break;
                }
                else{
                    System.out.println("Por favor introduce una opción válida.");
                    continue;
                }
        
            }
        }

    }

    public static void confirmacion(Reserva reservaUsuario){ //Este es el método para confirmar las elecciones

        reservaUsuario.confirmarHotel();
        System.out.println("Tu hotel ha sido reservado de manera exitosa.");

    }

    public static void modificar(Reserva reservaUsuario){ //Si hay algo mal con la reserva, este método es para modificar

        System.out.println("¿Que deseas modificar? Selecciona el número que corresponda a la operación que deseas realizar."+'\n'+
                           "1. Modificar destino."+'\n'+
                           "2. Modificar fechas."+'\n'+
                           "3. Modificar el número de personas."+'\n'+
                           "4. Modificar hotel y/o habitación."+'\n'+
                           "0. Regresar");
        
        String eleccion =scannerPrompt.nextLine();

        if (eleccion.equals("0")||eleccion.equalsIgnoreCase("cero")){
            //Presionar 0 regresa a confirmación
        }

        else if(eleccion.equals("1")||eleccion.equalsIgnoreCase("uno")){ //presionar 1 es para cambiar el destino y hotel
            go(true, reservaUsuario);
            ArrayList<Hotel> hoteles= new ArrayList<>();
            hoteles.addAll(reservaUsuario.getDestinoViaje().getHotelesDestino());
            listarHoteles(true, reservaUsuario, hoteles);
        }

        else if(eleccion.equals("2")||eleccion.equalsIgnoreCase("dos")){
            fechas(true, reservaUsuario, true); //Presionar 2 para cambiar las fechas
        }

        else if(eleccion.equals("3")||eleccion.equalsIgnoreCase("tres")){
            viajeros(true, reservaUsuario, true, true); //prsionar 3 permite cambio de nro de viajeros
        }

        else if(eleccion.equals("4")||eleccion.equalsIgnoreCase("cuatro")){ //Esta opcion es para cambiar el hotel y/o el cuarto
            ArrayList<Hotel> hoteles= new ArrayList<>();
            hoteles.addAll(reservaUsuario.getDestinoViaje().getHotelesDestino());
            listarHoteles(true, reservaUsuario, hoteles);
        }

        else{
            System.out.println("Por favor introduce un valor válido.");
        }

        revision(false, reservaUsuario);

    }

}
