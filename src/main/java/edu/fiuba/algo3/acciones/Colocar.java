package edu.fiuba.algo3.acciones;

import edu.fiuba.algo3.modelo.Jugador;

public class Colocar implements Accion{
    public Accion siguienteAccion(Jugador unJugador){
        return new Atacar();
    }
    public int numeroAccion(){
        return 3;
    }
}