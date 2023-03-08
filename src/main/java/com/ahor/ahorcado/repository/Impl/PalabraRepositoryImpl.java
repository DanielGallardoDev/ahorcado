package com.ahor.ahorcado.repository.Impl;

import com.ahor.ahorcado.repository.PalabraRepository;
import org.javatuples.Pair;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
@Repository
public class PalabraRepositoryImpl implements PalabraRepository {

    String[] palabras = {"barco","girasol","perro", "microscopio", "manzana", "ordenador",
            "teclado","cerveza", "armario", "microscopio"};


    List<String> listaPalabras =  Arrays.asList(palabras);


    @Override
    public Pair<String,char[]> obtenerPalabra() {

       int max =listaPalabras.size();
       int id = (int) Math.floor(Math.random()*(0-(max))+(max));

       String palabraElegida = listaPalabras.get(id);
       char[] palabraOculta = new char[palabraElegida.length()];

       for(int i=0; i<palabraElegida.length();i++){
           palabraOculta[i]='_';
       }

       return Pair.with(palabraElegida, palabraOculta);
    }

}
