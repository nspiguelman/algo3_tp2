package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.continente.Continente;
import edu.fiuba.algo3.estadoPaises.EstadoPaises;
import edu.fiuba.algo3.paises.Pais;
import edu.fiuba.algo3.excepciones.*;
import edu.fiuba.algo3.tarjetas.Tarjetas;

import java.util.ArrayList;

public class Jugador {

    private String color;
    private final EstadoPaises estadoPaises;
    private Tarjetas tarjetas;
    int ejercitosMaximosPorTurno;

    public Jugador(String color) {
        this.estadoPaises = new EstadoPaises();
        this.color = color;
        this.ejercitosMaximosPorTurno = 0;
        this.tarjetas = new Tarjetas();
    }

    public String obtenerColor() {
        return color;
    }

    public void elegirPais(Pais unPais) throws Exception {
        this.estadoPaises.elegirPaisEnBatalla(unPais);

    }

    public Tarjetas obtenerTarjetas() {return tarjetas;}
    public void agregarPais(Pais unPais) { estadoPaises.agregarPais(unPais); }
    public ArrayList<Pais> obtenerPaises() {
        return estadoPaises.obtenerPaises();
    }
    public int obtenerCanjeActual() {
        return tarjetas.obtenerCanjeActual();
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

    public void validarCantidadEjercitos(int cantidadASumar, int ejercitosPorFase) throws TegException {
        this.estadoPaises.validarCantidadEjercitos(ejercitosMaximosPorTurno, cantidadASumar, ejercitosPorFase);
    }

    public boolean tieneElPais(Pais paisDefensor) { return estadoPaises.tieneElPaisARREGLAR(paisDefensor); }
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

    public int obtenerEjercitosExtraAColocar(){
        return this.estadoPaises.obtenerEjercitosExtraAColocar();
    }

    public void setearEjercitosMaximos() {
        this.ejercitosMaximosPorTurno = estadoPaises.obtenerCantidadTotalDeEjercitos();
    }

    public void reagrupar(Pais unPais, Pais otroPais, int cantidadEjercitos) throws TegException{
        this.estadoPaises.reagrupar(unPais, otroPais, cantidadEjercitos);
    }

    public boolean cumplioObjetivos() {
        return (estadoPaises.obtenerPaises().size() == 30);
    }

    public boolean domina(String continente, int paisesParaDominar){
        return this.estadoPaises.domina(continente, paisesParaDominar);
    }

    public boolean tieneNPaisesLimitrofesEntreSi(int obtenerCantidadDePaises) {
        return true;
    }

    public boolean tieneNPaisesEnContinente(int cantidadDePaises, String continente) {
        return cantidadDePaises == (int)estadoPaises.obtenerPaises().stream().filter(pais -> pais.obtenerNombreContinente().equals(continente)).count();
    }

    public boolean sigueEnJuego() {
        return obtenerCantidadTotalDeEjercitos() > 0;
    }
}