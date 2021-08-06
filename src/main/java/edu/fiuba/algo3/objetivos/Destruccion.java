package edu.fiuba.algo3.objetivos;

import edu.fiuba.algo3.modelo.Jugador;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Destruccion implements TipoObjetivo {
    private Jugador jugadorAVencer;
    private ArrayList<Jugador> jugadoresAVencer;

    public Destruccion(ArrayList<TipoObjetivoDeserializer> tipoObjetivoJugador, ArrayList<Jugador> jugadores) {
        List<Jugador> jugadoresFiltrados = jugadores
            .stream()
            .filter(jugador -> tipoObjetivoJugador.get(0).obtenerColor().equals(jugador.obtenerColor()))
            .collect(Collectors.toList());
        this.jugadorAVencer = (jugadoresFiltrados.size() == 1) ? jugadoresFiltrados.get(0) : null;
        this.jugadoresAVencer = jugadores;
    }

    public void actualizarObjetivo(Jugador unJugadorConObjetivo) {
        if (jugadorAVencer != null) return;
        int indice = jugadoresAVencer.indexOf(unJugadorConObjetivo);
        jugadorAVencer = (indice == (jugadoresAVencer.size() - 1)) ? jugadoresAVencer.get(0) : jugadoresAVencer.get(indice + 1);
    }

    public String obtenerTipo() {
        return "Destrucción";
    }

    public boolean validarObjetivo(Jugador unJugador) {
        if (jugadorAVencer == null) return false;
        return !jugadorAVencer.sigueEnJuego();
    }
}