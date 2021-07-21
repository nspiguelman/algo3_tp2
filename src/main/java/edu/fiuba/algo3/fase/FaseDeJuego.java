package edu.fiuba.algo3.fase;

import edu.fiuba.algo3.excepciones.ColocarEjercitosException;
import edu.fiuba.algo3.excepciones.SiguienteFaseException;
import edu.fiuba.algo3.excepciones.TegException;
import edu.fiuba.algo3.modelo.Jugador;

import java.util.ArrayList;

public class FaseDeJuego implements Fase {
    private int movimientoActual = 1;

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
    public int ejercitosPorFase() {
        return 99;
    }

    @Override
    public void siguienteMovimiento() {
        movimientoActual++;
    }

    @Override
    public int movimientoActual() {
        return movimientoActual;
    }
    @Override
    public void reiniciarMovimientos(){
        movimientoActual = 1;
    }
}
