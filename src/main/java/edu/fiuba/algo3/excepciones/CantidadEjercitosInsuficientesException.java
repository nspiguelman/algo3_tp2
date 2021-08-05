package edu.fiuba.algo3.excepciones;

public class CantidadEjercitosInsuficientesException extends TegException{
    public CantidadEjercitosInsuficientesException(){
        super("No posee los suficientes ejercitos para realizar una reagrupación.");
    }
}
