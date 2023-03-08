package com.ahor.ahorcado.service;


import org.javatuples.Pair;

public interface PalabraService {

    char[] adivinarLetra(String palabra, char[] palabraOculta,  char letra);
    char[] adivinarPalabra(String palabraPorAdivinar, char[] palabraOculta, String palabra);
    boolean comprobarVictoria (String palabraPorAdivinar, char[] palabraOculta );
    boolean comprobarCambiosPalabraOculta( char[] palabraOcultaAntigua, char[] palabraOcultaActual);
    char[] realizarIntento(String palabraPorAdivinar, char[] palabraOculta, String intento);
    int contadorIntentos();
    void contadorIntentosReset();
    void decrementarIntentos();
    Pair<String,char[]> obtenerPalabra();
}
