package edu.fiuba.algo3.acciones;

import edu.fiuba.algo3.excepciones.AccionesException;
import edu.fiuba.algo3.excepciones.TegException;
import edu.fiuba.algo3.modelo.Jugador;

public interface Accion {

    Accion siguienteAccion(Jugador unJugador);
    int numeroAccion();

    void estaEnReagrupar() throws AccionesException;
    void estaEnAtacar() throws AccionesException;
    void estaEnColocar() throws AccionesException;
}
