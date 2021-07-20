package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.excepciones.*;
import edu.fiuba.algo3.fase.Fase;
import edu.fiuba.algo3.fase.FaseUnoColocacionEjercitos;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Juego {
    private Tablero tablero;
    private ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
    private Fase fase;
    private Turno turno;

    public Juego(ArrayList<String> colorJugadores) throws FileNotFoundException, TegException {
        for (String unColor : colorJugadores) {
            this.agregarJugador(unColor);
        }
        this.tablero = new Tablero(jugadores);
        this.fase = new FaseUnoColocacionEjercitos();
        this.turno = new Turno(jugadores);
    }

    public void agregarJugador(String unColor) throws TegException {
        boolean jugadorRepetido = jugadores.stream().anyMatch(jugador -> jugador.obtenerColor().equals(unColor));
        if (jugadorRepetido) {
            throw new JugadorExistenteException(unColor);
        }
        this.jugadores.add(new Jugador(unColor));
    }

    public void siguienteTurno(){
        turno.pasarTurno();
    }

    public ArrayList<Jugador> obtenerJugadores() {
        return jugadores;
    }

    public ArrayList<Pais> obtenerPaisesDeJugador(Jugador unJugador) {
        return unJugador.obtenerPaises();
    }

    public void agregarEjercitos(Jugador unJugador, Pais unPais, int cantidadEjercitos) throws TegException {
        verificacionDeEjercitos(unJugador, cantidadEjercitos);
        unJugador.agregarEjercitos(unPais, cantidadEjercitos);
    }

    public void verificacionDeEjercitos(Jugador unJugador, int cantidadEjercitos) throws TegException {
        int cantidadEjercitosPorFase = fase.ejercitosPorFase();
        unJugador.validarCantidadEjercitos(cantidadEjercitos, cantidadEjercitosPorFase);
    }

    public void siguienteFase() throws TegException {
        fase = fase.siguienteFase(jugadores);
    }

    public Jugador esElTurnoDe() {
        return turno.turnoActual();
    }
}