package com.ahor.ahorcado.service.Impl;

import com.ahor.ahorcado.repository.PalabraRepository;
import com.ahor.ahorcado.service.PalabraService;
import org.javatuples.Pair;
import org.springframework.stereotype.Service;

import java.util.Arrays;


@Service
public class PalabraServiceImpl implements PalabraService {

    private final static int MAX_INTENTOS = 8;
    private int numIntentos = MAX_INTENTOS;

    PalabraRepository palabraRepository;

    public PalabraServiceImpl(PalabraRepository palabraRepository) {
        this.palabraRepository = palabraRepository;
    }

    @Override
    public char[] adivinarLetra(String palabraPorAdivinar, char[] palabraOculta,  char letra) {
        char[] res = Arrays.copyOf(palabraOculta,palabraPorAdivinar.length());
        for (int i = 0; i < palabraPorAdivinar.length(); i++) {
            char letraAComprobar = palabraPorAdivinar.charAt(i);
            if (letraAComprobar == letra) {
                res[i]=letra;
            }
        }
        return res;
    }

    @Override
    public char[] adivinarPalabra(String palabraPorAdivinar, char[] palabraOculta, String palabra) {
        char[] res;
        if(palabraPorAdivinar.equals(palabra)){
           res = palabra.toCharArray();
        } else{
            res= palabraOculta;
            numIntentos=0;
        }
        return res;
    }
    @Override
    public boolean comprobarVictoria(String palabraPorAdivinar, char[] palabraOculta ){
       if(palabraPorAdivinar.equals(String.valueOf(palabraOculta))){
           return true;
       } else {
           return false;
       }
    }
    @Override
    public boolean comprobarCambiosPalabraOculta(char[] palabraOcultaAntigua, char[] palabraOcultaActual){
        if(String.valueOf(palabraOcultaAntigua).equals(String.valueOf(palabraOcultaActual))){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public char[] realizarIntento(String palabraPorAdivinar, char[] palabraOculta, String intento) {
        char[] res ;

        if(intento.length()>1){
            res= adivinarPalabra(palabraPorAdivinar, palabraOculta, intento);
        } else{
            res= adivinarLetra(palabraPorAdivinar, palabraOculta, intento.charAt(0));
        }
        if(comprobarCambiosPalabraOculta(palabraOculta, res)){
            decrementarIntentos();
        }
        return res;
    }

    @Override
    public int contadorIntentos() {
        return numIntentos;
    }

    @Override
    public int contadorIntentosReset() {
        return numIntentos= MAX_INTENTOS;
    }

    @Override
    public int decrementarIntentos() {
        return numIntentos--;
    }

    @Override
    public Pair<String, char[]> obtenerPalabra() {
        return palabraRepository.obtenerPalabra();
    }


}
