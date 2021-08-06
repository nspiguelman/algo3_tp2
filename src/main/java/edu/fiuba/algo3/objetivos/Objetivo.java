package edu.fiuba.algo3.objetivos;

import edu.fiuba.algo3.modelo.Jugador;
import java.util.ArrayList;


public class Objetivo {
    private String descripcion;
    private TipoObjetivo tipo;

    public Objetivo(String descripcion, String tipoObjetivo, ArrayList<TipoObjetivoDeserializer> objetivo, ArrayList<Jugador> jugadores) {
        this.descripcion = descripcion;
        this.tipo = tipoObjetivo.equals("Conquista") ? new Conquista(objetivo) : new Destruccion(objetivo, jugadores);
    }

    public String obtenerDescripcion() { return descripcion; }
    public String obtenerTipo() {
        return tipo.obtenerTipo();
    }
    public boolean validarObjetivo(Jugador unJugador) {
        return tipo.validarObjetivo(unJugador);
    }
    public void actualizarObjetivo(Jugador unJugador) { tipo.actualizarObjetivo(unJugador); }
}