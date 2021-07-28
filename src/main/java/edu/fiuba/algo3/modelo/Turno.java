package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public class Turno {
    int turnoActual = 1;
    private Jugador jugadorActual;
    private final ArrayList<Jugador> jugadores;

    public Turno(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
        jugadorActual = jugadores.get(0);
    }

    public void pasarTurno() {
        jugadorActual = jugadores.get(turnoActual%jugadores.size());
        turnoActual++;
    }

    public Jugador turnoActual() {
        return jugadorActual;
    }

    public boolean esElTurnoDe(String unColor) {
        return jugadorActual.esElJugador(unColor);
    }


}
