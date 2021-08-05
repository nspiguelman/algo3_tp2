package edu.fiuba.algo3.acciones;

import edu.fiuba.algo3.modelo.Jugador;

public class Atacar implements Accion {
    public Accion siguienteAccion(Jugador unJugador){
        return new Reagrupar();
    }
    public int numeroAccion(){
        return 1;
    }
}
