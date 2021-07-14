package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public class FaseDeJuego implements Fase {
    public FaseDeJuego() {}

    public Fase siguienteFase(ArrayList<Jugador> jugadores) throws Exception {
        for (Jugador jugador : jugadores) {
            try {
                jugador.validarCantidadEjercitos(5);
            } catch (Exception e) {
                throw new Exception("En esta etapa cada jugador debería tener 5 ejercitos");
            }
        }
        return new FaseDosColocacionEjercitos();
    }
}
