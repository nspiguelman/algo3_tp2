package edu.fiuba.algo3.modelo;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Collections;

public class Dados {

    public ArrayList<Integer> tirar(int atacante, int defensor) throws Exception{
        if (atacante == 1){
            throw new Exception("El atacante tiene una sola ficha");
        }
        if (atacante > 4){
            atacante = 4;
        }
        if (defensor > 3){
            defensor = 3;
        }
        ArrayList<Integer> ejercitosPerdidos = new ArrayList<Integer>();
        ArrayList<Integer> atacanteResultados = new ArrayList<Integer>();
        ArrayList<Integer> defensorResultados = new ArrayList<Integer>();
        for (int i=0; i<atacante-1; i=i+1){
            atacanteResultados.add((int)(Math.random()*6 + 1));
        }
        for (int i=0; i<defensor; i=i+1){
            defensorResultados.add((int)(Math.random()*6 + 1));
        }

        Collections.sort(atacanteResultados, Collections.reverseOrder());
        Collections.sort(defensorResultados, Collections.reverseOrder());


        int comparaciones = 0;
        if (atacanteResultados.size()<defensorResultados.size()){
            comparaciones = atacanteResultados.size();
        }else{
            comparaciones = defensorResultados.size();
        }
        ejercitosPerdidos.add(0);
        ejercitosPerdidos.add(0);
        for (int i = 0; i <= comparaciones - 1 ; i = i+1){
            if (atacanteResultados.get(i) <= defensorResultados.get(i)){
                ejercitosPerdidos.set(0,ejercitosPerdidos.get(0)+1);
            }else{
                ejercitosPerdidos.set(1,ejercitosPerdidos.get(1)+1);
            }
        }

        return ejercitosPerdidos;



    }
}
