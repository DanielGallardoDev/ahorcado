package com.ahor.ahorcado.service;


public interface PalabraService {

    char[] adivinarLetra(String palabra, char[] palabraOculta,  char letra);
    char[] adivinarPalabra(String palabraPorAdivinar, char[] palabraOculta, String palabra);
    boolean comprobarVictoria (String palabraPorAdivinar, char[] palabraOculta );
    boolean comprobarCambiosPalabraOculta( char[] palabraOcultaAntigua, char[] palabraOcultaActual);


}
