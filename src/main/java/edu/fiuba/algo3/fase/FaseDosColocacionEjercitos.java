package edu.fiuba.algo3.fase;

import edu.fiuba.algo3.excepciones.ColocarEjercitosException;
import edu.fiuba.algo3.excepciones.SiguienteFaseException;
import edu.fiuba.algo3.modelo.Jugador;
import java.util.ArrayList;

public class FaseDosColocacionEjercitos implements Fase {
    private final int cantidadEjercitosFaseDos = 8;
    private final int maximaCantidadDeEjercitosAAgregar = 3;

    public FaseDosColocacionEjercitos() { }

    public Fase siguienteFase(ArrayList<Jugador> jugadores) throws SiguienteFaseException {
        for (Jugador jugador : jugadores) {
            try {
                jugador.validarCantidadEjercitos(cantidadEjercitosFaseDos);
            } catch (Exception e) {
                throw new SiguienteFaseException(maximaCantidadDeEjercitosAAgregar);
            }
        }
        return new FaseDosColocacionEjercitos();
    }

    public void validarCantidadEjercitos(int cantidadEjercitos) throws ColocarEjercitosException {
        if (cantidadEjercitos > cantidadEjercitosFaseDos) {
            throw new ColocarEjercitosException(maximaCantidadDeEjercitosAAgregar);
        }
    }
}