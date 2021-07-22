package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.estadoPaises.EstadoPaises;
import edu.fiuba.algo3.paises.Pais;
import edu.fiuba.algo3.paises.PaisEnPaz;
import edu.fiuba.algo3.excepciones.PaisNoPerteneceAJugadorException;

import java.util.ArrayList;

public class Jugador {

    private Pais paisEnBatalla;
    private String color;
    private ArrayList<PaisEnPaz> paises;
    private final EstadoPaises estadoPaises;
    private ArrayList<TarjetaPais> tarjetas;

    public Jugador(String color) {
        this.estadoPaises = new EstadoPaises();
        this.paisEnBatalla = null;
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

    public void agregarPais(Pais unPais) { estadoPaises.agregarPais(unPais); }
    public ArrayList<Pais> obtenerPaises() {
        return estadoPaises.obtenerPaises();
    }
    private int obtenerCantidadTotalDeEjercitos() { return estadoPaises.obtenerCantidadTotalDeEjercitos();}
    public int obtenerCantidadDeEjercitos(){ return estadoPaises.obtenerCantidadDeEjercitosAgregados(); }
    public int obtenerEjercitosEnBatalla() {
        return paisEnBatalla.obtenerEjercitos();
    }

    public void agregarEjercitos(Pais unPais, int cantidadEjercitos) throws PaisNoPerteneceAJugadorException {
        try {
            estadoPaises.agregarEjercitos(unPais, cantidadEjercitos);
        } catch (PaisInvalidoException e) {
            throw new PaisNoPerteneceAJugadorException(unPais.obtenerNombrePais(), obtenerColor());
        }
    }

    public void validarCantidadEjercitos(int ejercitosASumar, int ejercitosPorFase) throws TegException { estadoPaises.validarCantidadEjercitos(ejercitosASumar, ejercitosPorFase); }
    public boolean tieneElPais(Pais paisDefensor) { return estadoPaises.tieneElPais(paisDefensor); }

    public void eliminarPaisEnBatalla() {
        estadoPaises.eliminarPaisEnBatalla();
    }

    public void conquistar(Pais unPais) {
        this.paisEnBatalla.reducirEjercitos(1);
        unPais.agregarEjercitos(1);
        this.agregarPais(unPais);
    }

    public void matarEjercito(int cantidadEjercitos) {
        this.paisEnBatalla.matarEjercitos(cantidadEjercitos);
    }

    public Pais paisEnBatalla() {
        return this.paisEnBatalla;
    }

    public boolean esElJugador(String colorJugador) {
        return this.color.equals(colorJugador);
    }
}
