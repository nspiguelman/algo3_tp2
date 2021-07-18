package edu.fiuba.algo3.fase;

import edu.fiuba.algo3.excepciones.ColocarEjercitosException;
import edu.fiuba.algo3.excepciones.SiguienteFaseException;
import edu.fiuba.algo3.modelo.Jugador;
import java.util.ArrayList;

public class FaseDosColocacionEjercitos implements Fase {
    private final int CANTIDAD_EJERCITOS_FASE_DOS = 8;
    private final int MAXIMA_CANTIDAD_EJERCITOS_A_AGREGAR = 3;

    public FaseDosColocacionEjercitos() { }

    public Fase siguienteFase(ArrayList<Jugador> jugadores) throws SiguienteFaseException {
        for (Jugador jugador : jugadores) {
            try {
                jugador.validarCantidadEjercitos(CANTIDAD_EJERCITOS_FASE_DOS);
            } catch (Exception e) {
                throw new SiguienteFaseException(MAXIMA_CANTIDAD_EJERCITOS_A_AGREGAR);
            }
        }
        return new FaseDosColocacionEjercitos();
    }

    public void validarCantidadEjercitos(int cantidadEjercitos) throws ColocarEjercitosException {
        if (cantidadEjercitos > CANTIDAD_EJERCITOS_FASE_DOS) {
            throw new ColocarEjercitosException(MAXIMA_CANTIDAD_EJERCITOS_A_AGREGAR);
        }
    }
}