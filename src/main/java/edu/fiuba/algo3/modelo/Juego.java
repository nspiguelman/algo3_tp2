package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.excepciones.*;
import edu.fiuba.algo3.fase.*;
import edu.fiuba.algo3.paises.Pais;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Juego {
    private Tablero tablero;
    private ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
    private Fase fase;
    private Turno turno;
    private Batalla batalla = new Batalla();

    public Juego(ArrayList<String> colorJugadores) throws FileNotFoundException, TegException {
        for (String unColor : colorJugadores) {
            this.agregarJugador(unColor);
        }
        this.tablero = new Tablero(jugadores);
        this.fase = new FaseUnoColocacionEjercitos();
        this.turno = new Turno(jugadores);
    }

    public void agregarJugador(String unColor) throws TegException {
        boolean jugadorRepetido = jugadores.stream().anyMatch(jugador -> jugador.obtenerColor().equals(unColor));
        if (jugadorRepetido) {
            throw new JugadorExistenteException(unColor);
        }
        this.jugadores.add(new Jugador(unColor));
    }

    public void siguienteTurno(){
        if (!this.verificarObjetivos())
        {
            fase.reiniciarAcciones();
            turno.pasarTurno();
        }
    }

    private boolean verificarObjetivos() {
        Jugador jugadorActual = turno.turnoActual();
        if (!jugadorActual.cumplioObjetivos())
        {
            return false;
        }
        return true;
    }

    public ArrayList<Jugador> obtenerJugadores() {
        return jugadores;
    }

    public ArrayList<Pais> obtenerPaisesDeJugador(Jugador unJugador) {
        return unJugador.obtenerPaises();
    }

    public void agregarEjercitos(Jugador unJugador, Pais unPais, int cantidadEjercitos) throws Exception {
        this.verificarTurno(unJugador);
        this.verificarMovimiento(3);
        this.verificacionDeEjercitos(unJugador, cantidadEjercitos);
        unJugador.agregarEjercitos(unPais, cantidadEjercitos);
    }

    public void reagrupar(Jugador unJugador, Pais unPais, Pais otroPais, int cantidadEjercitos) throws TegException{
        this.verificarTurno(unJugador);
        this.verificarMovimiento(2);
        unJugador.reagrupar(unPais, otroPais, cantidadEjercitos);
    }

    private void verificacionDeEjercitos(Jugador unJugador, int cantidadEjercitos) throws TegException {
        int cantidadEjercitosPorFase = fase.ejercitosPorFase(unJugador);
        unJugador.validarCantidadEjercitos(cantidadEjercitos, cantidadEjercitosPorFase);
    }
    private void verificarMovimiento(int accion) throws TegException {
        if (fase.accionActual() != accion){
            throw new AccionesException(); // seria Numero de movimiento 1 - atacar 2 - reagrupar 3 - colocar ejercitos
        }
    }

    private void verificarTurno(Jugador unJugador) throws TegException {
        String colorJugador = unJugador.obtenerColor();
        if (!turno.esElTurnoDe(colorJugador))
        {
            throw new TurnoException(colorJugador);
        }
    }


    public void ataqueDeA(Jugador atacante, Jugador defensor) throws Exception {
        this.verificarTurno(atacante);
        this.verificarMovimiento(1);
        batalla.batallar(atacante, defensor);
    }

    public void siguienteFase() throws TegException {
        fase = fase.siguienteFase(jugadores);
    }

    public void siguienteAccion(){

        Jugador jugadorActual = this.esElTurnoDe();
        if (this.obtenerAccion() == 3){
            this.siguienteTurno();
        }
        else{
            fase.siguienteAccion(jugadorActual);    // esto para verificar que se realice en el orden ataque-reagrupacion-agregarEjercitos
        }
    }

    public Jugador esElTurnoDe() {
        return turno.turnoActual();
    }

    public int obtenerAccion(){
        return this.fase.accionActual();
    }

    public int obtenerEjercitosPorFase(){
        Jugador jugador = this.esElTurnoDe();
        return this.fase.ejercitosPorFase(jugador);
    }

    public String obtenerFase() {
        return this.fase.obtenerFase();
    }
}