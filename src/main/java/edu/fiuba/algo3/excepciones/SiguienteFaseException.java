package edu.fiuba.algo3.excepciones;

public class SiguienteFaseException extends TegException {
    public SiguienteFaseException(int cantidad) {
        super("En la fase actual cada jugador debe agregar " + cantidad + " ejercitos");
    }
}