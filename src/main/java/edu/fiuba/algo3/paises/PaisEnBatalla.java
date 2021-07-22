package edu.fiuba.algo3.paises;

public class PaisEnBatalla implements EstadoBelico {
    private final Pais pais;

    public PaisEnBatalla(Pais pais) {
        this.pais = pais;
    }

    public void matarEjercitos(Pais pais, int cantidadDeEjercitos) {
        pais.reducirEjercitos(cantidadDeEjercitos);
    }
    public PaisEnPaz cambiarEstadoDeBatalla(){
        return new PaisEnPaz(this.pais);
    }

}