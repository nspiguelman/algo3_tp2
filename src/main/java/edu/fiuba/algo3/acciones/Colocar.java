package edu.fiuba.algo3.acciones;

import edu.fiuba.algo3.excepciones.AccionesException;
import edu.fiuba.algo3.excepciones.TegException;
import edu.fiuba.algo3.modelo.Jugador;

public class Colocar implements Accion {
    public Accion siguienteAccion(Jugador unJugador){
        return new Atacar();
    }
    public int numeroAccion(){
        return 3;
    }

    public void estaEnReagrupar() throws AccionesException { throw new AccionesException("No está en reagrupar"); }
    public void estaEnAtacar() throws AccionesException { throw new AccionesException("No está en atacar"); }
    public void estaEnColocar() throws AccionesException {}
}