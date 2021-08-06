package edu.fiuba.algo3.fase;

import edu.fiuba.algo3.acciones.*;
import edu.fiuba.algo3.excepciones.ColocarEjercitosException;
import edu.fiuba.algo3.excepciones.SiguienteFaseException;
import edu.fiuba.algo3.excepciones.TegException;
import edu.fiuba.algo3.modelo.Jugador;
import java.util.ArrayList;

public class FaseDeJuego implements Fase {
    private Accion accionActual = new Atacar(); // 1 - Ataque... 2 - Reagrupar.... 3 - Colocar Ejercitos

    public FaseDeJuego() {}

    public Fase siguienteFase(ArrayList<Jugador> jugadores) throws SiguienteFaseException {
        for (Jugador jugador : jugadores) {
            try {
                this.validarCantidadEjercitos(jugador);
            } catch (Exception e) {
                throw new SiguienteFaseException(5);
            }
        }
        return new FaseDosColocacionEjercitos();
    }

    public void validarCantidadEjercitos(Jugador unJugador) throws TegException {
        int ejercitosJugador = unJugador.obtenerCantidadTotalDeEjercitos();
        int diferencia = unJugador.obtenerEjercitosMaximos() + this.ejercitosPorFase(unJugador) - 1 - ejercitosJugador;

        if (diferencia >= 0) {
            throw new ColocarEjercitosException(this.ejercitosPorFase(unJugador));
        }
    }

    @Override
    public int ejercitosPorFase(Jugador unJugador) {
        int extras = unJugador.obtenerEjercitosExtraAColocar();
        int cantidadPaisesJugador = unJugador.obtenerPaises().size();
        int resto;
        resto = cantidadPaisesJugador%2;
        extras += unJugador.obtenerCanjeActual();
        return ((cantidadPaisesJugador-resto)/2) + extras;
    }

    @Override
    public void siguienteAccion(Jugador unJugador) {
        this.accionActual = accionActual.siguienteAccion(unJugador);
    }

    @Override
    public int accionActual() {
        return accionActual.numeroAccion();
    }

    @Override
    public void reiniciarAcciones() {
        accionActual = new Atacar();
    }

    @Override
    public String obtenerFase(){
        return "Juego";
    }
}
