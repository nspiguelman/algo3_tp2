package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.estadoPaises.EstadoPaises;
import edu.fiuba.algo3.paises.Pais;
import edu.fiuba.algo3.paises.PaisEnPaz;
import edu.fiuba.algo3.excepciones.*;

import java.util.ArrayList;

public class Jugador {

    private String color;
    private final EstadoPaises estadoPaises;
    private ArrayList<TarjetaPais> tarjetas;

    public Jugador(String color) {
        this.estadoPaises = new EstadoPaises();
        this.color = color;
    }

    public String obtenerColor() {
        return color;
    }

    public void elegirPais(Pais unPais) throws Exception {
        this.estadoPaises.elegirPaisEnBatalla(unPais);
    }

    public void agregarPais(Pais unPais) { estadoPaises.agregarPais(unPais); }
    public ArrayList<Pais> obtenerPaises() {
        return estadoPaises.obtenerPaises();
    }
    private int obtenerCantidadTotalDeEjercitos() { return estadoPaises.obtenerCantidadTotalDeEjercitos();}
    public int obtenerCantidadDeEjercitos(){ return estadoPaises.obtenerCantidadDeEjercitosAgregados(); }
    public int obtenerEjercitosEnBatalla() {
        return estadoPaises.obtenerEjercitosEnBatalla();
    }

    public void agregarEjercitos(Pais unPais, int cantidadEjercitos) throws Exception {
        try {
            estadoPaises.agregarEjercitos(unPais, cantidadEjercitos);
        } catch (PaisInvalidoException e) {
            throw new PaisNoPerteneceAJugadorException(unPais.obtenerNombrePais(), obtenerColor());
        }
    }

    public void validarCantidadEjercitos(int ejercitosASumar, int ejercitosPorFase) throws TegException { estadoPaises.validarCantidadEjercitos(ejercitosASumar, ejercitosPorFase); }
    public boolean tieneElPais(Pais paisDefensor) throws TegException{ return estadoPaises.tieneElPaisARREGLAR(paisDefensor); }

    public void eliminarPaisEnBatalla() {
        estadoPaises.eliminarPaisEnBatalla();
    }

    public void conquistar(Pais unPais) throws Exception{
        Pais paisEnBatalla = this.paisEnBatalla();
        this.estadoPaises.reducirEjercitos(paisEnBatalla, 1);
        unPais.agregarEjercitos(1);
        this.agregarPais(unPais);

    }

    public void matarEjercito(Pais unPais, int cantidadEjercitos) throws Exception {
        this.estadoPaises.reducirEjercitos(unPais, cantidadEjercitos);
    }

    public Pais paisEnBatalla() {
        return this.estadoPaises.obtenerPaisEnBatalla();
    }

    public boolean esElJugador(String colorJugador) {
        return this.color.equals(colorJugador);
    }
}
