package edu.fiuba.algo3.fase;

import edu.fiuba.algo3.excepciones.SiguienteFaseException;
import edu.fiuba.algo3.excepciones.TegException;
import edu.fiuba.algo3.modelo.Jugador;

import java.util.ArrayList;

public class FaseDeJuego implements Fase {
    private int accionActual = 1; // 1 - Ataque... 2 - Reagrupar.... 3 - Colocar Ejercitos

    public FaseDeJuego() {}

    public Fase siguienteFase(ArrayList<Jugador> jugadores) throws SiguienteFaseException {
        for (Jugador jugador : jugadores) {
            try {
                this.validarCantidadEjercitos(jugador);
            } catch (Exception e) {
                throw new SiguienteFaseException(5);
            }
        }
        return new FaseDosColocacionEjercitos();
    }

    public void validarCantidadEjercitos(Jugador unJugador) throws TegException {

    }

    @Override
    public int ejercitosPorFase(Jugador unJugador) {
        int cantidadPaisesJugador = unJugador.obtenerPaises().size();
        if ((cantidadPaisesJugador%2) != 0) {
            return (cantidadPaisesJugador-1)/2;
        }
        return cantidadPaisesJugador/2;
    }

    @Override
    public void siguienteAccion() {
        accionActual++;
    }

    @Override
    public int accionActual() {
        return accionActual;
    }
    @Override
    public void reiniciarAcciones(){
        accionActual = 1;
    }
}
