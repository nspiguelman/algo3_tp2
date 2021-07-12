package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.io.FileNotFoundException;

public class Juego {
    private Tablero tablero;
    private ArrayList<Jugador> jugadores = new ArrayList<Jugador>();

    public Juego(ArrayList<String> colorJugadores) throws FileNotFoundException {
        for (String unColor : colorJugadores) {
            this.jugadores.add(new Jugador(unColor));
        }
        this.tablero = new Tablero(jugadores);
    }

    public ArrayList<Jugador> jugadores() {
        return jugadores;
    }
}