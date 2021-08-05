package edu.fiuba.algo3.fase;

import edu.fiuba.algo3.excepciones.ColocarEjercitosException;
import edu.fiuba.algo3.excepciones.SiguienteFaseException;
import edu.fiuba.algo3.excepciones.TegException;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.acciones.*;

import java.util.ArrayList;

public interface Fase {
    Fase siguienteFase(ArrayList<Jugador> jugadores) throws TegException;
    void validarCantidadEjercitos(Jugador unJugador) throws TegException;

    int ejercitosPorFase(Jugador unJugador);
    void siguienteAccion(Jugador unJugador);
    int accionActual();
    void reiniciarAcciones();
    String obtenerFase();
}