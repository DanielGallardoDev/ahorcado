package com.ahor.ahorcado.console;

import com.ahor.ahorcado.repository.PalabraRepository;
import com.ahor.ahorcado.service.PalabraService;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.util.Scanner;
@Component
public class ConsoleReader {

    PalabraService palabraService;
    PalabraRepository palabraRepository;
    IntentosConsole intentosConsole;

    public ConsoleReader(PalabraService palabraService, PalabraRepository palabraRepository, IntentosConsole intentosConsole) {
        this.palabraService = palabraService;
        this.palabraRepository = palabraRepository;
        this.intentosConsole = intentosConsole;
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
                    int intentos =intentosConsole.contadorIntentos();
                    String palabraPorAdivinar = palabraRepository.obtenerPalabra().getValue0();
                    char[] palabraOculta = palabraRepository.obtenerPalabra().getValue1();

                    System.out.println("Aquí tienes la palabra a adivinar:");
                    System.out.println(palabraOculta);

                    while(!palabraService.comprobarVictoria(palabraPorAdivinar, palabraOculta) && intentos!=0){

                        System.out.println("Realiza un intento:");
                        String intento = sc.next();

                        palabraOculta= intentosConsole.realizarIntento(palabraPorAdivinar, palabraOculta, intento);
                        intentos = intentosConsole.contadorIntentos();

                        System.out.println(palabraOculta);
                        System.out.println(intentos+" intentos restantes");
                        System.out.println();
                    }
                    if(intentos>0){
                        System.out.println("¡Enhorabuena! Adivinaste la palabra!");
                    }else{
                        intentosConsole.contadorIntentosReset();
                        System.out.println("Te quedaste sin intentos, prueba a jugar de nuevo.");
                    }
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
}
