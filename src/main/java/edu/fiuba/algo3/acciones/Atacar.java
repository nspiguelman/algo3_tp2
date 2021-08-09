package edu.fiuba.algo3.acciones;

import edu.fiuba.algo3.excepciones.AccionesException;
import edu.fiuba.algo3.excepciones.TegException;
import edu.fiuba.algo3.modelo.Jugador;

public class Atacar implements Accion {
    public Reagrupar siguienteAccion(Jugador unJugador){
        return new Reagrupar();
    }

    public int numeroAccion(){
        return 1;
    }

    @Override
    public void estaEnReagrupar() throws AccionesException { throw new AccionesException("No está en reagrupar"); }
    public void estaEnAtacar() throws AccionesException {}
    public void estaEnColocar() throws AccionesException { throw new AccionesException("No está en colocar"); }
}
