package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.vistas.VistaAccion;
import edu.fiuba.algo3.vistas.VistaTurno;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.vistas.ContenedorMapa;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.util.ArrayList;
import java.util.Arrays;


public class PasarAccion implements EventHandler<ActionEvent> {

    private VistaTurno vista;
    private Button botonAccionar;
    private Juego juego;
    private ComboBox<String> paisesJugadorUno;
    private ComboBox<String> paisesJugadorDos;
    private ComboBox<String> ejercitosJugadorUno;
    private ArrayList<VistaAccion> vistasTablero;

    public PasarAccion(Juego juego, Button ejecutarAccion, ComboBox<String> paisesJugadorUno, ComboBox<String> paisesJugadorDos, ComboBox<String> ejercitosJugadorUno, VistaTurno vista, ArrayList<VistaAccion> vistasTablero){
        this.juego = juego;
        this.paisesJugadorUno = paisesJugadorUno;
        this.paisesJugadorDos = paisesJugadorDos;
        this.ejercitosJugadorUno = ejercitosJugadorUno;
        this.botonAccionar = ejecutarAccion;
        this.vista = vista;
        this.vistasTablero = vistasTablero;
    }

    @Override
    public void handle(ActionEvent event) {
        // Pasar a siguiente fase
        try {
            Jugador jugador = juego.turnoActual();
            ArrayList<Jugador> jugadores = juego.obtenerJugadores();

            if (jugadores.get(jugadores.size() - 1).obtenerColor().equals(jugador.obtenerColor()) && !juego.obtenerFase().equals("Juego")) {
                ColocarEjercitos handlerColocar = new ColocarEjercitos(this.juego, this.paisesJugadorUno, this.ejercitosJugadorUno, this.vista);
                AtacarAccion handlerAtaque = new AtacarAccion(this.juego, this.paisesJugadorUno, this.paisesJugadorDos, this.vista);
                vistasTablero.get(2).activar();
                botonAccionar.setOnAction(handlerColocar);
                juego.siguienteTurno();
                juego.siguienteFase();
                this.juego.turnoActual().setearEjercitosMaximos();
                if (juego.obtenerFase().equals("Juego")) {
                    vistasTablero.get(0).activar();
                    botonAccionar.setOnAction(handlerAtaque);
                }
                ContenedorMapa.actualizarVista();
            } else if (!juego.alguienGano())
            {
                this.juego.siguienteAccion();
                if (juego.obtenerAccion() == 1) {
                    vistasTablero.get(0).activar();
                    AtacarAccion handlerAtaque = new AtacarAccion(this.juego, this.paisesJugadorUno, this.paisesJugadorDos, this.vista);
                    botonAccionar.setOnAction(handlerAtaque);
                } else if (juego.obtenerAccion() == 2) {
                    vistasTablero.get(1).activar();
                    Reagrupar handlerReagrupar = new Reagrupar(this.juego, this.paisesJugadorUno, this.paisesJugadorDos, this.ejercitosJugadorUno, this.vista);
                    botonAccionar.setOnAction(handlerReagrupar);
                } else {
                    ColocarEjercitos handlerColocar = new ColocarEjercitos(this.juego, this.paisesJugadorUno, this.ejercitosJugadorUno, this.vista);
                    if (juego.obtenerFase().equals("Juego")) {
                        this.vista.fijarEjercitosPorFase();
                    }
                    vistasTablero.get(2).activar();
                    botonAccionar.setOnAction(handlerColocar);
                }
                ContenedorMapa.actualizarVista();
            }
            else{
                this.vista.finalizarJuego();
            }
            this.vista.limpiarJugadorUno();
            this.vista.limpiarJugadorDos();
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }
}