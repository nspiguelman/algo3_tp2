package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.excepciones.*;
import edu.fiuba.algo3.fase.Fase;
import edu.fiuba.algo3.fase.FaseUnoColocacionEjercitos;

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
        fase.reiniciarMovimientos();
        turno.pasarTurno();
    }

    public ArrayList<Jugador> obtenerJugadores() {
        return jugadores;
    }

    public ArrayList<Pais> obtenerPaisesDeJugador(Jugador unJugador) {
        return unJugador.obtenerPaises();
    }

    public void agregarEjercitos(Jugador unJugador, Pais unPais, int cantidadEjercitos) throws TegException {
        this.verificarTurno(unJugador);
        this.verificarMovimiento(3); /* necesita tener 3 puntos para agregar ejercitos
                                            1 - atacar 2 - reagrupar 3 - colocar ejercitos */
        this.verificacionDeEjercitos(unJugador, cantidadEjercitos);
        unJugador.agregarEjercitos(unPais, cantidadEjercitos);
    }

    private void verificarMovimiento(int accion) throws TegException {
        if (fase.movimientoActual() != accion){
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

    private void verificacionDeEjercitos(Jugador unJugador, int cantidadEjercitos) throws TegException {
        int cantidadEjercitosPorFase = fase.ejercitosPorFase();
        unJugador.validarCantidadEjercitos(cantidadEjercitos, cantidadEjercitosPorFase);
    }

    public void ataqueDeA(Jugador atacante, Jugador defensor) throws Exception {
        batalla.batallar(atacante, defensor);
    }

    public void siguienteFase() throws TegException {
        fase = fase.siguienteFase(jugadores);
    }

    public void siguienteMovimiento(){
        fase.siguienteMovimiento();    // esto para verificar que se realice en el orden ataque-reagrupacion-agregarejercitos
    }

    public Jugador esElTurnoDe() {
        return turno.turnoActual();
    }
}