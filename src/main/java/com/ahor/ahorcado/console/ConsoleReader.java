package com.ahor.ahorcado.console;

import com.ahor.ahorcado.service.PalabraService;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.util.Scanner;
@Component
public class ConsoleReader {

    PalabraService palabraService;

    public ConsoleReader(PalabraService palabraService) {
        this.palabraService = palabraService;
    }

    @PostConstruct
    public void init() throws ParseException {
        Scanner sc = new Scanner(System.in);
        String entradaConsola;
        int opcion = 1;

        while(opcion != 2){
            System.out.println("Bienvenido al juego del ahorcado");
            System.out.println("Menú:");
            System.out.println("1.- Juego nuevo");
            System.out.println("2.- Salir");
            System.out.println("Selecciona su operacion:");

            entradaConsola = sc.next();

            try{
                opcion= Integer.parseInt(entradaConsola);
            } catch (Exception e){
                System.out.println("Opcion no valida, ingresa un número");
            }

            switch (opcion){
                case 1:
                    int intentos = palabraService.contadorIntentos();
                    String palabraPorAdivinar = palabraService.obtenerPalabra().getValue0();
                    char[] palabraOculta = palabraService.obtenerPalabra().getValue1();

                    System.out.println("Aquí tienes la palabra a adivinar:");
                    System.out.println(palabraOculta);

                    while(!palabraService.comprobarVictoria(palabraPorAdivinar, palabraOculta) && intentos>0){

                        System.out.println("Realiza un intento:");
                        String intento = sc.next();

                        palabraOculta= palabraService.realizarIntento(palabraPorAdivinar, palabraOculta, intento);
                        intentos = palabraService.contadorIntentos();

                        System.out.println(palabraOculta);

                        if(!palabraService.comprobarVictoria(palabraPorAdivinar, palabraOculta) && intentos>0){
                            System.out.println(intentos+" intentos restantes");
                        }
                        System.out.println();
                    }

                    mensajeFinPartida(intentos);
                    palabraService.contadorIntentosReset();
                    System.out.println();
                    break;

                case 2:
                    System.out.println("Hasta luego!!");
                    System.out.println();
                    break;

                default:
                    System.out.println("Opción no válida");
                    break;
            }
        }
    }

    private static void mensajeFinPartida(int intentos) {
        if(intentos >0){
            System.out.println("¡Enhorabuena! Adivinaste la palabra!");
        } else if(intentos <0){
            System.out.println("Fallaste la palabra, prueba a jugar de nuevo.");
        } else{
            System.out.println("Te quedaste sin intentos, prueba a jugar de nuevo.");
        }
    }
}
