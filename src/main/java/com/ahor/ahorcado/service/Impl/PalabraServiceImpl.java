package com.ahor.ahorcado.service.Impl;

import com.ahor.ahorcado.service.PalabraService;
import org.springframework.stereotype.Service;

import java.util.Arrays;


@Service
public class PalabraServiceImpl implements PalabraService {

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


}
