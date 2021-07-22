package edu.fiuba.algo3.paises;

import edu.fiuba.algo3.excepciones.PaisNoEstaEnBatallaException;

public interface EstadoBelico {
    void matarEjercitos(Pais pais, int cantidadDeEjercitos) throws PaisNoEstaEnBatallaException, PaisNoEstaEnBatallaException;
}
