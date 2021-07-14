package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public class FaseDosColocacionEjercitos implements Fase {
    private int cantidadEjercitosFaseDos;

    public FaseDosColocacionEjercitos() {
        this.cantidadEjercitosFaseDos = 8;
    }

    public Fase siguienteFase(ArrayList<Jugador> jugadores) throws Exception {
        for (Jugador jugador : jugadores) {
            try {
                jugador.validarCantidadEjercitos(cantidadEjercitosFaseDos);
            } catch (Exception e) {
                throw new Exception("En esta etapa cada jugador debería agregar 3 ejercitos");
            }
        }
        return new FaseDosColocacionEjercitos();
    }
}
