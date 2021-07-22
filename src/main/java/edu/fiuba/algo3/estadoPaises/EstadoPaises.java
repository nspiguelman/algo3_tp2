package edu.fiuba.algo3.estadoPaises;

import edu.fiuba.algo3.excepciones.*;
import edu.fiuba.algo3.paises.*;


import java.util.ArrayList;

public class EstadoPaises {

    private final ArrayList<Pais> paises;
    private Pais paisEnBatalla;

    public EstadoPaises() {
        this.paisEnBatalla = null;
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

    public boolean tieneElPais(Pais unPais) throws TegException{
        if (!paises.contains(unPais)){
            throw new PaisInvalidoException();
        }
        return true; //refactorizar para eliminar if's
    }

    public void eliminarPaisEnBatalla() {
        paisEnBatalla.cambiarEstadoDeBatalla();
        paises.remove(paisEnBatalla);
    }


    public void elegirPaisEnBatalla(Pais unPais) throws Exception{
        boolean tieneElPais = this.tieneElPais(unPais);
        if (!tieneElPais){
            throw new Exception("El jugador no tiene el pais");
        }
        this.paisEnBatalla = unPais;
        unPais.cambiarEstadoDeBatalla();
    }

    public int obtenerEjercitosEnBatalla() {
        return paisEnBatalla.obtenerEjercitos();
    }

    public void reducirEjercitos(Pais unPais,int ejercitos) {
        this
    }
}
