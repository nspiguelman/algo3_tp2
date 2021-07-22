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

    public void agregarEjercitos(Pais unPais, int cantidadDeEjercitos) throws Exception {
        this.tieneElPais(unPais);
        unPais.agregarEjercitos(cantidadDeEjercitos);
    }

    public void validarCantidadEjercitos(int ejercitosASumar, int ejercitosPorFase) throws ColocarEjercitosException {
        int ejercitosDeJugador = this.obtenerCantidadDeEjercitosAgregados() + ejercitosASumar;
        if (ejercitosDeJugador > ejercitosPorFase) {
            throw new ColocarEjercitosException(ejercitosPorFase);
        }
    }

    public void tieneElPais(Pais unPais) throws TegException{
        if (!paises.contains(unPais)){
            throw new PaisInvalidoException();
        }
    }

    public void eliminarPaisEnBatalla() {
        paisEnBatalla.cambiarEstadoDeBatalla();
        paises.remove(paisEnBatalla);
    }


    public void elegirPaisEnBatalla(Pais unPais) throws Exception{
        this.tieneElPais(unPais);
        this.paisEnBatalla = unPais;
        unPais.cambiarEstadoDeBatalla();
    }

    public int obtenerEjercitosEnBatalla() {
        return paisEnBatalla.obtenerEjercitos();
    }

    public void reducirEjercitos(Pais unPais, int ejercitos) throws Exception{
        this.tieneElPais(unPais);
        unPais.matarEjercitos(ejercitos);
    }

    public Pais obtenerPaisEnBatalla() {
        return this.paisEnBatalla;
    }

    public boolean tieneElPaisARREGLAR(Pais paisDefensor) {
        return paises.contains(paisDefensor);
    }
}
