package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.excepciones.*;
import edu.fiuba.algo3.fase.*;
import edu.fiuba.algo3.paises.Pais;
import edu.fiuba.algo3.tarjetas.TarjetaPais;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

public class Juego {
    private Tablero tablero;
    private ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
    private Fase fase;
    private Turno turno;
    private Batalla batalla = new Batalla();

    public Juego(ArrayList<String> colorJugadores) throws FileNotFoundException, TegException {
        Random random = new Random();
        for (int i = colorJugadores.size(); i > 0; i--) {
            int value = random.nextInt(i);
            String colorActual = colorJugadores.get(value);
            this.agregarJugador(colorActual);
            colorJugadores.remove(value);
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

    public void siguienteTurno() throws TegException{
        if (!this.hayGanador()) {
            this.validarEjercitosAgregados();
            fase.reiniciarAcciones();
            turno.pasarTurno();
        }
    }

    public void siguienteAccion() throws TegException{
        Jugador jugadorActual = this.turnoActual();
        if (this.obtenerAccion() == 3) {
            this.siguienteTurno();
        }
        else if(this.obtenerAccion() == 2){
            TarjetaPais tarjetaJugador = this.tablero.obtenerTarjeta();
            this.turnoActual().agregarTarjeta(tarjetaJugador);
            fase.siguienteAccion(jugadorActual);
        }
        else {
            fase.siguienteAccion(jugadorActual);
        }
    }

    private void validarEjercitosAgregados() throws TegException {
        Jugador jugadorActual = this.turnoActual();
        this.fase.validarCantidadEjercitos(jugadorActual);
    }

    private boolean hayGanador() {
        return turno.turnoActual().cumplioObjetivos();
    }

    public ArrayList<Jugador> obtenerJugadores() {
        return jugadores;
    }

    public ArrayList<Pais> obtenerPaisesDeJugador(Jugador unJugador) {
        return unJugador.obtenerPaises();
    }

    public void agregarEjercitos(Jugador unJugador, Pais unPais, int cantidadEjercitos) throws Exception {
        this.verificarTurno(unJugador);
        this.fase.estaEnColocar();
        this.verificacionDeEjercitos(unJugador, cantidadEjercitos);
        unJugador.agregarEjercitos(unPais, cantidadEjercitos);
    }

    public void reagrupar(Jugador unJugador, Pais unPais, Pais otroPais, int cantidadEjercitos) throws TegException {
        this.verificarTurno(unJugador);
        this.fase.estaEnReagrupar();
        unJugador.reagrupar(unPais, otroPais, cantidadEjercitos);
    }

    private void verificacionDeEjercitos(Jugador unJugador, int cantidadEjercitos) throws TegException {
        int cantidadEjercitosPorFase = fase.ejercitosPorFase(unJugador);
        unJugador.validarCantidadEjercitos(cantidadEjercitos, cantidadEjercitosPorFase);
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
        this.fase.estaEnAtacar();
        batalla.batallar(atacante, defensor);
    }

    public void siguienteFase() throws TegException {
        fase = fase.siguienteFase(jugadores);
    }

    public Jugador turnoActual() {
        return turno.turnoActual();
    }

    public int obtenerAccion(){
        return this.fase.accionActual();
    }

    public int obtenerEjercitosPorFase(){
        Jugador jugador = this.turnoActual();
        return this.fase.ejercitosPorFase(jugador);
    }

    public String obtenerFase() {
        return this.fase.obtenerFase();
    }

    public ArrayList<Pais> obtenerPaises() {
        return this.tablero.obtenerPaises();
    }

    public boolean alguienGano() {
        return this.hayGanador();
    }
}