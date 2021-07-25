package edu.fiuba.algo3.excepciones;

public class SiguienteFaseException extends TegException {
    public SiguienteFaseException(int cantidad) {
        super("Para pasar a la siguiente fase cada jugador debe tener " + cantidad + " ejercitos agregados.");
    }
}