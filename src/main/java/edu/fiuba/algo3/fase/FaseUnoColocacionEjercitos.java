package edu.fiuba.algo3.fase;

import edu.fiuba.algo3.excepciones.ColocarEjercitosException;
import edu.fiuba.algo3.excepciones.SiguienteFaseException;
import edu.fiuba.algo3.modelo.Jugador;
import java.util.ArrayList;

public class FaseUnoColocacionEjercitos implements Fase {
    private int cantidadEjercitosFaseUno;
    public FaseUnoColocacionEjercitos() {
        this.cantidadEjercitosFaseUno = 5;
    }

    public Fase siguienteFase(ArrayList<Jugador> jugadores) throws SiguienteFaseException {
        for (Jugador jugador : jugadores) {
            try {
                jugador.validarCantidadEjercitos(cantidadEjercitosFaseUno);
            } catch (Exception e) {
                throw new SiguienteFaseException(cantidadEjercitosFaseUno);
            }
        }
        return new FaseDosColocacionEjercitos();
    }

    public void validarCantidadEjercitos(int cantidadEjercitos) throws ColocarEjercitosException {
        if (cantidadEjercitos > cantidadEjercitosFaseUno) {
            throw new ColocarEjercitosException(cantidadEjercitosFaseUno);
        }
    }
}
