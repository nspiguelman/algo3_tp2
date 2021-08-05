package edu.fiuba.algo3.objetivos;

import edu.fiuba.algo3.continente.Continente;
import edu.fiuba.algo3.modelo.Jugador;

import java.util.ArrayList;
import java.util.HashMap;


public class Objetivo {
    private String descripcion;
    private TipoObjetivo tipo;

    public Objetivo(String descripcion, String tipoObjetivo, ArrayList<TipoObjetivoDeserializer> objetivo, HashMap<String, HashMap> continentes, ArrayList<Jugador> jugadores) {
        this.descripcion = descripcion;
        this.tipo = tipoObjetivo.equals("Conquista") ? new Conquista(objetivo) : new Destruccion(objetivo, jugadores);
    }

    public String obtenerDescripcion() {
        return descripcion;
    }
    public String obtenerTipo() { return tipo.obtenerTipo(); }
    public boolean validarObjetivo(Jugador unJugador) {
        return tipo.validarObjetivo(unJugador);
    }





    // public String obtenerContinente(TipoObjetivoDeserializer objetivo) { return objetivo.obtenerContinente(); }
    // public int obtenerCantidadDePaises(TipoObjetivoDeserializer objetivo) { return objetivo.obtenerCantidadDePaises(); }
    // public String obtenerColor(TipoObjetivoDeserializer objetivo) { return objetivo.obtenerColor(); };
}