package edu.fiuba.algo3.modelo;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Collections;

public class Dados {

    public ArrayList<Integer> tirar(int fichasAtacante, int fichasDefensor) throws Exception {
        ArrayList<Integer> ejercitosPerdidos = new ArrayList<>();
        ArrayList<Integer> atacanteResultados = new ArrayList<>();
        ArrayList<Integer> defensorResultados = new ArrayList<>();

        if (fichasAtacante == 1){
            throw new Exception("El atacante tiene una sola ficha");

        } else if (fichasAtacante > 4) {
            fichasAtacante = 4;
        }

        if (fichasDefensor > 3) {
            fichasDefensor = 3;
        }

        for (int i = 0; i < fichasAtacante - 1; i = i + 1) {
            atacanteResultados.add((int)(Math.random() * 6 + 1));
        }

        for (int i = 0; i < fichasDefensor; i = i + 1) {
            defensorResultados.add((int)(Math.random() * 6 + 1));
        }

        Collections.sort(atacanteResultados, Collections.reverseOrder());
        Collections.sort(defensorResultados, Collections.reverseOrder());

        int comparaciones = 0;
        if (atacanteResultados.size() < defensorResultados.size()) {
            comparaciones = atacanteResultados.size();

        } else {
            comparaciones = defensorResultados.size();
        }
        ejercitosPerdidos.add(0);
        ejercitosPerdidos.add(0);

        for (int i = 0; i <= comparaciones - 1 ; i = i+1) {
            if (atacanteResultados.get(i) <= defensorResultados.get(i)) {
                ejercitosPerdidos.set(0, ejercitosPerdidos.get(0) + 1);

            } else {
                ejercitosPerdidos.set(1,ejercitosPerdidos.get(1)+1);
            }
        }

        return ejercitosPerdidos;
    }

}
