package edu.fiuba.algo3.acciones;

import edu.fiuba.algo3.modelo.Jugador;

public class Reagrupar implements Accion{

    public Accion siguienteAccion(Jugador unJugador){
        unJugador.setearEjercitosMaximos();
        return new Colocar();
    }

    public int numeroAccion(){
        return 2;
    }
}
