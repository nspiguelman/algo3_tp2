package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public class Batalla {
    private Jugador atacante;
    private Jugador defensor;

    public Batalla(){
    }

    public void batallar(Jugador atacante, Jugador defensor) throws Exception{

        ArrayList<Integer> ejercitosPerdidos = new ArrayList<Integer>();
        Dados dado = new Dados();
        ejercitosPerdidos = dado.tirar(atacante.obtenerEjercitosEnBatalla(), defensor.obtenerEjercitosEnBatalla());
        atacante.matarEjercito(ejercitosPerdidos.get(0));
        defensor.matarEjercito(ejercitosPerdidos.get(1));

        if (defensor.obtenerEjercitosEnBatalla() == 0){
            atacante.conquistar(defensor.paisEnBatalla());
            defensor.eliminarPais();
        }
    }
}