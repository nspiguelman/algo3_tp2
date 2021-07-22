package edu.fiuba.algo3.paises;

public class PaisEnBatalla implements EstadoBelico {


    public PaisEnBatalla() {

    }

    public void matarEjercitos(Pais pais, int cantidadDeEjercitos) {
        pais.reducirEjercitos(cantidadDeEjercitos);
    }
    public PaisEnPaz finalizarBatalla(){
        return new PaisEnPaz();
    }

}