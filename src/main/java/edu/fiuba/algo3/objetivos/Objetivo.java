package edu.fiuba.algo3.objetivos;

import edu.fiuba.algo3.modelo.Jugador;

import java.util.ArrayList;

public class Objetivo {
    private String descripcion;
    private TipoObjetivo tipo;
    private Boolean estaVigente;

    public Objetivo(String descripcion, String tipo, ArrayList<TipoObjetivoDeserializer> objetivo) {
        this.descripcion = descripcion;
        this.tipo = tipo == "Conquista" ? new Conquista(objetivo) : new Destruccion(objetivo);
        this.estaVigente = true;
    }

    public String obtenerDescripcion() {
        return descripcion;
    }
    public ArrayList<TipoObjetivoDeserializer> obtenerObjetivo() { return tipo.obtenerObjetivo(); }
    public String obtenerContinente(TipoObjetivoDeserializer objetivo) { return objetivo.obtenerContinente(); }
    public int obtenerCantidadDePaises(TipoObjetivoDeserializer objetivo) { return objetivo.obtenerCantidadDePaises(); }
    public String obtenerColor(TipoObjetivoDeserializer objetivo) { return objetivo.obtenerColor(); };

    // Cuando se valida Conquista el jugador es el mismo.
    // Cuando se valida Destrucción el jugador es el oponente.
    public boolean validarObjetivo(Jugador unJugador) {
        if (estaVigente) return tipo.validarObjetivo(unJugador);
        return false;
    }

    public void validarVigencia(Jugador unJugador) {
        estaVigente = tipo.estaVigente(unJugador);
    }
}