package edu.fiuba.algo3.objetivos;

import edu.fiuba.algo3.modelo.Jugador;

import java.util.ArrayList;

public interface TipoObjetivo {
    boolean validarObjetivo(Jugador unJugador);
    boolean estaVigente(Jugador unJugador);
    ArrayList<TipoObjetivoDeserializer> obtenerObjetivo();
}

