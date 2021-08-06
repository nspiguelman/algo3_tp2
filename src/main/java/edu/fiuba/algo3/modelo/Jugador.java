package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.continente.Continente;
import edu.fiuba.algo3.estadoPaises.EstadoPaises;
import edu.fiuba.algo3.objetivos.Objetivo;
import edu.fiuba.algo3.paises.Pais;
import edu.fiuba.algo3.excepciones.*;
import edu.fiuba.algo3.tarjetas.TarjetaPais;
import edu.fiuba.algo3.tarjetas.Tarjetas;

import java.util.ArrayList;

public class Jugador {

    private final String color;
    private final EstadoPaises estadoPaises;
    private final Tarjetas tarjetas;
    public int ejercitosMaximosPorTurno;
    private Objetivo objetivo;
    private Tablero tablero;
    private ArrayList<Pais> paisesConquistados;
    private int extras;

    public Jugador(String color) {
        this.extras = 0;
        this.estadoPaises = new EstadoPaises();
        this.color = color;
        this.ejercitosMaximosPorTurno = 0;
        this.tarjetas = new Tarjetas();
        this.paisesConquistados = new ArrayList<>();
    }

    public String obtenerColor() {
        return color;
    }

    public void elegirPais(Pais unPais) throws Exception {
        this.estadoPaises.elegirPaisEnBatalla(unPais);
    }

    public void agregarPais(Pais unPais) {
        estadoPaises.agregarPais(unPais);
    }

    public ArrayList<Pais> obtenerPaises() {
        return estadoPaises.obtenerPaises();
    }

    public int obtenerCanjeActual() {
        return tarjetas.obtenerCanjeActual();
    }

    public void agregarTarjeta(TarjetaPais tarjeta){
        if (!this.validarTarjeta(tarjeta)){
            this.tarjetas.agregarTarjeta(tarjeta);
        }
    }

    private boolean validarTarjeta(TarjetaPais tarjeta) {
        for (Pais pais: paisesConquistados){
            if(tarjeta.esDelPais(pais)){
                this.extras += 3;
                return true;
            }
        }
        return false;
    }

    public int obtenerCantidadTotalDeEjercitos() {
        return estadoPaises.obtenerCantidadTotalDeEjercitos();
    }

    public int obtenerCantidadDeEjercitos(){
        return estadoPaises.obtenerCantidadDeEjercitosAgregados();
    }

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
    public boolean tieneElPais(String otroPais) { return estadoPaises.tieneElPais(otroPais); }
    public void eliminarPaisEnBatalla() {
        estadoPaises.eliminarPaisEnBatalla();
    }

    public void conquistar(Pais unPais) throws Exception{
        Pais paisEnBatalla = this.paisEnBatalla();
        this.estadoPaises.reducirEjercitos(paisEnBatalla, 1);
        unPais.agregarEjercitos(1);
        this.agregarPais(unPais);
        this.paisesConquistados.add(unPais);
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
        return this.tablero.obtenerExtrasDeJugador(this);
    }

    public void setearEjercitosMaximos() {
        this.ejercitosMaximosPorTurno = estadoPaises.obtenerCantidadTotalDeEjercitos();
    }

    public void reagrupar(Pais unPais, Pais otroPais, int cantidadEjercitos) throws TegException{
        this.estadoPaises.reagrupar(unPais, otroPais, cantidadEjercitos);
    }

    public boolean cumplioObjetivos() {
        return (this.estadoPaises.obtenerPaises().size() >= 30 || this.objetivo.validarObjetivo(this));
    }

    public boolean tieneNPaisesEnContinente(int cantidadDePaises, String continente) {
        return cantidadDePaises == (int)estadoPaises.obtenerPaises().stream().filter(pais -> pais.obtenerNombreContinente().equals(continente)).count();
    }

    public boolean sigueEnJuego() {
        return obtenerCantidadTotalDeEjercitos() > 0;
    }

    public boolean equals(Jugador unJugador) {
        return (this.obtenerColor().equals(unJugador.obtenerColor())); }

    public void asignarObjetivo(Objetivo objetivo) {
        this.objetivo = objetivo;
    }

    public void asignarTablero(Tablero tablero){
        this.tablero = tablero;
    }

    public String obtenerDescripcionObjetivo(){
        return this.objetivo.obtenerDescripcion();
    }

    public int obtenerEjercitosMaximos() {
        return this.ejercitosMaximosPorTurno;
    }

    public void reiniciarPaisesConquistados() {
        this.paisesConquistados = new ArrayList<>();
    }

    public int obtenerExtras() {
        return this.extras;
    }
}