package edu.fiuba.algo3.paises;

import edu.fiuba.algo3.excepciones.*;

public class PaisEnPaz implements EstadoBelico {
    private Pais pais;

    public PaisEnPaz(Pais pais) {
        this.pais = pais;
    }

    public void matarEjercitos(Pais pais, int cantidadDeEjercitos) throws PaisNoEstaEnBatallaException {
        throw new PaisNoEstaEnBatallaException("No se puede matar ejércitos de un Pais en paz.");
    }

    public PaisEnBatalla cambiarEstadoDeBatalla(){
        return new PaisEnBatalla(this.pais);
    }
}