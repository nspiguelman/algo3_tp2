package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public class FaseUnoColocacionEjercitos implements Fase {
    private int cantidadEjercitosFaseUno;
    public FaseUnoColocacionEjercitos() {
        this.cantidadEjercitosFaseUno = 5;
    }

    public Fase siguienteFase(ArrayList<Jugador> jugadores) throws Exception {
        for (Jugador jugador : jugadores) {
            try {
                jugador.validarCantidadEjercitos(cantidadEjercitosFaseUno);
            } catch (Exception e) {
                throw new Exception("En esta etapa cada jugador debería agregar 5 ejercitos");
            }
        }
        return new FaseDosColocacionEjercitos();
    }
}
