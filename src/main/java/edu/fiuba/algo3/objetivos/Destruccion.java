package edu.fiuba.algo3.objetivos;

import edu.fiuba.algo3.modelo.Jugador;

import java.util.ArrayList;

public class Destruccion implements TipoObjetivo {
    private ArrayList<TipoObjetivoDeserializer> objetivo;

    public Destruccion(ArrayList<TipoObjetivoDeserializer> objetivo) { this.objetivo = objetivo; }

    public ArrayList<TipoObjetivoDeserializer> obtenerObjetivo() { return objetivo; }
    public boolean validarObjetivo(Jugador unJugador) {
        return true;
    }
    public boolean estaVigente(Jugador unJugador) { return true; }
}