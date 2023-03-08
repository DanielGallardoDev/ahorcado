package com.ahor.ahorcado.console.Impl;

import com.ahor.ahorcado.console.IntentosConsole;
import com.ahor.ahorcado.service.PalabraService;
import org.springframework.stereotype.Component;

@Component
public class IntentosConsoleImpl implements IntentosConsole {
    int maxIntentos= 8;
    int numIntentos = maxIntentos;

    PalabraService palabraService;

    public IntentosConsoleImpl(PalabraService palabraService) {

        this.palabraService = palabraService;
    }

    @Override
    public char[] realizarIntento(String palabraPorAdivinar, char[] palabraOculta, String intento ) {
        char[] res ;

        if(intento.length()>1){
            res= palabraService.adivinarPalabra(palabraPorAdivinar, palabraOculta, intento);
        } else{
            res= palabraService.adivinarLetra(palabraPorAdivinar, palabraOculta, intento.charAt(0));
        }
        if(palabraService.comprobarCambiosPalabraOculta(palabraOculta, res)){
            numIntentos= numIntentos-1;
        }
        return res;
    }

    @Override
    public int contadorIntentos() {
        return numIntentos;
    }

    @Override
    public int contadorIntentosReset() {
        return numIntentos=maxIntentos;
    }


}
