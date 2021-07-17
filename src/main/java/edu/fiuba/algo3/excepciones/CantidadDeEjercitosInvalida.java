package edu.fiuba.algo3.excepciones;

public class CantidadDeEjercitosInvalida extends TegException {
    public CantidadDeEjercitosInvalida(int cantidadEsperada, int cantidadActual) {
        super("El jugador deberia tener " + cantidadEsperada + " ejercitos. Debe agregar " + (cantidadEsperada - cantidadActual) + " ejercitos para poder continuar.");
    }
}