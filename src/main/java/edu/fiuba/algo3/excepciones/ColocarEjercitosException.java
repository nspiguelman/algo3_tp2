package edu.fiuba.algo3.excepciones;

public class ColocarEjercitosException extends TegException {
    public ColocarEjercitosException(int cantidad) {
        super("No se puede agregar mas de " + cantidad + " ejercitos en la actual fase de colocación");
    }
}