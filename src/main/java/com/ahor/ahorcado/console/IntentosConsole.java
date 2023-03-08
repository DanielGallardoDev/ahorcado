package com.ahor.ahorcado.console;

public interface IntentosConsole {

    char[] realizarIntento(String palabraPorAdivinar, char[] palabraOculta, String intento);

    int contadorIntentos();

    int contadorIntentosReset();

}

