package edu.fiuba.algo3.fase;

import edu.fiuba.algo3.excepciones.ColocarEjercitosException;
import edu.fiuba.algo3.excepciones.SiguienteFaseException;
import edu.fiuba.algo3.modelo.Jugador;
import java.util.ArrayList;

public class FaseUnoColocacionEjercitos implements Fase {
    private final int cantidadEjercitosFaseUno;
    private int accionActual=1;


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
    public int ejercitosPorFase(Jugador unJugador){
        unJugador.setearEjercitosMaximos();
        return 5;
    }

    @Override
    public void siguienteAccion(Jugador unJugador) {

    }
    @Override
    public int accionActual(){
        return 3;
    }

    @Override
    public void reiniciarAcciones(){

    }

}

/* Dos validaciones... Para pasar de fase

    siguienteFase()   -> debo verificar que la cantidad de ejercitos sea IGUAL a la cantidad necesaria por fase

    jugador.agregarEjercitos()     -> debo verificar que el jugador no se pase de los ejercitos maximos por fase

*

*/

