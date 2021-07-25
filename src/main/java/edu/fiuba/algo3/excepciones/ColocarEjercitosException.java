package edu.fiuba.algo3.excepciones;

public class ColocarEjercitosException extends TegException {
    public ColocarEjercitosException(int cantidad) {
        super("En la fase actual no es posible tener mas de " + cantidad + " ejercitos.");
    }
}