package edu.fiuba.algo3.excepciones;

public class AccionesException extends TegException {
    public AccionesException() {
        super("No posee las acciones necesarias para realizar este movimiento");
    }
}
