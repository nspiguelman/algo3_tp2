package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public class Juego {
    private Tablero tablero;
    private ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
    private Fase fase;

    public Juego(ArrayList<String> colorJugadores) throws Exception  {
        for (String unColor : colorJugadores) {
            this.agregarJugador(unColor);
        }
        this.tablero = new Tablero(jugadores);
        this.fase = new FaseUnoColocacionEjercitos();
    }

    public void agregarJugador(String unColor) throws Exception {
        for (Jugador jugador : jugadores) {
            if (jugador.asignarColor().equals(unColor)) {
                throw new Exception("No se puede asignar el mismo color a dos jugadores");
            }
        }
        this.jugadores.add(new Jugador(unColor));
    }

    public ArrayList<Jugador> obtenerJugadores() {
        return jugadores;
    }

    public ArrayList<Pais> obtenerPaisesDeJugador(Jugador unJugador) {
        return unJugador.obtenerPaises();
    }

    public void agregarEjercitos(Jugador unJugador, Pais unPais, int cantidadEjercitos) {
        unJugador.agregarEjercitos(unPais, cantidadEjercitos);
    }

    public void siguienteFase() throws Exception {
        fase = fase.siguienteFase(jugadores);
    }
}