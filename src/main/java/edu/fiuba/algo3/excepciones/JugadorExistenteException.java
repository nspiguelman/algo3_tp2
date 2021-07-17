package edu.fiuba.algo3.excepciones;

public class JugadorExistenteException extends TegException {
    public JugadorExistenteException(String unColor) {
        super("Ya existe un jugador con el color: " + unColor);
    }
}
