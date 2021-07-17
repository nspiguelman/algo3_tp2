package edu.fiuba.algo3.fase;

import edu.fiuba.algo3.excepciones.ColocarEjercitosException;
import edu.fiuba.algo3.excepciones.SiguienteFaseException;
import edu.fiuba.algo3.modelo.Jugador;

import java.util.ArrayList;

public interface Fase {
    Fase siguienteFase(ArrayList<Jugador> jugadores) throws SiguienteFaseException;
    void validarCantidadEjercitos(int cantidadEjercitos) throws ColocarEjercitosException;
}