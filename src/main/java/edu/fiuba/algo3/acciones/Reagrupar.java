package edu.fiuba.algo3.acciones;

import edu.fiuba.algo3.excepciones.AccionesException;
import edu.fiuba.algo3.excepciones.TegException;
import edu.fiuba.algo3.modelo.Jugador;

public class Reagrupar implements Accion{

    public Colocar siguienteAccion(Jugador unJugador){
        unJugador.setearEjercitosMaximos();
        return new Colocar();
    }

    public int numeroAccion(){
        return 2;
    }

    public void estaEnReagrupar() throws AccionesException {}

    public void estaEnAtacar() throws AccionesException {
        throw new AccionesException("No está en atacar");
    }

    public void estaEnColocar() throws AccionesException { throw new AccionesException("No está en colocar"); }
}
