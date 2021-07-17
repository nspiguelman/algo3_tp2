package edu.fiuba.algo3.excepciones;

public class PaisNoPerteneceAJugadorException extends TegException {
    public PaisNoPerteneceAJugadorException(String nombreDePais, String nombreDeJugador) {
        super(nombreDePais + " no pertenece al jugador: " + nombreDeJugador);
    }
}
