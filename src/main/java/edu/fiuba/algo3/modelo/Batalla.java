package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.paises.Pais;
import java.util.ArrayList;

public class Batalla {

    public Batalla() {
    }

    public void batallar(Jugador atacante, Jugador defensor) throws Exception {
        Pais paisAtacante = atacante.paisEnBatalla();
        Pais paisDefensor = defensor.paisEnBatalla();
        Dados dado = new Dados();
        ArrayList<Integer> ejercitosPerdidos = dado.tirar(atacante.obtenerEjercitosEnBatalla(), defensor.obtenerEjercitosEnBatalla());
        atacante.matarEjercito(paisAtacante, ejercitosPerdidos.get(0));
        defensor.matarEjercito(paisDefensor, ejercitosPerdidos.get(1));

        if (defensor.obtenerEjercitosEnBatalla() == 0) {
            atacante.conquistar(defensor.paisEnBatalla());
            defensor.eliminarPaisEnBatalla();
        }
    }
}