package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public interface Fase {
    Fase siguienteFase(ArrayList<Jugador> jugadores) throws Exception;
}