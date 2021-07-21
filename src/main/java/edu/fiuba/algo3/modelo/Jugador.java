package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.excepciones.CantidadDeEjercitosInvalida;
import edu.fiuba.algo3.excepciones.ColocarEjercitosException;
import edu.fiuba.algo3.excepciones.PaisNoPerteneceAJugadorException;
import edu.fiuba.algo3.excepciones.TegException;
import edu.fiuba.algo3.fase.Fase;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Jugador {

    private Pais paisEnBatalla;
    private String color;
    private ArrayList<Pais> paises;
    private ArrayList<TarjetaPais> tarjetas;

    public Jugador(String color) {
        this.paises = new ArrayList<Pais>();
        this.color = color;
    }

    public String obtenerColor() {
        return color;
    }

    public void elegirPais(Pais unPais) throws Exception {
        if (!paises.contains(unPais)) {
            throw new Exception("El atacante no contiene el pais");
        }
        this.paisEnBatalla = unPais;
    }

    public void agregarPais(Pais unPais) {
        this.paises.add(unPais);
    }

    public ArrayList<Pais> obtenerPaises() {
        return paises;
    }

    private int obtenerCantidadTotalDeEjercitos() {
        return paises.stream()
                .map(pais -> pais.obtenerEjercitos())
                .reduce(0, (subtotal, element) -> subtotal + element);
    }

    public int obtenerCantidadDeEjercitos(){
        return paises.stream()
                .map(pais -> pais.obtenerEjercitos())
                .reduce(0, (subtotal, element) -> subtotal + element) - paises.size();
    }
    public int obtenerEjercitosEnBatalla() {
        return paisEnBatalla.obtenerEjercitos();
    }

    public void agregarEjercitos(Pais unPais, int cantidadEjercitos) throws PaisNoPerteneceAJugadorException, ColocarEjercitosException {
        String nombrePais = unPais.obtenerNombrePais();
        if (paises.contains(unPais)){
            unPais.agregarEjercitos(cantidadEjercitos);
        }
        else{
            throw new PaisNoPerteneceAJugadorException(nombrePais, this.obtenerColor());
        }
    }

    public void validarCantidadEjercitos(int ejercitosASumar, int ejercitosPorFase) throws TegException {
        int ejercitosDeJugador = this.obtenerCantidadTotalDeEjercitos() + ejercitosASumar - paises.size();
        if (ejercitosDeJugador > ejercitosPorFase) {
            throw new ColocarEjercitosException(ejercitosPorFase);
        }
    }

    public void eliminarPais() {
        paises.remove(paisEnBatalla);
        paisEnBatalla = null;
    }

    public boolean tieneElPais(Pais paisDefensor) {
        return paises.contains(paisDefensor);
    }

    public void conquistar(Pais unPais){
        this.paisEnBatalla.reducirEjercitos(1);
        unPais.agregarEjercitos(1);
        this.agregarPais(unPais);
    }

    public void matarEjercito(int cantidadEjercitos) {

        this.paisEnBatalla.reducirEjercitos(cantidadEjercitos);
    }

    public Pais paisEnBatalla() {
        return this.paisEnBatalla;
    }

    public boolean esElJugador(String colorJugador) {
        return this.color.equals(colorJugador);
    }
}
