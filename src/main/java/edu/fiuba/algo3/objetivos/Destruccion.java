package edu.fiuba.algo3.objetivos;

import edu.fiuba.algo3.modelo.Jugador;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Destruccion implements TipoObjetivo {
    private Jugador jugadorAVencer;

    public Destruccion(ArrayList<TipoObjetivoDeserializer> tipoObjetivoJugador, ArrayList<Jugador> jugadores) {
        List<Jugador> x = jugadores
            .stream()
            .filter(jugador -> tipoObjetivoJugador.get(0).obtenerColor().equals(jugador.obtenerColor()))
            .collect(Collectors.toList());
        if (x.size() == 1) {
            this.jugadorAVencer = x.get(0);
        } else {
            this.jugadorAVencer = buscarElJugadorALaDerecha(jugadores);
        }
    }

    private Jugador buscarElJugadorALaDerecha(ArrayList<Jugador> jugadores) {
        int indice
    }

    public String obtenerTipo() {
        return "Destrucción";
    }

    public boolean validarObjetivo(Jugador unJugador) {
        return !jugadorAVencer.sigueEnJuego();
    }
}