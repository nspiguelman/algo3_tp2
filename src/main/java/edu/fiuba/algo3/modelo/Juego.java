package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.io.FileNotFoundException;

public class Juego {
    private Tablero tablero;
    private ArrayList<Jugador> jugadores = new ArrayList<Jugador>();

    public Juego(ArrayList<String> colorJugadores) throws Exception  {
        for (String unColor : colorJugadores) {
            this.agregarJugador(unColor);
        }
        this.tablero = new Tablero(jugadores);
    }

    public void agregarJugador(String unColor) throws Exception {
        for (Jugador jugador : jugadores) {
            if (jugador.color().equals(unColor)) {
                throw new Exception("No se puede asignar el mismo color a dos jugadores");
            }
        }
        this.jugadores.add(new Jugador(unColor));
    }

    public ArrayList<Jugador> jugadores() {
        return jugadores;
    }

    public ArrayList<Pais> obtenerPaisesDeJugador(Jugador unJugador) {
        return unJugador.paises();
    }

    public void asignarEjercitoAJugador(Jugador unJugador, Pais unPais) {
        unJugador.ejercito(unPais);
    }
}