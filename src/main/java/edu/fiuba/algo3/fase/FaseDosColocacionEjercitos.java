package edu.fiuba.algo3.fase;

import edu.fiuba.algo3.acciones.*;
import edu.fiuba.algo3.excepciones.ColocarEjercitosException;
import edu.fiuba.algo3.excepciones.SiguienteFaseException;
import edu.fiuba.algo3.excepciones.TegException;
import edu.fiuba.algo3.modelo.Jugador;
import java.util.ArrayList;

public class FaseDosColocacionEjercitos implements Fase {
    private final int cantidadEjercitosFaseDos;
    private Accion accionActual= new Colocar();

    public FaseDosColocacionEjercitos() { this.cantidadEjercitosFaseDos = 8; }

    public Fase siguienteFase(ArrayList<Jugador> jugadores) throws SiguienteFaseException {
        for (Jugador jugador : jugadores) {
            try {
                this.validarCantidadEjercitos(jugador);
            } catch (Exception e) {
                throw new SiguienteFaseException(cantidadEjercitosFaseDos);
            }
        }
        return new FaseDeJuego();
    }

    public void validarCantidadEjercitos(Jugador unJugador) throws TegException {
        int cantidadEjercitos = unJugador.obtenerCantidadDeEjercitos();
        if (cantidadEjercitos != cantidadEjercitosFaseDos) {
            throw new ColocarEjercitosException(cantidadEjercitosFaseDos);
        }
    }
    @Override
    public int ejercitosPorFase(Jugador unJugador) {
        unJugador.setearEjercitosMaximos();
        return 3;
    }

    @Override
    public void siguienteAccion(Jugador unJugador) {

    }

    @Override
    public int accionActual() {
        return accionActual.numeroAccion();
    }

    @Override
    public void reiniciarAcciones() {

    }
}