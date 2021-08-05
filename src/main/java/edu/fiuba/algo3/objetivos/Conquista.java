package edu.fiuba.algo3.objetivos;

import edu.fiuba.algo3.continente.Continente;
import edu.fiuba.algo3.modelo.Jugador;

import java.util.ArrayList;
import java.util.HashMap;

public class Conquista implements TipoObjetivo {
    private ArrayList<Continente> continentes = new ArrayList<>();

    public Conquista(ArrayList<TipoObjetivoDeserializer> continentesObjetivos) {
        for (TipoObjetivoDeserializer continente : continentesObjetivos) {
            this.continentes.add(new Continente(continente.obtenerContinente(), continente.obtenerCantidadDePaises(), 1));
        }
    }

    public String obtenerTipo() { return "Conquista"; }

    public boolean validarObjetivo(Jugador unJugador) {
        return continentes
            .stream()
            .allMatch(objetivoContinente -> objetivoContinente.obtenerNombreContinente().equals("Limitrofe") ?
                unJugador.tieneNPaisesLimitrofesEntreSi(objetivoContinente.obtenerCantidadDePaises()) :
                unJugador.tieneNPaisesEnContinente(objetivoContinente.obtenerCantidadDePaises(), objetivoContinente.obtenerNombreContinente())
            );
    }
}