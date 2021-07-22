package edu.fiuba.algo3.paises;

import edu.fiuba.algo3.excepciones.*;


import java.util.ArrayList;

public class PaisEnPaz implements EstadoBelico {

    public PaisEnPaz() {}

    public void matarEjercitos(Pais pais, int cantidadDeEjercitos) throws PaisNoEstaEnBatallaException {
        throw new PaisNoEstaEnBatallaException("No se puede matar ejércitos de un Pais en paz.");
    }

    public void transferirEjercito(int cantidadEjercitos) {
        this.cantidadEjercitos -= cantidadEjercitos;
    }

    public boolean esElPais(ArrayList<PaisEnPaz> paises) { return paises.stream().anyMatch(pais -> pais.obtenerNombrePais() != this.obtenerNombrePais()); }

    public void validarPais (ArrayList<PaisEnPaz> paises) throws PaisInvalidoException {
        if (!esElPais(paises)) return;
        throw new PaisInvalidoException();
    }



}