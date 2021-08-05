package edu.fiuba.algo3.continente;
import edu.fiuba.algo3.modelo.Jugador;

import java.util.HashMap;

public class Continente {

    private final int cantidadDePaises;
    private final String nombre;
    private final int extras;

    public Continente(String nombre, int cantidadDePaises, int paisesExtras) {
        this.cantidadDePaises = cantidadDePaises;
        this.nombre = nombre;
        this.extras = paisesExtras;
    }

    public boolean todosLosPaisesSonDominados(int paisesDominados) {
        return paisesDominados == this.cantidadDePaises;
    }

    public boolean esConquistadoPor(Jugador unJugador) {
        int paisesDominados = 0;
        for (int i = 0; i< unJugador.obtenerPaises().size(); i++) {
            if (unJugador.obtenerPaises().get(i).obtenerNombreContinente().equals(this.nombre)) {
                paisesDominados += 1;
            }
        }
        return todosLosPaisesSonDominados(paisesDominados);
    }

    public int obtenerExtras(Jugador unJugador) {
        return this.esConquistadoPor(unJugador) ? this.extras : 0;
    }

    public String obtenerNombreContinente() {
        return nombre;
    }

    public int obtenerCantidadDePaises() {
        return cantidadDePaises;
    }
}
