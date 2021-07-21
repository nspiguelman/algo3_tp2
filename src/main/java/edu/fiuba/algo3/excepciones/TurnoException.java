package edu.fiuba.algo3.excepciones;

public class TurnoException extends TegException {
    public TurnoException(String unColor) {
        super("El jugador " + unColor + " ha intentado realizar un movimiento, pero actualmente no es su turno.");
    }
}
