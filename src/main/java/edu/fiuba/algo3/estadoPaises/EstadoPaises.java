package edu.fiuba.algo3.estadoPaises;

import edu.fiuba.algo3.excepciones.*;
import edu.fiuba.algo3.paises.Pais;
import edu.fiuba.algo3.paises.PaisEnPaz;
import edu.fiuba.algo3.paises.PaisEnBatalla;

import java.util.ArrayList;

public class EstadoPaises {

    private final ArrayList<Pais> paises;
    private final PaisEnBatalla paisEnBatalla;

    public EstadoPaises() {
        this.paisEnBatalla = new EstadoBatalla();
        this.paises = new ArrayList<>();
    }

    public void agregarPais(Pais unPais) {
        paises.add(unPais);
    }
    public ArrayList<Pais> obtenerPaises() {
        return paises;
    }

    public int obtenerCantidadTotalDeEjercitos() {
        return paises.stream()
            .map(pais -> pais.obtenerEjercitos())
            .reduce(0, (subtotal, element) -> subtotal + element);
    }

    public int obtenerCantidadDeEjercitosAgregados() {
        return obtenerCantidadTotalDeEjercitos() - paises.size();
    }

    public void agregarEjercitos(Pais unPais, int cantidadDeEjercitos) throws PaisInvalidoException {
        unPais.validarPais(paises);
        unPais.agregarEjercitos(cantidadDeEjercitos);
    }

    public void validarCantidadEjercitos(int ejercitosASumar, int ejercitosPorFase) throws ColocarEjercitosException {
        int ejercitosDeJugador = this.obtenerCantidadDeEjercitosAgregados() + ejercitosASumar;
        if (ejercitosDeJugador > ejercitosPorFase) {
            throw new ColocarEjercitosException(ejercitosPorFase);
        }
    }

    public boolean tieneElPais(Pais unPais) {
        try {
            unPais.validarPais(paises);
            return true;
        } catch (PaisInvalidoException e) {
            return false;
        }
    }

    public void eliminarPaisEnBatalla() {
        paisEnBatalla.finalizarBatalla();
        paises.remove(paisEnBatalla);
    }
}
