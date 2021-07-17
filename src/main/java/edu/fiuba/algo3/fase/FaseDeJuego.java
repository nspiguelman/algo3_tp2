package edu.fiuba.algo3.fase;

import edu.fiuba.algo3.excepciones.ColocarEjercitosException;
import edu.fiuba.algo3.excepciones.SiguienteFaseException;
import edu.fiuba.algo3.modelo.Jugador;

import java.util.ArrayList;

public class FaseDeJuego implements Fase {
    public FaseDeJuego() {}

    public Fase siguienteFase(ArrayList<Jugador> jugadores) throws SiguienteFaseException {
        for (Jugador jugador : jugadores) {
            try {
                jugador.validarCantidadEjercitos(5);
            } catch (Exception e) {
                throw new SiguienteFaseException(5);
            }
        }
        return new FaseDosColocacionEjercitos();
    }

    public void validarCantidadEjercitos(int cantidadEjercitos) throws ColocarEjercitosException {

    }
}
