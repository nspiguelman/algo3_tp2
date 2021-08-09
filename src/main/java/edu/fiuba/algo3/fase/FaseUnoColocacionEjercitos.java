package edu.fiuba.algo3.fase;

import edu.fiuba.algo3.acciones.Accion;
import edu.fiuba.algo3.acciones.Colocar;
import edu.fiuba.algo3.excepciones.AccionesException;
import edu.fiuba.algo3.excepciones.ColocarEjercitosException;
import edu.fiuba.algo3.excepciones.SiguienteFaseException;
import edu.fiuba.algo3.excepciones.TegException;
import edu.fiuba.algo3.modelo.Jugador;
import java.util.ArrayList;

public class FaseUnoColocacionEjercitos implements Fase {
    private final int cantidadEjercitosFaseUno;
    private Accion accionActual = new Colocar();


    public FaseUnoColocacionEjercitos() {
        this.cantidadEjercitosFaseUno = 5;
    }

    public Fase siguienteFase(ArrayList<Jugador> jugadores) throws SiguienteFaseException {
        for (Jugador jugador : jugadores) {
            try {
                this.validarCantidadEjercitos(jugador);
            } catch (Exception e) {
                throw new SiguienteFaseException(cantidadEjercitosFaseUno);
            }
        }
        return new FaseDosColocacionEjercitos();
    }

    public void validarCantidadEjercitos(Jugador unJugador) throws ColocarEjercitosException {
        int cantidadEjercitos = unJugador.obtenerCantidadDeEjercitos();
        if (cantidadEjercitos != cantidadEjercitosFaseUno) {
            throw new ColocarEjercitosException(cantidadEjercitosFaseUno);
        }
    }

    @Override
    public int ejercitosPorFase(Jugador unJugador) {
        unJugador.setearEjercitosMaximos();
        return 5;
    }

    @Override
    public void siguienteAccion(Jugador unJugador) {

    }
    @Override
    public int accionActual(){
        return accionActual.numeroAccion();
    };

    @Override
    public void reiniciarAcciones() {

    }

    @Override
    public String obtenerFase(){
        return "ColocacionUno";
    }

    public void estaEnReagrupar() throws AccionesException { this.accionActual.estaEnReagrupar(); }
    public void estaEnAtacar() throws AccionesException { this.accionActual.estaEnAtacar(); }
    public void estaEnColocar() throws AccionesException { this.accionActual.estaEnColocar(); }
}

