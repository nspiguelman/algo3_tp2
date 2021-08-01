package edu.fiuba.algo3.acciones;

import edu.fiuba.algo3.modelo.Jugador;

public interface Accion {

    public Accion siguienteAccion(Jugador unJugador);
    public int numeroAccion();


}
